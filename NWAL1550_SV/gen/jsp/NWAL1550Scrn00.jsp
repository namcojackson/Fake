<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160223144742 --%>
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
			<input type="hidden" name="pageID" value="NWAL1550Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="DI Check Screen">
			
			
			
			
			
			
	<center>
		<table width="100%">
			<tr>
				<td>
			
<%-- ######################################## HEADER ######################################## --%>
<%--				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Entry" class="pTab_ON"><a href="#">Order Entry</a></li>
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
 --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">

<%-- ##### BODY(TAB) ##### --%>

<%-- ##### TAB(Line) ##### --%>
								<table>
									<col valign="top">
									<col valign="top">
									<col valign="top">
									<tr>
										<td>
											<fieldset>
												<legend style="font-size:12px;">Order Information</legend>
												<table style="table-layout:fixed;">
													<col width="100">
													<col width="210">
													<col width="10">
													<col width="80">
													<col width="204">
													<tr>
														<td class="stab">Order Number</td>
														<td>
															<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="100123" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/>
															<ezf:inputButton name="Search_Order" value="Search" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
														</td>
														<td></td>
														<td class="stab">Order Status</td>
														<td class="pOut"><ezf:label name="ordHdrStsDescTxt" ezfName="ordHdrStsDescTxt" /></td>
													</tr>
													<tr>
														<td class="stab">Category</td>
														<td class="pOut">
															<ezf:label name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" />
														</td>
														<td></td>
														<td class="stab">Reason Code</td>
														<td class="pOut">
															<ezf:label name="dsOrdTpNm" ezfName="dsOrdTpNm" />
														</td>
													</tr>
													<tr>
														<td class="stab">Sub Reason Code</td>
														<td class="pOut">
															<ezf:label name="dsOrdRsnDescTxt" ezfName="dsOrdRsnDescTxt" />
														</td>
														<td></td>
														<td class="stab">Order Date</td>
														<td class="pOut">
															<ezf:label name="ordDt" ezfName="ordDt" />
														</td>
													</tr>
												</table>
											</fieldset>
										</td>

										<td>
											<fieldset>
												<legend style="font-size:12px;">Order Pricing Summary</legend>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col align="center" width="70">
													<col align="center" width="100">
													<col align="center" width="90">
													<col align="center" width="90">
													<col align="center" width="90">
													<tr height="18">
														<td class="pClothBs">Section</td>
														<td class="pClothBs">Amount</td>
														<td class="pClothBs">Tax</td>
														<td class="pClothBs">Charges</td>
														<td class="pClothBs">Subtotal</td>
													</tr>
												</table>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="70">
													<col width="100" align="right">
													<col width="90" align="right">
													<col width="90" align="right">
													<col width="90" align="right">
													<tr>
														<td class="stab">Lines</td>
														<td><ezf:label name="lineDealNetAmt" ezfName="lineDealNetAmt" /></td>
														<td><ezf:label name="lineDealTaxAmt" ezfName="lineDealTaxAmt" /></td>
														<td><ezf:label name="lineDealChrgAmt" ezfName="lineDealChrgAmt" /></td>
														<td><ezf:label name="lineDealSubTotAmt" ezfName="lineDealSubTotAmt" /></td>
													</tr>
													<tr>
														<td class="stab">Maintenance</td>
														<td><ezf:label name="cpoSvcDealNetAmt" ezfName="cpoSvcDealNetAmt" /></td>
														<td><ezf:label name="cpoSvcDealTaxAmt" ezfName="cpoSvcDealTaxAmt" /></td>
														<td><ezf:label name="cpoSvcDealChrgAmt" ezfName="cpoSvcDealChrgAmt" /></td>
														<td><ezf:label name="cpoSvcDealSubTotAmt" ezfName="cpoSvcDealSubTotAmt" /></td>
													</tr>
													<tr>
														<td class="stab">RMA</td>
														<td><ezf:label name="rmaDealNetAmt" ezfName="rmaDealNetAmt" /></td>
														<td><ezf:label name="rmaDealTaxAmt" ezfName="rmaDealTaxAmt" /></td>
														<td><ezf:label name="rmaDealChrgAmt" ezfName="rmaDealChrgAmt" /></td>
														<td><ezf:label name="rmaDealSubTotAmt" ezfName="rmaDealSubTotAmt" /></td>
													</tr>
												</table>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="70">
													<col width="100" align="right">
													<col width="90" align="right">
													<col width="90" align="right">
													<col width="90" align="right">
													<tr>
														<td class="stab">Totals</td>
														<td><ezf:label name="ordTotDealNetAmt" ezfName="ordTotDealNetAmt" /></td>
														<td><ezf:label name="ordTotDealTaxAmt" ezfName="ordTotDealTaxAmt" /></td>
														<td><ezf:label name="ordTotDealChrgAmt" ezfName="ordTotDealChrgAmt" /></td>
														<td><ezf:label name="ordTotDealSubTotAmt" ezfName="ordTotDealSubTotAmt" /></td>
													</tr>
												</table>
												<table style="table-layout:fixed;">
													<col width="60">
													<col width="120">
													<col width="40">
													<tr>
														<td class="stab">Invoiced</td>
														<td class="pOut" align="right"><ezf:label name="invTotDealNetAmt" ezfName="invTotDealNetAmt" /></td>
														<td class="pOut"><ezf:label name="invAmtSlsPct" ezfName="invAmtSlsPct" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<br>

								<fieldset>
									<legend style="font-size:12px;">Data Integrity Check Information</legend>
									<table border="0" cellspacing="0">
										<col align="left" valign="top">
										<col align="left" valign="top" width="150">
										<col align="left" valign="top">
										<tr>
											<td>
												<table style="table-layout:fixed;" border="0" cellspacing="0">
													<col width="170">
													<col width="200">
													<tr>
														<td>Version Number</td>
														<td>
															<ezf:select name="diChkVrsnNum_SL" ezfName="diChkVrsnNum_SL" ezfCodeName="diChkVrsnNum_CD" ezfDispName="diChkVrsnNum_NM" otherAttr=" ezftoupper=\"\""/>
														</td>
													</tr>
													<tr>
														<td>Ran Date/Time</td>
														<td class="pOut"><ezf:label name="xxDtTm" ezfName="xxDtTm" /></td>
													</tr>
													<tr>
														<td>Submitted By User</td>
														<td class="pOut"><ezf:label name="xxPsnNm" ezfName="xxPsnNm" /></td>
													</tr>
													<tr>
														<td>Ran Job ID</td>
														<td class="pOut"><ezf:label name="diJobId" ezfName="diJobId" /></td>
													</tr>
												</table>
											</td>
											<td align="left">
												<table style="table-layout:fixed;" border="0" cellspacing="0">
													<tr>
														<td>
															<ezf:inputButton name="ExecDIChk" value="Execute DI Check" htmlClass="pBtn11" />
														</td>
													</tr>
													<tr>
														<td>
															<ezf:inputButton name="RefreshDIChkRslt" value="Refresh Check Result" htmlClass="pBtn11" />
														</td>
													</tr>
												</table>
											</td>
											<td>
												<fieldset>
													<legend style="font-size:12px;">Data Integrity Summary</legend>
													<table border="1" cellpadding="1" cellspacing="0"  style="table-layout:fixed;" cellspacing="0">
														<col align="left" width="100">
														<col align="left" valign="top" width="100">
														<col align="left" valign="top" width="100">
														<tr>
															<td rowspan="2" class="pClothBs" valign="buttom" align="center">Result Type</td>
															<td colspan="2" class="pClothBs" align="center">Level</td>
														</tr>
														<tr>
															<td class="pClothBs" align="center">HEADER</td>
															<td class="pClothBs" align="center">LINE</td>
														</tr>
														<tr>
															<td align="center">Warnings</td>
															<td align="right"><ezf:label name="xxNum_WH" ezfName="xxNum_WH" /></td>
															<td align="right"><ezf:label name="xxNum_WL" ezfName="xxNum_WL" /></td>
														</tr>
														<tr>
															<td align="center">Errors</td>
															<td align="right"><ezf:label name="xxNum_EH" ezfName="xxNum_EH" /></td>
															<td align="right"><ezf:label name="xxNum_EL" ezfName="xxNum_EL" /></td>
														</tr>
													</table>
												</fieldset>
											</td>
										</tr>
									</table>
								</fieldset>

								<fieldset>
									<legend style="font-size:12px;">Data Integrity Results</legend>
									<%--------------------------------------------%>
									<%---------------- List START ----------------%>
									<%--------------------------------------------%>
									<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
										<col align="left" valign="top">
										<tr>
											<td colspan="2">
												<div id="RightTop" style="overflow-x:hidden; width:1090;"
													onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="50">
														<col align="center" width="100">
														<col align="center" width="290">
														<col align="center" width="60">
														<col align="center" width="80">
														<col align="center" width="500">
														<tr height="24">
															<td class="pClothBs">Accept</td>
															<td class="pClothBs">DI Check Code</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">Level</td>
															<td class="pClothBs">Line Number</td>
															<td class="pClothBs">Incorrect Value</td>
														</tr>
													</table>
												</div>
												<div id="Right" style="overflow-x:hidden; width:1106; height:200px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
													<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="50">
														<col align="center" width="100">
														<col align="center" width="290">
														<col align="center" width="60">
														<col align="center" width="80">
														<col align="center" width="500">
														<ezf:row ezfHyo="A">
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																<td align="left"><ezf:label name="diChkCd_A" ezfName="diChkCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="diChkNm_A" ezfName="diChkNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"100\""/></td>
																<td><ezf:inputText name="diChkLvlNm_A" ezfName="diChkLvlNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"30\""/></td>
																<td><ezf:inputText name="xxLineNum_A" ezfName="xxLineNum_A" value="WWWWWWWWW1W" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																<td><ezf:inputText name="diChkErrTxt_A" ezfName="diChkErrTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"diChkErrTxt_A#{EZF_ROW_NUMBER}\" size=\"70\" maxlength=\"2000\" style=\"border:none;\""/></td>
															</tr>
														</ezf:row>
													</table>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</fieldset>
							</div>
						</td>
					</tr>
				</table>
			</center>
			
			
			

<%-- **** Task specific area ends here **** --%>
