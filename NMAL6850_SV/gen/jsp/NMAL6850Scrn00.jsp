<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200229160233 --%>
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
			<input type="hidden" name="pageID" value="NMAL6850Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supplier Search">

			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
								<tr>
									<td width="1070px" height="28px" valign="bottom">
										<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
											<tr title="Supplier Search Screen">
												<td width="107px" align="center" class="same">Supplier</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>

							<div class="pTab_BODY">
								<table  style="margin-left:5; margin-top:0;">
									<col width="" align="left">
									<tr>
										<td>
											<table border="0">
												<col width="100" align="left">
												<col width="60"  align="left">
												<col width="5"   align="left">
												<col width="88"  align="left">
												<col width="150" align="left">
												<col width="5"   align="left">
												<col width="80"  align="left">
												<col width="394" align="left">
												<col width="150" align="left">

												<tr height="20">
													<td class="stab">Supplier Number(*)</td>
													<td><ezf:inputText name="prntVndCd_H" ezfName="prntVndCd_H" value="1284147" otherAttr=" size=\"10\" maxlength=\"9\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Supplier Name(*)</td>
													<td><ezf:inputText name="splyNm_H" ezfName="splyNm_H" value="AZERTY INC." otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab"><ezf:anchor name="xxLinkAncr_ST" ezfName="xxLinkAncr_ST" ezfEmulateBtn="1" ezfGuard="OpenWin_SupplierType" >Supplier Type(*)</ezf:anchor></td>
													<td><ezf:inputText name="prntVndTpDescTxt_H" ezfName="prntVndTpDescTxt_H" value="CANON COMPANY" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
													<td>
														<ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" />
														<ezf:inputButton name="OnClick_New" value="New" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

							<%-- =============== PAGING =============== --%>
							<table width="100%">
								<tr align="right">
									<td width="1108px">
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
							<ezf:skip>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
								<col width="1106px" align="right">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
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
												<td class="pOut">20</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td> </td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:5px;">
								<%-- =============== TABLE HEADER =============== --%>
								<tr>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="110" align="center">
											<col width="204" align="center">
											<col width="200" align="center">
											<col width="110" align="center">
											<col width="90"  align="center">
											<col width="130" align="center">
											<col width="110" align="center">
											<col width="150" align="center">
											<tr height="28">
												<td class="pClothBs">Supplier Number</td>
												<td class="pClothBs">Supplier Name</td>
												<td class="pClothBs">Supplier Type</td>
												<td class="pClothBs">TaxPayer Reg No</td>
												<td class="pClothBs">Inactive On</td>
												<td class="pClothBs">Payment Terms</td>
												<td class="pClothBs">Payment Method</td>
												<td class="pClothBs">ARCS Supplier Number</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td style="vertical-align:top;">
										<div style="overflow-x:auto; overflow-y:scroll; height:450;">
											<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
												<col width="110"  align="left">
												<col width="204"  align="center">
												<col width="200"  align="center">
												<col width="110"  align="center">
												<col width="90"   align="left">
												<col width="130"  align="center">
												<col width="110"  align="center">
												<col width="150"  align="center">
												<ezf:row ezfHyo="A">
													<tr height="28">
														<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" ><ezf:label name="prntVndCd_A" ezfName="prntVndCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:inputText name="prntVndNm_A" ezfName="prntVndNm_A" value="AZERTY, A DIVISION OF UNITED STATIONERS SUPPLY CO." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="prntVndTpDescTxt_A" ezfName="prntVndTpDescTxt_A" value="VENDOR" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="taxPayerRgNum_A" ezfName="taxPayerRgNum_A" value="75-2770316" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
														<td><ezf:label name="inacDt_A" ezfName="inacDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="vndPmtTermDescTxt_A" ezfName="vndPmtTermDescTxt_A" value="2/CWO,NET 30" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="vndPmtMethDescTxt_A" ezfName="vndPmtMethDescTxt_A" value="Check" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="arcsSplyNum_A" ezfName="arcsSplyNum_A" value="666926" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"30\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="28">
														<td><a href="#" onclick="sendServer('OpenWin_Supplier')" ezfhyo="A"><label ezout name="prntVndCd_A" ezfname="prntVndCd_A" ezfhyo="A">8037</label></a></td>
														<td><input type="text" class="pPro" size="27" maxlength="30" value="AZERTY" name="splyNm_A" ezfname="splyNm_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="26" maxlength="30" value="VENDOR" name="splyTpDescTxt_A" ezfname="splyTpDescTxt_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="13" maxlength="30" value="75-2770317" name="taxPayerRgNum_A" ezfname="taxPayerRgNum_A" ezfhyo="A"></td>
														<td><label ezfout name="inacDt_A" ezfname="inacDt_A" ezfhyo="A">&nbsp;</label></td>
														<td><input type="text" class="pPro" size="16" maxlength="30" value="5/0,2/30NET 60" name="vndPmtMethDescTxt_A" ezfname="vndPmtMethDescTxt_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="13" maxlength="30" value="CLEANING" name="vndPmtMethDescTxt_A" ezfname="vndPmtMethDescTxt_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="19" maxlength="30" value="666927" name="arcsSplyNum_A" ezfname="arcsSplyNum_A" ezfhyo="A"></td>
													</tr>
													<tr height="28">
														<td><a href="#" onclick="sendServer('OpenWin_Supplier')" ezfhyo="A"><label ezout name="prntVndCd_A" ezfname="prntVndCd_A" ezfhyo="A">1284147</label></a></td>
														<td><input type="text" class="pPro" size="27" maxlength="30" value="AZERTY INC." name="splyNm_A" ezfname="splyNm_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="26" maxlength="30" value="VENDOR" name="splyTpDescTxt_A" ezfname="splyTpDescTxt_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="13" maxlength="30" value="75-2770318" name="taxPayerRgNum_A" ezfname="taxPayerRgNum_A" ezfhyo="A"></td>
														<td><label ezfout name="inacDt_A" ezfname="inacDt_A" ezfhyo="A">05/15/2015</label></td>
														<td><input type="text" class="pPro" size="16" maxlength="30" value="2/CWO,NET 30" name="arcsSplyNum_A" ezfname="arcsSplyNum_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="13" maxlength="30" value="Check" name="vndPmtMethDescTxt_A" ezfname="vndPmtMethDescTxt_A" ezfhyo="A"></td>
														<td><input type="text" class="pPro" size="19" maxlength="30" value="666928" name="arcsSplyNum_A" ezfname="arcsSplyNum_A" ezfhyo="A"></td>
													</tr>
												</ezf:skip>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
