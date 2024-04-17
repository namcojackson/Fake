<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100128042059 --%>
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
			<input type="hidden" name="pageID" value="ZZXL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="BatchProc/Sales Date Console">
		
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				  <!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1100px" height="28px" valign="bottom">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title='Order Entry'>
													<td width="107px" align="center" class="same">
														Archive View
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
				</table> -->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table width="100%" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr><td>&nbsp;</td></tr>
					<tr style="text-align: center">
						<td>
							<table width="674" border="0" cellpadding="1" cellspacing="0">
								<!-- Global Company Code -->
								<tr align="left">
									<td width="170" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
									<td width="284" class="stab">
										<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
									</td>
									<td width="120"></td>
								</tr>
								<!-- Date Type -->
								<tr align="left">
									<td width="170" class="stab">Date Type</td>
									<td width="384">
										<ezf:select name="dtProcCd" ezfName="dtProcCd" ezfBlank="1" ezfCodeName="xxProcTpCd_L" ezfDispName="xxDsplTpTxt_L" />
									</td>
									<td width="120"></td>
								</tr>
								<!-- Program ID -->
								<tr align="left">
									<td width="170" class="stab">Program ID(*)</td>
									<td width="384">
										<ezf:inputText name="dtMgtPgmId" ezfName="dtMgtPgmId" value="XXXXXXXXX1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
									</td>
									<td width="120" align="right">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
	<!-- **************************************** BODY **************************************** -->
				<hr size="1" width="62%" noshade>
				<%-- add --%>
				<table align="center" width="990" border="0" cellpadding="1" cellspacing="0">
					<tr align="center">
						<td>
							<table width="700" border="0" cellpadding="1" cellspacing="0">
								<col>
								<tr>
									<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<%-- item --%>
				<table  align="center" width="990" border="0" cellpadding="0" cellspacing="0">
					<tr align="center">
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="80">
								<col width="620">
								<tr>
									<td valign="top">
										<%-- LEFT-TABLE(TOP) --%>
										<div id="leftTopTBL" style="overflow-y:hidden; overflow-x:hidden;">
											<table border="1" cellpadding="1" cellspacing="0">
												<col width="30">
												<col width="50">
												<tr style="text-align: center" height="28">
													<td class="pClothBs" nowrap></td>
													<td class="pClothBs" nowrap></td>
												</tr>
											</table>
										</div>
										
										<%-- LEFT-TABLE(MID) --%>
										<div id="leftTBL" style="overflow-y:hidden; height:368; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
											<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
												<col width="26">
												<col width="46">
												<tbody>
													<ezf:row ezfHyo="A">
													<tr style="text-align: left" height="28">
														<!-- # -->
														<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<!-- Edit -->
														<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" /></td>
													</tr>
													</ezf:row>

													<ezf:skip>
													</ezf:skip>
												</tbody>
											</table>
										</div>
										
									</td>
									<td>
										<%-- RIGHT-TABLE(TOP) --%>
										<div id="rightTopTBL" style="overflow-y:hidden; overflow-x:hidden; width:604;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
											<table cellpadding="1" cellspacing="0" border="1" width="603">
												<col width="160">
												<col width="160"> 
												<col width="160">
												<tr style="text-align: left" height="28">
													<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDsplTpTxt_A' )">Date Type<img id="sortIMG.xxDsplTpTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dtMgtPgmId_A' )">Program ID<img id="sortIMG.dtMgtPgmId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mgtDt_A' )">Date<img id="sortIMG.mgtDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>
										
										<%-- RIGHT-TABLE(MID) --%>
										<div id="rightTBL" style="overflow-y:auto; height:368; overflow-x:hidden; width:620;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
											<table cellpadding="1" cellspacing="0" border="1" width="603" id="A_RightTBL">
												<col width="160">
												<col width="160">
												<col width="160">
												<tbody>
													<ezf:row ezfHyo="A">
													<tr height="28">
														<!-- Date Type -->
														<td><ezf:label name="xxDsplTpTxt_A" ezfName="xxDsplTpTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<!-- Program ID -->
														<td><ezf:label name="dtMgtPgmId_A" ezfName="dtMgtPgmId_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<!-- Date -->
														<td><ezf:label name="mgtDt_A" ezfName="mgtDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<%-- foot --%>
		</td>
	</tr>
</table>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>
<style TYPE="text/css">
	<!--
	input.cBtn1{font-family:'Arial',sans-serif;font-size:9pt;height:20;width:34;margin:0;color:#000000;}
	-->
</style>
			

<%-- **** Task specific area ends here **** --%>
