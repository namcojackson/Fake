<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240228103803 --%>
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
			<input type="hidden" name="pageID" value="NSAL1240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Config# Search Popup">

<%@ page import="business.servlet.NSAL1240.NSAL1240BMsg" %>
<%@ page import="business.servlet.NSAL1240.NSAL1240_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSAL1240BMsg bMsg = (NSAL1240BMsg)databean.getEZDBMsg(); %>

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" width="99%" align="center">
					<col width="" align="left" valign="top">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100" align="left">
								<col width="70">
								<col width="10">
								<col width="10">
								<col width="70">
								<col width="10">
								<col width="15">
								<col width="140" align="left">
								<col width="15">
								<col width="100" align="left">
								<col width="40">
								<col width="60">
								<col width="15">
								<col width="140" align="left">
								<col width="100">
								<tr height="21">
									<td class="stab">Configuration#(*)</td>
									<td colspan="5"><ezf:inputText name="xxScrItem29Txt_H1" ezfName="xxScrItem29Txt_H1" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Install Base Status</td>
									<td>&nbsp;</td>
									<td class="stab">Machine Allocated</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_HA" ezfName="xxChkBox_HA" value="Y" />ON</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_HB" ezfName="xxChkBox_HB" value="Y" />OFF</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="ownrAcctNum_LK" ezfName="ownrAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_IBOwnerAccount" >IB Owner Account#(*)</ezf:anchor></td>
									<td><ezf:inputText name="ownrAcctNum_H" ezfName="ownrAcctNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="21">
									<td class="stab">Serial#/Tag#(*)</td>
									<td colspan="5"><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Created</td>
									<td>&nbsp;</td>
									<td class="stab">Machine Only</td>
									<td colspan="2"><ezf:inputCheckBox name="xxChkBox_HC" ezfName="xxChkBox_HC" value="Y" /></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="billToAcctNum_LK" ezfName="billToAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_IBBillToAccount" >IB Bill To Account#(*)</ezf:anchor></td>
									<td><ezf:inputText name="billToAcctNum_H" ezfName="billToAcctNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="21">
									<td class="stab">Install Base ID(*)</td>
									<td colspan="5"><ezf:inputText name="xxScrItem29Txt_H2" ezfName="xxScrItem29Txt_H2" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />Waiting for Installation</td>
									<td>&nbsp;</td>
									<td class="stab">Stock Status</td>
									<td colspan="2">
										<ezf:select name="stkStsCd_H" ezfName="stkStsCd_H" ezfBlank="1" ezfCodeName="stkStsCd_L" ezfDispName="stkStsDescTxt_L" otherAttr=" style=\"width:130px\""/>
									</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="indBillToLocNum_LK" ezfName="indBillToLocNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_IBBillToLocation" >IB Bill To Location#(*)</ezf:anchor></td>
									<td><ezf:inputText name="indBillToLocNum_H" ezfName="indBillToLocNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="21">
									<td class="stab"><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseCd" >Item Code(*)</ezf:anchor></td>
									<td colspan="5"><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" />Installed</td>
									<td>&nbsp;</td>
									<td class="stab">WH Code</td>
									<td colspan="2"><ezf:inputText name="curLocNum_H1" ezfName="curLocNum_H1" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="curLocAcctNum_LK" ezfName="curLocAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_IBCurrentLocAccount" >IB Current Loc Account#(*)</ezf:anchor></td>
									<td><ezf:inputText name="curLocAcctNum_H" ezfName="curLocAcctNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="21">
									<td class="stab">Model Name(*)</td>
									<td colspan="5"><ezf:inputText name="t_MdlNm_H" ezfName="t_MdlNm_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H4" ezfName="xxChkBox_H4" value="Y" />Waiting for Removal</td>
									<td>&nbsp;</td>
									<td class="stab">Sub WH Code</td>
									<td colspan="2"><ezf:inputText name="curLocNum_H2" ezfName="curLocNum_H2" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="indCurLocNum_LK" ezfName="indCurLocNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_IBCurrentLocLocation" >IB Current Location#(*)</ezf:anchor></td>
									<td><ezf:inputText name="indCurLocNum_H" ezfName="indCurLocNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="21">
									<td class="stab">Shipped Date</td>
									<td><ezf:inputText name="shipDt_H1" ezfName="shipDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('shipDt_H1', 4);" ></td>
									<td>-</td>
									<td><ezf:inputText name="shipDt_H2" ezfName="shipDt_H2" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('shipDt_H2', 4);" ></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H5" ezfName="xxChkBox_H5" value="Y" />Remove</td>
									<td>&nbsp;</td>
									<td class="stab">Solution#(*)</td>
									<td colspan="2"><ezf:inputText name="xxScrItem29Txt_H3" ezfName="xxScrItem29Txt_H3" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr height="21">
									<td class="stab">Install Base Usage</td>
									<td colspan="5">
										<ezf:select name="svcMachUsgStsCd_H" ezfName="svcMachUsgStsCd_H" ezfBlank="1" ezfCodeName="svcMachUsgStsCd_L" ezfDispName="svcMachUsgStsDescTxt_L" otherAttr=" style=\"width:130px\""/>
									</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H6" ezfName="xxChkBox_H6" value="Y" />Terminated</td>
									<td>&nbsp;</td>
									<td class="stab">Solution Name(*)</td>
									<td colspan="2"><ezf:inputText name="svcSlnNm_H" ezfName="svcSlnNm_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr height="21">
									<td>&nbsp;</td>
									<td colspan="5">&nbsp;</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H7" ezfName="xxChkBox_H7" value="Y" />Duplicate</td>
									<td>&nbsp;</td>
									<td class="stab">Contract#(*)</td>
									<td colspan="2"><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr height="21">
									<td colspan="6">&nbsp;</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H8" ezfName="xxChkBox_H8" value="Y" />Dealer Service</td>
									<td>&nbsp;</td>
									<td colspan="3">&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table style="margin-top: 0px; margin-left: 5px" border="0" width="960px">
					<tr>
						<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
						<td align="right">
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
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
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

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

