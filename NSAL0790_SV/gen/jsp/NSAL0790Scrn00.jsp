<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170302100200 --%>
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
			<input type="hidden" name="pageID" value="NSAL0790Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Fleet Rollup Detail">

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
										<li title="Fleet Rollup Detail" class="pTab_ON"><a href="#">Rlup Dtl</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table border="0" width="99%" align="center">
					<col width="" align="" valign="top">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="100">
								<tr height="21">
									<td class="stab">Contract#</td>
									<td><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="190">
								<col width="60">
								<col width="250">
								<col width="80">
								<col width="100">
								<tr height="21">
									<td class="stab">Rollup Status</td>
									<td><ezf:inputText name="fleetCalcProcDescTxt_H" ezfName="fleetCalcProcDescTxt_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
									<td class="stab">Message</td>
									<td><ezf:inputText name="vldMsgTxt_H" ezfName="vldMsgTxt_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0" otherAttr=" size=\"70\" maxlength=\"1000\""/></td>
									<td>&nbsp;</td>
									<td align="right"><ezf:inputButton name="ResubmitRollup" value="Resubmit Rollup" htmlClass="pBtn9" otherAttr=" id=\"ResubmitRollup\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="99%">
					<tr align="right">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
								<col width="200">
								<col width="40">
								<col width="218">
								<col width="120">
								<col width="520">
								<tr height='25px'>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="right" class="stab">
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"           value="A" />
											<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"          value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import = "com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import = "business.servlet.NSAL0790.NSAL0790BMsg" %>
<% NSAL0790BMsg bMsg = (NSAL0790BMsg) databean.getEZDBMsg(); %>
				<table>
					<tr>
						<td>
							<div id='rightTblHead' style="width:1098px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" width="1098px" style="margin-right:20px" >
									<col width="48"  align="center">	<!-- Entry -->
									<col width="90" align="center">		<!-- Serial# -->
									<col width="110" align="center">	<!--Counter Name -->
									<col width="105" align="center">	<!-- Current Reading -->
									<col width="80" align="center">		<!-- Test Copies -->
									<col width="80" align="center">		<!-- Bill From -->
									<col width="80" align="center">		<!-- Bill To -->
									<col width="80"  align="center">	<!-- Read Completed -->
									<col width="95" align="center">		<!-- Prior Reading -->
									<col width="130"  align="center">	<!-- Process Flag -->
									<col width="198" align="center">	<!-- Process Message -->
									<tr height="25px">
										<td class="pClothBs colFix">&nbsp;</td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Counter Name</td>
										<td class="pClothBs">Current Reading</td>
										<td class="pClothBs">Test Copies</td>
										<td class="pClothBs">Bill From</td>
										<td class="pClothBs">Bill To</td>
										<td class="pClothBs">Read Comp</td>
										<td class="pClothBs">Prior Reading</td>
										<td class="pClothBs">Process Flag</td>
										<td class="pClothBs">Process Message</td>
									</tr>
								</table>
							</div>
							<div id="rightTbl" style="width:1115px; height:420px; display:block; overflow:scroll; margin:0px; padding:0px;" >
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" width="1098px">
									<col width="48"  align="center">	<!-- Entry -->
									<col width="90" align="left">		<!-- Serial# -->
									<col width="110" align="left">		<!-- Counter Name -->
									<col width="105" align="right">		<!-- Current Reading -->
									<col width="80" align="right">		<!-- Test Copies -->
									<col width="80" align="left">		<!-- Bill From -->
									<col width="80" align="left">		<!-- Bill To -->
									<col width="80"  align="left">		<!-- Read Completed -->
									<col width="95" align="right">		<!-- Prior Reading -->
									<col width="130"  align="left">		<!-- Process Flag -->
									<col width="198" align="left">		<!-- Process Message -->
									<% int i = 0; %>
									<ezf:row ezfHyo="A">
										<tr height="25px">
											<% if (bMsg.A.no(i).xxBtnFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) { %>
											<td rowspan=<%= bMsg.A.no(i).xxRowNum_A.getValue() %>><ezf:inputButton name="Entry" value="Entry" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"Entry\""/></td>
											<td rowspan=<%= bMsg.A.no(i).xxRowNum_A.getValue() %>><ezf:inputText name="serNum_A" ezfName="serNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<% } %>
											<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:label name="curReadMtrCnt_A" ezfName="curReadMtrCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="testCopyQty_A" ezfName="testCopyQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="curBllgPerFromDt_A" ezfName="curBllgPerFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="curBllgPerThruDt_A" ezfName="curBllgPerThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="mtrEntryCpltFlg_A" ezfName="mtrEntryCpltFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="priReadMtrCnt_A" ezfName="priReadMtrCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="fleetCalcProcDescTxt_A" ezfName="fleetCalcProcDescTxt_A" value="ReBill InComplete" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="vldMsgTxt_A" ezfName="vldMsgTxt_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										</tr>
										<% i++; %>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
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
