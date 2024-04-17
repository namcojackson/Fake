<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160706100413 --%>
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
			<input type="hidden" name="pageID" value="NFCL0740Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Write-Off Request Creation">


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">WriteOff Req</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">
						<tr height="30">
							<td></td>
						</tr>
						<tr>
							<td valign="top">
							<fieldset>
								<table border="0" style="table-layout:fixed;">
									<col width="10">
									<col width="150">
									<col width="250">
									<col width="50">
									<col width="150">
									<col width="450">
									<tr height="30">
										<td></td>
										<td class="stab">Reason</td>
										<td>
											<ezf:select name="arAdjRsnCd_H1" ezfName="arAdjRsnCd_H1" ezfBlank="1" ezfCodeName="arAdjRsnCd_LC" ezfDispName="arAdjRsnNm_LD" otherAttr=" style=\"width:240px;\""/>
										</td>
										<td></td>
										<td class="stab">Activity Name</td>
										<td>
											<ezf:select name="arAdjTpCd_H1" ezfName="arAdjTpCd_H1" ezfBlank="1" ezfCodeName="arAdjTpCd_LC" ezfDispName="arAdjTpDescTxt_LD" otherAttr=" style=\"width:420px;\""/>
										</td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab">Remaining Balance Low</td>
										<td><ezf:inputText name="dealRmngBalGrsAmt_H1" ezfName="dealRmngBalGrsAmt_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"19\" maxlength=\"19\""/></td>
										<td></td>
										<td class="stab">Remaining Balance High</td>
										<td><ezf:inputText name="dealRmngBalGrsAmt_H2" ezfName="dealRmngBalGrsAmt_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"19\" maxlength=\"19\""/></td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab">Invoice Number From</td>
										<td><ezf:inputText name="arTrxNum_H1" ezfName="arTrxNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
										<td></td>
										<td class="stab">Invoice Number To</td>
										<td><ezf:inputText name="arTrxNum_H2" ezfName="arTrxNum_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab">Due Date From</td>
										<td>
											<ezf:inputText name="invDueDt_H1" ezfName="invDueDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDueDt_H1', 4);" >
										</td>
										<td></td>
										<td class="stab">Due Date To</td>
										<td>
											<ezf:inputText name="invDueDt_H2" ezfName="invDueDt_H2" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDueDt_H2', 4);" >
										</td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab" ><ezf:anchor name="Click_LinkCustomer1" ezfName="Click_LinkCustomer1" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer1" >Customer Number</ezf:anchor></td>
										<td>
											<ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"10\" maxlength=\"20\""/>&nbsp;
											<ezf:inputButton name="Click_SetCustomerName1" value=">>" htmlClass="pBtn0" />
										</td>
										<td></td>
										<td class="stab">Customer Name</td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="NJ DEPT OF LAW & PUBLIC SAFETY" otherAttr=" size=\"40\" maxlength=\"360\""/></td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab">Invoice Class</td>
										<td>
											<ezf:select name="invTpCd_H1" ezfName="invTpCd_H1" ezfBlank="1" ezfCodeName="invTpCd_LC" ezfDispName="invTpNm_LD" otherAttr=" style=\"width:130px;\""/>
										</td>
										<td></td>
										<td class="stab">Option</td>
										<td rowspan="2" class="stab" valign="top">
											<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr=" otherattr=\"onclick=\\\" =\"\""/></input>Generate Report Only<br>
											<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr=" otherattr=\"onclick=\\\" =\"\""/></input>Create Adjustment
										</td>
									</tr>
									<tr height="30">
										<td></td>
										<td class="stab">Include Consolidated Invoice</td>
										<td><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" /></td>
										<td></td>
										<td class="stab"></td>
									</tr>
								</table>
							</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<style type="text/css">
	.myBtn{margin-left:3px;height:19px;width:16px;}
</style>


<%-- **** Task specific area ends here **** --%>
