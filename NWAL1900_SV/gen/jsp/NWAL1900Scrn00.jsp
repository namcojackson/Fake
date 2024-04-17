<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181130182548 --%>
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
			<input type="hidden" name="pageID" value="NWAL1900Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Analysis ">

<center>

	<table style="width:1010px;table-layout:fixed;">
		<col width="600">
		<col width="100">
		<col>
		<col width="300">
		<tr>
			<td>
				<table style="width:600px;table-layout:fixed;">
					<colgroup>
						<col width="40">
						<col width="40">
					</colgroup>
					<tr>
						<td>LOB</td>
						<td class="pOut" colspan="2"><ezf:label name="lineBizTpCd" ezfName="lineBizTpCd" /></td>
						<td class="stab" align="right">Transaction Type</td>
						<td >
							<ezf:select name="prcGrpTrxTpCd_H1" ezfName="prcGrpTrxTpCd_H1" ezfCodeName="prcGrpTrxTpCd_L1" ezfDispName="prcGrpTrxTpDescTxt_L1" />
						</td>
						<!-- Search Button -->
						<td><ezf:inputButton name="PriceSearch" value="Search" htmlClass="cBtn" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<hr>

	<table width="100%">
		<tr>
			<table border="0" cellpadding="1" cellspacing="0" width="100%">
				<tr align="right">
					<td>
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

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<col valign="top" align="right">
				<col valign="top" align="left">
				<tr>
					<td>
						<div id="LeftTBL_Top" style="height:24; overflow-x:hidden; width:300;">
							<table border="1" cellpadding="1" cellspacing="0" width="300">
								<col align="center" width="350">
								<tr height="62">
									<td class="pClothBs">Rule Or Table Name</td>
								</tr>
							</table>
						</div>

						<div id="LeftTBL"  style="overflow-x:hidden; overflow-y:hidden; height:403; width:300;"  onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
							<table id="A_Left" border="1" cellpadding="1" cellspacing="0" width="350">
								<col align="left" width="350">
								<ezf:row ezfHyo="A">
									<tr height="25" id="id_row{EZF_ROW_NUMBER}">
										<td>
											<ezf:anchor name="prcRuleNm_LK" ezfName="prcRuleNm_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_RecordScreen" otherAttr=" id=\"prcRuleNm_LK\" ezfanchortext=\"\"">
											<ezf:label name="prcRuleNm_A" ezfName="prcRuleNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										</td>
									</tr>
								</ezf:row>
								<ezf:skip>
									<tr height="25">
										<td>
											<a href="#" name="prcRuleNm_A" ezfName="prcRuleNm_A" ezfHyo="A" ezfanchortext onclick="sendServer('MoveWin_RecordScreen')" id="prcRuleNm_A#{EZF_ROW_NUMBER}">
											<label ezfout name="prcRuleNm_A" ezfName="prcRuleNm_A" ezfHyo="A">YKTEST CHARGE RULE (HEADER LEVEL)</label></a></td>
										</td>
									</tr>
									<tr height="25">
										<td>
											<a href="#" name="prcRuleNm_A" ezfName="prcRuleNm_A" ezfHyo="A" ezfanchortext onclick="sendServer('MoveWin_RecordScreen')" id="prcRuleNm_A#{EZF_ROW_NUMBER}">
											<label ezfout name="prcRuleNm_A" ezfName="prcRuleNm_A" ezfHyo="A">YKTEST CHARGE RULE (HEADER LEVEL)</label></a></td>
										</td>
									</tr>
								</ezf:skip>
							</table>
						</div>
					</td>

					<td>
						<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:673" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
							<table style="width:810px;table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">

								<col align="center" width="120">
								<col align="center" width="40">
								<col align="center" width="55">
								<col align="center" width="70">
								<col align="center" width="60">
								<col align="center" width="60">
								<col align="center" width="60">
								<col align="center" width="80">
								<col align="center" width="50">
								<tr height="62">
									<td class="pClothBs">Price Adjustment Type</td>
									<td class="pClothBs">Auto Apply</td>
									<td class="pClothBs">Override</td>
									<td class="pClothBs">Default Rule Precedence</td>
									<td class="pClothBs">Effective Date From</td>
									<td class="pClothBs">Effective Date To</td>
									<td class="pClothBs">Description</td>
									<td class="pClothBs">Precedence Group#</td>
									<td class="pClothBs">Action Type</td>
								</tr>
							</table>
						</div>

						<div id="RightTBL" style=" overflow-x:scroll; overflow-y:scroll; height:420; width:690" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
							<table id="A_Right" style="width:810px;table-layout:fixed;font-size:6" border="1" cellpadding="1" cellspacing="0">
								<col align="left" width="120">
								<col align="center" width="40">
								<col align="center" width="55">
								<col align="left" width="70">
								<col align="left" width="60">
								<col align="left" width="60">
								<col align="left" width="60">
								<col align="left" width="80">
								<col align="left" width="50">
							<ezf:row ezfHyo="A">
								<tr height="25">
								<td><ezf:inputText name="prcRuleCondTpNm_A" ezfName="prcRuleCondTpNm_A" value="Price Adjustment Table" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="applyAutoFlg_A" ezfName="xxCmntTxt_A1" value="YES" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="ovrdFlg_A" ezfName="xxCmntTxt_A2" value="YES" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="defRulePrcdNum_A" ezfName="defRulePrcdNum_A" value="100" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="09/04/2018" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="09/14/2018" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td id="Description{EZF_ROW_NUMBER}"><ezf:inputText name="xxCmntTxt_A" ezfName="xxCmntTxt_A3" value="Applied" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="prcRulePrcdPk_A" ezfName="prcRulePrcdPk_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								<td><ezf:inputText name="prcPrcdActTpNm_A" ezfName="prcPrcdActTpNm_A" value="Lowset" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
							</ezf:row>
							<ezf:skip>
								<tr height="25">
								<td><input type="text" size="30" maxlength="30" value="Price Rules" name="prcRuleCondTpCd_A" ezfname="prcRuleCondTpCd_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="3" maxlength="3" value="NO" name="applyAutoFlg_A" ezfname="xxCmntTxt_A1" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="3" maxlength="3" value="NO" name="ovrdFlg_A" ezfname="xxCmntTxt_A2" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="999" name="defRulePrcdNum_A" ezfname="defRulePrcdNum_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="09/04/2018" name="effFromDt_A" ezfname="effFromDt_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="09/04/2018" name="effThruDt_A" ezfname="effThruDt_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input id="Description{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="Qualified " name="xxCmntTxt_A" ezfname="xxCmntTxt_A3" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="1" name="prcRulePrcdPk_A" ezfname="prcRulePrcdPk_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="Lowset" name="prcPrcdActTpNm_A" ezfname="prcPrcdActTpNm_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
							</ezf:skip>
							<ezf:skip>
								<tr height="25">
								<td><input type="text" size="30" maxlength="30" value="Price Rules" name="prcRuleCondTpCd_A" ezfname="prcRuleCondTpCd_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="3" maxlength="3" value="NO" name="applyAutoFlg_A" ezfname="xxCmntTxt_A1" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="3" maxlength="3" value="NO" name="ovrdFlg_A" ezfname="xxCmntTxt_A2" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="999" name="defRulePrcdNum_A" ezfname="defRulePrcdNum_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="09/04/2018" name="effFromDt_A" ezfname="effFromDt_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="09/04/2018" name="effThruDt_A" ezfname="effThruDt_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input id="Description{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="Qualified " name="xxCmntTxt_A" ezfname="xxCmntTxt_A3" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="1" name="prcRulePrcdPk_A" ezfname="prcRulePrcdPk_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
								<td><input type="text" size="10" maxlength="10" value="Lowset" name="prcPrcdActTpNm_A" ezfname="prcPrcdActTpNm_A" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A"></td>
							</ezf:skip>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</tr>
	</table>
</center>

<STYLE type="text/css"> 
<!-- 
tr.pHightlightBGcolor	{background-color:#FFFF00;}
tr.pErrorBGcolor		{background-color:#FF0000;}
--> 
</STYLE> 


<%-- **** Task specific area ends here **** --%>
