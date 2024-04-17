<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180615184712 --%>
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
			<input type="hidden" name="pageID" value="NFCL3040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Inquiry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
					<div class="pTab_BODY">
								<table border="1" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="5">
												<col width="250">
												<col width="350">
												<col width="5">
												<col width="200">
												<col width="300">
												<col width="5">
												<tr>
													<td></td>
													<td class="stab" colspan="2"><b>Batch</b></td>
													<td></td>
													<td class="stab" colspan="2"><b>Common</b></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td class="stab" style="hight:37px;">Receipt Source</td>
													<td>
														<ezf:select name="arRcptSrcCd_H" ezfName="arRcptSrcCd_H" ezfBlank="1" ezfCodeName="arRcptSrcCd_LC" ezfDispName="arRcptSrcDescTxt_LD" otherAttr=" style=\"width:250px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab">Creation Date</td>
													<td>
														<ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H1', 4);" >
														-
														<ezf:inputText name="cratDt_H2" ezfName="cratDt_H2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H2', 4);" >
													</td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td class="stab" style="hight:37px;">Batch Name(*)</td>
													<td><ezf:inputText name="arBatRcptNm_H" ezfName="arBatRcptNm_H" value="BOA_CHI_20150303_001" otherAttr=" size=\"35\" maxlength=\"55\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankAcctNm_L1" ezfName="dsBankAcctNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_1" otherAttr=" tabindex=\"1502\"">Remittance Bank Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankAcctNm_H" ezfName="dsBankAcctNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td class="stab">Batch Status</td>
													<td>
														<ezf:select name="arBatRcptStsCd_H" ezfName="arBatRcptStsCd_H" ezfBlank="1" ezfCodeName="arBatRcptStsCd_LC" ezfDispName="arBatRcptStsNm_LD" otherAttr=" style=\"width:250px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankBrNm_L1" ezfName="dsBankBrNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_2" otherAttr=" tabindex=\"1502\"">Remittance Branch Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankBrNm_H" ezfName="dsBankBrNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td class="stab">Lockbox File Name(*)</td>
													<td><ezf:inputText name="arLockBoxFileNm_H" ezfName="arLockBoxFileNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"35\" maxlength=\"50\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankAcctNum_L1" ezfName="dsBankAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_3" otherAttr=" tabindex=\"1502\"">Remittance Account Number(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankAcctNum_H" ezfName="dsBankAcctNum_H" otherAttr=" size=\"31\" maxlength=\"14\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td class="stab">Lockbox</td>
													<td>
														<ezf:select name="arLockBoxCd_H" ezfName="arLockBoxCd_H" ezfBlank="1" ezfCodeName="arLockBoxCd_LC" ezfDispName="arLockBoxNm_LD" otherAttr=" style=\"width:250px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab">&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td></td>
													<td class="stab">Lockbox Batch</td>
													<td><ezf:inputText name="arLockBoxBatNum_H" ezfName="arLockBoxBatNum_H" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">&nbsp;</td>
													<td align="right">
														<ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn8" otherAttr=" tabindex=\"1502\""/>
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

<%-- ######################################## DETAIL ######################################## --%>
					<%-- ###TAB - BODY --%>
					<div class="">
						<table border="0" cellpadding="1" cellspacing="0" width="1100">
							<tr>
								<td>
									<table width="100%">
										<col valign="top">
											<tr>
												<td>
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</td>
												<td align="right">
													<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
													</ezf:skip>
													<%-- Pagenation --%>
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
													</jsp:include>
												</td>
											</tr>
										</col>
									</table>
								</td>
							</tr>
							<div id="parentBoxA">
								<table>
									<tr>
										<td>
											<div style="float:left; display:block"> <!-- left table -->
												<div id="leftTblHead" style="display:block;"></div>
												<div id="leftTbl" style="float:left; display:block; height:338px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 0px"></div>
											</div>  <!-- left table -->
											<div style="float:left"> <!-- right table -->
												<div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1100px" style="margin-right:0px">
														<col width="240" align="center"> <!-- Batch Name -->
														<col width="150" align="center"> <!-- Receipt Source -->
														<col width="80" align="center">  <!-- Batch Date -->
														<col width="65" align="center">  <!-- Batch Status -->
														<col width="60" align="center">  <!-- Ctrl Cuont -->
														<col width="80" align="center">  <!-- Control Amount -->
														<col width="170" align="center"> <!-- Lockbox File Nm -->
														<col width="170" align="center"> <!-- Lockbox Name -->
														<col width="66" align="center">  <!-- Lockbox Batch -->
														<tr>
															<td id="AH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptNm')">Batch Name<img id="sortIMG.arBatRcptNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH1"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arRcptSrcNm')">Receipt Source<img id="sortIMG.arRcptSrcNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH2"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptDt')">Batch Date<img id="sortIMG.arBatRcptDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH3"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptStsNm')">Batch<BR>Status<img id="sortIMG.arBatRcptStsNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH4"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptCnt')">Ctrl Count<img id="sortIMG.arBatRcptCnt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH5"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptAmt')">Control Amount<img id="sortIMG.arBatRcptAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH6"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxFileNm')">Lockbox File Nm<img id="sortIMG.arLockBoxFileNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH7"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxNm')">Lockbox Name<img id="sortIMG.arLockBoxNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH8"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxBatNum')">Lockbox<BR>Batch<img id="sortIMG.arLockBoxBatNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														</tr>
													</table>
												</div><!-- rightTblHead-->
												<div id="rightTbl" style="width:1100px; height:338px; display:block; overflow-y:scroll; overflow-x:none; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1100px" >
														<col width="240" align="left">   <!-- Batch Name -->
														<col width="150" align="left">   <!-- Receipt Source -->
														<col width="80" align="left">    <!-- Batch Date -->
														<col width="65" align="left">    <!-- Batch Status -->
														<col width="60" align="right">   <!-- Ctrl Count -->
														<col width="80" align="right">   <!-- Control Amount -->
														<col width="170" align="left">   <!-- Lockbox File Nm -->
														<col width="170" align="left">   <!-- Lockbox Name -->
														<col width="66" align="left">    <!-- Lockbox Batch -->
														<ezf:row ezfHyo="A">
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td>
																	<div id="arBatRcptNm#{EZF_ROW_NUMBER}">
																		<ezf:anchor name="Click_LinkBatchName" ezfName="Click_LinkBatchName" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_LinkBatchName" otherAttr=" id=\"arBatRcptNm#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"1502\"">
																			<ezf:label name="arBatRcptNm" ezfName="arBatRcptNm" ezfHyo="A" ezfArrayNo="0" />
																		</ezf:anchor>
																	</div>
																</td>
																<td><ezf:inputText name="arRcptSrcNm" ezfName="arRcptSrcNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptDt" ezfName="arBatRcptDt" value="10/08/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptStsNm" ezfName="arBatRcptStsNm" value="Closed" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptCnt" ezfName="arBatRcptCnt" value="35" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="arBatRcptAmt" ezfName="arBatRcptAmt" value="171256.21" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="arLockBoxFileNm" ezfName="arLockBoxFileNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arLockBoxNm" ezfName="arLockBoxNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arLockBoxBatNum" ezfName="arLockBoxBatNum" value="123456798" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
														</ezf:row>
													</table>
												</div><!-- rightTbl-->
											</div><!-- right table-->
										</td>
									</tr>
								</table>
							</div><!-- parentBoxA -->
							<tr><td><hr></td></tr>
						</table>
					</div>
				</div>

			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, true);
</script>

<%-- **** Task specific area ends here **** --%>
