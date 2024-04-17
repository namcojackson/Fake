<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161107102052 --%>
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
			<input type="hidden" name="pageID" value="NSAL0750Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update Invoicing Rule">
<%@ page import="business.servlet.NSAL0750.NSAL0750BMsg" %>
<%@ page import="business.servlet.NSAL0750.NSAL0750_ABMsg" %>
<%  NSAL0750BMsg bMsg = (NSAL0750BMsg)databean.getEZDBMsg(); %>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Update Invoicing Rule" class="pTab_ON"><a href="#">Upd Inv Rule</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="100">
									<col width="600">
									<tr>
										<td class="stab">Invoicing Rule</td>
										<td><ezf:select name="baseBllgTmgCd_H3" ezfName="baseBllgTmgCd_H3" ezfBlank="1" ezfCodeName="baseBllgTmgCd_H1" ezfDispName="bllgTmgTpNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"80\" rows=\"4\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="100%">
						<tr>
							<td>
								<table  border="0" cellpadding="0" cellspacing="0" width="1090">
									<col width="" align="right" valign="top">
									<tr>
										<td>
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">0</td>
														<td class="stab">to</td>
														<td class="pOut">0</td>
														<td class="stab">of</td>
														<td class="pOut">0</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
													</tr>
												</table>
											</ezf:skip>
											<table width="500">
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
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="200" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="150" align="center"><!-- Serial# -->
									<col width="150" align="center"><!-- Status -->
									<col width="100" align="center"><!-- Start Date -->
									<col width="100" align="center"><!-- End Date -->
									<col width="100" align="center"><!-- Rule -->
									<col width="100" align="center"><!-- New Rule -->
									<col width="150" align="center"><!-- Return Message -->
									<tr>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('SelectAllContract')" /></td>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Status</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">Rule</td>
										<td class="pClothBs">New Rule</td>
										<td class="pClothBs">Return Message</td>
									</tr>
								</table>
								<div style="width:1058; height:365; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 24" align="left"><!-- CheckBox -->
										<col width="200" align="center"><!-- Contract# -->
										<col width=" 24" align="left"><!-- CheckBox -->
										<col width="150" align="center"><!-- Serial# -->
										<col width="150" align="center"><!-- Status -->
										<col width="100" align="center"><!-- Start Date -->
										<col width="100" align="center"><!-- End Date -->
										<col width="100" align="center"><!-- Rule -->
										<col width="100" align="center"><!-- New Rule -->
										<col width="150" align="center"><!-- Return Message -->
										<% int i = 0; %>
										<% int idx=-1; %>
										<ezf:row ezfHyo="A">
										<% NSAL0750_ABMsg abMsg = bMsg.A.no(i++); %>
											<tr height="25">
												<td>
													<% boolean flgD1 = "Y".equals(abMsg.xxFlgNm_D1.getValue()); %>
													<% if(flgD1) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/>
													<% if(flgD1) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="xxScrItem34Txt" ezfName="xxScrItem34Txt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWW" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"28\""/></td>
												<td>
													<% boolean flgD2 = "Y".equals(abMsg.xxFlgNm_D2.getValue()); %>
													<% if(flgD2) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="xxChkBox_D2" ezfName="xxChkBox_D2" value="Y" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" id=\"xxChkBox_D2#{EZF_ROW_NUMBER}\""/>
													<% if(flgD2) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"20\""/></td>
												<td><ezf:inputText name="dsContrStsDescTxt" ezfName="dsContrStsDescTxt" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"20\""/></td>
												<td><ezf:inputText name="contrVrsnEffFromDt" ezfName="contrVrsnEffFromDt" value="01/01/2015" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" value="12/31/2015" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="bllgTmgTpNm_D1" ezfName="bllgTmgTpNm_D1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="bllgTmgTpNm_D2" ezfName="bllgTmgTpNm_D2" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="contrMsgTxt" ezfName="contrMsgTxt" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"20\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" ezfArrayNo="${idx}" id="xxChkBox_A#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" class="pPro" size="12" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfhyo="A" ezfArrayNo="${idx}" id="xxChkBox_A#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="dsContrStsDescTxt_A1" ezfname="dsContrStsDescTxt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2015" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="12/31/2015" name="contrVrsnEffThruDt_A1" ezfname="contrVrsnEffThruDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="bllgTmgTpNm_A1" ezfname="bllgTmgTpNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="bllgTmgTpNm_A2" ezfname="bllgTmgTpNm_A2" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="48" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" ezfArrayNo="${idx}" id="xxChkBox_A#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" class="pPro" size="12" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfhyo="A" ezfArrayNo="${idx}" id="xxChkBox_A#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="dsContrStsDescTxt_A1" ezfname="dsContrStsDescTxt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2015" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="12/31/2015" name="contrVrsnEffThruDt_A1" ezfname="contrVrsnEffThruDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="bllgTmgTpNm_A1" ezfname="bllgTmgTpNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="bllgTmgTpNm_A2" ezfname="bllgTmgTpNm_A2" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="48" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>

										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
