<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100129054510 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0120Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Process Maintenance">
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
			<table width="100%" border="0">
				<col width="120">
				<col width="130">
				<col width="40">
				<col width="100">
				<col width="510">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab">Global Company CD</td>
					<td class="pout" ><ezf:label name="glblCmpyCd" ezfName="glblCmpyCd" /></td>
					<td class="pout" ><ezf:label name="glblCmpyNm" ezfName="glblCmpyNm" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5"><hr></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<col width="120">
				<col width="130">
				<col width="220">
				<col width="430">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab">Process Group</td>
					<td class="pout" ><ezf:label name="menuProcGrpNm_B1" ezfName="menuProcGrpNm_B1" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>

			<!-- header -->
			<table border="0" >
				<col width="50">
				<col width="900">
				<td>&nbsp;</td>
				<td>
					<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
						<col width="300" align="center">
						<col width="50"  align="center">
						<col width="430" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs">Process</td>
							<td class="pClothBs">PopUp</td>
							<td class="pClothBs">URL</td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
						<tr height="30">
							<td><ezf:inputText name="menuProcNm_B1" ezfName="menuProcNm_B1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"30\""/></td>
							<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" /></td>
							<td><ezf:inputText name="othSysUrl_B1" ezfName="othSysUrl_B1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"50\""/></td>
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
						<col width="300" align="center">
						<col width="50"  align="center">
						<col width="430" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'menuProcNm_B2' )">Process<img id="sortIMG.menuProcNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'othSysFlg_B2' )">PopUp<img id="sortIMG.othSysFlg_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'othSysUrl_B2' )">URL<img id="sortIMG.othSysUrl_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
					</table>
					<div style="overflow-y:scroll; height:362; width:920;">
						<table id="B" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
							<col width="30" align="center">
							<col width="270" align="left">
							<col width="50" align="center">
							<col width="430" align="left">
							<col width="120" align="center">
							<ezf:row ezfHyo="B">
							<tr height="24">
								<td ><ezf:inputCheckBox name="xxChkBox_B2" ezfName="xxChkBox_B2" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
								<td ><ezf:label name="menuProcNm_B2" ezfName="menuProcNm_B2" ezfHyo="B" ezfArrayNo="0" /></td>
								<td ><ezf:label name="othSysFlg_B2" ezfName="othSysFlg_B2" ezfHyo="B" ezfArrayNo="0" /></td>
								<td ><ezf:label name="othSysUrl_B2" ezfName="othSysUrl_B2" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn4" />
								<ezf:inputButton name="Detail" value="Detail" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn4" /></td>
							</tr>
							</ezf:row>
							<ezf:skip>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Loan/Trial</label></td>
								<td ><label ezfout>Y</label></td>
								<td ><label ezfout>&nbsp;</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" />
								<input type="Button" name="Detail" value="Detail" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Intangible</label></td>
								<td ><label ezfout>N</label></td>
								<td ><label ezfout>&nbsp;</label></td>
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
