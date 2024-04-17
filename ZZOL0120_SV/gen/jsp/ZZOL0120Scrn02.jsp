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
			<input type="hidden" name="pageID" value="ZZOL0120Scrn02">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Upper TAB Maintenance">
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
					<td class="pout" ><ezf:label name="menuProcGrpNm_C1" ezfName="menuProcGrpNm_C1" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab">Process</td>
					<td class="pout" ><ezf:label name="menuProcNm_C1" ezfName="menuProcNm_C1" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<!-- header -->
			<table border="0" >
				<col width="10">
				<col width="900">
				<td>&nbsp;</td>
				<td>
					<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
						<col width="220" align="center">
						<col width="50"  align="center">
						<col width="300" align="center">
						<col width="100" align="center">
						<col width="80" align="center">
						<col width="80" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs">Upper TAB Name</td>
							<td class="pClothBs">Order</td>
							<td class="pClothBs">Function List Name</td>
							<td class="pClothBs">ID</td>
							<td class="pClothBs">My Proc</td>
							<td class="pClothBs">Usable</td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
						<tr height="30">
							<td><ezf:inputText name="upTabNm_C1" ezfName="upTabNm_C1" value="XXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"12\""/></td>
							<td><ezf:inputText name="upTabSortNum_C1" ezfName="upTabSortNum_C1" value="99999" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
							<td><ezf:inputText name="bizAppNm_C1" ezfName="bizAppNm_C1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"35\""/></td>
							<td><ezf:inputText name="bizAppId_C1" ezfName="bizAppId_C1" value="XXXXXXXX" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
							<td ><ezf:inputCheckBox name="myProcUsbleFlg_C1" ezfName="myProcUsbleFlg_C1" value="Y" /></td>
							<td ><ezf:inputCheckBox name="upTabUsbleFlg_C1" ezfName="upTabUsbleFlg_C1" value="Y" /></td>
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
						<col width="220" align="center">
						<col width="50"  align="center">
						<col width="300" align="center">
						<col width="100" align="center">
						<col width="80" align="center">
						<col width="80" align="center">
						<col width="120" align="center">
						<tr height="24">
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'upTabNm_C2' )">Upper TAB Name<img id="sortIMG.upTabNm_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'upTabSortNum_C2' )">Order<img id="sortIMG.upTabSortNum_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'bizAppNm_C2' )">Function List Name<img id="sortIMG.bizAppNm_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'bizAppId_C2' )">ID<img id="sortIMG.bizAppId_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'myProcUsbleFlg_C2' )">My Proc<img id="sortIMG.myProcUsbleFlg_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'upTabUsbleFlg_C2' )">Usable<img id="sortIMG.upTabUsbleFlg_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs">&nbsp;</td>
						</tr>
					</table>
					<div style="overflow-y:scroll; height:340; width:968; table-layout:fixed;">
						<table style="table-layout:fixed" id="C" border="1" cellpadding="1" cellspacing="0">
							<col width="40" align="center">
							<col width="180" align="left">
							<col width="50" align="right">
							<col width="300" align="left">
							<col width="100" align="left">
							<col width="80" align="center">
							<col width="80" align="center">
							<col width="120" align="center">
							<ezf:row ezfHyo="C">
							<tr height="24">
								<td ><ezf:inputCheckBox name="xxChkBox_C2" ezfName="xxChkBox_C2" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="upTabNm_C2" ezfName="upTabNm_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="upTabSortNum_C2" ezfName="upTabSortNum_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="bizAppNm_C2" ezfName="bizAppNm_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="bizAppId_C2" ezfName="bizAppId_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="myProcUsbleFlg_C2" ezfName="myProcUsbleFlg_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td ><ezf:label name="upTabUsbleFlg_C2" ezfName="upTabUsbleFlg_C2" ezfHyo="C" ezfArrayNo="0" /></td>
								<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn4" /></td>
							</tr>
							</ezf:row>
							<ezf:skip>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Search</label></td>
								<td ><label ezfout>   3</label></td>
								<td ><label ezfout>Order Search</label></td>
								<td ><label ezfout>NWAL0030</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Hold Rlse</label></td>
								<td ><label ezfout>   4</label></td>
								<td ><label ezfout>Hold Release</label></td>
								<td ><label ezfout>NWAL0050</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">N</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
							</tr>
							<tr height="24">
								<td ><input type="checkbox"></td>
								<td ><label ezfout>Ord Uplord</label></td>
								<td ><label ezfout>   2</label></td>
								<td ><label ezfout>Order Upload</label></td>
								<td ><label ezfout>NWAL0020</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td ><label ezfout ezfhyo="A" name="fcId_A1" ezfname="fcId_A1">Y</label></td>
								<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
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
