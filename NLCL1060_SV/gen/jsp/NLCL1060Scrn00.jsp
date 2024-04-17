<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230601144813 --%>
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
			<input type="hidden" name="pageID" value="NLCL1060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="3rd Party Onhand Inventory Inquiry Popup">

<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table>
					<col width="1100">
					<tr>
						<td>
 						  <fieldset>
							<table>
								<col width="200">
								<col width="150">
								<col width="100">
								<col width="325">
								<col width="118" align="right">
								<tr>
									<td class="stab">Check CUSA WH All</td>
									<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
									<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_IT" ezfEmulateBtn="1" ezfGuard="Item_Link" otherAttr=" tabindex=\"-1\"">Item Number(*)</ezf:anchor></td>
                                    <td><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" value="ITEM NUMBER" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SU" ezfEmulateBtn="1" ezfGuard="Supplier_Link" otherAttr=" tabindex=\"-1\"">Supplier Site Code</ezf:anchor></td>
                                    <td><ezf:inputText name="vndCd" ezfName="vndCd" value="SUPPLIER SITE" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
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
                <div id="parentBoxA">
                   <div style="float:left; display:block"> <!-- left table -->
                       <div id='leftTblHead' style="display:block;">
                       </div>
                       <div id='leftTbl' style="float:left; display:block; overflow:hidden; margin:0px; padding:0px;">
                       </div>
                   </div>  <!-- left table -->
                   <div style="float:left"> <!-- right table -->
				 <%-- Head --%>
                 <div id='rightTblHead' style="width:1003px; display:block; overflow:hidden; margin:0px;padding:0px;">
                     <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="999px">
                    <col width="100"   align="center">
                    <col width="113"   align="center">
                    <col width="126"   align="center">
                    <col width="117"   align="center">
                    <col width="125"   align="center">
                    <col width="70"    align="center">
                    <col width="60"    align="center">
                    <col width="95"    align="center">
                    <col width="123"   align="center">
                    <col width="70"    align="center">
						<tr>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A1')">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A1')">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'vndCd_A1')">Supplier Site Code<img id="sortIMG.vndCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dplyVndNm_A1')">Supplier Site Name<img id="sortIMG.dplyVndNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlWhNm_A1')">Warehouse Name<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlWhCd_A1')">Warehouse Code<img id="sortIMG.rtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invtyQty_A1')">On Hand Qty<img id="sortIMG.invtyQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'locStsDescTxt_A1')">Location Status<img id="sortIMG.locStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsDescTxt_A1')">Stock Status<img id="sortIMG.stkStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'locTpDescTxt_A1')">Location Type<img id="sortIMG.locTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						</tr>
					</table>
				</div>

				<%-- Body --%>
				<div id="rightTbl" style="width:1003px; height:392px; display:block; overflow:hidden; margin:0px; padding:0px;" >
                    <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A" width="999px" >
                    <col width="100"   align="left">
                    <col width="113"   align="left">
                    <col width="126"   align="left">
                    <col width="117"   align="left">
                    <col width="125"   align="left">
                    <col width="70"    align="left">
                    <col width="60"    align="right">
                    <col width="95"    align="left">
                    <col width="123"   align="left">
                    <col width="70"    align="left">
						<tbody>
						<ezf:row ezfHyo="A">
						     <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><label><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="FM1-A441-00R" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td>
                                  <label>
                                    <ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="REFURB ENV. HEATER DRIVER PCB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/>
                                  </label>
                                </td>
                                <td><label><ezf:inputText name="vndCd_A1" ezfName="vndCd_A1" value="VS0001105" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="dplyVndNm_A1" ezfName="dplyVndNm_A1" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="HYTEC DEALER SERVICES INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="V06" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="invtyQty_A1" ezfName="invtyQty_A1" value="111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"text-align:right; border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="locStsDescTxt_A1" ezfName="locStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="stkStsDescTxt_A1" ezfName="stkStsDescTxt_A1" value="5:To be reman" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></label></td>
                                <td><label><ezf:inputText name="locTpDescTxt_A1" ezfName="locTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent;\""/></label></td>
                            </tr>
						</ezf:row>
						<ezf:skip>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            <tr id="A_TR_{EZF_ROW_NUMBER}">
                                <td><input type="text" value="FM1-A441-00R" name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="REFURB ENV. HEATER DRIVER PCB" name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="VS0001105" name="mdseCd_AS" ezfname="mdseCd_AS" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES,INC. - WEST PALM B-ADB" name="whNm_A1" ezfname="whNm_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="HYTEC DEALER SERVICES INC" name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="V06" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="2" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A"></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="5:To be reman" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                                <td><input type="text" value="" name="ordQty_A1" ezfname="ordQty_A1" ezfhyo="A" ></td>
                            </tr>
                            </ezf:skip>   
						</tbody>
					  </table>
				  </div>
			   </div>
             </div> 
           </td>
         </tr>
      </table>
 </center>

<%-- **** Task specific area ends here **** --%>
