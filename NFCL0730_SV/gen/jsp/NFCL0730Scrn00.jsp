<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221122120437 --%>
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
			<input type="hidden" name="pageID" value="NFCL0730Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Invoice Adjustment">


<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">

					<table width="100%" border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
								<table width="1120" border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="050">
												<col width="120">
												<col width="080">
												<col width="120">
												<col width="">
												<tr>
													<td class="stab">Invoice#</td>
													<td><ezf:inputText name="arTrxNum_H1" ezfName="arTrxNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/>
													<td class="stab">Invoice Balance</td>
													<td><ezf:inputText name="dealRmngBalGrsAmt_H1" ezfName="dealRmngBalGrsAmt_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/>
												</tr>
											</table>
											<hr>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="050">
												<col width="380">
												<col width="090">
												<col width="120">
												<col width="050">
												<col width="120">
												<col width="140">
												<tr>
													<td class="stab">Activity</td>
													<td>
														<ezf:select name="arAdjTpCd_H1" ezfName="arAdjTpCd_H1" ezfBlank="1" ezfCodeName="arAdjTpCd_LC" ezfDispName="arAdjTpDescTxt_LD" otherEvent1=" onchange=\"sendServer('Onchange_PD_Activity')\"" otherAttr=" style=\"width:360px;\""/>
													</td>
													<td class="stab">Adjustment Date</td>
													<td>
														<ezf:inputText name="adjDt_H1" ezfName="adjDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('adjDt_H1', 4);" >
													</td>
													<td class="stab">Amount</td>
													<td><ezf:inputText name="dealArAdjAmt_H1" ezfName="dealArAdjAmt_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"19\""/></td>
													
													<td>
														<ezf:inputButton name="AddLine" value="Add" htmlClass="pBtn6" />
													</td>
												</tr>
												<tr>
													<td class="stab">Comments</td>
													<td colspan="2" ><ezf:inputText name="adjCmntTxt_H1" ezfName="adjCmntTxt_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"65\" maxlength=\"65\" ezftoupper=\"\""/></td>
													<td class="stab" colspan="4" >
														Charge Account
														<ezf:inputText name="xxCmntTxt_H1" ezfName="xxCmntTxt_H1" value="110.210.343210.46543210.50.610.710.80.910" otherAttr=" size=\"41\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/>
														<ezf:inputButton name="OpenWin_ChargeAccount" value="A" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" tabindex=\"-1\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td>
								<div id="LeftTable_A_Top" style="overflow-x:hidden; overflow-y:hidden; width:1060; float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'LeftTable_A' ) );">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
										<col width="030" align="center">
										<col width="130" align="center">
										<col width="240" align="center">
										<col width="130" align="center">
										<col width="130" align="center">
										<col width="200" align="center">
										<col width="200" align="center">
										<col width="300" align="center">
										<tr>
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Number</td>
											<td class="pClothBs">Activity Name</td>
											<td class="pClothBs">Adjustment Date</td>
											<td class="pClothBs">Amount</td>
											<td class="pClothBs">Comments</td>
											<td class="pClothBs">Note</td>
											<td class="pClothBs">Charge Account</td>
										</tr>
									</table>
								</div>
								<div id="LeftTable_A" style="overflow-y:scroll; height:435; overflow-x:scroll; width:1079; float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'LeftTable_A_Top' ) );">
									<table border="1" cellpadding="1" cellspacing="0"  style="table-layout: fixed">
										<col width="030" align="center">
										<col width="130" align="left">
										<col width="240" align="left">
										<col width="130" align="center">
										<col width="130" align="right">
										<col width="200" align="left">
										<col width="200" align="left">
										<col width="300" align="left">
										<ezf:row ezfHyo="A">
											<tr height="24" id="id_row{EZF_ROW_NUMBER}">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:label name="arAdjNum_A1" ezfName="arAdjNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arAdjNum_A1\""/></td>
												<td><ezf:inputText name="arAdjTpDescTxt_A1" ezfName="arAdjTpDescTxt_A1" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:label name="glDt_A1" ezfName="glDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"glDt_A1\""/></td>
												<td><ezf:label name="dealApplyAmt_A1" ezfName="dealApplyAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealApplyAmt_A1\""/></td>
												<td><ezf:inputText name="adjCmntTxt_A1" ezfName="adjCmntTxt_A1" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="arWrtOffNoteTxt_A1" ezfName="arWrtOffNoteTxt_A1" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="xxCmntTxt_A1" ezfName="xxCmntTxt_A1" value="110.210.343210.46543210.50.610.710.80.910" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\" style=\"border:none; background-color:transparent;\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="1100" align="right">
						<tr>
							<td>
								<ezf:inputButton name="DeleteLines" value="Delete" htmlClass="pBtn6" />
							</td>
						<tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
