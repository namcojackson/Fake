<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190123171518 --%>
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
			<input type="hidden" name="pageID" value="NSAL0380Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Renewal & Escalation Details">

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
									<li title = "Renewal & Escalation Details" class="pTab_ON" ><a href="#">Rnwl Rules</a></li>
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
				<br>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<col width="80">
						<col width="90">
						<col width="2" align="center">
						<col width="80">
						<col width="263">
						<col width="80">
						<col width="90">
						<col width="2" align="center">
						<col width="80">
						<col width="263">
						<col width="">
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchSerFrom" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchSerFrom" >Serial # From</ezf:anchor></td>
							<td><ezf:inputText name="serNum_HF" ezfName="serNum_HF" value="HHT0254" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
							<td class="stab">-</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchSerThru" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchSerThru" >Serial # Thru</ezf:anchor></td>
							<td><ezf:inputText name="serNum_HT" ezfName="serNum_HT" value="HHT0254" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchIBFrom" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchIBFrom" >IB ID From</ezf:anchor></td>
							<td><ezf:inputText name="svcMachMstrPk_HF" ezfName="svcMachMstrPk_HF" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td class="stab">-</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchIBThru" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchIBThru" >IB ID Thru</ezf:anchor></td>
							<td><ezf:inputText name="svcMachMstrPk_HT" ezfName="svcMachMstrPk_HT" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<hr width="98%" align="left">
					<table width="1107" border="0" cellpadding="0" cellspacing="0">
						<col width="125">
						<col width="40">
						<col width="85">
						<col width="70">
						<col width="110">
						<col width="50">
						<col width="80">
						<col width="400	">
						<col width="70">
						<tr>
							<td class="stab">New Renewal Type</td>
							<td colspan="3"><ezf:select name="contrAutoRnwTpCd_H" ezfName="contrAutoRnwTpCd_H" ezfBlank="1" ezfCodeName="contrAutoRnwTpCd_PL" ezfDispName="contrAutoRnwTpDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChangeNewRnwTpCd')\"" otherAttr=" style=\"width:190;\""/></td>
							<td class="stab">New Base %</td>
							<td><ezf:inputText name="basePrcUpRatio_H" ezfName="basePrcUpRatio_H" value="123" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
							<td class="stab">Reason Code</td>
							<td colspan="2"><ezf:select name="svcMemoRsnCd_H" ezfName="svcMemoRsnCd_H" ezfBlank="1" ezfCodeName="svcMemoRsnCd_PL" ezfDispName="svcMemoRsnDescTxt_PL" otherAttr=" style=\"width:188;\""/></td>
						</tr>
						<tr>
							<td class="stab">New Renewal Method</td>
							<td colspan="2"><ezf:select name="rnwPrcMethCd_H" ezfName="rnwPrcMethCd_H" ezfBlank="1" ezfCodeName="rnwPrcMethCd_PL" ezfDispName="rnwPrcMethDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChangeNewRnwlMethod')\"" otherAttr=" style=\"width:122;\""/></td>
							<td></td>
							<td class="stab">New Overage %</td>
							<td><ezf:inputText name="mtrPrcUpRatio_H" ezfName="mtrPrcUpRatio_H" value="123" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
							<td rowspan="2" class="stab">Notes</td>
							<td rowspan="2"><ezf:textArea name="svcCmntTxt_H" ezfName="svcCmntTxt_H" otherAttr=" cols=\"60\" rows=\"4\""/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="stab">New # Days</td>
							<td><ezf:inputText name="befEndRnwDaysAot_H" ezfName="befEndRnwDaysAot_H" value="123" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
							<td  class="stab" align="right">Max %&nbsp;</td>
							<td><ezf:inputText name="maxPrcUpRatio_H" ezfName="maxPrcUpRatio_H" value="123" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
							<td class="stab">New Max # Renews</td>
							<td><ezf:inputText name="maxContrRnwCnt_H" ezfName="maxContrRnwCnt_H" value="123" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2"><ezf:inputButton name="AplToAll" value="Apply To All" htmlClass="pBtn6" /></td>
						</tr>
					</table>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NSAL0380.NSAL0380BMsg" %>
