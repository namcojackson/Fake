<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180405135238 --%>
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
			<input type="hidden" name="pageID" value="NMAL7000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price List Search">
<center>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Price List Search" class="pTab_ON"><a href="#">Prc List Srch</a></li>
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
				<div class="pTab_BODY_In">
<%------------------------------------%>
<%-- Save Option					--%>
<%------------------------------------%>
				<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
					<col width="152">
					<col width="345">
					<col width="110">
					<col width="300">
					<col width="">
					<tr>
						<td class="stab">Saved Search Options</td>
						<td>
							<ezf:select name="srchOptPk_H1" ezfName="srchOptPk_H1" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
						</td>
						<td class="stab">Search Option Name</td>
						<td class="stab"><ezf:inputText name="srchOptNm_H1" ezfName="srchOptNm_H1" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td>
							<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="190" width="1100" rules="none"  style="padding: 10px; margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100">
							<table cellpadding="0" border="0">
								<col align="left" width="135">
								<col align="left" width="300">
								<col align="left" width="155">
								<col align="left" width="260">
								<col align="left" width="110">
								<col align="left" width="130">
								<tr>
									<td class="stab">Price List Name(*)</td>
									<td><ezf:inputText name="prcCatgNm_H1" ezfName="prcCatgNm_H1" value="Price List Name" otherAttr=" size=\"40\" maxlength=\"75\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="dsAcctNum_LK" ezfName="dsAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AccountNum" >Account Number</ezf:anchor></td>
									<td>
										<ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Effective Date From</td>
									<td>
										<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Price List Display Name(*)</td>
									<td><ezf:inputText name="prcListDplyNm_H1" ezfName="prcListDplyNm_H1" otherAttr=" size=\"40\" maxlength=\"100\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AccountName" >Account Name(*)</ezf:anchor></td>
									<td>
										<ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"35\" maxlength=\"360\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Effective Date To</td>
									<td>
										<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Price List Description(*)</td>
									<td><ezf:inputText name="prcCatgDescTxt_H1" ezfName="prcCatgDescTxt_H1" otherAttr=" size=\"40\" maxlength=\"2000\" ezftoupper=\"\""/></td>
									<td class="stab"><ezf:anchor name="csmpNum_LK" ezfName="csmpNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNum" >CSMP#</ezf:anchor></td>
									<td>
										<ezf:inputText name="csmpNum_H1" ezfName="csmpNum_H1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Status</td>
									<td valign="middle">
										<ezf:select name="prcDispRecTpCd_H1" ezfName="prcDispRecTpCd_H1" ezfCodeName="prcDispRecTpCd_L1" ezfDispName="prcDispRecTpDescTxt_L1" otherAttr=" style=\"width: 130px\""/>
									</td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="prcContrNum_LK" ezfName="prcContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ContractNum" >Contract#</ezf:anchor></td>
									<td>
										<ezf:inputText name="prcContrNum_H1" ezfName="prcContrNum_H1" value="NIPA-2015" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
									<td class="stab"><ezf:anchor name="coaBrCd_LK" ezfName="coaBrCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" >Branch#</ezf:anchor></td>
									<td>
										<ezf:inputText name="coaBrCd_H1" ezfName="coaBrCd_H1" value="XXXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="prcContrNm_LK" ezfName="prcContrNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ContractName" >Contract Name(*)</ezf:anchor></td>
									<td>
										<ezf:inputText name="prcContrNm_H1" ezfName="prcContrNm_H1" otherAttr=" size=\"40\" maxlength=\"150\" ezftoupper=\"\""/>
									</td>
									<td class="stab"><ezf:anchor name="splyAgmtPlnNm_LK" ezfName="splyAgmtPlnNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SupplyPlan" >Related Supply Plan Name(*)</ezf:anchor></td>
									<td>
										<ezf:inputText name="splyAgmtPlnNm_H1" ezfName="splyAgmtPlnNm_H1" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Price List Type</td>
									<td>
										<ezf:select name="prcListTpCd_H1" ezfName="prcListTpCd_H1" ezfBlank="1" ezfCodeName="prcListTpCd_L1" ezfDispName="prcListTpDescTxt_L1" otherAttr=" style=\"width: 200px\""/>
									</td>
									<td class="stab"><ezf:anchor name="prcContrNm_LK" ezfName="prcListMdseCd_LK" ezfEmulateBtn="1" ezfGuard="MoveWin_ItemNumber" >Item Number(*)</ezf:anchor></td>
									<td>
										<ezf:inputText name="prcListMdseCd_H1" ezfName="prcListMdseCd_H1" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Price List Structure</td>
									<td>
										<ezf:select name="prcListStruTpCd_H1" ezfName="prcListStruTpCd_H1" ezfBlank="1" ezfCodeName="prcListStruTpCd_L1" ezfDispName="prcListStruTpDescTxt_L1" otherAttr=" style=\"width: 200px\""/>
									</td>
									<td class="stab"><ezf:anchor name="prcContrNm_LK" ezfName="mdlNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceModelName" >Service Model Name(*)</ezf:anchor></td>
									<td>
										<ezf:inputText name="mdlNm_H1" ezfName="mdlNm_H1" otherAttr=" size=\"35\" maxlength=\"150\" ezftoupper=\"\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Price List Group Name</td>
									<td>
										<ezf:select name="prcListGrpCd_H1" ezfName="prcListGrpCd_H1" ezfBlank="1" ezfCodeName="prcListGrpCd_L1" ezfDispName="prcListGrpDescTxt_L1" otherAttr=" style=\"width: 200px\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="left"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%------------------------------------%>
