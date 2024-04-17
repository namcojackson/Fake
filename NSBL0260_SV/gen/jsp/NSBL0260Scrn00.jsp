<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170608093556 --%>
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
			<input type="hidden" name="pageID" value="NSBL0260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="EOL Exception Maintenance">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<%-- ######################################## Header Start ############################################## --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="EOL Exception Maintenance" class="pTab_ON"><a href="#">EOL Ex Mnt</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="15">
									<col width="10">
									<col width="10">
									<col width="15">
									<col width="20">
									<col width="25">
									<col width="5">
									<col width="20">
									<col width="50">
									<col width="5">
									<col width="20">
									<col width="15">
									<col width="15">
									<col width="65">
									<tr>
										<td class="stab" colspan="4">
											Position to <ezf:anchor name="mdlNm_CL" ezfName="mdlNm_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelHeaderComb" >Model Name</ezf:anchor>
											&nbsp;/&nbsp;<ezf:anchor name="serNum_CL" ezfName="serNum_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_SerialHeaderComb" >Serial#</ezf:anchor>
										</td>
										<td colspan="2"><ezf:inputText name="mdlNm_C" ezfName="mdlNm_C" value="xxxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">/</td>
										<td colspan="7"><ezf:inputText name="serNum_C" ezfName="serNum_C" value="xxxxxxxxx1xxxxxxxxx2xxxxx" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab" colspan="4"><ezf:anchor name="mdlNm_HL" ezfName="mdlNm_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelHeader" >Model Name(*)</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="mdlNm_H" ezfName="mdlNm_H" value="xxxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="dsAcctNum_HL" ezfName="dsAcctNum_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Account" >Customer#(*)</ezf:anchor></td>
										<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="xxxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
										<td></td>
										<td class="stab">Serviceable</td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_ON" ezfName="xxChkBox_ON" value="N" />On</td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_OF" ezfName="xxChkBox_OF" value="N" />Off</td>
										<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;</td>
										</tr>
								</table>
							</td>
						</tr>
					</table>
					<%-- ######################################## Header End   ############################################## --%>
					<hr>
					<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table width="99%">
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
					<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
					<%-- ######################################## List Start ############################################## --%>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
						<col align="left" valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-x:hidden; width:1106;">
									<table border="1" cellpadding="1" cellspacing="0" width="" height="28">
										<col width="40"  align="center">		<!-- Check Box -->
										<col width="150"  align="center">		<!-- Model Name -->
										<col width="150"  align="center">		<!-- Serial Number -->
										<col width="100"  align="center">		<!-- No New Contracts After Date -->
										<col width="100"  align="center">		<!-- Stop Service Date -->
										<col width="50"  align="center">		<!-- Serv Flag -->
										<col width="80"  align="center">		<!-- Config# -->
										<col width="90"  align="center">		<!-- Customer# -->
										<col width="150"  align="center">		<!-- Customer Name -->
										<col width="90"  align="center">		<!-- City -->
										<col width="40"  align="center">		<!-- State -->
										<tr>
											<td class="pClothBs"></td>
											<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlNm_A')">Model Name</a><img id="sortIMG.mdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial Number</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMdlEolDt_AN')">No New Contracts<br>After Date</a><img id="sortIMG.dsMdlEolDt_AN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMdlEolDt_AS')">Stop Service<br>Date</a><img id="sortIMG.dsMdlEolDt_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'actvFlg_A')">Serv<br>Flag</a><img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcConfigMstrPk_A')">Config#</a><img id="sortIMG.svcConfigMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A')">Customer#</a><img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">Customer Name</a><img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ctyAddr_A')">City</a><img id="sortIMG.ctyAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stCd_A')">State</a><img id="sortIMG.stCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="width:1106; overflow-y:scroll; height:410;">
									<table id="A" border="1" cellpadding="1" cellspacing="0">
										<col width="40"  align="center">		<!-- Check Box -->
										<col width="150"  align="center">		<!-- Model Name -->
										<col width="150"  align="center">		<!-- Serial Number -->
										<col width="100"  align="center">		<!-- No New Contracts After Date -->
										<col width="100"  align="center">		<!-- Stop Service Date -->
										<col width="50"  align="center">		<!-- Serv Flag -->
										<col width="80"  align="center">		<!-- Config# -->
										<col width="90"  align="center">		<!-- Customer# -->
										<col width="150"  align="center">		<!-- Customer Name -->
										<col width="90"  align="center">		<!-- City -->
										<col width="40"  align="center">		<!-- State -->
										<ezf:row ezfHyo="A">
											<tr>
												<td>
													<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:inputText name="mdlNm_A" ezfName="mdlNm_A" value="xxxxxxxxx1xxxxxxxxx2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
													<ezf:inputButton name="OpenWin_ModelLine" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
												</td>
												<td>
													<ezf:inputText name="serNum_A" ezfName="serNum_A" value="xxxxxxxxx1xxxxxxxxx2xxxxx" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\""/>
													<ezf:inputButton name="OpenWin_SerialLine" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
												</td>
												<td>
													<ezf:inputText name="dsMdlEolDt_AN" ezfName="dsMdlEolDt_AN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsMdlEolDt_AN', 4, {EZF_ROW_NUMBER});">
												</td>
												<td>
													<ezf:inputText name="dsMdlEolDt_AS" ezfName="dsMdlEolDt_AS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsMdlEolDt_AS', 4, {EZF_ROW_NUMBER});">
												</td>
												<td>
													<ezf:label name="actvFlg_A" ezfName="actvFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="xxxxxxxxx1xxxxxxxxx2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/>
												</td>
												<td>
													<ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxx" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/>
												</td>
												<td>
													<ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0xxxxxxxx1xxxxxxxxx2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"360\""/>
												</td>
												<td>
													<ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" value="xxxxxxxxx1xxxxxxxxx2xxxxx" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"25\""/>
												</td>
												<td>
													<ezf:inputText name="stCd_A" ezfName="stCd_A" value="AB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/>
												</td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
											</tr>
											<ezf:skip>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<%-- ######################################## List End   ######################################## --%>
					<br>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" height="24">
						<tr>
							<td>
								<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"InsertRow\""/>
								<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"DeleteRow\""/>
								<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" otherAttr=" id=\"SelectAll\""/>
								<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn6" otherAttr=" id=\"UnselectAll\""/>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
