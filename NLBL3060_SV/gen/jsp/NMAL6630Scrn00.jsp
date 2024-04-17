<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100716100447 --%>
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
			<input type="hidden" name="pageID" value="NMAL6630Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Parts Export Selling Rate Maintenance">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="680" height="65">
						<col align="left" width="160">
						<col align="left" width="305">
						<col align="left" width="15">
						<tr><td/></tr>
						<tr>
							<td class="stab">Product Control Code</td>
							<td><ezf:inputText name="firstProdCtrlCd" ezfName="firstProdCtrlCd" value="XXXXXXX8" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
						</tr>
						<tr>
							<td class="stab">Parts Export Selling Rate Code</td>
							<td><ezf:inputText name="prtExptSellRateCd" ezfName="prtExptSellRateCd" value="XX3" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
						</tr>
					</table>
					
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NLBL3060.NMAL6630BMsg" %>
<%@ page import="business.servlet.NLBL3060.NMAL6630_ABMsg" %>
<%@ page import="business.servlet.NLBL3060.common.NMAL6630CommonLogic" %>
<%  NMAL6630BMsg bMsg = (NMAL6630BMsg)databean.getEZDBMsg(); %>
					<table>
						<hr>
						<col valign="top">
						<tr>
							<td width="682" align="right">
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
						</tr>
						<tr>
							<td>
								<div id="topTBL" style="overflow-x:hidden; width:690; overflow-y:none; height:30;">
									<table border="1" cellpadding="1" cellspacing="0">
										<col align="center" width="29">
										<col align="center" width="104">
										<col align="center" width="114">
										<col align="center" width="140">
										<col align="center" width="135">
										<col align="center" width="135">
										
										<tr style="height:30;">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstProdCtrlCd_A1' )">Prod Ctrl Code<img id="sortIMG.firstProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}" tabindex="-1"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prtExptSellRateCd_A1' )">Selling Rate Code<img id="sortIMG.prtExptSellRateCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}" tabindex="-1"></td>
											<td class="pClothBs">Selling Rate [%]</td>
											<td class="pClothBs">Eff From Date</td>
											<td class="pClothBs">Eff Thru Date</td>
										</tr>
									</table>
								</div>
<%-- ### TABLE A ### --%>
								<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:700; height:390;">
									<table border="1" cellpadding="1" cellspacing="0" id="A">
										<col align="center" width="30">
										<col align="left"   width="104">
										<col align="left"   width="114">
										<col align="right"  width="140">
										<col align="left"   width="135">
										<col align="left"   width="135">
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
										<% NMAL6630_ABMsg detailMsg = bMsg.A.no(i++); %>
										<tr height="28">
											<td>
												<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
												<% if(disabledxxChkBox){ %>
												<span style='visibility:hidden'>
												<% } %>
												<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
												<% if(disabledxxChkBox){ %>
												</span>
												<% } %>
											</td>
											<td>&nbsp;<ezf:inputText name="firstProdCtrlCd_A1" ezfName="firstProdCtrlCd_A1" value="XXXXXXX8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td>&nbsp;<ezf:inputText name="prtExptSellRateCd_A1" ezfName="prtExptSellRateCd_A1" value="XX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="xxDiscRatio_A1" ezfName="xxDiscRatio_A1" value="9999.99999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"9\" style=\"text-align:right\""/>&nbsp;</td>
											<td>&nbsp;<ezf:inputText name="rateEffFromDt_A1" ezfName="rateEffFromDt_A1" value="20100701" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td>&nbsp;<ezf:label name="rateEffThruDt_A1" ezfName="rateEffThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10"  style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										<tr height="28">
											<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezftoupper ezfhyo="A"></td>
											<td>&nbsp;<input type="text" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
											<td><input type="text" class="pHsu" size="10" maxlength="10" style="text-align:right" ezfhyo="A">&nbsp;</td>
											<td>&nbsp;<input type="text" class="pHsu" size="8" maxlength="8" ezfhyo="A"></td>
											<td>&nbsp;<label ezfout ezfhyo="A"></label></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td width="700" height="35"align="right">
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="81">
									<col width="10">
									<col width="81">
									<tr>
										<td align="left"><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
										<td align="left"><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
									</tr>
								</table>
							</td>
						</tr>
<%-- ######################################## FOOTER ######################################## --%>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );

		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>
<%-- **** Task specific area ends here **** --%>
