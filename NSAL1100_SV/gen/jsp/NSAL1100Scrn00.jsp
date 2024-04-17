<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180612160947 --%>
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
			<input type="hidden" name="pageID" value="NSAL1100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Approval List">

<% 
	String modeCd = ((business.servlet.NSAL1100.NSAL1100Bean)databean).getXxModeCd_P(); 
%>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Credit Rebill Detail Screen" class="pTab_ON"><a href="#">Cr Rbl Dtl</a></li>
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

				<div class="pTab_BODY">
				<table border="0" width="99%" align="center">
					<tr height="35" valign="bottom">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="20">
								<col width="110" align="left">
								<col width="100">
								<col width="20">
								<col width="60" align="left">
								<col width="90">
								<col width="20">
								<col width="50" align="left">
								<col width="150">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Customer Incident#</td>
									<td><ezf:inputText name="custIncdtId_H" ezfName="custIncdtId_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
									<td>&nbsp;</td>
									<td class="stab">CR Status</td>
									<td><ezf:inputText name="svcCrRebilStsDescTxt_H" ezfName="svcCrRebilStsDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
									<td>&nbsp;</td>
<!-- START 2018/06/12 T.Ogura [QC#22382,MOD] -->
<!--									<td class="stab">Description</td>-->
									<td class="stab">Reason</td>
<!-- END   2018/06/12 T.Ogura [QC#22382,MOD] -->
									<td><ezf:inputText name="svcCrRebilDescTxt_H" ezfName="svcCrRebilDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"50\" maxlength=\"4000\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="85%" border="0">
					<tr align="right">
						<td>
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">200</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

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

<%-- ######################################## DETAIL ######################################## --%>
				<table width="95%" border="0" align="center">
					<tr>
						<td>
							<div id="Top" style="width:931px; height:40px; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="50"  align="center">	<!-- Seq -->
									<col width="200" align="center">	<!-- user -->
									<col width="100" align="center">	<!-- Approval Date-->
									<col width="360" align="center">	<!-- Comments -->
									<col width="200" align="center">	<!-- Actual Approver -->
									<tr height="40px">
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxRowNum_A')">Seq</a><img id="sortIMG.xxRowNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxWfAsgToNm_A')">User</a><img id="sortIMG.xxWfAsgToNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTxt_A')">Approval Date</a><img id="sortIMG.xxDtTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxWfCmntTxt_A')">Comments</a><img id="sortIMG.xxWfCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxWfActOpNm_A')">Actual Approver</a><img id="sortIMG.xxWfActOpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:948px; height:302px; overflow-y:scroll;" >
								<table id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="50"  align="right">		<!-- Seq -->
									<col width="200" align="left">		<!-- user -->
									<col width="100" align="center">	<!-- Approval Date-->
									<col width="360" align="left">		<!-- Comments -->
									<col width="200" align="left">		<!-- Actual Approver -->
									<ezf:row ezfHyo="A">
										<tr height="25px">
											<td><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxWfAsgToNm_A" ezfName="xxWfAsgToNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:label name="xxDtTxt_A" ezfName="xxDtTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxWfCmntTxt_A" ezfName="xxWfCmntTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="xxWfActOpNm_A" ezfName="xxWfActOpNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										</tr>
										<ezf:skip>
										</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<hr>

				<table border="0" width="95%" align="center">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
<!-- START 2018/06/12 T.Ogura [QC#22382,DEL] -->
<!--
								<col width="50" valign="top">
								<col width="200" valign="top">
								<col width="50" valign="top">
-->
<!-- END   2018/06/12 T.Ogura [QC#22382,DEL] -->
								<col width="65" valign="top">
								<col width="400" valign="top">
								<tr height="70px">
<!-- START 2018/06/12 T.Ogura [QC#22382,DEL] -->
<!--
									<td class="stab">Reason</td>
									<td>
									<% if ("2".equals(modeCd)) { %>
										<select name="svcCrRebilRsnCd" ezfname="svcCrRebilRsnCd" ezflist="svcCrRebilRsnCd_L,svcCrRebilRsnDescTxt_L,99, ,blank" style="width:200px">
											<option value=""></option>
											<option value="1">METER- INCORRECT READ ENTRY</option>
											<option value="2">METER-ADJUSTED/OVER ESTIMATED</option>
											<option value="3">GS-INCORRECT RENEWED START READ</option>
											<option value="4">SS- INCORRECT RENEWED START READ</option>
											<option value="5">SS-INCORRECT CONT LINE SETUP</option>
											<option value="6">SYS-INCORRECT TEST COPY ALLOCATION</option>
											<option value="7">SYS-MISSED BILLING CYCLES BILLED AT ZERO</option>
											<option value="8">OTHER CONTRACT ISSUES</option>
										</select>
									<% } else  { %>
										<select name="svcCrRebilRsnCd" ezfname="svcCrRebilRsnCd" ezflist="svcCrRebilRsnCd_L,svcCrRebilRsnDescTxt_L,99, ," style="width:200px">
											<option value="1">METER- INCORRECT READ ENTRY</option>
											<option value="2">METER-ADJUSTED/OVER ESTIMATED</option>
											<option value="3">GS-INCORRECT RENEWED START READ</option>
											<option value="4">SS- INCORRECT RENEWED START READ</option>
											<option value="5">SS-INCORRECT CONT LINE SETUP</option>
											<option value="6">SYS-INCORRECT TEST COPY ALLOCATION</option>
											<option value="7">SYS-MISSED BILLING CYCLES BILLED AT ZERO</option>
											<option value="8">OTHER CONTRACT ISSUES</option>
										</select>
									<% } %>
									</td>
									<td>&nbsp;</td>
-->
<!-- END   2018/06/12 T.Ogura [QC#22382,DEL] -->
									<td class="stab">Comment</td>
									<td><ezf:textArea name="svcCrRebilRsnTxt" ezfName="svcCrRebilRsnTxt" otherAttr=" cols=\"70\" rows=\"4\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="">
								<col width="2">
								<col width="">
								<tr height="24">
									<td><ezf:inputButton name="Continue" value="Continue" htmlClass="pBtn9" otherAttr=" id=\"Continue\""/></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="OverrideApprove" value="Override Approve" htmlClass="pBtn9" otherAttr=" id=\"OverrideApprove\""/></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="CancelCI" value="Cancel CI" htmlClass="pBtn9" otherAttr=" id=\"CancelCI\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>


<%-- **** Task specific area ends here **** --%>
