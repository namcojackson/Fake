<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151127171714 --%>
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
			<input type="hidden" name="pageID" value="NMAL6870Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Multi Selection Popup">

<%-- +++++ [START] Programming JSP for Dynamic Columns Table +++++ --%>
<%@ page import="business.servlet.NMAL6870.NMAL6870BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NMAL6870BMsg bMsg = (NMAL6870BMsg)databean.getEZDBMsg(); %>
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
										<ezf:inputText name="xxComnScrCondValTxt_H0" ezfName="xxComnScrCondValTxt_H0" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H0\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H1" ezfName="xxComnScrCondValTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H1\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H2" ezfName="xxComnScrCondValTxt_H2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H2\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H3" ezfName="xxComnScrCondValTxt_H3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H3\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H4" ezfName="xxComnScrCondValTxt_H4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H4\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H5" ezfName="xxComnScrCondValTxt_H5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H5\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H6" ezfName="xxComnScrCondValTxt_H6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H6\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H7" ezfName="xxComnScrCondValTxt_H7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H7\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H8" ezfName="xxComnScrCondValTxt_H8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H8\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_H9" ezfName="xxComnScrCondValTxt_H9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_H9\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_HA" ezfName="xxComnScrCondValTxt_HA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_HA\""/>
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
										<ezf:inputText name="xxComnScrCondValTxt_HB" ezfName="xxComnScrCondValTxt_HB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\" id=\"xxComnScrCondValTxt_HB\""/>
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
			<td align="left">
				<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" />
				<ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn8" />
			</td>
		</tr>
		<tr>
			<td>
				<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:983;">
					<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
						<col align="center" width="27">
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
							<td class="pClothBs">&nbsp;</td>
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
					<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all"> 
						<col align="center" width="27">
						<ezf:row ezfHyo="A">
						<tr>
							<td align="center">
								<ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left" id="xxComnScrColValTxt_A0#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A0" ezfName="xxComnScrColValTxt_A0" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A1#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A1" ezfName="xxComnScrColValTxt_A1" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A2#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A2" ezfName="xxComnScrColValTxt_A2" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A3#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A3" ezfName="xxComnScrColValTxt_A3" ezfHyo="A" ezfArrayNo="0" />
								</a>
							</td>
							<td align="left"  id="xxComnScrColValTxt_A4#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A4" ezfName="xxComnScrColValTxt_A4" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A5#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A5" ezfName="xxComnScrColValTxt_A5" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A6#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A6" ezfName="xxComnScrColValTxt_A6" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A7#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A7" ezfName="xxComnScrColValTxt_A7" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A8#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A8" ezfName="xxComnScrColValTxt_A8" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"  id="xxComnScrColValTxt_A9#{EZF_ROW_NUMBER}">
								<ezf:label name="xxComnScrColValTxt_A9" ezfName="xxComnScrColValTxt_A9" ezfHyo="A" ezfArrayNo="0" />
							</td>
						</tr>
						</ezf:row>
						<ezf:skip>
						<tr class="pEvenNumberBGcolor">
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
							<td align="center">
								<input type="checkbox" class="" value="Y" checked name="xxChkBox" ezfname="xxChkBox" ezfhyo="A">
							</td>
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