<%@ page import="business.servlet.NSAL0380.NSAL0380_ABMsg" %>
<%  NSAL0380BMsg bMsg = (NSAL0380BMsg)databean.getEZDBMsg(); %>

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
								<table border="0" cellpadding="0" cellspacing="0">
									<col >
									<tr>
										<td>
											<div style="float:left;">
											<div id="TopTBL" style="width:1114; display:block; overflow:hidden; margin:0px; padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'TBL' ));">
												<table border="1" cellpadding="1" cellspacing="0" align="left" id="AHEAD" style="width:1260;">
													<col width="16" align="center"></col>	<!-- Expansion/Contraction -->
													<col width="71" align="center"></col>	<!-- Contract# -->
													<col width="25" align="center"></col>	<!-- Contract# Check -->
													<col width="75" align="center"></col>	<!-- Serial# -->
													<col width="66" align="center"></col>	<!-- Machine Master -->
													<col width="80" align="center"></col>	<!-- Service Program -->
													<col width="25" align="center"></col>	<!-- Serial# Check -->
													<col width="52" align="center"></col>	<!-- Base/Overage -->
													<col width="73" align="center"></col>	<!-- Original Start -->
													<col width="73" align="center"></col>	<!-- Original End -->
													<col width="157" align="center"></col>	<!-- Renewal Type -->
													<col width="124" align="center"></col>	<!-- Renewal Method -->
													<col width="40" align="center"></col>	<!-- Renewal # Days -->
													<col width="40" align="center"></col>	<!-- Renewal Max# Renew -->
													<col width="42" align="center"></col>	<!-- Renewal Max % -->
													<col width="42" align="center"></col>	<!-- Renewal Base % -->
													<col width="42" align="center"></col>	<!-- Renewal Overage % -->
													<col width="40" align="center"></col>	<!-- Times Renewed -->
													<col width="32" align="center"></col>	<!-- NoteFlag -->
													<col width="72" align="center"></col>	<!-- Return Message -->
													<tr>
														<td class="pClothBs" rowspan="2">&nbsp;</td>
														<td class="pClothBs" rowspan="2">Contract#</td>
														<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('SelectAllContract')" /></td>
														<td class="pClothBs" rowspan="2">Serial#</td>
														<td class="pClothBs" rowspan="2">Machine Master</td>
														<td class="pClothBs" rowspan="2">Service Program</td>
														<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
														<td class="pClothBs" rowspan="2">Base /<br>Overage</td>
														<td class="pClothBs" rowspan="2">Original<br>Start</td>
														<td class="pClothBs" rowspan="2">Original<br>End</td>
														<td class="pClothBs" colspan="8">Renewal</td>
														<td class="pClothBs" rowspan="2">Notes</td>
														<td class="pClothBs" rowspan="2">Return Message</td>
													</tr>
													<tr>
														<td class="pClothBs">Type</td>
														<td class="pClothBs">Method</td>
														<td class="pClothBs">#Days</td>
														<td class="pClothBs">Max#<br>Renew</td>
														<td class="pClothBs">Max%</td>
														<td class="pClothBs">Base%</td>
														<td class="pClothBs">Ovrg%</td>
														<td class="pClothBs">Times<br>Rnwed</td>
													</tr>
												</table>
											</div>
											<div id="TBL" style="overflow:scroll; height:360; width:1131; display:block; word-break: break-all;"  onScroll="synchroScrollLeft( this.id, new Array( 'TopTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="width:1260;">
													<col width="16" align="center"></col>	<!-- Expansion/Contraction -->
													<col width="71"></col>					<!-- Contract# -->
													<col width="25" align="center"></col>	<!-- Contract# Check -->
													<col width="75"></col>					<!-- Serial# -->
													<col width="66" align="right"></col>	<!-- Machine Master -->
													<col width="80" align="center"></col>	<!-- Service Program -->
													<col width="25" align="center"></col>	<!-- Base/Overage Check -->
													<col width="52"></col>					<!-- Base/Overage -->
													<col width="73" align="center"></col>	<!-- Original Start -->
													<col width="73" align="center"></col>	<!-- Original End -->
													<col width="157"></col>					<!-- Renewal Type -->
													<col width="124"></col>					<!-- Renewal Method -->
													<col width="40" align="center"></col>	<!-- Renewal # Days -->
													<col width="40" align="center"></col>	<!-- Renewal Max# Renew -->
													<col width="42" align="center"></col>	<!-- Renewal Max % -->
													<col width="42" align="center"></col>	<!-- Renewal Base % -->
													<col width="42" align="center"></col>	<!-- Renewal Overage % -->
													<col width="40" align="right"></col>	<!-- Times Renewed -->
													<col width="32" align="center"></col>	<!-- NoteFlag -->
													<col width="72" align="center"></col>	<!-- Return Message -->
													<% int i = 0; %>
													<ezf:row ezfHyo="A">
														<% NSAL0380_ABMsg abMsg = bMsg.A.no(i++); %>
														<tr>
															<td>
																<% boolean isDplyCtrlFlg = "Y".equals(abMsg.xxDplyCtrlFlg_A.getValue()); %>
																<% if(!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
																	<% boolean isSmryLineFlg = "Y".equals(abMsg.xxSmryLineFlg_A.getValue()); %>
																	<a id="xxSmryLineFlg_A#{EZF_ROW_NUMBER}" href="#" onclick="clickImg(this, '{EZF_ROW_NUMBER}'); return false;">
																		<% if(isSmryLineFlg)  out.println("<img src=\"./img/biz/downarrow2.png\"  value=\"Expansion\">"); %>
																		<% if(!isSmryLineFlg) out.println("<img src=\"./img/biz/rightarrow2.png\" value=\"Contraction\">"); %>
																	</a>
																<% if(!isDplyCtrlFlg) out.println("</span>"); %>
															</td>
															<td>
																<ezf:inputHidden name="dsContrPk_A" ezfName="dsContrPk_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="prntDsContrDtlPk_A" ezfName="prntDsContrDtlPk_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<% boolean isLv1 = "1".equals(abMsg.xxDplyByCtrlAncrLvlCd_A.getValue()); %>
																<% boolean isLv2 = "2".equals(abMsg.xxDplyByCtrlAncrLvlCd_A.getValue()); %>
																<% boolean isProtectedA1 = abMsg.xxChkBox_A1.isInputProtected(); %>
																<% boolean isProtectedA2 = abMsg.xxChkBox_A2.isInputProtected(); %>
																<% if(isProtectedA1) out.println("<span style='visibility:hidden'>"); %>
																<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
																<% if(isProtectedA1) out.println("</span>"); %>
															<td>
																<ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:label name="svcMachMstrPk_A1" ezfName="svcMachMstrPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
															</td>
															<td>
																<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="RENTAL SUPPLY INCLUSIVE SERVIC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"250\""/>
															</td>
															<td>
																<% if(isProtectedA2) out.println("<span style='visibility:hidden'>"); %>
																<ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" />
																<% if(isProtectedA2) out.println("</span>"); %>
															</td>
															<td><ezf:label name="dsContrBaseUsgNm_A" ezfName="dsContrBaseUsgNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="contrEffFromDt_A" ezfName="contrEffFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="contrEffThruDt_A" ezfName="contrEffThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<ezf:select name="contrAutoRnwTpCd_A" ezfName="contrAutoRnwTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrAutoRnwTpCd_PL" ezfDispName="contrAutoRnwTpDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChangeRnwTpCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:160;\" id=\"OnChangeRnwTpCd#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
																<ezf:select name="rnwPrcMethCd_A" ezfName="rnwPrcMethCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rnwPrcMethCd_PL" ezfDispName="rnwPrcMethDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChangeRnwlMethod', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:122;\" id=\"OnChangeRnwlMethod#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
																
																<% if(isLv1 || isLv2) { %>
																<ezf:inputText name="befEndRnwDaysAot_A" ezfName="befEndRnwDaysAot_A" value="123" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeBefEndRnwDays', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"4\" maxlength=\"3\" ezftoupper=\"\" id=\"OnChangeBefEndRnwDays#{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputText name="befEndRnwDaysAot_A" ezfName="befEndRnwDaysAot_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"3\" ezftoupper=\"\""/>
																<% } %>
															</td>
															<td>
																<% if(isLv1 || isLv2) { %>
																<ezf:inputText name="maxContrRnwCnt_A" ezfName="maxContrRnwCnt_A" value="123" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeMaxRnwCnt', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"4\" maxlength=\"3\" ezftoupper=\"\" id=\"OnChangeMaxRnwCnt#{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputText name="maxContrRnwCnt_A" ezfName="maxContrRnwCnt_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"3\" ezftoupper=\"\""/>
																<% } %>
															</td>
															<td>
																<% if(isLv1 || isLv2) { %>
																<ezf:inputText name="maxPrcUpRatio_A" ezfName="maxPrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeMaxPrcUpRatio', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" id=\"OnChangeMaxPrcUpRatio#{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputText name="maxPrcUpRatio_A" ezfName="maxPrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
																<% } %>
															</td>
															<td>
																<% if(isLv1 || isLv2) { %>
																<ezf:inputText name="basePrcUpRatio_A" ezfName="basePrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeBasePrcUpRatio', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" id=\"OnChangeBasePrcUpRatio#{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputText name="basePrcUpRatio_A" ezfName="basePrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
																<% } %>
															</td>
															<td>
																
																<% if(isLv1 || isLv2) { %>
																<ezf:inputText name="mtrPrcUpRatio_A" ezfName="mtrPrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeMtrPrcUpRatio', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" id=\"OnChangeMtrPrcUpRatio#{EZF_ROW_NUMBER}\""/>
																<% } else { %>
																<ezf:inputText name="mtrPrcUpRatio_A" ezfName="mtrPrcUpRatio_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
																<% } %>
															</td>
															<td><ezf:label name="contrRnwTotCnt_A" ezfName="contrRnwTotCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<% boolean yFlg = "Y".equals(abMsg.xxCntDplyFlg_A.getValue()); %>
																<% if(!yFlg) out.println("<span style='visibility:hidden'>"); %>
																<ezf:anchor name="" ezfName="" ezfHyo="MoveWin_MemoEntry" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_MemoEntry', '{EZF_ROW_NUMBER}" >Y</ezf:anchor>
																<% if(!yFlg) out.println("</span>"); %>
															</td>
															<td><ezf:inputText name="xxDsMsgEntryTxt_A" ezfName="xxDsMsgEntryTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr>
 															<td><img src="./img/biz/rightarrow2.png"></td>
															<td><label>WTY250311</label></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>PTR01922</label></td>
															<td><label>10001023</label></td>
															<td><input type="text" size="10" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC"></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>OVERAGE</label></td>
															<td><label>01/01/2014</label></td>
															<td><label>01/01/2015</label></td>
															<td><select style="width:160;"><option checked>Manual Renew Base Overage</option></select></td>
															<td><select style="width:122;"><option checked>Markup Percent</option></select></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><label>1234</label></td>
															<td><a href="#">Y</a></td>
															<td><input type="text" class="pPro" size="9"></td>
														</tr>
														<tr>
 															<td><img src="./img/biz/rightarrow2.png"></td>
															<td><label>7942-5</label></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>0822309085</label></td>
															<td><label>10001023</label></td>
															<td><input type="text" size="10" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC"></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>BASE</label></td>
															<td><label>01/01/2014</label></td>
															<td><label>01/01/2015</label></td>
															<td><select style="width:160;"><option checked>Manual Renew-Base-Overage</option></select></td>
															<td><select style="width:122;"><option checked>Model Percent</option></select></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><label>1234</label></td>
															<td><a href="#">Y</a></td>
															<td><input type="text" class="pPro" size="9"></td>
														</tr>
														<tr>
 															<td><img src="./img/biz/rightarrow2.png"></td>
															<td><label>9073</label></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>9024108832</label></td>
															<td><label>10001023</label></td>
															<td><input type="text" size="10" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC"></td>
															<td><input type="checkbox" value="Y"></td>
															<td><label>OVERAGE</label></td>
															<td><label>01/01/2014</label></td>
															<td><label>01/01/2015</label></td>
															<td><select style="width:160;"><option checked>Manual Renew-Overage-Only</option></select></td>
															<td><select style="width:122;"><option checked>Markup Percent</option></select></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="3" value="123"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><input type="text" size="4" maxlength="4" value="1234"></td>
															<td><label>1234</label></td>
															<td><a href="#">Y</a></td>
															<td><input type="text" class="pPro" size="9"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
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

<style TYPE="text/css">
<!--
	a img{border-style:none;}
-->
</style>

<script type="text/javascript">
	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>


<%-- **** Task specific area ends here **** --%>
