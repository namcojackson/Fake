<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161129161239 --%>
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
<fmt:setBundle basename="I18N_NSAL0340Scrn00" var="I18N_SCREEN_ID" scope="request" />
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

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0340Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0340Scrn00.title" bundle="${I18N_SCREEN_ID}">Skip Month</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Skip Month" class="pTab_ON" ><a href="#"><fmt:message key="i18n.NSAL0340Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Skip Month</fmt:message></a></li>
								</td>

								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>

							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">

<%-- ######################################## HEADER ######################################## --%>
					<br>
					<table border="0" align="center" cellspacing="0" cellpadding="0" width="940">
						<tr>
							<td>
			 					<table border="1" cellspacing="0" cellpadding="2" height="30" style="word-break: break-all;">
									<col align="left" width="130">
									<col align="left" width="220">
									<tr>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td><ezf:label name="dsContrNum" ezfName="dsContrNum" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<table border="0" align="center" cellspacing="0" cellpadding="0" width="940">
						<tr>
							<td>
			 					<table border="1" cellspacing="0" cellpadding="2" height="30" style="word-break: break-all;">
									<col align="left" width="130">
									<col align="left" width="220">
									<col align="left" width="130">
									<col align="left" width="170">
									<col align="left" width="240">
									<tr>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td><ezf:label name="serNum" ezfName="serNum" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Item</fmt:message></td>
										<td><ezf:label name="mdseCd" ezfName="mdseCd" /></td>
										<td><ezf:label name="mdseDescShortTxt" ezfName="mdseDescShortTxt" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" align="center" cellspacing="0" cellpadding="0" width="940">
						<tr>
							<td>
			 					<table border="1" cellspacing="0" cellpadding="2" height="30" style="word-break: break-all;">
									<col align="left" width="130">
									<col align="left" width="220">
									<col align="left" width="130">
									<col align="left" width="170">
									<tr>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
										<td><ezf:label name="t_MdlNm" ezfName="t_MdlNm" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Charge Type</fmt:message></td>
										<td><ezf:label name="svcInvChrgTpNm" ezfName="svcInvChrgTpNm" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>

					<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>

<%-- ######################################## DETAIL ######################################## --%>

					<br>
					<table border="0" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
						 					<div id="TopTBL" style="overflow-y:hidden; overflow-x:hidden;">
												<table border="1" cellpadding="2" cellspacing="0" align="left">
													<col width="140"></col>
													<col width="140"></col>
													<tr height="35">
														<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Month</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NSAL0340Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Skip Type</fmt:message></td>
													</tr>
											    </table>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
						 					<div id="TBL" style="overflow-y:hidden; overflow-x:hidden;">
												<table id="A" border="1" cellpadding="2" cellspacing="0" align="left">
													<col width="140"></col>
													<col width="140"></col>
													<ezf:row ezfHyo="A">
													<tr height="30">
														<td><ezf:label name="xxDtlNm_A0" ezfName="xxDtlNm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
														<td>
															<ezf:select name="skipRecovTpCd_A0" ezfName="skipRecovTpCd_A0" ezfHyo="A" ezfArrayNo="0" ezfCodeName="skipRecovTpCd_L0" ezfDispName="skipRecovTpDescTxt_L0" otherAttr=" style=\"width:140;\""/>
														</td>
														<td class="pAuditInfo">
															<ezf:inputHidden name="xxRecHistCratTs_A0" ezfName="xxRecHistCratTs_A0" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistCratByNm_A0" ezfName="xxRecHistCratByNm_A0" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistUpdTs_A0" ezfName="xxRecHistUpdTs_A0" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistUpdByNm_A0" ezfName="xxRecHistUpdByNm_A0" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistTblNm_A0" ezfName="xxRecHistTblNm_A0" ezfHyo="A" ezfArrayNo="0" />
														</td>
													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="30"><td>February</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>March</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>April</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>May</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>June</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>July</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>August</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>September</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>October</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>November</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													<tr height="30"><td>December</td><td><select name="skipRecovTpCd_A0" style="width:140;"><option>None</option><option>Skip</option><option>Recover</option></select></td></tr>
													</ezf:skip>
											    </table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
