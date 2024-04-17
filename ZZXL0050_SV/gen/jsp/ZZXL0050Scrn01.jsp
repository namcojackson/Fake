<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100128043647 --%>
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
			<input type="hidden" name="pageID" value="ZZXL0050Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="BatchProc/Sales Date Edit">
			
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				   <!--<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
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
			<%-- item --%>
			<table width="350" align="center" border="0" cellpadding="1" cellspacing="0">
				<tr align="left">
					<td>
						<table border="0" cellpadding="1" cellspacing="0">
							<col width="170">
							<col width="180">
							<tbody>
								<tr height="35"><td/></tr>
								<!-- Global Company Code -->
								<tr align="left" height="35">
									<td class="stab">Global Company Code</td>
									<td>
										<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- Date Type -->
								<tr align="left" height="35">
									<td class="stab">Date Type</td>
									<td>
										<ezf:select name="dtProcCd_01" ezfName="dtProcCd_01" ezfCodeName="xxProcTpCd_L" ezfDispName="xxDsplTpTxt_L" />
									</td>
								</tr>
								<!-- Program ID -->
								<tr align="left" height="35">
									<td class="stab">Program ID</td>
									<td>
										<ezf:inputText name="dtMgtPgmId_01" ezfName="dtMgtPgmId_01" value="XXXXXXXXX1" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- Date -->
								<tr align="left">
									<td class="stab">Date</td>
									<td>
										<ezf:inputText name="mgtDt_01" ezfName="mgtDt_01" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mgtDt_01', 4);" >
									</td>
								</tr>
							</tbody>
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
	
	function synchroScroll_fromRightTblAction2() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL2' );
		var rightTBL    = this.document.getElementById( 'rightTBL2'     );
		var leftTBL     = this.document.getElementById( 'leftTBL2' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction2() {
		var leftTBL  = this.document.getElementById( 'leftTBL2'  );
		var rightTBL = this.document.getElementById( 'rightTBL2' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>


<%-- **** Task specific area ends here **** --%>
