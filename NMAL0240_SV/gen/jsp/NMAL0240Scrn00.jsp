<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181207143300 --%>
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
<fmt:setBundle basename="I18N_NMAL0240Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL0240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL0240Scrn00.title" bundle="${I18N_SCREEN_ID}">Bill of Material Main</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				</div>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY" style="padding-top:5px;" align="center">
					<fieldset style="width:1100;">
						<table width="95%" border="0" cellpadding="0" cellspacing="0" align="left">
							<col width="150px">
							<col width="200px">
							<col width="120px">
							<col width="300px">
							<col width="80px">
							<col width="30px">
							<tr>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.1" bundle="${I18N_SCREEN_ID}">BOM Type</fmt:message></td>
								<td>
									<ezf:select name="mdseItemTpCd" ezfName="mdseItemTpCd" ezfBlank="1" ezfCodeName="mdseItemTpCd_L" ezfDispName="mdseItemTpDescTxt_L" otherAttr=" style=\"width:200px;\""/>
								</td>
							</tr>
							<tr>
								<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BomItem" ><fmt:message key="i18n.NMAL0240Scrn00.label.2" bundle="${I18N_SCREEN_ID}">BOM Item#</fmt:message></ezf:anchor></td>
								<td>
									<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
									<ezf:inputButton name="GetBomInfo" value=">>" htmlClass="pBtn0" />
								</td>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.3" bundle="${I18N_SCREEN_ID}">BOM Merch Type</fmt:message></td>
								<td>
									<ezf:select name="coaMdseTpCd" ezfName="coaMdseTpCd" ezfBlank="1" ezfCodeName="coaMdseTpCd_L" ezfDispName="coaProjDescTxt_L" otherAttr=" style=\"width:200px;\""/>
								</td>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.4" bundle="${I18N_SCREEN_ID}">BOM Active</fmt:message></td>
								<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
							</tr>
							<tr>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.5" bundle="${I18N_SCREEN_ID}">BOM Item Description</fmt:message></td>
								<td>
									<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456789012345XXXXXXXXXX12345" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/>
								</td>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.6" bundle="${I18N_SCREEN_ID}">BOM Product Code</fmt:message></td>
								<td>
									<ezf:inputText name="xxScrItem61Txt" ezfName="xxScrItem61Txt" value="AAA:123456789012345XXXXXXXXXX12345123456789012345XXXXXXXXXX12345" otherAttr=" size=\"50\" maxlength=\"58\" ezftoupper=\"\""/>
								</td>
								<td><fmt:message key="i18n.NMAL0240Scrn00.label.7" bundle="${I18N_SCREEN_ID}">CUSA Set</fmt:message></td>
								<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
							</tr>
						</table>
					</fieldset>
					<%@ page import="business.servlet.NMAL0240.NMAL0240BMsg" %>
					<%@ page import="business.servlet.NMAL0240.NMAL0240_ABMsg" %>
					<%  NMAL0240BMsg bMsg = (NMAL0240BMsg)databean.getEZDBMsg(); %>
					<fieldset style="width:1100;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="right">
									<ezf:inputButton name="OpenWin_BOMTextEntry" value="Comment/Text" htmlClass="pBtn10" />
									<ezf:inputButton name="CMN_Download" value="Download" htmlClass="pBtn6" />
								</td>
							</tr>
							<tr><td height="5"></td></tr>
							<tr>
								<td>
									<!-- Current Revision -->
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table border="1" cellpadding="0" cellspacing="0">
													<col width="100">
													<col width="50">
													<col width="150">
													<col width="380">
													<col width="80">
													<col width="110">
													<col width="80">
													<col width="110">
													<tr>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Revision</fmt:message></td>
														<td><ezf:inputText name="cmpsnRevnNum_A" ezfName="cmpsnRevnNum_A" value="001" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Revision Comments</fmt:message></td>
														<td><ezf:inputText name="cmpsnRevnCmntTxt_A" ezfName="cmpsnRevnCmntTxt_A" value="001" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Start Date</fmt:message></td>
														<td>
															<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_A\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4);">
														</td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.11" bundle="${I18N_SCREEN_ID}">End Date</fmt:message></td>
														<td>
															<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_A\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4);">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" />
												<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" />
											</td>
										</tr>
										<tr>
											<td>
												<table border="1" cellpadding="0" cellspacing="0" width="1067">
													<col align="center" width="25"> <!-- Checkbox -->
													<col align="center" width="60" colspan="2"> <!-- Sequence -->
													<col align="center" width="235" colspan="3"> <!-- Component -->
													<col align="center" width="155"> <!-- Component Description -->
													<col align="center" width="40"> <!-- Quantity -->
													<col align="center" width="190"> <!-- Merch Type -->
													<col align="center" width="70"> <!-- Prod Code -->
													<col align="center" width="110"> <!-- Effective Start -->
													<col align="center" width="110"> <!-- Effective End -->
													<col align="center" width="70"> <!-- Active -->
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Sequence</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Component</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Component Description</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Quantity</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Merch Type</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Prod Code</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Effective Start</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Effective End</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Active</fmt:message></td>
													</tr>
												</table>
												<div style="overflow-y:scroll; height:150; width:1065;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
														<col width="25"> <!-- Checkbox -->
														<col width="30"> <!-- Sequence 1 -->
														<col width="30"> <!-- Sequence 2 -->
														<col width="40" align="center"> <!-- Component Item Button -->
														<col width="40" align="center"> <!-- Component +- Button -->
														<col width="155"> <!-- Component MDSE Code -->
														<col width="155"> <!-- Component Description -->
														<col width="40"> <!-- Quantity -->
														<col width="190"> <!-- Merch Type -->
														<col width="70"> <!-- Prod Code -->
														<col width="110"> <!-- Effective Start -->
														<col width="110"> <!-- Effective End -->
														<col width="70"> <!-- Active -->
														<% int i = 0; %>
														<ezf:row ezfHyo="A">
														<tr>
															<td class="pOut"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
															<td class="pOut"><ezf:label name="xxNum_A1" ezfName="xxNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="pOut"><ezf:label name="xxNum_A2" ezfName="xxNum_A2" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="pOut"><ezf:inputButton name="OpenWin_CmpsnItemA" value="Item" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CmpsnItemA{EZF_ROW_NUMBER}\""/></td>
															<td class="pOut">
																<% if("Y".equals(bMsg.A.no(i).xxPgFlg_A1.getValue())) { %>
																<ezf:inputButton name="OpenCmpsnA" value="+" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenCmpsnA{EZF_ROW_NUMBER}\""/>
																<% } else {%>
																<ezf:inputButton name="CloseCmpsnA" value="-" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"CloseCmpsnA{EZF_ROW_NUMBER}\""/>
																<% } i++; %>
															</td>
															<td class="pOut">
																<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/><ezf:inputButton name="GetMdse" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"GetMdse{EZF_ROW_NUMBER}\""/>
															</td>
															<td class="pOut"><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="childMdseQty_A1" ezfName="childMdseQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="coaProjDescTxt_A1" ezfName="coaProjDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="xxScrItem10Txt_A1" ezfName="xxScrItem10Txt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
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
							<tr><td height="10"></td></tr>
							<tr>
								<td>
									<!-- Previous Revision -->
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table border="1" cellpadding="0" cellspacing="0">
													<col width="100">
													<col width="50">
													<col width="150">
													<col width="380">
													<col width="80">
													<col width="110">
													<col width="80">
													<col width="110">
													<tr>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Revision</fmt:message></td>
														<td><ezf:inputText name="cmpsnRevnNum_B" ezfName="cmpsnRevnNum_B" value="001" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Revision Comments</fmt:message></td>
														<td><ezf:inputText name="cmpsnRevnCmntTxt_B" ezfName="cmpsnRevnCmntTxt_B" value="001" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Start Date</fmt:message></td>
														<td>
															<ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_B\""/>
														</td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.11" bundle="${I18N_SCREEN_ID}">End Date</fmt:message></td>
														<td>
															<ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_B\""/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td><br></td>
										</tr>
										<tr>
											<td>
												<table border="1" cellpadding="0" cellspacing="0" width="1067">
													<col align="center" width="25"> <!-- Checkbox -->
													<col align="center" width="60" colspan="2"> <!-- Sequence -->
													<col align="center" width="235" colspan="3"> <!-- Component -->
													<col align="center" width="155"> <!-- Component Description -->
													<col align="center" width="40"> <!-- Quantity -->
													<col align="center" width="190"> <!-- Merch Type -->
													<col align="center" width="70"> <!-- Prod Code -->
													<col align="center" width="110"> <!-- Effective Start -->
													<col align="center" width="110"> <!-- Effective End -->
													<col align="center" width="70"> <!-- Active -->
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Sequence</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Component</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Component Description</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Quantity</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Merch Type</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Prod Code</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Effective Start</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Effective End</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL0240Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Active</fmt:message></td>
													</tr>
												</table>
												<div style="overflow-y:scroll; height:150; width:1065;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B">
														<col width="25"> <!-- Checkbox -->
														<col width="30"> <!-- Sequence 1 -->
														<col width="30"> <!-- Sequence 2 -->
														<col width="40" align="center"> <!-- Component Item Button -->
														<col width="40" align="center"> <!-- Component +- Button -->
														<col width="155"> <!-- Component MDSE Code -->
														<col width="155"> <!-- Component Description -->
														<col width="40"> <!-- Quantity -->
														<col width="190"> <!-- Merch Type -->
														<col width="70"> <!-- Prod Code -->
														<col width="110"> <!-- Effective Start -->
														<col width="110"> <!-- Effective End -->
														<col width="70"> <!-- Active -->
														<% int j = 0; %>
														<ezf:row ezfHyo="B">
														<tr>
															<td class="pOut"><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
															<td class="pOut"><ezf:label name="xxNum_B1" ezfName="xxNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td class="pOut"><ezf:label name="xxNum_B2" ezfName="xxNum_B2" ezfHyo="B" ezfArrayNo="0" /></td>
															<td class="pOut"><ezf:inputButton name="OpenWin_CmpsnItemB" value="Item" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CmpsnItemB{EZF_ROW_NUMBER}\""/></td>
															<td class="pOut">
																<% if("Y".equals(bMsg.B.no(j).xxPgFlg_B1.getValue())) { %>
																<ezf:inputButton name="OpenCmpsnB" value="+" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenCmpsnB{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputButton name="CloseCmpsnB" value="-" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"CloseCmpsnB{EZF_ROW_NUMBER}\""/>
																<% } j++; %>
															</td>
															<td class="pOut">
																<ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/><ezf:inputButton name="GetMdse" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"GetMdse{EZF_ROW_NUMBER}\""/>
															</td>
															<td class="pOut"><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="childMdseQty_B1" ezfName="childMdseQty_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="coaProjDescTxt_B1" ezfName="coaProjDescTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="coaProdCd_B1" ezfName="coaProdCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td class="pOut"><ezf:inputText name="xxScrItem10Txt_B1" ezfName="xxScrItem10Txt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
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
							<tr><td height="5"></td></tr>
							<tr>
								<td align="right">
									<ezf:inputButton name="OpenWin_CreateNew" value="Create New" htmlClass="pBtn6" />
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
