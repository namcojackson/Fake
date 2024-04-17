<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160725112312 --%>
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
			<input type="hidden" name="pageID" value="NWAL1130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CommonPopup(BIG)">

<%-- +++++ [START] Programming JSP for Dynamic Columns Table +++++ --%>
<%@ page import="business.servlet.NWAL1130.NWAL1130BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NWAL1130BMsg bMsg = (NWAL1130BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## FROM (COMMON)HEADER ######################################## --%>
				<table width="100%">
					<tr>
						<td>
							<table>
								<col align="right">
								<col>
								<col width="5">
								<col align="right">
								<col>
								<tr id="xxComnScrCondLbTxt1">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H0)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H0" ezfName="xxComnScrCondLbTxt_H0" />
										<ezf:label name="xxScrItem7Txt_H0" ezfName="xxScrItem7Txt_H0" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H0)) { %>
										<ezf:inputText name="xxScrItem500Txt_H0" ezfName="xxScrItem500Txt_H0" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H0\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H1)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H1" ezfName="xxComnScrCondLbTxt_H1" />
										<ezf:label name="xxScrItem7Txt_H1" ezfName="xxScrItem7Txt_H1" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H1)) { %>
										<ezf:inputText name="xxScrItem500Txt_H1" ezfName="xxScrItem500Txt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H1\""/>
<% } %>
									</td>
								</tr>
								<tr id="xxComnScrCondLbTxt2">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H2)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H2" ezfName="xxComnScrCondLbTxt_H2" />
										<ezf:label name="xxScrItem7Txt_H2" ezfName="xxScrItem7Txt_H2" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H2)) { %>
										<ezf:inputText name="xxScrItem500Txt_H2" ezfName="xxScrItem500Txt_H2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H2\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H3)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H3" ezfName="xxComnScrCondLbTxt_H3" />
										<ezf:label name="xxScrItem7Txt_H3" ezfName="xxScrItem7Txt_H3" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H3)) { %>
										<ezf:inputText name="xxScrItem500Txt_H3" ezfName="xxScrItem500Txt_H3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H3\""/>
<% } %>
									</td>
								</tr>
								<tr id="xxComnScrCondLbTxt3">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H4)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H4" ezfName="xxComnScrCondLbTxt_H4" />
										<ezf:label name="xxScrItem7Txt_H4" ezfName="xxScrItem7Txt_H4" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H4)) { %>
										<ezf:inputText name="xxScrItem500Txt_H4" ezfName="xxScrItem500Txt_H4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H4\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H5)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H5" ezfName="xxComnScrCondLbTxt_H5" />
										<ezf:label name="xxScrItem7Txt_H5" ezfName="xxScrItem7Txt_H5" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H5)) { %>
										<ezf:inputText name="xxScrItem500Txt_H5" ezfName="xxScrItem500Txt_H5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H5\""/>
<% } %>
									</td>
								</tr>
								<tr id="xxComnScrCondLbTxt4">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H6)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H6" ezfName="xxComnScrCondLbTxt_H6" />
										<ezf:label name="xxScrItem7Txt_H6" ezfName="xxScrItem7Txt_H6" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H6)) { %>
										<ezf:inputText name="xxScrItem500Txt_H6" ezfName="xxScrItem500Txt_H6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H6\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H7)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H7" ezfName="xxComnScrCondLbTxt_H7" />
										<ezf:label name="xxScrItem7Txt_H7" ezfName="xxScrItem7Txt_H7" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H7)) { %>
										<ezf:inputText name="xxScrItem500Txt_H7" ezfName="xxScrItem500Txt_H7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H7\""/>
<% } %>
									</td>
								</tr>
								<tr id="xxComnScrCondLbTxt5">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H8)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H8" ezfName="xxComnScrCondLbTxt_H8" />
										<ezf:label name="xxScrItem7Txt_H8" ezfName="xxScrItem7Txt_H8" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H8)) { %>
										<ezf:inputText name="xxScrItem500Txt_H8" ezfName="xxScrItem500Txt_H8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H8\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H9)) { %>
										<ezf:label name="xxComnScrCondLbTxt_H9" ezfName="xxComnScrCondLbTxt_H9" />
										<ezf:label name="xxScrItem7Txt_H9" ezfName="xxScrItem7Txt_H9" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_H9)) { %>
										<ezf:inputText name="xxScrItem500Txt_H9" ezfName="xxScrItem500Txt_H9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H9\""/>
<% } %>
									</td>
								</tr>
								<tr id="xxComnScrCondLbTxt6">
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_HA)) { %>
										<ezf:label name="xxComnScrCondLbTxt_HA" ezfName="xxComnScrCondLbTxt_HA" />
										<ezf:label name="xxScrItem7Txt_HA" ezfName="xxScrItem7Txt_HA" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_HA)) { %>
										<ezf:inputText name="xxScrItem500Txt_HA" ezfName="xxScrItem500Txt_HA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_HA\""/>
<% } %>
									</td>
									<td></td>
									<td class="stab">
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_HB)) { %>
										<ezf:label name="xxComnScrCondLbTxt_HB" ezfName="xxComnScrCondLbTxt_HB" />
										<ezf:label name="xxScrItem7Txt_HB" ezfName="xxScrItem7Txt_HB" />
<% } %>
									</td>
									<td>
