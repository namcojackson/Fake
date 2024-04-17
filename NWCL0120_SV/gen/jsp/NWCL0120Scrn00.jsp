<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160411111731 --%>
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
			<input type="hidden" name="pageID" value="NWCL0120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="EasyPAC1 Invoice List Review Screen">





<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### FROM HEADER ################################################### --%>
				
				<ezf:skip>
				<div>
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="ICMS Tax Mst" class="pTab_ON"><a href="#">ICMS Tax Mst</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div>
					<table width="" border="0" cellpadding="0" cellspacing="0">
						<col align="center" width="150">
						<col align="left" width="120">	<!-- From Date -->
						<col align="center" width="20">
						<col align="left" width="120">	<!-- To Date -->
						<col align="right" width="600">	<!-- Search -->
						<tr>
						</tr>
						<tr>
							<td>Invoice Date From</td>
							<td>
								<ezf:inputText name="invDt_FM" ezfName="invDt_FM" value="03/15/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('invDt_FM', 4);" >
							</td>
							<td>-</td>
							<td>
								<ezf:inputText name="invDt_TO" ezfName="invDt_TO" value="03/15/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('invDt_TO', 4);" >
							</td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
						</tr>
					</table>
					
					<hr>
<%-- #################################################### TO HEADER ################################################### --%>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="1120" height="35">
						<tr>
							<td width="3">&nbsp;</td>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td align="right" style="padding-right:10px;">

<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
								<table border="0" cellpadding="0" cellspacing="0">
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
										<td class="pOut">6</td>
										<td class="stab">of</td>
										<td class="pOut">6</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" otherAttr=" id=\"btnPrev\""/></td>
										<td></td>
										<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" otherAttr=" id=\"btnNext\""/></td>
									</tr>
								</table>
 After Convert to JSP, this area suppose to be deleted [TO] --%>

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

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div id="parentBoxA">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col align="left" valign="top" width="1107">
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
							
							
								<div style="float:left; display:block"> <!-- left table -->
									<div id='leftTblHead' style="display:block;">
									</div>
									<div id='leftTbl' style="float:left; display:block; height:438px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
									</div>
								</div>  <!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id='rightTblHead' style="width:1107px; display:block; overflow:hidden; margin:0px;padding:0px;">

									<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1306px" style="margin-right:20px" >
										<col align="center" width="080">	<!-- Exclude from invoice List Creation -->
										<col align="center" width="070">	<!-- Account# -->
										<col align="center" width="070">	<!-- Bill To -->
										<col align="center" width="162">	<!-- Bill To Name -->
										<col align="center" width="080">	<!-- Invoice Date -->
										<col align="center" width="080">	<!-- Invoice# -->
										<col align="center" width="070">	<!-- Order# -->
										<col align="center" width="162">	<!-- Order Category -->
										<col align="center" width="162">	<!-- Order Reason Code -->
										<col align="center" width="040">	<!-- Short fall -->
										<col align="center" width="080">	<!-- Net Value -->
										<col align="center" width="250">	<!-- Invoice Error Message -->
										<tr height="48">
											<td id="AH0" class="pClothBs colFix">Exclude From<br>Invoice List<br>Creation</td>
											<td id="AH1" class="pClothBs colFix">Account#</td>
											<td id="AH2" class="pClothBs colFix">Bill To</td>
											<td id="AH3" class="pClothBs colFix">Bill To Name</td>
											<td id="AH4" class="pClothBs colFix">Invoice Date</td>
											<td id="AH5" class="pClothBs colFix">Invoice#</td>
											<td id="AH6" class="pClothBs">Order#</td>
											<td id="AH7" class="pClothBs">Order<br>Category</td>
											<td id="AH8" class="pClothBs">Order<br>Reason<br>Code</td>
											<td id="AH9" class="pClothBs">Short<br>fall</td>
											<td id="AH10" class="pClothBs">Net Value</td>
											<td id="AH11" class="pClothBs">Invoice Error Message</td>
										</tr>
									</table>
								</div>
								<div  id="rightTbl" style="width:1124px; height:455px; display:block; overflow:scroll; margin:0px; padding:0px;" 
								onScroll="synchroScrollLeft( this.id, new Array( 'rightTblHead' ) );synchroScrollTop( this.id, new Array( 'leftTbl' ) );">
									
									
									<table id="A" border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="1306px" >
											<col align="center" width="080">	<!-- Exclude from invoice List Creation -->
											<col align="left" width="070">		<!-- Account# -->
											<col align="left" width="070">		<!-- Bill To -->
											<col align="left" width="162">		<!-- Bill To Name -->
											<col align="left" width="080">		<!-- Invoice Date -->
											<col align="left" width="080">		<!-- Invoice# -->
											<col align="left" width="070">		<!-- Order# -->
											<col align="left"  width="162">		<!-- Order Category -->
											<col align="left" width="162">		<!-- Order Reason Code -->
											<col align="center" width="040">	<!-- Short fall -->
											<col align="right" width="080">		<!-- Net Value -->
											<col align="left" width="250">		<!-- Invoice Error Message -->
											<ezf:row ezfHyo="A">
											<tr id="id_row${EZF_ROW_NUMBER}" height="28">
												<td><ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A0{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:label name="sellToCustCd_A0" ezfName="sellToCustCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="billToCustCd_A0" ezfName="billToCustCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="dsAcctNm_A0" ezfName="dsAcctNm_A0" value="WWWWWWWWWXWWWWWWWWWXWWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"30\""/></td>
												<td><ezf:label name="invDt_A0" ezfName="invDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invNum_A0" ezfName="invNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="cpoOrdNum_A0" ezfName="cpoOrdNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="dsOrdCatgDescTxt_A0" ezfName="dsOrdCatgDescTxt_A0" value="ORDER CATEGORY 6789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"25\""/></td>
												<td><ezf:inputText name="dsOrdTpDescTxt_A0" ezfName="dsOrdTpDescTxt_A0" value="ORDER REASON CODE 9012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"25\""/></td>
												<td><ezf:label name="xxFlgNm_A0" ezfName="xxFlgNm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="invTotDealNetAmt_A0" ezfName="invTotDealNetAmt_A0" value="123456789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
												<td><ezf:inputText name="invErrMsgTxt_A0" ezfName="invErrMsgTxt_A0" value="Invoice ErrorMessage" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"34\" maxlength=\"50\""/></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											</ezf:skip>
									</table>
								</div>
							</div>
							<script type="text/javascript" defer>
								S21TableUI.initialize("parentBoxA", "AHEAD", "A", 7);
							</script>

							</td>
							<!-- @@@@@ Right -->
							
						</tr>
					</table>
					</div>
							
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>






<%-- **** Task specific area ends here **** --%>
