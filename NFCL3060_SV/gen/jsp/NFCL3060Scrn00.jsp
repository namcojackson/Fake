<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160714100714 --%>
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
			<input type="hidden" name="pageID" value="NFCL3060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Installment Invoice Inquiry Screen">
			
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
					<table width="92%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<fieldset>
								<br></br>
									<table>
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="300">
											<td class="stab">Invoice Number</td>
											<td>
												<ezf:inputText name="arTrxNum" ezfName="arTrxNum" value="162524365" otherAttr=" size=\"25\" maxlength=\"25\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Payment Term</td>
											<td>
												<ezf:inputText name="xxDplyByItemNm" ezfName="xxDplyByItemNm" value="CW CHECK WITH ORDER" otherAttr=" size=\"70\" maxlength=\"70\""/>
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
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;"></div>
										<div id="leftTbl" style="float:left; display:block; height:380px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1044px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1044px" style="margin-right:20px">
													<col width="15"   align="center">
													<col width="100"  align="center">
													<col width="90"   align="center">
													<col width="90"   align="center">
													<col width="90"   align="center">
													<col width="128"  align="center">
													<col width="128"  align="center">
													<col width="90"   align="center">
													<tr height="24">
														<td class="pClothBs " id="AH0">&nbsp;</td>
														<td class="pClothBs " id="AH1">Invoice Number</td>
														<td class="pClothBs " id="AH2">Installment Number</td>
														<td class="pClothBs " id="AH3">Class</td>
														<td class="pClothBs " id="AH4">Due Date</td>
														<td class="pClothBs " id="AH5">Original Amount</td>
														<td class="pClothBs " id="AH6">Balance Due</td>
														<td class="pClothBs " id="AH7">Status</td>
													</tr>
											</table>
										</div><!-- rightTblHead-->
										<div id="rightTbl" style="width:1062px; height:410px; display:block; overflow-x:hidden; overflow-y:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1044px">
												<col width="15"   align="center">
												<col width="100"  align="left">
												<col width="90"   align="right">
												<col width="90"   align="left">
												<col width="90"   align="center">
												<col width="128"  align="right">
												<col width="128"  align="right">
												<col width="90"   align="left">
						 								<ezf:row ezfHyo="A">
															<tr height="20px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td style="height:25px; text-align:center"><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"radio#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:label name="arTrxNum_A" ezfName="arTrxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="arTrxNum_B" ezfName="arTrxNum_B" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="arTrxTpDescTxt_A" ezfName="arTrxTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="invDueDt_A" ezfName="invDueDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="arCashApplyStsDescTxt_A" ezfName="arCashApplyStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</tr>
														</ezf:row>
														<ezf:skip>
														</ezf:skip>
											</table>
										</div><!-- rightTbl-->
									</div><!-- right table -->
								</td>
							</tr>
						</table>
					</div><!-- parentBoxA -->
					<table width="94%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right">
								<ezf:inputButton name="Adjust" value="Adjust" htmlClass="pBtn8" />
								<ezf:inputButton name="Activity" value="Activity" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
