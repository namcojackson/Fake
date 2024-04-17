<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160311200858 --%>
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

<%@ page import="business.servlet.NLCL0090.*" %>
<% NLCL0090BMsg scrnMsg = (NLCL0090BMsg)databean.getEZDBMsg(); %>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NLCL0090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Item Change Entry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
					<%-- include S21BusinessProcessTAB --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					
					<table border="0" width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0">
									<col width="30">
									<col width="80">
									<col width="45">
									<col width="150">
									<col width="80">
									<col width="45">
									<col width="150">
									<col width="20">
									<col width="100">
									<col width="145">
									<col width="30">
									<col width="145">
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="xxLinkProt_P1" ezfName="xxLinkProt_P1" ezfEmulateBtn="1" ezfGuard="OpenWin_RetailWH_Info" >Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="WWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="locNm_P1" ezfName="locNm_P1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"20\" maxlength=\"360\" tabindex=\"-1\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkProt_P2" ezfName="xxLinkProt_P2" ezfEmulateBtn="1" ezfGuard="OpenWin_RetailSubWH_Info" >Sub Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="WWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="locNm_P2" ezfName="locNm_P2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"20\" maxlength=\"360\" tabindex=\"-1\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Transaction Number</td>
										<td><ezf:inputText name="invtyOrdNum" ezfName="invtyOrdNum" value="WWWWWWWW" otherAttr=" size=\"19\" maxlength=\"8\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<td colspan="2" align="left"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Location Status</td>
										<td colspan="2">
											<% if (scrnMsg.locStsCd_H1.no(1).isClear()) {%>
												<ezf:select name="locStsCd" ezfName="locStsCd" ezfCodeName="locStsCd_H1" ezfDispName="xxLocStsTxt_P1" otherAttr=" style=\"width:190px;\""/>
											<% } else { %>
												<ezf:select name="locStsCd" ezfName="locStsCd" ezfBlank="1" ezfCodeName="locStsCd_H1" ezfDispName="xxLocStsTxt_P1" otherAttr=" style=\"width:190px;\""/>
											<% } %>
										</td>
										<td class="stab">Stock Status</td>
										<td colspan="2">
											<% if (scrnMsg.stkStsCd_H1.no(1).isClear()) {%>
												<ezf:select name="stkStsCd" ezfName="stkStsCd" ezfCodeName="stkStsCd_H1" ezfDispName="xxStkStsTxt_P1" otherAttr=" style=\"width:190px;\""/>
											<% } else { %>
												<ezf:select name="stkStsCd" ezfName="stkStsCd" ezfBlank="1" ezfCodeName="stkStsCd_H1" ezfDispName="xxStkStsTxt_P1" otherAttr=" style=\"width:190px;\""/>
											<% } %>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Transaction Status</td>
										<td><ezf:inputText name="invtyOrdStsDescTxt" ezfName="invtyOrdStsDescTxt" value="Closed" otherAttr=" size=\"19\" maxlength=\"50\" tabindex=\"-1\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Comment</td>
										<td colspan="5">
											<ezf:inputText name="firstInvtyOrdCmntTxt" ezfName="firstInvtyOrdCmntTxt" value="TEST DATA ONE" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Shipping Order</td>
										<td><ezf:inputText name="soNum" ezfName="soNum" value="WWWWWWWW" otherAttr=" size=\"19\" maxlength=\"15\" tabindex=\"-1\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td colspan="5">
											<ezf:inputText name="scdInvtyOrdCmntTxt" ezfName="scdInvtyOrdCmntTxt" value="TEST DATA TWO" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td colspan="5">
											<ezf:inputText name="thirdInvtyOrdCmntTxt" ezfName="thirdInvtyOrdCmntTxt" value="TEST DATA THREE" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Transaction Submit</td>
										<td><ezf:inputText name="xxTsDsp19Txt_SB" ezfName="xxTsDsp19Txt_SB" value="03/02/2016 10:50:20" otherAttr=" size=\"19\" maxlength=\"19\" tabindex=\"-1\""/></td>
										<td class="stab">Close</td>
										<td><ezf:inputText name="xxTsDsp19Txt_CL" ezfName="xxTsDsp19Txt_CL" value="03/02/2016 15:23:14" otherAttr=" size=\"19\" maxlength=\"19\" tabindex=\"-1\""/></td>
									</tr>
								</table>
								<hr>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NLCL0090.NLCL0090BMsg" %>