<%-- ######################################## DETAIL ######################################## --%>
				<table>
					<tr>
						<td>
							<div id="parentBoxA">
							<div style="float:left; display:block"> <!-- left table -->
								<div id='leftTblHead' style="display:block;overflow:hidden;margin:0px;padding:0px;">
								</div>
							<div id='leftTbl' style="float:left; display:block; height:252px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
							</div>
							</div>  <!-- left table -->
							<div style="float:left"> <!-- right table -->
								<div id='rightTblHead' style="width:980px; display:block; overflow:hidden; margin:0px;padding:0px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="2600px" style="margin-right:20px" >
										<col width="30"  align="center">
										<col width="25"  align="center">
										<col width="100" align="center">	 <!-- Configuration# -->
										<col width="100" align="center">	 <!-- Serial# -->
										<col width="100" align="center">	 <!-- Install Base ID -->
										<col width="100" align="center">	 <!-- Mdse Code -->
										<col width="100" align="center">	 <!-- Mdse Description -->
										<col width="100" align="center">	 <!-- Model Name -->
										<col width="80"  align="center">	 <!-- Shipped Date -->
										<col width="100" align="center">	 <!-- Install Base Usage -->
										<col width="100" align="center">	 <!-- Install Base Status -->
										<col width="40"  align="center">	 <!-- Avail -->
										<col width="100" align="center">	 <!-- Stock Status -->
										<col width="100" align="center">	 <!-- Solution# -->
										<col width="100" align="center">	 <!-- Solution Name -->
										<col width="100" align="center">	 <!-- Contract# -->
										<col width="80"  align="center">	 <!-- IB Owner Account# -->
										<col width="140" align="center">	 <!-- IB Owner Account Name -->
										<col width="80"  align="center">	 <!-- IB Bill To Account# -->
										<col width="140" align="center">	 <!-- IB Bill To Account Name -->
										<col width="80"  align="center">	 <!-- IB Bill To Location# -->
										<col width="140" align="center">	 <!-- IB Bill To Address -->
										<col width="80"  align="center">	 <!-- IB Current Account# -->
										<col width="140" align="center">	 <!-- IB Current Loc Account Name -->
										<col width="80"  align="center">	 <!-- IB Current Location# -->
										<col width="140" align="center">	 <!-- IB Current Loc Address -->
										<tr height="40px">
											<td id="AH0" class="pClothBs">&nbsp;</td>
											<td id="AH1" class="pClothBs">&nbsp;</td>
											<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcConfigMstrPk_A')">Configuration#</a><img id="sortIMG.svcConfigMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">Install Base ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A')">Item Description</a><img id="sortIMG.mdseDescShortTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')">Model Name</a><img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipDt_A')">Shipped Date</a><img id="sortIMG.shipDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachUsgStsDescTxt_A')">IB Usage</a><img id="sortIMG.svcMachUsgStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrStsDescTxt_A')">IB Status</a><img id="sortIMG.svcMachMstrStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMaintAvalFlg_A')">Avail</a><img id="sortIMG.svcMachMaintAvalFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsDescTxt_A')">Stock Status</a><img id="sortIMG.stkStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnSq_A')">Solution#</a><img id="sortIMG.svcSlnSq_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnNm_A')">Solution</a><img id="sortIMG.svcSlnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ownrAcctNum_A')">IB Owner<br>Account#</a><img id="sortIMG.ownrAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A1')">IB Owner<br>Account Name</a><img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToAcctNum_A')">IB Bill To<br>Account#</a><img id="sortIMG.billToAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A2')">IB Bill To<br>Account Name</a><img id="sortIMG.dsAcctNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'indBillToLocNum_A')">IB Bill To<br>Location#</a><img id="sortIMG.indBillToLocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxComnScrColValTxt_A2')">IB Bill To<br>Address</a><img id="sortIMG.xxComnScrColValTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'curLocAcctNum_A')">IB Current<br>Account#</a><img id="sortIMG.curLocAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A3')">IB Current Loc<br>Account Name</a><img id="sortIMG.dsAcctNm_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'indCurLocNum_A')">IB Current<br>Location#</a><img id="sortIMG.indCurLocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxComnScrColValTxt_A3')">IB Current Loc<br>Address</a><img id="sortIMG.xxComnScrColValTxt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
								<div id="rightTbl" style="width:997px; height:269px; display:block; overflow:scroll; margin:0px; padding:0px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" width="2600px">
										<col width="30"  align="center">
										<col width="25"  align="center">
										<col width="100" align="center">	 <!-- Configuration# -->
										<col width="100" align="center">	 <!-- Serial# -->
										<col width="100" align="center">	 <!-- Install Base ID -->
										<col width="100" align="center">	 <!-- Mdse Code -->
										<col width="100" align="center">	 <!-- Mdse Description -->
										<col width="100" align="center">	 <!-- Model Name -->
										<col width="80"  align="center">	 <!-- Shipped Date -->
										<col width="100" align="center">	 <!-- Install Base Usage -->
										<col width="100" align="center">	 <!-- Install Base Status -->
										<col width="40"  align="center">	 <!-- Avail -->
										<col width="100" align="center">	 <!-- Stock Status -->
										<col width="100" align="center">	 <!-- Solution# -->
										<col width="100" align="center">	 <!-- Solution Name -->
										<col width="100" align="center">	 <!-- Contract# -->
										<col width="80"  align="center">	 <!-- IB Owner Account# -->
										<col width="140" align="center">	 <!-- IB Owner Account Name -->
										<col width="80"  align="center">	 <!-- IB Bill To Account# -->
										<col width="140" align="center">	 <!-- IB Bill To Account Name -->
										<col width="80"  align="center">	 <!-- IB Bill To Location# -->
										<col width="140" align="center">	 <!-- IB Bill To Address -->
										<col width="80"  align="center">	 <!-- IB Current Account# -->
										<col width="140" align="center">	 <!-- IB Current Loc Account Name -->
										<col width="80"  align="center">	 <!-- IB Current Location# -->
										<col width="140" align="center">	 <!-- IB Current Loc Address -->
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
											<c:set var="dplyCtrlFlg" scope="page" value="<%= bMsg.A.no(i).xxDplyCtrlFlg_A.getValue()%>" />
											<% i++; %>
											<tr height="25px">
												<td id="xxDplyCtrlFlg_A#{EZF_ROW_NUMBER}">
													<c:choose>
														<c:when test="${empty dplyCtrlFlg}">
															&nbsp;
														</c:when>
														<c:when test="${dplyCtrlFlg == 'N'}">
															<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Expand" >
																<img src="./img/biz/rightarrow2.png" border="0">
															</ezf:anchor>
														</c:when>
														<c:when test="${dplyCtrlFlg == 'Y'}">
															<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Collapse">
																<img src="./img/biz/downarrow2.png" border="0">
															</ezf:anchor>
														</c:when>
													</c:choose>
												</td>
												<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:label name="shipDt_A" ezfName="shipDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="svcMachUsgStsDescTxt_A" ezfName="svcMachUsgStsDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcMachMstrStsDescTxt_A" ezfName="svcMachMstrStsDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:label name="svcMachMaintAvalFlg_A" ezfName="svcMachMaintAvalFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="stkStsDescTxt_A" ezfName="stkStsDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcSlnSq_A" ezfName="svcSlnSq_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcSlnNm_A" ezfName="svcSlnNm_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="ownrAcctNum_A" ezfName="ownrAcctNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="billToAcctNum_A" ezfName="billToAcctNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsAcctNm_A2" ezfName="dsAcctNm_A2" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="indBillToLocNum_A" ezfName="indBillToLocNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxComnScrColValTxt_A2" ezfName="xxComnScrColValTxt_A2" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="curLocAcctNum_A" ezfName="curLocAcctNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsAcctNm_A3" ezfName="dsAcctNm_A3" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="indCurLocNum_A" ezfName="indCurLocNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxComnScrColValTxt_A3" ezfName="xxComnScrColValTxt_A3" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</div> <!-- right table -->
							</div> <!-- parent box -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
</script>


<%-- **** Task specific area ends here **** --%>