<% if (ZYPCommonFunc.hasValue(bMsg.xxComnScrQueryFltrTxt_HB)) { %>
										<ezf:inputText name="xxScrItem500Txt_HB" ezfName="xxScrItem500Txt_HB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_HB\""/>
<% } %>
									</td>
								</tr>
							</table>
						</td>
						<td valign="bottom">
							<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<%-- ######################################## TO (COMMON)HEADER ########################################## --%>
	<hr />
<%-- ######################################## FROM (COMMON)DETAIL ######################################## --%>
	<table width="100%">
		<tr>
			<td>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
<!--
				<table width="100%">
					<col align="right">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col >
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
									<td></td>
									<td class="stab">Showing</td>
									<td class="pOut">1</td>
									<td class="stab">to</td>
									<td class="pOut">40</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
									<td></td>
									<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
-->
				<table border="0" cellpadding="1" cellspacing="0" width="100%">
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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
				<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:983;">
					<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<col align="center">
						<tr>
							<td id="xxComnScrColLbTxt_C0" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A0' )">
									<ezf:label name="xxComnScrColLbTxt_C0" ezfName="xxComnScrColLbTxt_C0" />
									<img id="sortIMG.xxComnScrColValTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C1" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A1' )">
									<ezf:label name="xxComnScrColLbTxt_C1" ezfName="xxComnScrColLbTxt_C1" />
									<img id="sortIMG.xxComnScrColValTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C2" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A2' )">
									<ezf:label name="xxComnScrColLbTxt_C2" ezfName="xxComnScrColLbTxt_C2" />
									<img id="sortIMG.xxComnScrColValTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C3" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A3' )">
									<ezf:label name="xxComnScrColLbTxt_C3" ezfName="xxComnScrColLbTxt_C3" />
									<img id="sortIMG.xxComnScrColValTxt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C4" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A4' )">
									<ezf:label name="xxComnScrColLbTxt_C4" ezfName="xxComnScrColLbTxt_C4" />
									<img id="sortIMG.xxComnScrColValTxt_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C5" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A5' )">
									<ezf:label name="xxComnScrColLbTxt_C5" ezfName="xxComnScrColLbTxt_C5" />
									<img id="sortIMG.xxComnScrColValTxt_A5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C6" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A6' )">
									<ezf:label name="xxComnScrColLbTxt_C6" ezfName="xxComnScrColLbTxt_C6" />
									<img id="sortIMG.xxComnScrColValTxt_A6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C7" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A7' )">
									<ezf:label name="xxComnScrColLbTxt_C7" ezfName="xxComnScrColLbTxt_C7" />
									<img id="sortIMG.xxComnScrColValTxt_A7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C8" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A8' )">
									<ezf:label name="xxComnScrColLbTxt_C8" ezfName="xxComnScrColLbTxt_C8" />
									<img id="sortIMG.xxComnScrColValTxt_A8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
							<td id="xxComnScrColLbTxt_C9" class="pClothBs">
								<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrColValTxt_A9' )">
									<ezf:label name="xxComnScrColLbTxt_C9" ezfName="xxComnScrColLbTxt_C9" />
									<img id="sortIMG.xxComnScrColValTxt_A9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
								</a>
							</td>
						</tr>
					</table>
				</div>

				<div id="bottomTBL" style="overflow-x:scroll; overflow-y:scroll; width:1000; height:342;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
					<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;"> 
						<ezf:row ezfHyo="A">
						<tr>
							<td align="left" id="xxComnScrColValTxt_A0#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C0)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A0" ezfName="xxComnScrColValTxt_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column1" >
									<ezf:label name="xxComnScrColValTxt_A0" ezfName="xxComnScrColValTxt_A0" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B0" ezfName="xxComnScrColValTxt_B0" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A1#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C1)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A1" ezfName="xxComnScrColValTxt_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column2" otherAttr=" id=\"xxComnScrColValTxt_A1\"">
									<ezf:label name="xxComnScrColValTxt_A1" ezfName="xxComnScrColValTxt_A1" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B1" ezfName="xxComnScrColValTxt_B1" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A2#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C2)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A2" ezfName="xxComnScrColValTxt_A2" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column3" otherAttr=" id=\"xxComnScrColValTxt_A2\"">
									<ezf:label name="xxComnScrColValTxt_A2" ezfName="xxComnScrColValTxt_A2" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B2" ezfName="xxComnScrColValTxt_B2" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A3#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C3)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A3" ezfName="xxComnScrColValTxt_A3" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column4" otherAttr=" id=\"xxComnScrColValTxt_A3\"">
									<ezf:label name="xxComnScrColValTxt_A3" ezfName="xxComnScrColValTxt_A3" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B3" ezfName="xxComnScrColValTxt_B3" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A4#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C4)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A4" ezfName="xxComnScrColValTxt_A4" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column5" otherAttr=" id=\"xxComnScrColValTxt_A4\"">
									<ezf:label name="xxComnScrColValTxt_A4" ezfName="xxComnScrColValTxt_A4" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B4" ezfName="xxComnScrColValTxt_B4" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A5#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C5)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A5" ezfName="xxComnScrColValTxt_A5" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column6" otherAttr=" id=\"xxComnScrColValTxt_A5\"">
									<ezf:label name="xxComnScrColValTxt_A5" ezfName="xxComnScrColValTxt_A5" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B5" ezfName="xxComnScrColValTxt_B5" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A6#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C6)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A6" ezfName="xxComnScrColValTxt_A6" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column7" otherAttr=" id=\"xxComnScrColValTxt_A6\"">
									<ezf:label name="xxComnScrColValTxt_A6" ezfName="xxComnScrColValTxt_A6" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B6" ezfName="xxComnScrColValTxt_B6" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A7#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C7)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A7" ezfName="xxComnScrColValTxt_A7" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column8" otherAttr=" id=\"xxComnScrColValTxt_A7\"">
									<ezf:label name="xxComnScrColValTxt_A7" ezfName="xxComnScrColValTxt_A7" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B7" ezfName="xxComnScrColValTxt_B7" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A8#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C8)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A8" ezfName="xxComnScrColValTxt_A8" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column9" otherAttr=" id=\"xxComnScrColValTxt_A8\"">
									<ezf:label name="xxComnScrColValTxt_A8" ezfName="xxComnScrColValTxt_A8" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B8" ezfName="xxComnScrColValTxt_B8" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A9#{EZF_ROW_NUMBER}">
