<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530103048 --%>
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
			<input type="hidden" name="pageID" value="NSAL1380Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Agreement Entry">
			
<left>
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
										<li title="Supply Agreement Plan Search" class="pTab_ON"><a href="#">AgmtPln Set</a></li>
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
				<div class="pTab_BODY_In">
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="180" width="1100" rules="none"  style="padding: 10px; margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100" align="left">
							<table border="0" cellpadding="0" cellspacing="0">
								<colgroup>
									<col width="110">
									<col width="010">
									<col width="115">
									<col width="080">
									<col width="010">
									<col width="420">
								</colgroup>
								<tr>
									<td class="stab"><label>Maintenance Shell#</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="shellLineNum" ezfName="shellLineNum" value="11" otherAttr=" maxlength=\"10\" size=\"10\" style=\"text-align:right\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Shell Item</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="svcPgmMdseCd" ezfName="svcPgmMdseCd" value="WWWWWWWW" otherAttr=" maxlength=\"16\" size=\"20\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Shell Term</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="termMthAot" ezfName="termMthAot" value="123" otherAttr=" maxlength=\"3\" size=\"4\" style=\"text-align:right\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Supply Base Price</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyBaseAmt" ezfName="splyBaseAmt" value="123.12" otherAttr=" maxlength=\"24\" size=\"24\" style=\"text-align:right\""/></td>
								</tr>
							<!-- -->
								<tr>
									<td class="stab"><label>Plan ID</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtPlnPk" ezfName="splyAgmtPlnPk" value="99" otherAttr=" size=\"10\" maxlength=\"28\" style=\"text-align:right\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Plan Name</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtPlnNm" ezfName="splyAgmtPlnNm" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Plan Short Name</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtPlnShortNm" ezfName="splyAgmtPlnShortNm" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Description/Notes</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtPlnDescTxt" ezfName="splyAgmtPlnDescTxt" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Plan Type</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtPlnTpDescTxt" ezfName="splyAgmtPlnTpDescTxt" value="XXXXXXXXX0X" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Document Type</label></td>
									<td></td>
									<td colspan="4"><ezf:inputText name="splyAgmtDocTpDescTxt" ezfName="splyAgmtDocTpDescTxt" value="XXXXXXXXX0X" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
								</tr>
							<!-- -->
								<tr>
									<td class="stab"><label>Annual Term Cap</label></td>
									<td></td>
									<td><ezf:inputText name="annTermCapNum" ezfName="annTermCapNum" value="123456789" otherAttr=" maxlength=\"9\" size=\"10\""/></td>

									<td class="stab"><label>Total Term Cap</label></td>
									<td></td>
									<td><ezf:inputText name="qtyContrCapQty" ezfName="qtyContrCapQty" value="15" otherAttr=" maxlength=\"9\" size=\"10\" style=\"text-align:right\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="01" cellspacing="0" width="1076" align="center"  rules="none"  style="padding: 10px; margin-bottom: 10px;">
				<tr>
				<td width="3"></td>
				<td>
						<div style="float:left"> <!-- right table -->
							<div id="rightTblHead" style="width:1059px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1059px" style="margin-right:20px">
									<col align="center" width="135">	<!-- Item Type				-->
									<col align="center" width="085">	<!-- Item Code		-->
									<col align="center" width="085">	<!-- Manufacture Item#	-->
									<col align="center" width="135">	<!-- Description		-->
									<col align="center" width="190">	<!-- Frequency				-->
									<col align="center" width="070">	<!-- First Qty			-->
									<col align="center" width="070">		<!-- Quantity				-->
									<col align="center" width="070">	<!-- Annual Entitlement	-->
									<col align="center" width="070">	<!-- Term Total Entitlement		-->
									<tr height="40">
										<td class="pClothBs">Item Type</td>
										<td class="pClothBs">Item Code</td>
										<td class="pClothBs">Manufacture<br>Item#</td>
										<td class="pClothBs">Description</td>
										<td class="pClothBs">Frequency</td>
										<td class="pClothBs">First Qty</td>
										<td class="pClothBs">Quantity</td>
										<td class="pClothBs">Annual<br>Entitlement</td>
										<td class="pClothBs">Term Total<br>Entitlement</td>
									</tr>
								</table>
							</div>
							<div id="rightTBL" style="width:1076px; height:291px; display:block; overflow:scroll; margin:0px; padding:0px;"  onScroll="synchroScrollLeft(this.id, new Array('rightTblHead'));">
								<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed" width="1059px">
									<col align="center" width="135">	<!-- Item Type				-->
									<col align="center" width="085">	<!-- Item Code				-->
									<col align="center" width="085">	<!-- Manufacture Item#	-->
									<col align="center" width="135">	<!-- Description		-->
									<col align="center" width="190">	<!-- Frequency				-->
									<col align="center" width="070">	<!-- First Qty			-->
									<col align="center" width="070">	<!-- Quantity				-->
									<col align="center" width="070">	<!-- Annual Entitlement	-->
									<col align="center" width="070">	<!-- Term Total Entitlement		-->
									<ezf:row ezfHyo="A">
										<tr id="id_leftA_row{EZF_ROW_NUMBER}" height="25">
											<td><ezf:inputText name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="XXXXXXXXX1XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\""/></td>
											<td><ezf:inputText name="mnfItemCd_A" ezfName="mnfItemCd_A" value="XXXXXXXXX1XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\""/></td>
											<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td>
												<ezf:select name="shpgIntvlCd_A" ezfName="shpgIntvlCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="shpgIntvlCd_P" ezfDispName="shpgIntvlDescTxt_P" otherAttr=" style=\"width: 200px\""/>
											</td>
											<td><ezf:inputText name="firstShipQty_A" ezfName="firstShipQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right\""/></td>
											<td><ezf:inputText name="otmShipQty_A" ezfName="otmShipQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right\""/></td>
											<td><ezf:inputText name="xxTotQtyCnt_A" ezfName="xxTotQtyCnt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right\""/></td>
											<td><ezf:inputText name="xxTotQtyCnt_AT" ezfName="xxTotQtyCnt_AT" value="3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right\""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="imgSplyTpNm_A" ezfName="imgSplyTpNm_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly/></td>
											<td><input type="text" size="10" maxlength="16" value="XXXXXXXXX1XX" name="mnfItemCd_A" ezfname="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td><input type="text" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" class="pPro" readonly></td>
											<td>
												<select style="width: 200px"name="splyAgmtFreqTpCd_A" ezfname="splyAgmtFreqTpCd_A" ezflist="splyAgmtFreqTpCd_P,splyAgmtFreqTpDescTxt_P,99, ,noblank" ezfhyo="A" >
													<option>QUARTERLY</option>
													<option>SEMI-ANNUAL</option>
													<option>MTH</option>
													<option>EVERY 2</option>
													<option>ANNUAL</option>
													<option>UPON REQUEST BY CUSTOMER</option>
													<option>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</option>
												</select>
											</td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnFirstQty_A" ezfname="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="splyAgmtPlnQty_A" ezfname="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" style="text-align:right"></td>
											<td><input type="text" size="10" maxlength="10" value="1" name="xxTotQtyCnt_AA" ezfName="xxTotQtyCnt_AA" ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
											<td><input type="text" size="10" maxlength="10" value="3" name="xxTotQtyCnt_A"  ezfName="xxTotQtyCnt_A"  ezfHyo="A" ezfArrayNo="0" class="pPro" style="text-align:right" readonly/></td>
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
</left>


<%-- **** Task specific area ends here **** --%>
