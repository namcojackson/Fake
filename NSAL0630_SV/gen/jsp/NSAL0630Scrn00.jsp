<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161104111637 --%>
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
			<input type="hidden" name="pageID" value="NSAL0630Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract on Hold">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract on Hold" class="pTab_ON"><a href="#">Cont Hold</a></li>
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
<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
				<table border="0" width="99%" align="center">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="bottom">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td class="stab">Reason Code:</td>
									<td align="left">
										<ezf:select name="svcMemoRsnCd_H" ezfName="svcMemoRsnCd_H" ezfBlank="1" ezfCodeName="svcMemoRsnCd_L" ezfDispName="svcMemoRsnNm_L" otherAttr=" id=\"svcMemoRsnCd_H\" style=\"width:250px\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Notes:</td>
									<td>
										<ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"101\" rows=\"4\" tabindex=\"33\""/>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

<%-- ######################################## DETAIL ######################################## --%>
				<table width="100%">
					<tr>
						<td>
							<table width="98%">
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
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="overflow-x:hidden; width:1117;">
								<table border="1"width="1100" height="24" align="left" border="1" cellpadding="1" cellspacing="0">
									<col width="20"  align="center">		<!-- (Check Box) -->
									<col width="100" align="center">		<!-- Contract# -->
									<col width="150"  align="center">		<!-- Customer Name -->
									<col width="275" align="center">		<!-- Bill To -->
									<col width="90"  align="center">		<!-- Contract Start Date -->
									<col width="90"  align="center">		<!-- Contract End Date -->
									<col width="90"  align="center">		<!-- Contract Status -->
									<col width="235"  align="center">		<!-- Return Message -->
									<tr height="27">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" onClick="sendServer('CheckAll')" /></td>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs">Customer Name</td>
										<td class="pClothBs">Bill To</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">Status</td>
										<td class="pClothBs">Return Message</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:1117; overflow-y:scroll; height:410;">
								<table border="1"width="1100" height="24" align="left" id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="20"  align="center">		<!-- (Check Box) -->
									<col width="100" align="center">		<!-- Contract# -->
									<col width="150"  align="center">		<!-- Customer Name -->
									<col width="275" align="center">		<!-- Bill To -->
									<col width="90"  align="center">		<!-- Contract Start Date -->
									<col width="90"  align="center">		<!-- Contract End Date -->
									<col width="90"  align="center">		<!-- Contract Status -->
									<col width="235"  align="center">		<!-- Return Message -->
									<ezf:row ezfHyo="A">
										<tr>
											<td>
												<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="contrNumTxt_A" ezfName="contrNumTxt_A" value="contrNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"contrNumTxt_A#{EZF_ROW_NUMBER}\" size=\"12\" maxlength=\"34\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="CHICAGO BULLS INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A#{EZF_ROW_NUMBER}\" size=\"19\" maxlength=\"60\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="billToCustLocAddr_A" ezfName="billToCustLocAddr_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToCustLocAddr_A#{EZF_ROW_NUMBER}\" size=\"38\" maxlength=\"170\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="contrVrsnEffFromDt_A" ezfName="contrVrsnEffFromDt_A" value="10/28/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"contrVrsnEffFromDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="contrVrsnEffThruDt_A" ezfName="contrVrsnEffThruDt_A" value="10/28/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"contrVrsnEffThruDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsContrStsDescTxt_A" ezfName="dsContrStsDescTxt_A" value="Approved" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsContrStsDescTxt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"50\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsMsgTxt_A" ezfName="dsMsgTxt_A" value="Status Not Eligible." ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"color:#ff0000\" id=\"dsMsgTxt_A#{EZF_ROW_NUMBER}\" size=\"32\" maxlength=\"50\" tabindex=\"-1\""/>
											</td>
										</tr>
										<ezf:skip>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input  style="color:#008000"id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A">
											</td>
											<td>
												<input id="contrNumTxt_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="34" class="pPro" value="contrNum" name="contrNumTxt_A" tabindex="-1" ezfname="contrNumTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="19" maxlength="60" class="pPro" value="CHICAGO BULLS INC" name="dsAcctNm_A" tabindex="-1" ezfname="dsAcctNm_A" ezfhyo="A">
											</td>
											<td>
												<input id="billToCustLocAddr_A#{EZF_ROW_NUMBER}" type="text" size="38" maxlength="170" class="pPro" value="---------1---------2---------3" name="billToCustLocAddr_A" tabindex="-1" ezfname="billToCustLocAddr_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxFromDtCd_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxFromDtCd_A" tabindex="-1" ezfname="xxFromDtCd_A" ezfhyo="A">
											</td>
											<td>
												<input id="xxTmFrameTx_A#{EZF_ROW_NUMBER}t" type="text" size="10" maxlength="10" class="pPro" value="10/28/2015" name="xxTmFrameTxt_A" tabindex="-1" ezfname="xxTmFrameTxt_A" ezfhyo="A">
											</td>
											<td>
												<input id="dsContrStsDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="50" class="pPro" value="Approved" name="dsContrStsDescTxt_A" tabindex="-1" ezfname="dsContrStsDescTxt_A" ezfhyo="A">
											</td>
											<td>
												<input style="color:#ff0000" id="drctSlsIntfcErrMsgTxt_A#{EZF_ROW_NUMBER}" type="text" size="32" maxlength="50" class="pPro" value="Status Not Eligible." name="drctSlsIntfcErrMsgTxt_A" tabindex="-1" ezfname="drctSlsIntfcErrMsgTxt_A" ezfhyo="A">
											</td>
										</tr>

										</ezf:skip>
									</ezf:row>
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
<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>
<!-- ###SCRIPT -->
<script type="text/javascript">
	function synchroHeaderRightScroll() {
		var topTBL    = document.getElementById( 'topHeaderTBL'    );
		var rightTBL  = document.getElementById( 'rightHeaderTBL'  );
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromHeaderLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		var rightTBL = this.document.getElementById( 'rightHeaderTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroLineRightScroll() {
		var topTBL    = document.getElementById( 'topLineTBL'    );
		var rightTBL  = document.getElementById( 'rightLineTBL'  );
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromLineLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		var rightTBL = this.document.getElementById( 'rightLineTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