<%-- Button							--%>
<%------------------------------------%>
				<table border="0" cellpadding="1" cellspacing="0" width="1100" height="24">
					<col width="800" align="left">
					<col width="300" align="right">
					<tr>
						<td>
							<ezf:inputButton name="MoveWin_RegisterCustomer" value="Register Customers for Pricing" htmlClass="pBtn15" />
							<ezf:inputButton name="MoveWin_UploadPricing" value="Upload Pricing" htmlClass="pBtn15" />
							<ezf:inputButton name="MoveWin_CopyPricing" value="Copy Pricing" htmlClass="pBtn15" />
						</td>
						<td></td>
					</tr>
				</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="1" cellspacing="0" width="1105" align="center"  rules="none"  style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
					<tr align="right">
						<col width="150"  align="center">
						<col width="400"  align="center">
						<col width="550"  align="right">
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td>&nbsp;</td>
						<td>
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"          value="A" />
								<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
								<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
								<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
								<jsp:param name="ViewMode"         value="FULL" />
							</jsp:include>
							<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
									<col>
									<col width="54"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="26"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td>Results 990 - 1000 of 1000</td>
										<td class="stab">Showing</td>
										<td><input type="text" value="1" size="4" maxlength="4" style="text-align:right" class="pPro" readOnly name="xxPageShowFromNum" ezfname="xxPageShowFromNum"></td>
										<td class="stab">/</td>
										<td><input type="text" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly name="xxPageShowToNum" ezfname="xxPageShowToNum"></td>
										<td class="stab">page</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" name="PageJump" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" disabled></td>
									</tr>
								</table>
							</ezf:skip>
						</td>
					</tr>
				</table>
				<table>
				<tr>
				<td width="5"></td>
				<td>
				<div id="parentBoxA">
				<div style="float:left; display:block"> <!-- left table -->
					<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;">
					</div>
					<div id="leftTbl" style="float:left; display:block; height:213px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
					</div>
				</div>  <!-- left table -->
				<div style="float:left"> <!-- right table -->
					<div id="rightTblHead" style="width:1085px; display:block; overflow:hidden; margin:0px;padding:0px;">
						<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1345px" style="margin-right:20px">
							<col align="center" width="25">		<!-- Select					-->
							<col align="center" width="65">		<!-- Price List ID			-->
							<col align="center" width="430">	<!-- Price List Name		-->
							<col align="center" width="135">	<!-- Price List Type		-->
							<col align="center" width="135">	<!-- Price List Structure	-->
							<col align="center" width="70">		<!-- Staus					-->
							<col align="center" width="90">		<!-- Contract#				-->
							<col align="center" width="135">	<!-- Contract Name			-->
							<col align="center" width="100">	<!-- Price List Group Name	-->
							<col align="center" width="80">		<!-- Effective Date From	-->
							<col align="center" width="80">		<!-- Effective Date To		-->
							<tr height="35">
								<td id="AH0"  class="pClothBs">&nbsp;<br>&nbsp;</td>
								<td id="AH1"  class="pClothBs">Price List ID</td>
								<td id="AH2"  class="pClothBs">Price List Name</td>
								<td id="AH3"  class="pClothBs">Price List Type</td>
								<td id="AH4"  class="pClothBs">Price List Structure</td>
								<td id="AH5"  class="pClothBs">Status</td>
								<td id="AH6"  class="pClothBs">Contract#</td>
								<td id="AH7"  class="pClothBs">Contract Name</td>
								<td id="AH8"  class="pClothBs">Price List Group Name</td>
								<td id="AH9"  class="pClothBs">Effective Date<br>From</td>
								<td id="AH10" class="pClothBs">Effective Date<br>To</td>
							</tr>
						</table>
					</div>
					<div id="rightTbl" style="width:1102px; height:230px; display:block; overflow:scroll; margin:0px; padding:0px;" >
						<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="1345px">
							<col width="25" align="center">
							<col width="65" align="center">
							<col width="430" align="center">
							<col width="135" align="center">
							<col width="135" align="center">
							<col width="70" align="center">
							<col width="90" align="center">
							<col width="135" align="center">
							<col width="100" align="center">
							<col width="80" align="center">
							<col width="80" align="center">
							<% int valueOfxxRadioBtn = 0; %>
							<ezf:row ezfHyo="A">
								<tr id="id_leftA_row{EZF_ROW_NUMBER}">
									<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
									<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_PriceListSetup" otherAttr=" ezfanchortext=\"\" id=\"prcCatgCd_LK#{EZF_ROW_NUMBER}\""><ezf:label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
									<td><ezf:inputText name="prcCatgNm_A1" ezfName="prcCatgNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"60\" maxlength=\"75\""/></td>
									<td><ezf:inputText name="prcListTpDescTxt_A1" ezfName="prcListTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="prcListStruTpDescTxt_A1" ezfName="prcListStruTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="prcDispRecTpDescTxt_A1" ezfName="prcDispRecTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="prcContrNum_A1" ezfName="prcContrNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
									<td><ezf:inputText name="prcContrNm_A1" ezfName="prcContrNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/></td>
									<td><ezf:inputText name="prcListGrpDescTxt_A1" ezfName="prcListGrpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
								</tr>
								<% valueOfxxRadioBtn++; %>
							</ezf:row>
							<ezf:skip>
							</ezf:skip>
						</table>
					</div>
				</div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A");
</script>

<%-- **** Task specific area ends here **** --%>