<%@ page import="business.servlet.NLCL0090.NLCL0090_ABMsg" %>
<%@ page import="business.servlet.NLCL0090.NLCL0090_BBMsg" %>
<%  NLCL0090BMsg bMsg = (NLCL0090BMsg)databean.getEZDBMsg(); %>
								<table cellpadding="0" cellspacing="0" align="center" border="0">
									<tr>
										<td>
											<fieldset>
												<legend>
													<table>
														<tr>
															<td>From Items</td>
														</tr>
													</table>
												</legend>
												<table cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table cellpadding="1" cellspacing="0" border="0">
																<col width="104" align="center">
																<col width="30" align="center">
																<col width="104" align="center">
																<col width="60"  align="center">
																<col width="95" align="center">
																<col width="104" align="center">
																<col width="50"  align="right">
																<tr>
																	<td align="center" class="stab"><ezf:anchor name="mdseCd_AF" ezfName="mdseCd_AF" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWinMDSECode_From" >Item Number</ezf:anchor></td>
																	<td>&nbsp;</td>
																	<td align="center" class="stab">Item Description</td>
																	<td align="center" class="stab">Quantity</td>
																	<td align="center" class="stab">Current Available</td>
																	<td align="center" class="stab">Serial Number</td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td><ezf:inputText name="mdseCd_HF" ezfName="mdseCd_HF" value="1862B001AA" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																	<td><ezf:inputButton name="Search_MDSEInfo_From" value=">>" htmlClass="pBtn0" /></td>
																	<td><ezf:inputText name="mdseDescShortTxt_HF" ezfName="mdseDescShortTxt_HF" value="SD1000DIGITAL ELPH(SLN)" otherAttr=" size=\"13\" maxlength=\"250\" tabindex=\"-1\""/></td>
																	<td><ezf:inputText name="invtyQty_HF" ezfName="invtyQty_HF" value="999,999" otherAttr=" size=\"7\" maxlength=\"7\" htmlclass=\"pTxtR\""/></td>
																	<td><ezf:inputText name="invtyAvalQty_HF" ezfName="invtyAvalQty_HF" value="123,456,789" otherAttr=" size=\"11\" maxlength=\"11\" htmlclass=\"pTxtR\" tabindex=\"-1\""/></td>
																	<td><ezf:inputText name="serNum_HF" ezfName="serNum_HF" value="xxxxxxx" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																	<td><ezf:inputButton name="Add_Detail_From" value="Add" htmlClass="pBtn3" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table cellpadding="1" cellspacing="0" border="0">
																<col width="">
																<tr>
																	<td valign="top">
																		<div style="overflow-y:none; overflow-x:hidden; width:581;">
																			<table border="1" cellpadding="1" cellspacing="0" width="581">
																				<col width="25" align="center">
																				<col width="24" align="center">
																				<col width="112" align="center">
																				<col width="112" align="center">
																				<col width="57" align="center">
																				<col width="95" align="center">
																				<col width="112" align="center">
																				<tr height="28">
																					<td class="pClothBs">D</td>
																					<td class="pClothBs">Line</td>
																					<td class="pClothBs">Item Number</td>
																					<td class="pClothBs">Item Description</td>
																					<td class="pClothBs">Quantity</td>
																					<td class="pClothBs">Current Available</td>
																					<td class="pClothBs">Serial Number</td>
																				</tr>
																			</table>
																		</div>
																		<div style="overflow-y:auto; height:318;">
																			<table border="1" cellpadding="1" cellspacing="0" width="581" id="A">
																				<col width="25" align="center">
																				<col width="24" align="center">
																				<col width="112" align="left">
																				<col width="112" align="left">
																				<col width="57" align="left">
																				<col width="95" align="left">
																				<col width="112" align="left">
																				<% int indexA = 0; %>
																				<ezf:row ezfHyo="A">
																				<tr height="28">
																					<td><ezf:inputCheckBox name="xxChkBox_DF" ezfName="xxChkBox_DF" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																					<% if ("Y".equals(bMsg.A.no(indexA).dispFlg_DF.getValue())) { %>
																					<td><ezf:label name="invtyOrdLineNum_DF" ezfName="invtyOrdLineNum_DF" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="mdseCd_DF" ezfName="mdseCd_DF" value="1862B001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\""/></td>
																					<td><ezf:inputText name="mdseDescShortTxt_DF" ezfName="mdseDescShortTxt_DF" value="SD1000DIGITAL" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"250\""/></td>
																					<td><ezf:inputText name="invtyQty_DF" ezfName="invtyQty_DF" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" htmlclass=\"pTxtR\""/></td>
																					<td><ezf:inputText name="invtyAvalQty_DF" ezfName="invtyAvalQty_DF" value="234,567,890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\""/></td>
																					<% } else { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<% } %>
																					<td><ezf:inputText name="serNum_DF" ezfName="serNum_DF" value="xxxxxx" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				</tr>
																				<% indexA++; %>
																				</ezf:row>
																				<ezf:skip>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td><label ezfout name="invtyOrdLineNum_DF" ezfname="invtyOrdLineNum_DF" ezfhyo="A">2</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="16" value="1862B001AA" name="mdseCd_DF" ezfname="mdseCd_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="250" value="SD1000DIGITAL" name="mdseDescShortTxt_DF" ezfname="mdseDescShortTxt_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value="3" htmlClass="pTxtR" name="invtyQty_DF" ezfname="invtyQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="13" maxlength="19" value="234,567,890" name="invtyAvalQty_DF" ezfname="invtyAvalQty_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx" name="serNum_DF" ezfname="serNum_DF" ezfhyo="A"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td><label ezfout name="invtyOrdLineNum_DF" ezfname="invtyOrdLineNum_DF" ezfhyo="A">3</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="16" value="1862B001AA" name="mdseCd_DF" ezfname="mdseCd_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="250" value="SD1000DIGITAL" name="mdseDescShortTxt_DF" ezfname="mdseDescShortTxt_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value="999,999" htmlClass="pTxtR" name="invtyQty_DF" ezfname="invtyQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="13" maxlength="19" value="234,567,890" name="invtyAvalQty_DF" ezfname="invtyAvalQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx" name="serNum_DF" ezfname="serNum_DF" ezfhyo="A"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td><label ezfout name="invtyOrdLineNum_DF" ezfname="invtyOrdLineNum_DF" ezfhyo="A">4</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="16" value="1862B001AA" name="mdseCd_DF" ezfname="mdseCd_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="250" value="SD1000DIGITAL" name="mdseDescShortTxt_DF" ezfname="mdseDescShortTxt_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value="999,999" htmlClass="pTxtR" name="invtyQty_DF" ezfname="invtyQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="13" maxlength="19" value="234,567,890" name="invtyAvalQty_DF" ezfname="invtyAvalQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx" name="serNum_DF" ezfname="serNum_DF" ezfhyo="A"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td><label ezfout name="invtyOrdLineNum_DF" ezfname="invtyOrdLineNum_DF" ezfhyo="A">5</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="16" value="1862B001AA" name="mdseCd_DF" ezfname="mdseCd_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="250" value="SD1000DIGITAL" name="mdseDescShortTxt_DF" ezfname="mdseDescShortTxt_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value="4" htmlClass="pTxtR" name="invtyQty_DF" ezfname="invtyQty_DF" ezfhyo="A"></td>
																					<td><input type="text" readonly class="pPro" size="13" maxlength="19" value="234,567,890" name="invtyAvalQty_DF" ezfname="invtyAvalQty_DF" ezfhyo="A"></td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx" name="serNum_DF" ezfname="serNum_DF" ezfhyo="A"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_DF" ezfname="xxChkBox_DF" ezfhyo="A"></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><input type="text" class="pHsu" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>11</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>12</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>13</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				</ezf:skip>
																			</table>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td><ezf:inputButton name="Del_Detail_From" value="Delete Row" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>&nbsp;</td>
										<td>
											<fieldset>
												<legend>
													<table>
														<tr>
															<td>To Items</td>
														</tr>
													</table>
												</legend>
												<table cellpadding="0" cellspacing="0">
													<col width="">
													<tr>
														<td>
															<table cellpadding="1" cellspacing="0">
																<col width="104" align="center">
																<col width="30" align="center">
																<col width="104" align="center">
																<col width="60"  align="center">
																<col width="104"  align="center">
																<col width="50"  align="right">
																<tr>
																	<td class="stab"><ezf:anchor name="mdseCd_AT" ezfName="mdseCd_AT" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWinMDSECode_To" >Item Number</ezf:anchor></td>
																	<td>&nbsp;</td>
																	<td class="stab">Item Description</td>
																	<td class="stab">Quantity</td>
																	<td class="stab">Serial Number</td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td><ezf:inputText name="mdseCd_HT" ezfName="mdseCd_HT" value="1862B001AA" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																	<td><ezf:inputButton name="Search_MDSEInfo_To" value=">>" htmlClass="pBtn0" /></td>
																	<td><ezf:inputText name="mdseDescShortTxt_HT" ezfName="mdseDescShortTxt_HT" value="SD1000DIGITAL ELPH(SLN)" otherAttr=" size=\"13\" maxlength=\"250\" tabindex=\"-1\""/></td>
																	<td><ezf:inputText name="invtyQty_HT" ezfName="invtyQty_HT" value="999,999" otherAttr=" size=\"7\" maxlength=\"7\" htmlclass=\"pTxtR\""/></td>
																	<td><ezf:inputText name="serNum_HT" ezfName="serNum_HT" value="xxxxxxx" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																	<td><ezf:inputButton name="Add_Detail_To" value="Add" htmlClass="pBtn3" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table cellpadding="1" cellspacing="0">
																<col width="">
																<tr>
																	<td valign="top">
																		<div style="overflow-y:none; overflow-x:hidden; width:486;">
																			<table border="1" cellpadding="1" cellspacing="0" width="486">
																				<col width="25" align="center">
																				<col width="24" align="center">
																				<col width="112" align="center">
																				<col width="112" align="center">
																				<col width="57" align="center">
																				<col width="112" align="center">
																				<tr height="28">
																					<td class="pClothBs">D</td>
																					<td class="pClothBs">Line</td>
																					<td class="pClothBs">Item Number</td>
																					<td class="pClothBs">Item Description</td>
																					<td class="pClothBs">Quantity</td>
																					<td class="pClothBs">Serial Number</td>
																				</tr>
																			</table>
																		</div>
																		<div style="overflow-y:auto; height:318;">
																			<table border="1" cellpadding="1" cellspacing="0" width="486" id="B">
																				<col width="25" align="center">
																				<col width="24" align="center">
																				<col width="112" align="left">
																				<col width="112" align="left">
																				<col width="57" align="left">
																				<col width="112" align="left">
																				<% int indexB = 0; %>
																				<ezf:row ezfHyo="B">
																				<tr height="28">
																					<td><ezf:inputCheckBox name="xxChkBox_DT" ezfName="xxChkBox_DT" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																					<% if ("Y".equals(bMsg.B.no(indexB).dispFlg_DT.getValue())) { %>
																					<td><ezf:label name="invtyOrdLineNum_DT" ezfName="invtyOrdLineNum_DT" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="mdseCd_DT" ezfName="mdseCd_DT" value="1862B001AA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\""/></td>
																					<td><ezf:inputText name="mdseDescShortTxt_DT" ezfName="mdseDescShortTxt_DT" value="SD1000DIGITAL" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"250\""/></td>
																					<td><ezf:inputText name="invtyQty_DT" ezfName="invtyQty_DT" value="100" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" htmlclass=\"pTxtR\""/></td>
																					<% } else { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<% } %>
																					<td><ezf:inputText name="serNum_DT" ezfName="serNum_DT" value="xxxxxx" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				</tr>
																				<% indexB++; %>
																				</ezf:row>
																				<ezf:skip>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>2</label></td>
																					<td><label ezfout>WWWWWWWWWW12</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWW30"></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value="999,999"></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>3</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>4</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>5</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>6</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>7</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>8</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>9</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>10</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>11</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>12</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				<tr height="28">
																					<td><input type="checkbox" class="" value="Y"></td>
																					<td><label ezfout>13</label></td>
																					<td><label ezfout>&nbsp;</label></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value=""></td>
																					<td><input type="text" class="pHsu pTxtR" size="7" maxlength="7" value=""></td>
																					<td><input type="text" readonly class="pPro" size="15" maxlength="30" value="xxxxxx"></td>
																				</tr>
																				</ezf:skip>
																			</table>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td><ezf:inputButton name="Del_Detail_To" value="Delete Row" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
<%-- ######################################## FOOTER ######################################## --%>
						 	</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
