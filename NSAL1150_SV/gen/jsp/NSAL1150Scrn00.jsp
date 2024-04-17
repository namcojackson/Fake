<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530142858 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NSAL1150Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL1150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL1150Scrn00.title" bundle="${I18N_SCREEN_ID}">Supply Watch Used / Allowed</fmt:message>">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL1150Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Actual Counters for Interface</fmt:message>" class="pTab_ON"><a href="#">Actl Cntr</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;width=1082px;margin-left:20;" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="100%" align="center">
									<col width="55" align="right">
									<col width="180">
									<col width="100" align="right">
									<col width="350">
									<col width="50" align="right">
									<col width="180">
									<col align="right">
									<tr height="21">
										<td class="stab"><ezf:anchor name="xxLinkProt_01" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" ><fmt:message key="i18n.NSAL1150Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Customer#</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="condSqlTxt_CU" ezfName="condSqlTxt_CU" value="WWWWWWWWW,WWWWWWWWW,WWWWWWWWW" otherAttr=" size=\"20\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkProt_02" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerName" ><fmt:message key="i18n.NSAL1150Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Customer Name(*)</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"40\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkProt_03" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="OpenWin_ContractNum" ><fmt:message key="i18n.NSAL1150Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="condSqlTxt_CO" ezfName="condSqlTxt_CO" value="WWWWWWWWW,WWWWWWWWW,WWWWWWWWW" otherAttr=" size=\"20\""/></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<hr />
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
						<col width="200">
						<col width="">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<!-- Pagination & Navigation--START-->
							<td align="right" class="stab">
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ViewMode"          value="LEFT_NONE" />
								</jsp:include>
								<ezf:skip>
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
										<td><input type="text" name="xxPageShowFromNum" ezfName="xxPageShowFromNum" value="1" size="4" maxlength="4" class="pPro" style="text-align:right" id="txtShowingFrom" disabled></td>
										<td class="stab">to</td>
										<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" value="3" size="4" maxlength="4" class="pPro" style="text-align:right" id="txtShowingTot" disabled></td>
										<td class="stab">of</td>
										<td><input type="text" name="xxPageShowCurNum" ezfName="xxPageShowCurNum" value="1" size="4" maxlength="4" class="pPro" style="text-align:right" id="txtShowingCur" disabled></td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:450px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="2580px" style="margin-right:20px" >
												<col width="100" align="center">	<!-- Cust# -->
												<col width="100" align="center">	<!-- Cust Name -->
												<col width="80" align="center">	<!-- Contract# -->
												<col width="80" align="center">	<!-- Active Like Status -->
												<col width="100" align="center">	<!-- Service Program -->
												<col width="100" align="center">	<!-- Model# -->
												<col width="100" align="center">	<!-- Serial# -->
												<col width="80" align="center">	<!-- Machine Act Like Status -->
												<col width="60" align="center">	<!-- Toner Allotment -->
												<col width="80" align="center">	<!-- Days from Last Bill Date -->
												<col width="100" align="center">	<!-- BW Volume -->
												<col width="100" align="center">	<!-- CLR Volume -->
												<col width="100" align="center">	<!-- BW Allowed -->
												<col width="100" align="center">	<!-- CLR Allowed -->
												<col width="100" align="center">	<!-- BW Used -->
												<col width="100" align="center">	<!-- CLR Used -->
												<col width="50" align="center">	<!-- BW% Used -->
												<col width="50" align="center">	<!-- CLR% Used -->
												<tr height="34px">
													<td id="AH0" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Cust#</fmt:message></td>
													<td id="AH1" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Cust Name</fmt:message></td>
													<td id="AH2" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
													<td id="AH3" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Active Like Status</fmt:message></td>
													<td id="AH4" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Service Program</fmt:message></td>
													<td id="AH5" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Model#</fmt:message></td>
													<td id="AH6" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
													<td id="AH7" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Machine Act Like Status</fmt:message></td>
													<td id="AH8" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Toner Allotment</fmt:message></td>
													<td id="AH9" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Days from Last Bill Date</fmt:message></td>
													<td id="AH10" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.13" bundle="${I18N_SCREEN_ID}">BW Volume</fmt:message></td>
													<td id="AH11" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.14" bundle="${I18N_SCREEN_ID}">CLR Volume</fmt:message></td>
													<td id="AH12" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.15" bundle="${I18N_SCREEN_ID}">BW Allowed</fmt:message></td>
													<td id="AH13" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.16" bundle="${I18N_SCREEN_ID}">CLR Allowed</fmt:message></td>
													<td id="AH14" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.17" bundle="${I18N_SCREEN_ID}">BW Used</fmt:message></td>
													<td id="AH15" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.18" bundle="${I18N_SCREEN_ID}">CLR Used</fmt:message></td>
													<td id="AH16" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.19" bundle="${I18N_SCREEN_ID}">BW% Used</fmt:message></td>
													<td id="AH17" class="pClothBs"><fmt:message key="i18n.NSAL1150Scrn00.label.20" bundle="${I18N_SCREEN_ID}">CLR% Used</fmt:message></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1117px; height:450px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2580px" >
												<col width="100" align="center">	<!-- Cust# -->
												<col width="100" align="center">	<!-- Cust Name -->
												<col width="80" align="center">	<!-- Contract# -->
												<col width="80" align="center">	<!-- Active Like Status -->
												<col width="100" align="center">	<!-- Service Program -->
												<col width="100" align="center">	<!-- Model# -->
												<col width="100" align="center">	<!-- Serial# -->
												<col width="80" align="center">	<!-- Machine Act Like Status -->
												<col width="60" align="center">	<!-- Toner Allotment -->
												<col width="80" align="center">	<!-- Days from Last Bill Date -->
												<col width="100" align="center">	<!-- BW Volume -->
												<col width="100" align="center">	<!-- CLR Volume -->
												<col width="100" align="center">	<!-- BW Allowed -->
												<col width="100" align="center">	<!-- CLR Allowed -->
												<col width="100" align="center">	<!-- BW Used -->
												<col width="100" align="center">	<!-- CLR Used -->
												<col width="50" align="center">	<!-- BW% Used -->
												<col width="50" align="center">	<!-- CLR% Used -->
												<ezf:row ezfHyo="A">
												<tr height="23px">
													<td align="left" class="stab"><ezf:label name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center" class="stab"><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center" class="stab"><ezf:label name="actvFlg_AC" ezfName="actvFlg_AC" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center" class="stab"><ezf:label name="actvFlg_AM" ezfName="actvFlg_AM" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="fromLastBillDtDaysAot_A" ezfName="fromLastBillDtDaysAot_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="bwAvgMlyCopyCnt_A" ezfName="bwAvgMlyCopyCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="colorAvgMlyCopyCnt_A" ezfName="colorAvgMlyCopyCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="bwPrrtQty_A" ezfName="bwPrrtQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="colorPrrtQty_A" ezfName="colorPrrtQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="bwUsedQty_A" ezfName="bwUsedQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="colorUsedQty_A" ezfName="colorUsedQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="splyAllocPct_AB" ezfName="splyAllocPct_AB" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="right" class="stab"><ezf:label name="splyAllocPct_AC" ezfName="splyAllocPct_AC" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="23px">
													<td align="left" class="stab"><label ezfout name="shipToCustAcctCd_A" ezfname="shipToCustAcctCd_A" ezfhyo="A">WWWWWWWWW1</label></td>
													<td align="center"><input type="text" name="dsAcctNm_A" ezfname="dsAcctNm_A" readOnly value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" size="12" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td align="center" class="stab"><label ezfout name="dsContrNum_A" ezfname="dsContrNum_A" ezfhyo="A">1234567</label></td>
													<td align="center" class="stab"><label ezfout name="actvFlg_AC" ezfname="actvFlg_AC" ezfhyo="A">Y</label></td>
													<td align="center"><input type="text" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" readOnly value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" size="12" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td align="center"><input type="text" name="t_MdlNm_A" ezfname="t_MdlNm_A" readOnly value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" size="12" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td align="center"><input type="text" name="serNum_A" ezfname="serNum_A" readOnly value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" size="12" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td align="center" class="stab"><label ezfout name="actvFlg_AM" ezfname="actvFlg_AM" ezfhyo="A">N</label></td>
													<td align="right" class="stab"><label ezfout name="svcTermAttrbMapValCd_A" ezfname="svcTermAttrbMapValCd_A" ezfhyo="A">100.00</label></td>
													<td align="right" class="stab"><label ezfout name="fromLastBillDtDaysAot_A" ezfname="fromLastBillDtDaysAot_A" ezfhyo="A">9,999</label></td>
													<td align="right" class="stab"><label ezfout name="bwAvgMlyCopyCnt_A" ezfname="bwAvgMlyCopyCnt_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="colorAvgMlyCopyCnt_A" ezfname="colorAvgMlyCopyCnt_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="bwPrrtQty_A" ezfname="bwPrrtQty_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="colorPrrtQty_A" ezfname="colorPrrtQty_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="bwUsedQty_A" ezfname="bwUsedQty_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="colorUsedQty_A" ezfname="colorUsedQty_A" ezfhyo="A">1,234,567,890,123</label></td>
													<td align="right" class="stab"><label ezfout name="splyAllocPct_AB" ezfname="splyAllocPct_AB" ezfhyo="A">100.00</label></td>
													<td align="right" class="stab"><label ezfout name="splyAllocPct_AC" ezfname="splyAllocPct_AC" ezfhyo="A">0.00</label></td>
												</tr>
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	.totalLineBGcolor{background-color:#FDEADA;}
-->
</style>

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 3, true);
</script>


<%-- **** Task specific area ends here **** --%>
