<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161129162812 --%>
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
			<input type="hidden" name="pageID" value="NFBL2070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Compensation Credit IF Error Correction">
<center>
	<%-- ######################################## HEADER ######################################## --%>
	<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
	<div class="pTab_BODY">
	<Table border="0" cellpadding="1" cellspacing="1" width="100%">
		<tr>
			<td class="stab" align="left">Serial No</td>
			<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td class="stab" align="left">Division</td>
			<td><ezf:inputText name="rtlDivCd_H1" ezfName="rtlDivCd_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td class="stab" align="left">Dealer Sell To</td>
			<td><ezf:inputText name="sellToCustCd_H1" ezfName="sellToCustCd_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td class="stab" align="left">Dealer Ship To</td>
			<td><ezf:inputText name="svcDlrCd_H1" ezfName="svcDlrCd_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td></td>
		</tr>
		<tr>
			<td class="stab" align="left">Status</td>
			<td><ezf:inputText name="apInvRossStsCd_H1" ezfName="apInvRossStsCd_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td class="stab" align="left">Credit No</td>
			<td><ezf:inputText name="rtlInvNum_H1" ezfName="rtlInvNum_H1" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
			<td class="stab" align="left">Credit Issue From</td>
			<td colSpan="3">
				<ezf:inputText name="rtlInvApvlDt_HF" ezfName="rtlInvApvlDt_HF" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" id=\"rtlInvApvlDt_HF\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rtlInvApvlDt_HF', 4);" >
				&nbsp; - &nbsp;
				<ezf:inputText name="rtlInvApvlDt_HT" ezfName="rtlInvApvlDt_HT" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" id=\"rtlInvApvlDt_HT\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rtlInvApvlDt_HT', 4);" >
			</td>
			<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
		</tr>
	</Table>

	<hr>

	<%-- ######################################## DETAIL ######################################## --%>
	<Table border="0" cellpadding="1" cellspacing="1" width="100%">
		<tr>
			<td>
				<table border="0">
					<tr>
						<col width="1070" align="center">
						<td align="right">
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"         value="A" />
								<jsp:param name="ShowingFrom"     value="xxPageShowFromNum_P1" />
								<jsp:param name="ShowingTo"       value="xxPageShowToNum_P1" />
								<jsp:param name="ShowingOf"       value="xxPageShowOfNum_P1" />
								<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum_P1" />
								<jsp:param name="ShowingTotal"    value="xxPageShowTotNum_P1" />
								<jsp:param name="ViewMode"        value="FULL" />
							</jsp:include>
						</td>
					</tr>
				</table>
				<div id="parentBoxA">
					<div style="float:left; display:block"> <!-- left table -->
						<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
						<div id="leftTbl" style="float:left; display:block; height:168px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
					</div> <!-- left table -->
					<div style="float:left"> <!-- right table -->
						<div id="rightTblHead" style="width:1104px; display:block; overflow-x:hidden; overflow-y:hidden; margin:0px;padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTbl' ) );" >
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="3000px" style="margin-right:20px">
								<col width="20" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
								<col width="100" align="center">
			                          		<tr height="33">
									<td class="pClothBs" align="center" id="AH0">&nbsp;</td>
									<td class="pClothBs" align="center" id="AH1">V Number</td>
									<td class="pClothBs" align="center" id="AH2">S Number</td>
									<td class="pClothBs" align="center" id="AH3" >Credit Issue Dt</td>
									<td class="pClothBs" align="center" id="AH4" >Serial No.</td>
									<td class="pClothBs" align="center" id="AH5" >Status</td>
									<td class="pClothBs" align="center" id="AH6" >Model</td>
									<td class="pClothBs" align="center" id="AH7" >Bus Type</td>
									<td class="pClothBs" align="center" id="AH8" >Comp From Date</td>
									<td class="pClothBs" align="center" id="AH9" >Comp To Date</td>
									<td class="pClothBs" align="center" id="AH10">Amount</td>
									<td class="pClothBs" align="center" id="AH11">Error Status</td>
									<td class="pClothBs" align="center" id="AH12">Bill To</td>
									<td class="pClothBs" align="center" id="AH13">Sell To</td>
									<td class="pClothBs" align="center" id="AH14">Rtl Inv Line Num</td>
									<td class="pClothBs" align="center" id="AH15">Trl inv Chrg To desc Txt</td>
									<td class="pClothBs" align="center" id="AH16">Ship Qty</td>
									<td class="pClothBs" align="center" id="AH17">Sls Tax Rate</td>
									<td class="pClothBs" align="center" id="AH18">Trl Div Cd</td>
									<td class="pClothBs" align="center" id="AH19">Item Cd</td>
									<td class="pClothBs" align="center" id="AH20">Svc Dlr Cd</td>
									<td class="pClothBs" align="center" id="AH21">Instl Post Cd</td>
									<td class="pClothBs" align="center" id="AH22">Instl Cd</td>
									<td class="pClothBs" align="center" id="AH23">Istl Sub Loc Cd</td>
									<td class="pClothBs" align="center" id="AH24">Inv Line Crat Dt</td>
									<td class="pClothBs" align="center" id="AH25">Inv Line Mod Dt</td>
									<td class="pClothBs" align="center" id="AH26">Retail Inv Pk</td>
									<td class="pClothBs" align="center" id="AH27">Retail Inv Line Pk</td>
								</tr>
							</table>
						</div>
						<div id="rightTbl" style="width:1121px; height:418px; display:block; overflow-y:scroll; overflow-x:scroll; margin:0px; padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTblHead' ) );">
							<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="3000px">
								<col width="20">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="100">
								<tbody>
								<ezf:row ezfHyo="A">
			                              		<tr height="25">
									<td>
										<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_Fix', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/>
										<!--<input type="hidden" size="0" value="" id="rtlInvPk_A1" name="rtlInvPk_A1" ezfname="rtlInvPk_A1">-->
										<!--<input type="hidden" size="0" value="" id="rtlInvLinePk_A1" name="rtlInvLinePk_A1" ezfname="rtlInvLinePk_A1">-->
									</td>
			  						<td><ezf:inputText name="rtlInvNum_A1" ezfName="rtlInvNum_A1" value="XXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"13\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="itrlRtlSmryInvNum_A1" ezfName="itrlRtlSmryInvNum_A1" value="XXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"13\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="rtlInvApvlDt_A1" ezfName="rtlInvApvlDt_A1" value="XX/XX/XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="rtlInvStsCd_A1" ezfName="rtlInvStsCd_A1" value="XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"2\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="mdlNm_A1" ezfName="mdlNm_A1" value="XXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="bllgBizTpCd_A1" ezfName="bllgBizTpCd_A1" value="XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"2\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="bllgPerFromDt_A1" ezfName="bllgPerFromDt_A1" value="XX/XX/XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="bllgPerThruDt_A1" ezfName="bllgPerThruDt_A1" value="XX/XX/XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="dealGrsUnitPrcAmt_A1" ezfName="dealGrsUnitPrcAmt_A1" value="99999999999999999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\""/></td>
									<td><ezf:inputText name="apInvRossStsCd_A1" ezfName="apInvRossStsCd_A1" value="XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"2\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="billToCustCd_A1" ezfName="billToCustCd_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="sellToCustCd_A1" ezfName="sellToCustCd_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="rtlInvLineNum_A1" ezfName="rtlInvLineNum_A1" value="XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"5\""/></td>
									<td><ezf:inputText name="rtlInvChrgTpDescTxt_A1" ezfName="rtlInvChrgTpDescTxt_A1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="shipQty_A1" ezfName="shipQty_A1" value="XXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="slsTaxRate_A1" ezfName="slsTaxRate_A1" value="XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"9\""/></td>
									<td><ezf:inputText name="rtlDivCd_A1" ezfName="rtlDivCd_A1" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"3\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="XXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"16\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="svcDlrCd_A1" ezfName="svcDlrCd_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="instlPostCd_A1" ezfName="instlPostCd_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"15\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="instlCd_A1" ezfName="instlCd_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="istlSubLocCd_A1" ezfName="istlSubLocCd_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="invLineCratDt_A1" ezfName="invLineCratDt_A1" value="XX/XX/XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="invLineModDt_A1" ezfName="invLineModDt_A1" value="XX/XX/XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="rtlInvPk_A1" ezfName="rtlInvPk_A1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"28\""/></td>
									<td><ezf:inputText name="rtlInvLinePk_A1" ezfName="rtlInvLinePk_A1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"28\""/></td>
								</tr>
			                   			</ezf:row>
			                   			</tbody>
							</table>
						</div>
					</div> <!-- right table -->
				</div>
			</td>
		</tr>
	</table>
	</div>
</center>

<%-- **** Task specific area ends here **** --%>