<% if (ZYPCommonFunc.hasValue(bMsg.xxSelFlg_C9)) { %>
								<ezf:anchor name="xxComnScrColValTxt_A9" ezfName="xxComnScrColValTxt_A9" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Column10" otherAttr=" id=\"xxComnScrColValTxt_A9\"">
									<ezf:label name="xxComnScrColValTxt_A9" ezfName="xxComnScrColValTxt_A9" ezfHyo="A" ezfArrayNo="0" />
								</ezf:anchor>
<% } else {%>
								<ezf:label name="xxComnScrColValTxt_B9" ezfName="xxComnScrColValTxt_B9" ezfHyo="A" ezfArrayNo="0" />
<% } %>
							</td>
						</tr>
						</ezf:row>
						<ezf:skip>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="xxComnScrColLbTxt_A0" ezfname="xxComnScrColLbTxt_A0" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr class="pEvenNumberBGcolor">
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1
									</label>
								</a>
							</td>
							<td align="left" style="width:80ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" style="width:20ex;word-break:break-all">
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
							<td align="left" >
								<a href="#" onclick="sendServer('Select_Column')" name="" ezfname="" ezfhyo="A">
									<label ezfout name="" ezfname="" ezfhyo="A">
										XXXXXXXXX1XXXXXXXXX2
									</label>
								</a>
							</td>
						</tr>
						</ezf:skip>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
