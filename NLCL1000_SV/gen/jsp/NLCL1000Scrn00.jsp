<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180711155527 --%>
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
			<input type="hidden" name="pageID" value="NLCL1000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Stock Over View Popup">

<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
<!--
				<table>
					<col width="120">
					<col width="120">
					<col width="100">
					<col width="150">
					<col width="180">
					<col width="180">
					<col width="5">
					<col width="5">
					<col width="118" align="right">
					<tr>
						<td>
 							<fieldset>
 								<legend></legend>
								<table>
									<col width="20">
									<col width="16">
									<col width="5">
									<col width="20">
									<col width="32">
									<tr>
										<td>Internal</td>
										<td><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN" onclick="sendServer('OnClick_XX_CHK_BOX_IN')"></td>
										<td></td>
										<td>External</td>
										<td><input type="checkbox" value="Y" name="xxChkBox_EX" ezfname="xxChkBox_EX"></td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
 							<fieldset>
 								<legend>Internal</legend>
								<table>
									<col width="20">
									<col width="16">
									<col width="5">
									<col width="20">
									<col width="32">
									<tr>
										<td>WH</td>
										<td><input type="checkbox" value="Y" name="xxChkBox_WH" ezfname="xxChkBox_WH"></td>
										<td></td>
										<td>Tech</td>
										<td><input type="checkbox" value="Y" name="xxChkBox_TE" ezfname="xxChkBox_TE"></td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
							<table>
								<col width="90">
								<tr>
									<td class="stab">SWH : NEW&nbsp;<input type="checkbox" value="Y" name="xxChkBox_NE" ezfname="xxChkBox_NE"></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<col width="140">
								<tr>
									<td class="stab">Display Supersede&nbsp;<input type="checkbox" value="Y" name="xxChkBox_DI" ezfname="xxChkBox_DI"></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<col width="160">
								<tr>
									<td class="stab">Available Location Only&nbsp;<input type="checkbox" value="Y" name="xxChkBox_AV" ezfname="xxChkBox_AV"></td>
								</tr>
							</table>
						</td>

						<td>
							<table>
								<col width="180">
								<tr>
									<td class="stab">Search by Original Condition<input type="checkbox" value="Y" name="xxChkBox_SE" ezfname="xxChkBox_SE"></td>
								</tr>
							</table>
						</td>
						<td></td>
						<td></td>
						<td><input type="button" class="pBtn6" value="Search" name="Search" onclick="sendServer(this)" ></td>
					</tr>
				</table>
-->
				<table>
					<col width="1100">
					<tr>
						<td>
 							<fieldset>
							<table>
								<col width="200">
								<col width="30">
								<col width="500">
								<col width="118" align="right">
								<tr>
									<td class="stab">Check Alternate WH/Sub WH New</td>
									<td><ezf:inputCheckBox name="xxChkBox_NW" ezfName="xxChkBox_NW" value="Y" /></td>
									<td></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Check Alternate WH/Sub WH All</td>
									<td><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" /></td>
									<td></td>
								<!-- Add Start 2018/07/05 Hd.Sugawara QC#21076 -->
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Check CUSA WH All</td>
									<td><ezf:inputCheckBox name="xxChkBox_DS" ezfName="xxChkBox_DS" value="Y" /></td>
									<td></td>
								<!-- Add End   2018/07/05 Hd.Sugawara QC#21076 -->
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								</tr>
							</table>
              </fieldset>
            </td>
          </tr>
        </table>
				<hr>

<%-- ######################################## DETAIL ######################################## --%>
				<%-- Pagenation --%>
				<table width="97%" border="0" cellpadding="1" cellspacing="0">
					<tr align="right" height="25">
						<td valign="middle">
<%--
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="54"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="10">
								<col>
								<col width="1">
								<col>
								<tr>
									<td class="stab">Showing</td>
									<td class="pOut">1</td>
									<td class="stab">to</td>
									<td class="pOut">20</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Prev" value="Prev" htmlClass="pBtn3" /></td>
									<td></td>
									<td><ezf:inputButton name="Next" value="Next" htmlClass="pBtn3" /></td>
								</tr>
							</table>
--%>
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
							</jsp:include>

						</td>
					</tr>
				</table>

				<%-- Head --%>
				<table border="1" cellpadding="1" cellspacing="0" width="982">
					<col width="20"   align="center">
					<col width="80"   align="center">
					<col width="116"   align="center">
					<col width="100"   align="center">
					<col width="116"    align="center">
					<col width="55"    align="center">
					<col width="55"    align="center">
					<col width="36"   align="center">
					<col width="75"   align="center">
					<col width="75"   align="center">
					<col width="74"   align="center">
					<col width="86"   align="center">
					<tr>
						<td class="pClothBs">#</td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A1')">Item Code<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A1')">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_AS')">Supersede Item<img id="sortIMG.mdseCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'whNm_A1')">WH Name<img id="sortIMG.whNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlWhCd_A1')">WH Cd<img id="sortIMG.rtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlSwhCd_A1')">Sub WH<img id="sortIMG.rtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsCd_A1')">SS<img id="sortIMG.stkStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ordQty_A1')">Request Qty<img id="sortIMG.ordQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem10Txt_A1')">Availability<img id="sortIMG.xxScrItem10Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'etaDt_A1')">ETA<img id="sortIMG.etaDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem20Txt_A2')">Line#<img id="sortIMG.xxScrItem20Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					</tr>
				</table>

				<%-- Body --%>
				<div style="overflow-y:scroll; overflow-x:hidden; height:434; width:999;">
					<table border="1" cellpadding="1" cellspacing="0" width="982" id="A">
						<col width="20"   align="center">
						<col width="80"   align="center">
						<col width="116"    align="center">
						<col width="100"   align="center">
						<col width="116"    align="center">
						<col width="55"    align="center">
						<col width="55"    align="center">
						<col width="36"   align="left">
						<col width="75"   align="center">
						<col width="75"   align="center">
						<col width="74"   align="left">
						<col width="86"   align="left">
						<tbody>
						<ezf:row ezfHyo="A">
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
							<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
							<td><ezf:inputText name="mdseCd_AS" ezfName="mdseCd_AS" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
							<td><ezf:inputText name="whNm_A1" ezfName="whNm_A1" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
							<td><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
							<td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
							<td><ezf:label name="stkStsCd_A1" ezfName="stkStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:inputText name="ordQty_A1" ezfName="ordQty_A1" value="1,234,567,890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></td>
							<td><ezf:inputText name="xxScrItem10Txt_A1" ezfName="xxScrItem10Txt_A1" value="1,234,567,890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></td>
							<td><ezf:label name="etaDt_A1" ezfName="etaDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="xxScrItem20Txt_A1" ezfName="xxScrItem20Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						</ezf:row>
						<ezf:skip>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						<tr id="A_TR_{EZF_ROW_NUMBER}">
							<td><input type="checkbox" value="Y"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="10"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="13"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="15"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><input type="text" value="XXXXAXXXXAXXXXAXXXXAXXXXAXXXXA" size="6"></td>
							<td><label>1</label></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><input type="text" value="1,234,567,890" size="9"></td>
							<td><label>10/27/2015</label></td>
							<td><label>1.1</label></td>
						</tr>
						</ezf:skip>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
