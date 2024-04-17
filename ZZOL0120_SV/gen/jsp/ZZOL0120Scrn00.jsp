<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100923045959 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Process Group Maintenance">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<!-- include S21BusinessProcessTAB -->
		<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
		<%--
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
			<tr>
				<td width="1100px" height="28px" valign="bottom">
						<div>
							<table border="0" cellpadding="0" cellspacing="0">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
										<tr title='Order Entry'>
											<td width="107px" align="center" class="same">
												Order Entry
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Hold Release'>
											<td width="90px" align="center" class="disabled">
												Hld Rlse
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Soft Allocation'>
											<td width="90px" align="center" class="disabled">
												Soft Alloc
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Hard Allocation'>
											<td width="90px" align="center" class="disabled">
												Hard Alloc
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Order Inquiry by Status'>
											<td width="90px" align="center" class="disabled">
												Ordr Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Sales Summary'>
											<td width="90px" align="center" class="disabled">
												Sales Sum
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='ATP Inquiry by Order'>
											<td width="90px" align="center" class="disabled">
												ATP Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='iW Remote Upload'>
											<td width="90px" align="center" class="disabled">
												iW Rmt Upld
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='iW Remote Inquiry'>
											<td width="90px" align="center" class="disabled">
												iW Rmt Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Upload Screen for LAN Data from Canon Inc.'>
											<td width="90px" align="center" class="disabled">
												LAN Upld
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
							</table>
						</div>
				</td>
				<td valign="bottom" align="center">
						<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
				</td>
				<td valign="bottom" align="center">
					<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
				</td>
			</tr>
		</table>
		--%>
		<div class="pTab_BODY">
			<table width="100%" height="5" border="0">
				<tr valign="middle">
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" height="35" border="0">
				<col width="120">
				<col width="130">
				<col width="40">
				<col width="100">
				<col width="510">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab" align="left"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
					<td><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ABR" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
					<td class="pout" id="fcNmField"><ezf:label name="glblCmpyNm" ezfName="glblCmpyNm" /></td>
					<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
				</tr>
				<tr>
					<td colspan="5"><hr></td>
				</tr>
			</table>
			
			<!-- header -->
			<table border="0" >
				<col width="50">
				<col width="900">
				<td>&nbsp;</td>
				<td>
					<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
						<col width="230" align="center">
						<col width="100" align="center">
						<col width="50"  align="center">
						<col width="300" align="center">
						<col width="200" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs">Process Group Name</td>
							<td class="pClothBs">Group Code</td>
							<td class="pClothBs">Order</td>
							<td class="pClothBs">Description</td>
							<td class="pClothBs">EAR Name</td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
						<tr height="30">
							<td><ezf:inputText name="menuProcGrpNm" ezfName="menuProcGrpNm" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"28\""/></td>
							<td><ezf:inputText name="menuProcGrpCd" ezfName="menuProcGrpCd" value="9999999999" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
							<td><ezf:inputText name="menuProcGrpSortNum" ezfName="menuProcGrpSortNum" value="99999" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
							<td><ezf:inputText name="menuProcGrpDescTxt" ezfName="menuProcGrpDescTxt" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"36\""/></td>
							<td><ezf:inputText name="wfAppNm" ezfName="wfAppNm" value="XXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"23\""/></td>
							<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn4" />
							<ezf:inputButton name="Upd" value="Upd" htmlClass="pBtn4" /></td>
						</tr>
					</table>
					<table border="0" >
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
						<col width="230" align="center">
						<col width="100" align="center">
						<col width="50"  align="center">
						<col width="300" align="center">
						<col width="200" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuProcGrpNm_A2' )">Process Group Name<img id="sortIMG.menuProcGrpNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuProcGrpCd_A2' )">Group Code<img id="sortIMG.menuProcGrpCd_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuProcGrpSortNum_A2' )">Order<img id="sortIMG.menuProcGrpSortNum_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuProcGrpDescTxt_A2' )">Description<img id="sortIMG.menuProcGrpDescTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wfAppNm_A2' )">EAR Name<img id="sortIMG.wfAppNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
					</table>
					<div style="overflow-y:scroll; height:387; width:920;">
						<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
							<col width="30" align="center">
							<col width="200" align="left">
							<col width="100" align="right">
							<col width="50" align="right">
							<col width="300" align="left">
							<col width="200" align="left">
							<col width="120" align="center">
							<ezf:row ezfHyo="A">
							<tr height="24">
								<td ><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td ><ezf:label name="menuProcGrpNm_A2" ezfName="menuProcGrpNm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
								<td ><ezf:label name="menuProcGrpCd_A2" ezfName="menuProcGrpCd_A2" ezfHyo="A" ezfArrayNo="0" /></td>
								<td ><ezf:label name="menuProcGrpSortNum_A2" ezfName="menuProcGrpSortNum_A2" ezfHyo="A" ezfArrayNo="0" /></td>
								<td ><ezf:label name="menuProcGrpDescTxt_A2" ezfName="menuProcGrpDescTxt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
								<td ><ezf:label name="wfAppNm_A2" ezfName="wfAppNm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" />
								<ezf:inputButton name="Detail" value="Detail" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" /></td>
							</tr>
							</ezf:row>
							<ezf:skip>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Logistics Process</label></td>
								<td ><label ezfout>0000000002</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Logistics Description</label></td>
								<td ><label ezfout>Logistics EAR</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" />
								<input type="Button" name="Detail" value="Detail" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Billing & AR Process</label></td>
								<td ><label ezfout>0000000003</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Billing Description</label></td>
								<td ><label ezfout>Billing EAR</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" />
								<input type="Button" name="Detail" value="Detail" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							</ezf:skip>
						</table>
					</div>
				</td>
			</table>
		</div>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
