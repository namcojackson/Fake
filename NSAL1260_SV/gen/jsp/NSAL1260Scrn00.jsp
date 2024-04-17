<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160224123549 --%>
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
			<input type="hidden" name="pageID" value="NSAL1260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Machine Upload">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table style="margin-top: 0px; margin-left: 10px" border="0" width="800px">
						<tr>
							<td>
								<fieldset style="margin:5px 0px 0px 0px; height:95%;">
								<legend style="font-size:12px;">Contract Header Information</legend>
								<table>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="2" cellspacing="0">
												<col width="75" align="right">
												<col width="100">
												<col width="10">
												<col width="300">
												<tr>
													<td class="stab">Account</td>
													<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" tabindex=\"1\""/></td>
													<td></td>
													<td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"50\" tabindex=\"1\""/></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="2" cellspacing="0">
												<col width="75" align="right">
												<col width="100">
												<col width="20" align="right">
												<col width="100">
												<col width="160" align="right">
												<col width="220">
												<tr>
													<td class="stab">Effective from</td>
													<td><ezf:inputText name="contrVrsnEffFromDt_H" ezfName="contrVrsnEffFromDt_H" value="01/01/2015" otherAttr=" size=\"12\" tabindex=\"1\""/></td>
													<td class="stab">to</td>
													<td><ezf:inputText name="contrVrsnEffThruDt_H" ezfName="contrVrsnEffThruDt_H" value="12/31/2018" otherAttr=" size=\"12\" tabindex=\"1\""/></td>
													<td class="stab">Base Frequency</td>
													<td><ezf:inputText name="bllgCycleDescTxt_H" ezfName="bllgCycleDescTxt_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" tabindex=\"1\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</fieldset>
							</td>
					</tr>
					</table>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table style="margin-top: 5px; margin-left: 10px" border="0" width="960px">
						<col width="20">
						<col width="150">
						<col width="70">
						<col width="">
						<tr>
							<td class="stab">File</td>
							<td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"30\" maxlength=\"9999\""/></td>
							<td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn5" /></td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="170" align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td class="stab">Results 1 - 100 of 489  Showing</td>
										<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" ></td>
										<td class="stab">/</td>
										<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="3" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" ></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
									</tr>
								</table>
								</ezf:skip>

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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" width="99%" align="center">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width="25"  align="center">	<!-- Select -->
									<col width="80"  align="center">	<!-- IB ID -->
									<col width="90"  align="center">	<!-- Serial# -->
									<col width="135" align="center">	<!-- Frequency -->
									<col width="90"  align="center">	<!-- Price -->
									<col width="170" align="center">	<!-- Meter Read Method -->
									<col width="80"  align="center">	<!-- Account# -->
									<col width="100" align="center">	<!-- Account Name -->
									<col width="80"  align="center">	<!-- Effective From -->
									<col width="80"  align="center">	<!-- Effective To -->
									<col width="30"  align="center">	<!-- Bill Usg -->
									<tr>
										<td class="pClothBs"></td>
										<td class="pClothBs">IB ID</td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Frequency</td>
										<td class="pClothBs">Price</td>
										<td class="pClothBs">Meter Read Method</td>
										<td class="pClothBs">Account#</td>
										<td class="pClothBs">Account Name</td>
										<td class="pClothBs">Effective<br>From</td>
										<td class="pClothBs">Effective<br>To</td>
										<td class="pClothBs">Bill Usg</td>
									</tr>
								</table>
								<div style="overflow-x:hidden; width:980; overflow-y:scroll; height:366;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="25"  align="center">	<!-- Select -->
										<col width="80"  align="center">	<!-- IB ID -->
										<col width="90"  align="center">	<!-- Serial# -->
										<col width="135" align="center">	<!-- Frequency -->
										<col width="90"  align="right">		<!-- Price -->
										<col width="170" align="center">	<!-- Meter Read Method -->
										<col width="80"  align="center">	<!-- Account# -->
										<col width="100" align="center">	<!-- Account Name -->
										<col width="80"  align="center">	<!-- Effective From -->
										<col width="80"  align="center">	<!-- Effective To -->
										<col width="30"  align="center">	<!-- Bill Usg -->
										<ezf:row ezfHyo="A">
										<tr height="28">
											<td><ezf:inputCheckBox name="xxChkBox_AA" ezfName="xxChkBox_AA" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>

											<td><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="1234567890123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
											<td><ezf:select name="bllgCycleCd_A" ezfName="bllgCycleCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleCd_L" ezfDispName="bllgCycleDescTxt_L" />
											</td>
											<td><ezf:inputText name="basePrcDealAmt_A" ezfName="basePrcDealAmt_A" value="123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\""/></td>
											<td><ezf:select name="mtrReadMethCd_A" ezfName="mtrReadMethCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mtrReadMethCd_L" ezfDispName="mtrReadMethDescTxt_L" />
											<td><ezf:inputText name="billToAcctNum_A" ezfName="billToAcctNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
											<td><ezf:inputText name="contrVrsnEffFromDt_A" ezfName="contrVrsnEffFromDt_A" value="01/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputText name="contrVrsnEffThruDt_A" ezfName="contrVrsnEffThruDt_A" value="12/31/2018" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputCheckBox name="xxChkBox_AB" ezfName="xxChkBox_AB" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<table style="margin-top: 0px; margin-left: 10px" border="0">
								<tr>
									<td align="left"><ezf:inputButton name="Add_Machines" value="Add Machine" htmlClass="pBtn7" /></td>
								</tr>
							</table>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<style type="text/css">
configarrow {

}
</style>

<%-- **** Task specific area ends here **** --%>
