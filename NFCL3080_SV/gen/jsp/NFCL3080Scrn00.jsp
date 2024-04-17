<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220824081941 --%>
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
			<input type="hidden" name="pageID" value="NFCL3080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter Details">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
			    <%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				</div>
				<%-- ###TAB - BODY --%>
			    <div class="pTab_BODY" style="padding-top:5px;" align="center">
					<table width="94%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<fieldset>
								<br></br>
									<table>
											<td class="stab">Invoice Number</td>
											<td>
												<ezf:inputText name="invNum" ezfName="invNum" value="162524365" otherAttr=" size=\"25\" maxlength=\"25\""/>
											</td>
									</table>
								<br></br>
								</fieldset>
							</td>
						</tr>
					</table>
					<hr>

<%-- ######################################## DETAIL ######################################## --%>
					<div id="parentBoxA">
						<table>
							<tr>
								<td width="10"></td>
								<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;"></div>
										<div id="leftTbl" style="float:left; display:block; height:400px; overflow:scroll; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1163px" style="margin-right:20px">
													<col width="50"    align="center">	
													<col width="75"   align="center">
													<col width="75"   align="center">
													<col width="85"   align="center">
													<col width="150"   align="center">
													<col width="100"   align="center">
													<col width="100"   align="center">
													<col width="55"   align="center">
													<col width="60"   align="center">
													<col width="60"    align="center">
													<col width="60"   align="center">
													<col width="60"   align="center">
													<col width="60"   align="center">
													<col width="80"   align="center">
													<col width="90"   align="center">
													<tr height="32">
														<td class="pClothBs " id="AH0">Line</td>
														<td class="pClothBs " id="AH1">Billing From</td>
														<td class="pClothBs " id="AH1">Billing To</td>
														<td class="pClothBs " id="AH2">Serial Number</td>
														<td class="pClothBs " id="AH3">Meter Type</td>
														<td class="pClothBs " id="AH4">Start Reading</td>
														<td class="pClothBs " id="AH5">End Reading</td>
														<td class="pClothBs " id="AH6">Test Copies</td>
														<td class="pClothBs " id="AH7">Copies Made</td>
														<td class="pClothBs " id="AH8">Multiplier</td>
														<td class="pClothBs " id="AH9">Allowance</td>
														<td class="pClothBs " id="AH10">Billable Copies</td>
														<td class="pClothBs " id="AH11">Rate</td>
														<td class="pClothBs " id="AH12">Amount</td>
														<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
														<!-- <td class="pClothBs " id="AH13">Location</td> -->
														<td class="pClothBs " id="AH13">Ship To Code</td>
														<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
													</tr>
											</table>
										</div><!-- rightTblHead-->
										<div id="rightTbl" style="width:1100px; height:430px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1163px">
													<col width="50"    align="right">	
													<col width="75"   align="left">
													<col width="75"   align="left">
													<col width="85"   align="left">
													<col width="150"   align="left">
													<col width="100"   align="right">
													<col width="100"   align="right">
													<col width="55"   align="right">
													<col width="60"   align="right">
													<col width="60"    align="right">
													<col width="60"   align="right">
													<col width="60"   align="right">
													<col width="60"   align="right">
													<col width="80"   align="right">
													<col width="90"   align="left">
						 								<ezf:row ezfHyo="A">
															<tr height="20px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td><ezf:label name="invLineNum_A" ezfName="invLineNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="bllgPerFromDt_A" ezfName="bllgPerFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="bllgPerThruDt_A" ezfName="bllgPerThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="Black and White" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="prevTotCopyQty_A" ezfName="prevTotCopyQty_A" value="546,236" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="totCopyQty_A" ezfName="totCopyQty_A" value="546,606" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="testCopyQty_A" ezfName="testCopyQty_A" value="50" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:label name="testCopyQty_B" ezfName="testCopyQty_B" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="contrMtrMultRate_A" ezfName="contrMtrMultRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="copyInclQty_A" ezfName="copyInclQty_A" value="0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:label name="bllgCopyQty_A" ezfName="bllgCopyQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" value="0.004400" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:label name="mtrChrgDealAmt_A" ezfName="mtrChrgDealAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="shipToCustCd_A" ezfName="shipToCustCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</tr>
														</ezf:row>
														<ezf:skip>
														</ezf:skip>
											</table>
										</div><!-- rightTbl-->
									</div><!-- right table -->
								</div>	
								</td>
							</tr>
						</table>
					</div><!-- parentBoxA -->
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "A", -1, true);
</script>

<%-- **** Task specific area ends here **** --%>
