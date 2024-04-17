<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180510121816 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_ZYPL0210Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

<%@ page import="business.servlet.ZYPL0210.ZYPL0210BMsg" %>
<%@ page import="business.servlet.ZYPL0210.constant.ZYPL0210Constant" %>
<%
 boolean viewDownloadErr = false;
 ZYPL0210BMsg bmsg = (ZYPL0210BMsg)databean.getEZDBMsg();
 String upldCsvId = "";
 if (bmsg.upldCsvId_CT.isClear()) {
     upldCsvId = bmsg.upldCsvId_0H.getValue();
 } else {
     upldCsvId = bmsg.upldCsvId_CT.getValue();
 }

 if (ZYPL0210Constant.CSV_ID_LIST_DOWNLOAD_NUMBER_FORMAT.contains(upldCsvId)){
     viewDownloadErr = true;
 }
%>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZYPL0210Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.ZYPL0210Scrn00.title" bundle="${I18N_SCREEN_ID}">CSV Upload</fmt:message>">

				<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

							<div class="pTab_BODY">

							<table width="1050" align="center" border="0">
								<col width="140">
								<col width="530">
								<col width="140">
								<col width="140">
								<col width="100">
								<!-- Target Table Name -->
								<tr>
									<td><fmt:message key="i18n.ZYPL0210Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Upload Name:</fmt:message></td>
									<td>
										<ezf:select name="upldCsvId_0H" ezfName="upldCsvId_0H" ezfCodeName="upldCsvId_0S" ezfDispName="upldCsvNm" otherAttr=" style=\"width:470\""/>
									</td>
									<td align="right"><fmt:message key="i18n.ZYPL0210Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Uploaded UserID:</fmt:message></td>
									<td align="right"><ezf:inputText name="xxUpldCsvUsrTxt_0H" ezfName="xxUpldCsvUsrTxt_0H" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
									<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
								</tr>

								<tr>
									<td><fmt:message key="i18n.ZYPL0210Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Upload Request:</fmt:message></td>
									<td colspan="4"><ezf:inputFile name="xxFileData_CU" ezfName="xxFileData_CU" otherAttr=" size=\"67\" maxlength=\"9999\""/></td>
								</tr>

								<tr>
									<td><fmt:message key="i18n.ZYPL0210Scrn00.label.4" bundle="${I18N_SCREEN_ID}">File Description:</fmt:message></td>
									<td colspan="2"><ezf:inputText name="upldCsvFileDescTxt_0H" ezfName="upldCsvFileDescTxt_0H" otherAttr=" size=\"67\" maxlength=\"128\""/></td>
									<td align="right"><ezf:anchor name="" ezfName="xxUpldCsvPro	tFlg" ezfEmulateBtn="1" ezfGuard="TemplateDownload" ><fmt:message key="i18n.ZYPL0210Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Template Download</fmt:message></ezf:anchor></td>
									<td align="right"><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn7" /></td>
								</tr>

							</table>

							<hr size="1" width="98%" noshade>

							<%-- Pagenation --%>
							<table width="1103" border="0" cellpadding="1" cellspacing="0">
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

							<table height="380" border="0">
								<col valign="top">
								<tr>
									<td>
										<table width="1120">
											<col valign="top">
											<tr>
												<td>
													<table height="380" width="100%" border="0">
														<col valign="top">
														<tr>
															<td>

																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>

																		<td valign="top">
																			<table border="1" cellpadding="1" cellspacing="0">
																				<col width="230" align="center">

																				<tr>
																					<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Upload CSV Request PK</fmt:message></td>
																				</tr>
																			</table>

																			<div id="leftTBL" style="overflow-y:hidden; height:340; overflow-x:hidden; width:;">
																				<table border="1" cellpadding="1" cellspacing="0" id="L">
																					<col width="230" align="">

																					<tbody>
																						<ezf:row ezfHyo="A">
																							<tr height="28" id="leftTBL_A_tr_{EZF_ROW_NUMBER}">
																								<td align="right"><ezf:label name="upldCsvRqstPk" ezfName="upldCsvRqstPk" ezfHyo="A" ezfArrayNo="0" /></td>
																							</tr>
																						</ezf:row>
																						<ezf:skip>
																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>


																							<tr height="28">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td align="right"><label ezfout>1234567890123456789012345678</label></td>
																							</tr>


																						</ezf:skip>
																					</tbody>
																				</table>
																			</div>
																		</td>

																		<td valign="top">
																			<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:888;">
																			<% if(viewDownloadErr){ %>
																				<table border="1" cellpadding="1" cellspacing="0" width="1280">
																					<col width="90" align="center">
																					<col width="70" align="center">
																					<col width="128"  align="center">
																					<col width="210"  align="center">
																					<col width="90" align="center">
																					<col width="132" align="center">
																					<col width="379" align="center">
																					<col width="63" align="center">
																					<tr>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Time</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.9" bundle="${I18N_SCREEN_ID}">User</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.10" bundle="${I18N_SCREEN_ID}">File</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Result</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Message</fmt:message></td>
																						<td class="pClothBs"><br></td>
																					</tr>
																				</table>
																			</div>

																			<div id="rightTBL" style="height:357; overflow-y:scroll;overflow-x:scroll; width:905;" onscroll="synchroRightScroll();">
																				<table border="1" cellpadding="1" cellspacing="0" width="1280" id="R">
																					<col width="90" align="center">
																					<col width="70" align="center">
																					<col width="128"  align="center">
																					<col width="210"  align="center">
																					<col width="90" align="center">
																					<col width="132" align="center">
																					<col width="379" align="left">
																					<col width="63" align="center">

																					<tbody>
																						<ezf:row ezfHyo="A">
																							<tr  height="28" id="rightTBL_A_tr_{EZF_ROW_NUMBER}">
																								<td><ezf:label name="xxUpldCsvDt" ezfName="xxUpldCsvDt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvTm" ezfName="xxUpldCsvTm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvUsrTxt" ezfName="xxUpldCsvUsrTxt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:anchor name="" ezfName="xxUpldCsvDnldFlg" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download_Row" ><fmt:message key="i18n.ZYPL0210Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Download All</fmt:message></ezf:anchor>
																								&nbsp;&nbsp;&nbsp;<ezf:anchor name="" ezfName="xxUpldCsvErrDnldFlg" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download_Error_Row" ><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></ezf:anchor></td>
																								<td><ezf:label name="upldCsvStsNm" ezfName="upldCsvStsNm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="upldCsvRsltNm" ezfName="upldCsvRsltNm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvMsgTxt" ezfName="xxUpldCsvMsgTxt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="More_Row" ><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></ezf:anchor></td>
																							</tr>
																						</ezf:row>
																			<%} else { %>
																				<table border="1" cellpadding="1" cellspacing="0" width="1150">
																					<col width="90" align="center">
																					<col width="70" align="center">
																					<col width="128"  align="center">
																					<col width="80"  align="center">
																					<col width="90" align="center">
																					<col width="132" align="center">
																					<col width="379" align="center">
																					<col width="63" align="center">
																					<tr>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Time</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.9" bundle="${I18N_SCREEN_ID}">User</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.10" bundle="${I18N_SCREEN_ID}">File</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Result</fmt:message></td>
																						<td class="pClothBs"><fmt:message key="i18n.ZYPL0210Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Message</fmt:message></td>
																						<td class="pClothBs"><br></td>
																					</tr>
																				</table>
																			</div>

																			<div id="rightTBL" style="height:357; overflow-y:scroll;overflow-x:scroll; width:905;" onscroll="synchroRightScroll();">
																				<table border="1" cellpadding="1" cellspacing="0" width="1150" id="R">
																					<col width="90" align="center">
																					<col width="70" align="center">
																					<col width="128"  align="center">
																					<col width="80"  align="center">
																					<col width="90" align="center">
																					<col width="132" align="center">
																					<col width="379" align="left">
																					<col width="63" align="center">

																					<tbody>
																						<ezf:row ezfHyo="A">
																							<tr  height="28" id="rightTBL_A_tr_{EZF_ROW_NUMBER}">
																								<td><ezf:label name="xxUpldCsvDt" ezfName="xxUpldCsvDt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvTm" ezfName="xxUpldCsvTm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvUsrTxt" ezfName="xxUpldCsvUsrTxt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:anchor name="" ezfName="xxUpldCsvDnldFlg" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download_Row" ><fmt:message key="i18n.ZYPL0210Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Download</fmt:message></ezf:anchor></td>
																								<td><ezf:label name="upldCsvStsNm" ezfName="upldCsvStsNm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="upldCsvRsltNm" ezfName="upldCsvRsltNm" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:label name="xxUpldCsvMsgTxt" ezfName="xxUpldCsvMsgTxt" ezfHyo="A" ezfArrayNo="0" /></td>
																								<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="More_Row" ><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></ezf:anchor></td>
																							</tr>
																						</ezf:row>
																			<% } %>
																						<ezf:skip>
																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>
																							</tr>

																							<tr height="28">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																							<tr height="28" class="pEvenNumberBGcolor">
																								<td><label ezfout>04/06/2009</label></td>
																								<td><label ezfout>12:34:56</label></td>
																								<td><label ezfout>Q1234Q1234Q12349</label></td>
																								<td><A href="#" onclick="sendServer('Download_Row')" ezfname="xxUpldCsvDnldFlg" ezfhyo="A">Download All</A>&nbsp;&nbsp;&nbsp;<A href="#" onclick="sendServer('Download_Error_Row')" ezfname="xxUpldCsvErrDnldFlg" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Download Errors</fmt:message></A></td>
																								<td><label ezfout name="upldCsvSts" ezfname="upldCsvSts" ezfhyo="A">Completed</label></td>
																								<td><label ezfout name="upldCsvRslt" ezfname="upldCsvRslt" ezfhyo="A">Loading Warning</label></td>
																								<td><label ezfout name="upldCsvErrId" ezfname="upldCsvErrId" ezfhyo="A">ZZZM9999E xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxx6</label></td>
																								<td><a href="#" onclick="sendServer('More_Row')" ezfhyo="A"><fmt:message key="i18n.ZYPL0210Scrn00.label.16" bundle="${I18N_SCREEN_ID}">More..</fmt:message></a></td>

																							</tr>

																						</ezf:skip>
																					</tbody>
																				</table>
																			</div>

																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>

									</td>
								</tr>
							</table>

							<table width="1130" align="center" border="0">
								<tr>
									<td width="147"><br></td>
									<td width="676"><br></td>
									<td width="129" align="center"><ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn11" /></td>
								</tr>
							</table>
						</div>


						</td>
					</tr>
				</table>

			<script type="text/javascript">
				function synchroRightScroll() {
					var rightTBL = document.getElementById( 'rightTBL' );
					var leftTBL  = document.getElementById( 'leftTBL'  );
					leftTBL.scrollTop = rightTBL.scrollTop;
					topTBL.scrollLeft = rightTBL.scrollLeft;
				}
			</script>


<%-- **** Task specific area ends here **** --%>
