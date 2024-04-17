<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160701184601 --%>
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
			<input type="hidden" name="pageID" value="NMAL7130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Contract">

<center>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Price Contract" class="pTab_ON"><a href="#">Prc Contract</a></li>
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
				<div class="pTab_BODY">
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="100" width="1100" rules="none"  style="padding: 5px; margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100">
							<table cellpadding="0" border="0">
								<col align="left" width="100">
								<col align="left" width="300">
								<col align="left" width="60">
								<col align="left" width="120">
								<col align="left" width="80">
								<col align="left" width="120">
								<col align="left" width="60">
								<col align="left">
								<tr>
									<td class="stab">Contract ID</td>
									<td colspan="5">
										<ezf:inputText name="prcContrPk_H1" ezfName="prcContrPk_H1" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
										&nbsp;
										<ezf:inputButton name="SrchPrcContr" value="Search" htmlClass="pBtn5" otherAttr=" id=\"SrchPrcContr\""/>
									</td>
									<td colspan="2">
										<ezf:inputButton name="CopyPrcContr" value="COPY PRICE CONTRACT" htmlClass="pBtn13" otherAttr=" id=\"CopyPrcContr\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Contract Name</td>
									<td>
										<ezf:inputText name="prcContrNm_H1" ezfName="prcContrNm_H1" value="PRICE-CONTRACT-NAME" otherAttr=" size=\"50\" maxlength=\"150\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Start Date</td>
									<td>
										<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
									</td>
									<td class="stab">Version#</td>
									<td>
										<ezf:inputText name="prcContrVrsnNum_H1" ezfName="prcContrVrsnNum_H1" value="1" otherAttr=" size=\"10\" maxlength=\"10\""/>
									</td>
									<td class="stab">Created By</td>
									<td>
										<ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" value="q99999(XXXXXXXXXXXXXXX XXXXXXX)" otherAttr=" size=\"20\" maxlength=\"99\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Contract#</td>
									<td>
										<ezf:inputText name="prcContrNum_H1" ezfName="prcContrNum_H1" value="PRICE-CONTRACT-NUMBER" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
									<td class="stab">End Date</td>
									<td>
										<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
									</td>
									<td class="stab">Renewed Date</td>
									<td>
										<ezf:inputText name="prcContrRnwDt_H1" ezfName="prcContrRnwDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcContrRnwDt_H1', 4);" >
									</td>
									<td class="stab">Create Date</td>
									<td>
										<ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Bid#</td>
									<td>
										<ezf:inputText name="prcContrCustBidNum_H1" ezfName="prcContrCustBidNum_H1" value="1234567890" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Active</td>
									<td>
										<ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" />
									</td>
									<td class="stab">Term</td>
									<td>
										<ezf:inputText name="prcContrTermMthNum_H1" ezfName="prcContrTermMthNum_H1" value="3" otherAttr=" size=\"3\" maxlength=\"3\""/>
										Months
									</td>
									<td class="stab">Updated By</td>
									<td>
										<ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" value="q99999(XXXXXXXXXXXXXXX XXXXXXX)" otherAttr=" size=\"20\" maxlength=\"99\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">CSAP Contract</td>
									<td>
										<ezf:inputCheckBox name="assnPgmContrFlg_H1" ezfName="assnPgmContrFlg_H1" value="Y" />
									</td>
									<td class="stab">Line of Business</td>
									<td>
										<ezf:select name="lineBizTpCd_H1" ezfName="lineBizTpCd_H1" ezfBlank="1" ezfCodeName="lineBizTpCd_L1" ezfDispName="lineBizTpDescTxt_L1" otherAttr=" style=\"width: 80px\""/>
									</td>
									<td colspan="2">
										<ezf:inputButton name="OpenWin_Attachment" value="ATTACHMENTS" htmlClass="pBtn8" otherAttr=" id=\"OpenWin_Attachment\""/>
									</td>
									<td class="stab">Update Date</td>
									<td>
										<ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Brief Description</td>
									<td colspan="7">
										<ezf:inputText name="prcContrShortDescTxt_H1" ezfName="prcContrShortDescTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"120\" maxlength=\"150\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="1130">
					<tr>
						<td>
							<div class="pTab_HEAD">
							<ul>
								<li class="pTab_ON"  id="RegdAcct"      title="Registered Accounts">          <ezf:anchor name="" ezfName="xxTabProt_H1" ezfEmulateBtn="1" ezfGuard="TAB_RegdAcct" >Regd Acct</ezf:anchor></li>
								<li class="pTab_OFF" id="RelnPrcList"   title="Related Price Lists">          <ezf:anchor name="" ezfName="xxTabProt_H2" ezfEmulateBtn="1" ezfGuard="TAB_RelnPrcList" >Reln Prc List</ezf:anchor></li>
								<li class="pTab_OFF" id="InitPrcPntSum" title="Initial Price Points & Summary"><ezf:anchor name="" ezfName="xxTabProt_H3" ezfEmulateBtn="1" ezfGuard="TAB_InitPrcPntSum" >Init Prc Pnt Sum</ezf:anchor></li>
								<li class="pTab_OFF" id="TrxChrg"       title="Transaction Charges">          <ezf:anchor name="" ezfName="xxTabProt_H4" ezfEmulateBtn="1" ezfGuard="TAB_TrxChrg" >Trx Chrg</ezf:anchor></li>
								<li class="pTab_OFF" id="TermCond"      title="Term & Conditions">            <ezf:anchor name="" ezfName="xxTabProt_H5" ezfEmulateBtn="1" ezfGuard="TAB_TermCond" >Term Cond</ezf:anchor></li>
							</ul>
							</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'RegdAcct'}">
							<script type="text/javascript">
								document.getElementById( "RegdAcct").className = "pTab_ON";
								document.getElementById( "RelnPrcList" ).className = "pTab_OFF";
								document.getElementById( "InitPrcPntSum" ).className = "pTab_OFF";
								document.getElementById( "TrxChrg" ).className = "pTab_OFF";
								document.getElementById( "TermCond" ).className = "pTab_OFF";
							</script>

							<div class="pTab_BODY_In" id="RegdAcct_div">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="stab">Show Active & Inactive</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_AH" ezfName="xxChkBox_AH" value="Y" onClick="sendServer('OnClick_RegdAcct')" />
										</td>
										<td width="915" align="right">
											<ezf:inputButton name="MoveWin_CsmpSrchMassUpd" value="REGISTER CSMP" htmlClass="pBtn9" otherAttr=" id=\"regdCsmp\""/>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<div style="overflow-y:scroll; height:350; width:1117; word-break:break-all ">
											<table border="1" cellpadding="0" cellspacing="0" width="" id="A" style="table-layout:fixed; ">
												<col align="center" width="90">
												<col align="center" width="240">
												<col align="center" width="70">
												<col align="center" width="120">
												<col align="center" width="320">
												<col align="center" width="100">
												<col align="center" width="80">
												<col align="center" width="80">
												<tr>
													<th class="pClothBs">Account#</th>
													<th class="pClothBs">Account Name</th>
													<th class="pClothBs">C/P</th>
													<th class="pClothBs">CSMP#</th>
													<th class="pClothBs">Price List</th>
													<th class="pClothBs">Price List Type</th>
													<th class="pClothBs">Start Date</th>
													<th class="pClothBs">End Date</th>
												</tr>
												<ezf:row ezfHyo="A">
												<tr>
													<td align="left" id="dsAcctNum_A1#{EZF_ROW_NUMBER}">
														<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_AcctInfo', '{EZF_ROW_NUMBER}" >
															<ezf:label name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"360\""/></td>
													<td><ezf:label name="dsAcctTpDescTxt_A1" ezfName="dsAcctTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left" id="csmpNum_A1#{EZF_ROW_NUMBER}">
														<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_CsmpSrchMassUpd', '{EZF_ROW_NUMBER}" >
															<ezf:label name="csmpNum_A1" ezfName="csmpNum_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td align="left" id="prcCatgNm_A1#{EZF_ROW_NUMBER}" style="width:320">
														<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_PrcList', '{EZF_ROW_NUMBER}" >
															<ezf:label name="prcCatgNm_A1" ezfName="prcCatgNm_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="prcListTpDescTxt_A1" ezfName="prcListTpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
													<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr>
													<td align="left" id="dsAcctNum_A1#{EZF_ROW_NUMBER}">
														<a href="#" onclick="sendServer('MoveWin_AcctInfo', '{EZF_ROW_NUMBER}')" ezfHyo="A">
															<label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">WWWWWWWWW1WWWW5</label>
														</a>
													</td>
													<td><input type="text" size="30" maxlength="360" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" name="dsAcctNm_A1" ezfname="dsAcctNm_A1" ezfHyo="A"></td>
													<td><label ezfout name="dsAcctTpNm_A1" ezfname="dsAcctTpNm_A1" ezfhyo="A">CUSTOMER</label></td>
													<td align="left" id="csmpNum_A1#{EZF_ROW_NUMBER}">
														<a href="#" onclick="sendServer('MoveWin_CsmpSrchMassUpd', '{EZF_ROW_NUMBER}')" ezfHyo="A">
															<label ezfout name="csmpNum_A1" ezfname="csmpNum_A1" ezfhyo="A">WWWWWWWWW1WWWW5</label>
														</a>
													</td>
													<td align="left" id="prcCatgNm_A1#{EZF_ROW_NUMBER}">
														<a href="#" onclick="sendServer('MoveWin_PrcList', '{EZF_ROW_NUMBER}')" ezfHyo="A">
															<label ezfout name="prcCatgNm_A1" ezfname="prcCatgNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWW35WWWW4WWWWWWWWW5</label>
														</a>
													</td>
													<td><input type="text" size="12" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2" name="prcListTpNm_A1" ezfname="prcListTpNm_A1" ezfHyo="A"></td>
													<td><label ezfout name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A">99/99/9999</label></td>
													<td><label ezfout name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A">99/99/9999</label></td>
												</tr>
												</ezf:skip>
											</table>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'RelnPrcList'}">
							<script type="text/javascript">
								document.getElementById( "RegdAcct").className = "pTab_OFF";
								document.getElementById( "RelnPrcList" ).className = "pTab_ON";
								document.getElementById( "InitPrcPntSum" ).className = "pTab_OFF";
								document.getElementById( "TrxChrg" ).className = "pTab_OFF";
								document.getElementById( "TermCond" ).className = "pTab_OFF";
							</script>

							<div class="pTab_BODY_In" id="RelnPrcList_div">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="stab">Show Active & Inactive</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_BH" ezfName="xxChkBox_BH" value="Y" onClick="sendServer('OnClick_RelnPrcList')" />
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<div style="overflow-y:scroll; height:350; width:1117">
											<table border="1" cellpadding="0" cellspacing="0" width="1100" id="B">
												<col align="center" width="480">
												<col align="center" width="160">
												<col align="center" width="160">
												<col align="center" width="150">
												<col align="center" width="150">
												<tr>
													<th class="pClothBs">Price List Name</th>
													<th class="pClothBs">Price List Type</th>
													<th class="pClothBs">Price List Status</th>
													<th class="pClothBs">Price List Start Date</th>
													<th class="pClothBs">Price List End Date</th>
												</tr>
												<ezf:row ezfHyo="B">
												<tr>
													<td align="left" id="prcCatgNm_B1#{EZF_ROW_NUMBER}">
														<ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_PrcList', '{EZF_ROW_NUMBER}" >
															<ezf:label name="prcCatgNm_B1" ezfName="prcCatgNm_B1" ezfHyo="B" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:label name="prcListTpDescTxt_B1" ezfName="prcListTpDescTxt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="xxScrItem20Txt_B1" ezfName="xxScrItem20Txt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="effFromDt_B1" ezfName="effFromDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt_B1" ezfName="effThruDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr>
													<td align="left" id="prcCatgNm_B1#{EZF_ROW_NUMBER}">
														<a href="#" onclick="sendServer('MoveWin_PriList', '{EZF_ROW_NUMBER}')" ezfHyo="B">
															<label ezfout name="prcCatgNm_B1" ezfname="prcCatgNm_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWW35WWWW4WWWWWWWWW5</label>
														</a>
													</td>
													<td><label ezfout name="prcListTpNm_B1" ezfname="prcListTpNm_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2</label></td>
													<td><label ezfout name="xxScrItem20Txt_B1" ezfname="xxScrItem20Txt_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2</label></td>
													<td><label ezfout name="effFromDt_B1" ezfname="effFromDt_B1" ezfhyo="B">99/99/9999</label></td>
													<td><label ezfout name="effThruDt_B1" ezfname="effThruDt_B1" ezfhyo="B">99/99/9999</label></td>
												</tr>
												</ezf:skip>
											</table>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'InitPrcPntSum'}">
							<script type="text/javascript">
								document.getElementById( "RegdAcct").className = "pTab_OFF";
								document.getElementById( "RelnPrcList" ).className = "pTab_OFF";
								document.getElementById( "InitPrcPntSum" ).className = "pTab_ON";
								document.getElementById( "TrxChrg" ).className = "pTab_OFF";
								document.getElementById( "TermCond" ).className = "pTab_OFF";
							</script>

							<div class="pTab_BODY_In" id="InitPrcPntSum_div">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td style="border-right-style:solid;" width="550">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="100">
												<col width="130">
												<col width="250">
												<tr></tr>
												<tr>
													<td colspan="2"><b><u>Initial Contract Details</u></b></td>
												</tr>
												<tr>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctNum" otherAttr=" ezfanchortext=\"\"">Leasing Company</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="dsAcctNum_C1" ezfName="dsAcctNum_C1" value="1002805" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
														<ezf:inputButton name="Setting_AcctNm" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Setting_AcctNm\""/>
													</td>
													<td class="stab">
														<ezf:inputText name="dsAcctNm_C1" ezfName="dsAcctNm_C1" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Initial Funding Rate</td>
													<td>
														<ezf:inputText name="initFdRate_C1" ezfName="initFdRate_C1" value="0.0218" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
											</table>
										</td>
										<td width="550">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="280">
												<col width="270">
												<tr></tr>
												<tr>
													<td><b><u>Current Contract Summary</u></b></td>
												</tr>
												<tr>
													<td class="stab">Equipment Revenue Achieved Contract Start to Date</td>
													<td><ezf:inputText name="equipRevSumAmt_C1" ezfName="equipRevSumAmt_C1" value="145,355,112.25" otherAttr=" size=\"22\" maxlength=\"22\""/></td>
												</tr>
												<tr>
													<td class="stab">Number of Main Units (Engines) Sold To Date</td>
													<td><ezf:inputText name="mainUnitSumCnt_C1" ezfName="mainUnitSumCnt_C1" value="1,234,567" otherAttr=" size=\"9\" maxlength=\"9\""/></td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'TrxChrg'}">
							<script type="text/javascript">
								document.getElementById( "RegdAcct").className = "pTab_OFF";
								document.getElementById( "RelnPrcList" ).className = "pTab_OFF";
								document.getElementById( "InitPrcPntSum" ).className = "pTab_OFF";
								document.getElementById( "TrxChrg" ).className = "pTab_ON";
								document.getElementById( "TermCond" ).className = "pTab_OFF";
							</script>

							<div class="pTab_BODY_In" id="TrxChrg_div">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn8" otherAttr=" id=\"InsertRow\""/>
										</td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn8" otherAttr=" id=\"DeleteRow\""/>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td>
											<%-- ### Transaction Charges -Left TBL - TOP --%>
											<div id="LeftTBL_Top_TrxChrg" style="overflow-x:none; overflow-y:none; width:25; height:34; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" width="25" height="34">
													<col align="center" width="25">
													<tr>
														<td class="pClothBs">&nbsp;</td>
													</tr>
												</table>
											</div>
											<%-- ### Transaction Charges -Right TBL - TOP --%>
											<div id="RightTBL_Top_TrxChrg" style="overflow-x:hidden; overflow-y:hidden; width:1075; height:34; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" width="2710px" style="table-layout: fixed;">
													<col align="center" width="160">
													<col align="center" width="190">
													<col align="center" width="100">
													<col align="center" width="200">
													<col align="center" width="260">
													<col align="center" width="380">
													<col align="center" width="170">
													<col align="center" width="260">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="120">
													<col align="center" width="70">
													<col align="center" width="160">
													<col align="center" width="80">
													<col align="center" width="160">
													<col align="center" width="80">
													<tr>
														<td class="pClothBs">Charge Type</td>
														<td class="pClothBs">Item Code</td>
														<td class="pClothBs">Rebate%<br>(Enter in Decimal)</td>
														<td class="pClothBs">Admin Fee</td>
														<td class="pClothBs">Charge Name</td>
														<td class="pClothBs">Transaction Type Restriction<br>(Blank means applicable to All transactions)</td>
														<td class="pClothBs">Special Qualifying<br>Account#</td>
														<td class="pClothBs">Account Name</td>
														<td class="pClothBs">Start Date</td>
														<td class="pClothBs">End Date</td>
														<td class="pClothBs">Rebate Type</td>
														<td class="pClothBs">Required</td>
														<td class="pClothBs">Created By</td>
														<td class="pClothBs">Create Date</td>
														<td class="pClothBs">Updated By</td>
														<td class="pClothBs">Update Date</td>
													</tr>
												</table>
											</div>
											<%-- ### Transaction Charges -Left TBL - BOTTOM --%>
											<div id="LeftTBL_TrxChrg" style="overflow-x:hidden; overflow-y:hidden; height:298; width:25; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL_TrxChrg' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="25" id="D1">
													<col align="center" width="25">
													<ezf:row ezfHyo="D">
														<tr id="id_C_row{EZF_ROW_NUMBER}" height="25">
															<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
											<%-- ### Transaction Charges -Right TBL - BOTTOM --%>
											<div id="RightTBL_TrxChrg" style="overflow-x:scroll; overflow-y:scroll; height:315; width:1092; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL_TrxChrg' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top_TrxChrg' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="2710px" id="D2" style="table-layout: fixed;">
													<col align="center" width="160">
													<col align="center" width="190">
													<col align="center" width="100">
													<col align="center" width="200">
													<col align="center" width="260">
													<col align="center" width="380">
													<col align="center" width="170">
													<col align="center" width="260">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="120">
													<col align="center" width="70">
													<col align="center" width="160">
													<col align="center" width="80">
													<col align="center" width="160">
													<col align="center" width="80">
													<ezf:row ezfHyo="D">
														<tr id="id_C_row{EZF_ROW_NUMBER}" height="25">
															<td>
																<ezf:select name="prcContrChrgTpCd_D1" ezfName="prcContrChrgTpCd_D1" ezfHyo="D" ezfArrayNo="0" ezfCodeName="prcContrChrgTpCd_L1" ezfDispName="prcContrChrgTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcContrChrgTp', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 130px\""/>
															</td>
															<td>
																<ezf:inputText name="mdseCd_D1" ezfName="mdseCd_D1" value="9999A999AA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_ItemSrch" value="Item" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" />
															</td>
															<td><ezf:inputText name="prcContrTrxChrgPct_D1" ezfName="prcContrTrxChrgPct_D1" value="0.00075" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"11\""/></td>
															<td><ezf:inputText name="prcContrTrxChrgAmt_D1" ezfName="prcContrTrxChrgAmt_D1" value="1,000.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"22\""/></td>
															<td><ezf:inputText name="prcContrTrxChrgNm_D1" ezfName="prcContrTrxChrgNm_D1" value="Charg Name" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
															<td>
																<ezf:inputText name="xxRecNmTxt_D1" ezfName="xxRecNmTxt_D1" value="ESS-LEASE-ALTERNATE,ESS-LEASE-CFS" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"500\""/>
																<ezf:inputButton name="OpenWin_TrxTp" value="TP" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<td>
																<ezf:inputText name="dsAcctNum_D1" ezfName="dsAcctNum_D1" value="1949581" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_AcctSrch" value="ACCT" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" />
															</td>
															<td><ezf:inputText name="dsAcctNm_D1" ezfName="dsAcctNm_D1" value="DS ACCOUNT NAME" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"360\""/></td>	
															<td>
																<ezf:inputText name="effFromDt_D1" ezfName="effFromDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D1', 4, '{EZF_ROW_NUMBER}');" >
															</td>
															<td>
																<ezf:inputText name="effThruDt_D1" ezfName="effThruDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D1', 4, '{EZF_ROW_NUMBER}');" >
															</td>
															<td>
																<ezf:select name="prcContrRebTpCd_D1" ezfName="prcContrRebTpCd_D1" ezfHyo="D" ezfArrayNo="0" ezfCodeName="prcContrRebTpCd_L1" ezfDispName="prcContrRebTpDescTxt_L1" otherAttr=" style=\"width: 90px\""/>
															</td>
															<td><ezf:inputCheckBox name="appReqFlg_D1" ezfName="appReqFlg_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="xxFullNm_D1" ezfName="xxFullNm_D1" value="DS ACCOUNT NAME" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
															<td>
																<ezf:inputText name="cratDt_D1" ezfName="cratDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
															</td>
															<td><ezf:inputText name="xxFullNm_D2" ezfName="xxFullNm_D2" value="DS ACCOUNT NAME" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
															<td>
																<ezf:inputText name="lastUpdDt_D1" ezfName="lastUpdDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'TermCond'}">
							<script type="text/javascript">
								document.getElementById( "RegdAcct").className = "pTab_OFF";
								document.getElementById( "RelnPrcList" ).className = "pTab_OFF";
								document.getElementById( "InitPrcPntSum" ).className = "pTab_OFF";
								document.getElementById( "TrxChrg" ).className = "pTab_OFF";
								document.getElementById( "TermCond" ).className = "pTab_ON";
							</script>

							<div class="pTab_BODY_In" id="TermCond_div">
								<div style="overflow-y:scroll; height:360; width:1110; display:table-cell;vertical-align:top">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td style="border-right-style:solid;" width="550">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="270">
												<col width="280">
												<tr height="22"><td>&nbsp;</td></tr>
												<tr>
													<td colspan="2"><b><u>PRICING & ORDER TRANSACTION CONDITIONS</u></b></td>
												</tr>
												<tr>
													<td class="stab">Flexibility Percentage</td>
													<td>
														<ezf:inputText name="ordTrxFlexPct_E1" ezfName="ordTrxFlexPct_E1" value="12.34" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Allow Decline Maintenance</td>
													<td><ezf:inputCheckBox name="allwDclnMaintFlg_E1" ezfName="allwDclnMaintFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Must Use Equipment Pricing</td>
													<td><ezf:inputCheckBox name="mustUseEquipPrcFlg_E1" ezfName="mustUseEquipPrcFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Lease Returns Included in Pricing</td>
													<td><ezf:inputCheckBox name="leaseRtrnInclInPrcFlg_E1" ezfName="leaseRtrnInclInPrcFlg_E1" value="Y" /></td>
												<tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">Override System Toner Type</td>
													<td><ezf:inputCheckBox name="ovrdSysTonerTpFlg_E1" ezfName="ovrdSysTonerTpFlg_E1" value="Y" /></td>
												<tr>
													<td class="stab">Bill Toner Freight Charges</td>
													<td><ezf:inputCheckBox name="billTonerFrtChrgFlg_E1" ezfName="billTonerFrtChrgFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Toner Allowance Percentage</td>
													<td>
														<ezf:inputText name="tonerAlwncPct_E1" ezfName="tonerAlwncPct_E1" value="43.21" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">Non Standard Start Time</td>
													<td>
														<ezf:inputText name="nonStdStartTm_E1" ezfName="nonStdStartTm_E1" value="123456" otherAttr=" size=\"6\" maxlength=\"6\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Loaner Entitlement</td>
													<td><ezf:inputCheckBox name="lnrEttlFlg_E1" ezfName="lnrEttlFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Max Downtime to Ship Loaner (in Days)</td>
													<td>
														<ezf:inputText name="maxDownTmDaysAot_E1" ezfName="maxDownTmDaysAot_E1" value="999" otherAttr=" size=\"3\" maxlength=\"3\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Like for Like Replacement Option</td>
													<td><ezf:inputCheckBox name="lflReplOptFlg_E1" ezfName="lflReplOptFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Like for Like Replacement Term (in Months)</td>
													<td>
														<ezf:inputText name="lflReplTermNum_E1" ezfName="lflReplTermNum_E1" value="999" otherAttr=" size=\"3\" maxlength=\"3\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Unlimited Training Required</td>
													<td><ezf:inputCheckBox name="unltdTngReqFlg_E1" ezfName="unltdTngReqFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Cusomer Productivity (CPP)</td>
													<td><ezf:inputCheckBox name="custPrvtyFlg_E1" ezfName="custPrvtyFlg_E1" value="Y" /></td>
												<tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">HDD Services Included</td>
													<td><ezf:inputCheckBox name="hddSvcInclFlg_E1" ezfName="hddSvcInclFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">HDD Cleaning Price Guarantee</td>
													<td><ezf:inputCheckBox name="hddCleanPrcGtdFlg_E1" ezfName="hddCleanPrcGtdFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Time & Materials Uplift</td>
													<td>
														<ezf:inputText name="tmAndMatUplftTxt_E1" ezfName="tmAndMatUplftTxt_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td colspan="2"><b><u>DOCUMENTATION REQUIREMENTS</u></b></td>
												</tr>
												<tr>
													<td class="stab">Form of Agreement</td>
													<td>
														<ezf:inputText name="docReqFrmAgmtNm_E1" ezfName="docReqFrmAgmtNm_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"35\" maxlength=\"50\""/>
													</td>
												<tr>
													<td class="stab">Master Agreement Doc Name</td>
													<td>
														<ezf:inputText name="mstrAgmtDocNm_E1" ezfName="mstrAgmtDocNm_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"35\" maxlength=\"50\""/>
													</td>
												<tr>
													<td class="stab">MAA Replaces Acquisition Agreement</td>
													<td><ezf:inputCheckBox name="mstrReplAquFlg_E1" ezfName="mstrReplAquFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">MAA Replaces Combined Purchase Agreement</td>
													<td><ezf:inputCheckBox name="mstrReplCmbnPrchFlg_E1" ezfName="mstrReplCmbnPrchFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">MAA Replaces Unified Lease Agreement</td>
													<td><ezf:inputCheckBox name="mstrReplLeaseFlg_E1" ezfName="mstrReplLeaseFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Lease Transaction Allowed</td>
													<td><ezf:inputCheckBox name="leaseTrxAllwFlg_E1" ezfName="leaseTrxAllwFlg_E1" value="Y" /></td>
												<tr>
												<tr>
													<td class="stab">Supplemental Terms to Company Standard Forms</td>
													<td>
														<ezf:inputText name="supplTermCmpyStdFrmTxt_E1" ezfName="supplTermCmpyStdFrmTxt_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"35\" maxlength=\"50\""/>
													</td>
												</tr>
											</table>
										</td>
										<td width="550">
											<table border="0" cellpadding="0" cellspacing="0" valign="top">
												<col width="270">
												<col width="280">
												<tr height="28">
													<td class="stab">
														<ezf:inputButton name="CratNewVrsn" value="New Version" htmlClass="pBtn6" otherAttr=" id=\"CratNewVrsn\""/>
														Version
														<ezf:select name="prcTermCondVrsnNum_E1" ezfName="prcTermCondVrsnNum_E1" ezfBlank="1" ezfCodeName="prcTermCondVrsnNum_L1" ezfDispName="prcTermCondVrsnNum_L2" otherEvent1=" onchange=\"sendServer('OnChange_PrcTermCondVrsnNum', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 40px\""/>
													</td>
													<td>
														Status
														<ezf:select name="prcTermCondStsCd_E1" ezfName="prcTermCondStsCd_E1" ezfBlank="1" ezfCodeName="prcTermCondStsCd_L1" ezfDispName="prcTermCondStsDescTxt_L1" otherAttr=" style=\"width: 210px\""/>
													</td>
												</tr>
												<tr>
													<td><b><u>SERVICE & CONTRACT CONDITIONS</u></b></td>
												</tr>
												<tr>
													<td class="stab">After Hrs Bill Rate</td>
													<td>
														<ezf:inputText name="aftHourBillRate_E1" ezfName="aftHourBillRate_E1" value="0.00123" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Response Time Measurement Period</td>
													<td>
														<ezf:select name="rspTmMeasPerCd_E1" ezfName="rspTmMeasPerCd_E1" ezfBlank="1" ezfCodeName="rspTmMeasPerCd_L1" ezfDispName="rspTmMeasPerDescTxt_L1" otherAttr=" style=\"width: 80px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Response Time Commitment</td>
													<td>
														<ezf:inputText name="rspTmComitTxt_E1" ezfName="rspTmComitTxt_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Service ETA Call Required (in Hours)</td>
													<td>
														<ezf:inputText name="svcEtaCallReqHrsNum_E1" ezfName="svcEtaCallReqHrsNum_E1" otherAttr=" size=\"xx\" maxlength=\"xx\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">Toner Type</td>
													<td>
														<ezf:inputText name="tonerTpNm_E1" ezfName="tonerTpNm_E1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Toner Yield</td>
													<td>
														<ezf:inputText name="tonerYieldCnt_E1" ezfName="tonerYieldCnt_E1" value="1,234,567,890" otherAttr=" size=\"13\" maxlength=\"13\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Staples Included Service</td>
													<td><ezf:inputCheckBox name="stplInclSvcFlg_E1" ezfName="stplInclSvcFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Contract Price Type</td>
													<td>
														<ezf:select name="prcContrPrcTpCd_E1" ezfName="prcContrPrcTpCd_E1" ezfBlank="1" ezfCodeName="prcContrPrcTpCd_L1" ezfDispName="prcContrPrcTpDescTxt_L1" otherAttr=" style=\"width: 90px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Daily First Call Guarantee</td>
													<td><ezf:inputCheckBox name="dlyFirstCallGtdFlg_E1" ezfName="dlyFirstCallGtdFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Onsite Tech Included</td>
													<td><ezf:inputCheckBox name="onSiteTechInclFlg_E1" ezfName="onSiteTechInclFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Primary Tech Included</td>
													<td><ezf:inputCheckBox name="primTechInclFlg_E1" ezfName="primTechInclFlg_E1" value="Y" /></td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">imageWARE Escalator</td>
													<td><ezf:inputCheckBox name="iwrEsclFlg_E1" ezfName="iwrEsclFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Max Renewal Rate Increase</td>
													<td>
														<ezf:inputText name="maxRnwIncrAmtRate_E1" ezfName="maxRnwIncrAmtRate_E1" value="1,234.5678" otherAttr=" size=\"24\" maxlength=\"24\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Max Std Annual Increase Percentage</td>
													<td>
														<ezf:inputText name="maxStdAnnIncrPct_E1" ezfName="maxStdAnnIncrPct_E1" value="0.1234" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Early Termination Option</td>
													<td><ezf:inputCheckBox name="erlTrmnOptFlg_E1" ezfName="erlTrmnOptFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Uptime Percentage Guarantee</td>
													<td>
														<ezf:inputText name="upTmGtdPct_E1" ezfName="upTmGtdPct_E1" value="0.1234" otherAttr=" size=\"11\" maxlength=\"11\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Fleet Contracts Allowed</td>
													<td><ezf:inputCheckBox name="fleetContrAllwFlg_E1" ezfName="fleetContrAllwFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Aggregate Contracts Allowed</td>
													<td><ezf:inputCheckBox name="aggrContrAllwFlg_E1" ezfName="aggrContrAllwFlg_E1" value="Y" /></td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td><b><u>REPORTING REQUIREMENTS</u></b></td>
												</tr>
												<tr>
													<td class="stab">Canon Customer QBR Reporting Required</td>
													<td><ezf:inputCheckBox name="custQtlyBizRvwReqFlg_E1" ezfName="custQtlyBizRvwReqFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Canon Standard QBR Reporting Required</td>
													<td><ezf:inputCheckBox name="stdQtlyBizRvwReqFlg_E1" ezfName="stdQtlyBizRvwReqFlg_E1" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">Required Reporting Interval</td>
													<td>
														<ezf:select name="reqRptIntvlCd_E1" ezfName="reqRptIntvlCd_E1" ezfBlank="1" ezfCodeName="reqRptIntvlCd_L1" ezfDispName="reqRptIntvlDescTxt_L1" otherAttr=" style=\"width: 110px\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>&nbsp;</td></tr>
											</table>
										</td>
									</tr>
								</table>
								</div>
							</div>
							</c:when>
							</c:choose>
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
