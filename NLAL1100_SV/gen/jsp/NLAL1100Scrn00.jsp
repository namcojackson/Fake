<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231219115441 --%>
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
		<input type="hidden" name="pageID" value="NLAL1100Scrn00">
		<!-- Set Page Name -->
		<input type="hidden" name="pageName" value="Manage RMA Orders">

<center>
	<table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
						<tr>
							<td width="1070px" height="28px" valign="bottom">
								<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
									<tr title="PR Entry Screen">
										<td width="107px" align="center" class="same">Mng RMA</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</ezf:skip>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<!-- Saved Search -->
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S1" ezfName="srchOptPk_S1" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
							<td>
								<ezf:inputButton name="Save_Search" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="Delete_Search" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<!-- header -->
					<table border="0" cellpadding="0" cellspacing="0" height="">
						<tr>
							<td valign="top" width="10">&nbsp;</td>
							<td valign="top" width="">
								<table cellpadding="0" height="110">
									<col width="130" align="left">
									<col width="90" align="left">
									<col width="100"  align="left">
									<col width="75" align="left">
									<col width="40"  align="center">
									<col width="130" align="left">
									<col width="110" align="left">
									<col width="115" align="left">
									<col width="130" align="left">
									<col width="50"  align="left">
									<col width="110" align="left">
									<!-- 1 -->
									<tr>
										<td class="stab">RMA Number (*)</td>
										<td><ezf:inputText name="rmaNum_H1" ezfName="rmaNum_H1" value="CP000001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_RW" ezfName="xxLinkAncr_RW" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoForWh" >Warehouse (*)</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="A01" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="MONROE" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_PS" ezfName="xxLinkAncr_PS" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" >Coordinator</ezf:anchor></td>
										<td>
											<ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" value="A01" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search_CoordInfo" value=">>" htmlClass="pBtn0" />
										</td>
										<td>
											<ezf:inputText name="xxPsnFirstMidLastNm_H1" ezfName="xxPsnFirstMidLastNm_H1" value="MONROE" otherAttr=" size=\"16\" maxlength=\"90\" tabindex=\"-1\""/>
										</td>
										<td class="stab">Serial#(*)</td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<!-- 2 -->
									<tr>
										<td class="stab">Shipping Order Number (*)</td>
										<td><ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="SO000001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_SW" ezfName="xxLinkAncr_SW" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoForSwh" >Sub Warehouse (*)</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd_H1" ezfName="rtlSwhCd_H1" value="NEW" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlSwhNm_H1" ezfName="rtlSwhNm_H1" value="NEW" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\""/></td>
										<td class="stab">Request Pickup Date</td>
										<td>
										    <ezf:inputText name="rqstPickUpDt_HF" ezfName="rqstPickUpDt_HF" value="06/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstPickUpDt_HF', 4 );">
										    -
										</td>
										<td>
										    <ezf:inputText name="rqstPickUpDt_HT" ezfName="rqstPickUpDt_HT" value="06/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstPickUpDt_HT', 4 );">
										</td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_CO" ezfName="xxLinkAncr_CO" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td><ezf:inputText name="svcConfigMstrPk_H1" ezfName="svcConfigMstrPk_H1" otherAttr=" size=\"16\" maxlength=\"28\" ezftoupper=\"\""/></td>
									</tr>
									<!-- 3 -->
									<tr>
										<td class="stab">RWS Number (*)</td>
										<td><ezf:inputText name="rwsNum_H1" ezfName="rwsNum_H1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_AC" ezfName="xxLinkAncr_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustCode" >Customer Pickup (*)</ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipToCustInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"16\" maxlength=\"360\" tabindex=\"-1\""/></td>
										<td class="stab">Schedule Pickup Date</td>
										<td>
										    <ezf:inputText name="schdPickUpDt_HF" ezfName="schdPickUpDt_HF" value="06/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdPickUpDt_HF', 4 );">
										    -
										</td>
										<td>
										    <ezf:inputText name="schdPickUpDt_HT" ezfName="schdPickUpDt_HT" value="06/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdPickUpDt_HT', 4 );">
										</td>
										<td class="stab" colspan="2"><ezf:inputCheckBox name="xxChkBox_CL" ezfName="xxChkBox_CL" value="Y" />Include Closed Line</td>
									</tr>
									<!-- 4 -->
									<tr>
										<td class="stab">RMA Line Status</td>
										<td>
										    <ezf:select name="rtrnLineDplyStsCd_H1" ezfName="rtrnLineDplyStsCd_H1" ezfBlank="1" ezfCodeName="rtrnLineDplyStsCd_L1" ezfDispName="rtrnLineDplyStsDescTxt_L1" otherAttr=" style=\"width:78px;\""/>
										</td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_CR" ezfName="xxLinkAncr_CR" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrCode" >Pickup Carrier (*)</ezf:anchor></td>
										<td><ezf:inputText name="carrCd_H1" ezfName="carrCd_H1" value="FDEG" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_CarrInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="carrNm_H1" ezfName="carrNm_H1" value="FEDEX" otherAttr=" size=\"16\" maxlength=\"60\" tabindex=\"-1\""/></td>
										<td class="stab">Actual Pickup Date</td>
										<td>
										    <ezf:inputText name="actlPickUpDt_HF" ezfName="actlPickUpDt_HF" value="06/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlPickUpDt_HF', 4 );">
										    -
										</td>
										<td>
										    <ezf:inputText name="actlPickUpDt_HT" ezfName="actlPickUpDt_HT" value="06/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlPickUpDt_HT', 4 );">
										</td>
										<td></td>
										<td></td>
									</tr>
									<!-- 5 -->
									<tr>
										<td class="stab">Aging Bucket</td>
										<td>
											<ezf:select name="ordAgingBcktCd_H1" ezfName="ordAgingBcktCd_H1" ezfBlank="1" ezfCodeName="ordAgingBcktCd_L1" ezfDispName="ordAgingBcktDescTxt_L1" otherAttr=" style=\"width:78px;\""/>
										</td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_SR" ezfName="xxLinkAncr_SR" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsRepCode" >Sales Rep (*)</ezf:anchor></td>
										<td><ezf:inputText name="slsRepBrCd_H1" ezfName="slsRepBrCd_H1" value="H00166" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SlsRepInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="tocNm_H1" ezfName="tocNm_H1" value="YASUHIKO IMAZU" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\""/></td>
										<td class="stab">Carrier Requested</td>
										<td colspan="2">
											<ezf:inputCheckBox name="sendRqstFlg_Y" ezfName="sendRqstFlg_Y" value="Y" /> Yes
											<ezf:inputCheckBox name="sendRqstFlg_N" ezfName="sendRqstFlg_N" value="Y" /> No 
										</td>
										<td></td>
										<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
                    </table>
					<hr style="height: 0px;" cellpadding="0">
<%-- ######################################## RMA Comment #################################### --%>
					<table border="0" cellpadding="0" cellspacing="0">
						<col align="left" width="10">
						<col align="left" width="950">
						<tr>
							<td>&nbsp;</td>
							<td>
								<fieldset>
									<legend>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" width="80">
											<tr>
 												<td class="stab">RMA Comments</td>
											</tr>
										</table>
									</legend>
									<table border="0" cellpadding="0" cellspacing="0">
										<col align="left"   width="130">
										<col align="center" width="200">
										<col align="center" width="200">
										<tr>
											<td valign="top">
												<div id="RMA Comments" style="width:830; overflow-x:hidden; overflow-y:scroll; height:35;">
												<table border="0" cellpadding="0" cellspacing="0" id="C">
													<ezf:row ezfHyo="C">
													<tr>
														<td valign="center"><ezf:inputText name="xxScrItem130Txt_C1" ezfName="xxScrItem130Txt_C1" value="Q10072(XXXXX YYYYY)" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"130\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>&nbsp;&nbsp;</td>
														<td valign="center"><ezf:inputText name="xxTsDsp19Txt_C1" ezfName="xxTsDsp19Txt_C1" value="06/09/2015 10:40:40" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td valign="center"><ezf:textArea name="rtrnTrxCmntTxt_C1" ezfName="rtrnTrxCmntTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"70\" maxlength=\"180\""/></td>
													</tr>
													</ezf:row>
												</table>
												</div>
											</td>
											<td valign="bottom"><ezf:inputButton name="Add_Comment" value="Add" htmlClass="pBtn3" /></td>
											<td valign="bottom"><ezf:inputButton name="Save_Comment" value="Save" htmlClass="pBtn3" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
					<hr>
<%-- #################################################### Detail ################################################### --%>
<%@ page import="business.servlet.NLAL1100.NLAL1100BMsg" %>
<%@ page import="business.servlet.NLAL1100.NLAL1100_ABMsg" %>
<%  NLAL1100BMsg bMsg = (NLAL1100BMsg)databean.getEZDBMsg(); %>
					<!-- Pagenation -->
					<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
						<col align="left" width="180">
						<col align="left" width="100">
						<col align="left" width="100">
						<col align="left" width="">
						<col align="right" width="700">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn8" /></td>
							<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn8" /></td>
							<td>&nbsp;</td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
														<td>Results 999 - 999 of 999</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
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
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</ezf:skip>
								<table cellspacing="0" cellpadding="0" border="0" style="float: right;">
									<tbody>
									    <tr align="right">
									        <td>
									            <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									                <jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
									                <jsp:param name="TableNm"         value="A"                 />
									                <jsp:param name="ShowingFrom"     value="xxPageShowFromNum" />
									                <jsp:param name="ShowingTo"       value="xxPageShowToNum"   />
									                <jsp:param name="ShowingOf"       value="xxPageShowOfNum"   />
									                <jsp:param name="ShowingCurrent"  value="xxPageShowCurNum"  />
									                <jsp:param name="ShowingTotal"    value="xxPageShowTotNum"  />
									                <jsp:param name="ViewMode"        value="FULL"              />
									            </jsp:include>
									        </td>
									    </tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
					<!-- Detail -->
					<div id="parentBoxA">
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:160px; overflow:hidden; margin-left: 0px; padding:0px;">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<%-- ******************************** --%>
											<%-- *** Right Table Area(Header) *** --%>
											<%-- ******************************** --%>
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" height="28" id="AHEAD" width="1956px" style="margin-right:20px" >
												<col width="40"  align="center"><!-- Expand / Collapse (Lock) -->
												<col width="30"  align="center"><!-- Check Box (Lock) -->
												<col width="70"  align="center"><!-- Configuration PK -->
												<col width="70"  align="center"><!-- RMA Order Number -->
												<col width="60"  align="center"><!-- RMA Order Line Number -->
												<col width="70"  align="center"><!-- RWS Number -->
												<col width="40"  align="center"><!-- RWS Line Number -->
												<col width="100" align="center"><!-- Retail Warehouse Name -->
												<col width="70"  align="center"><!-- Retail Sub Warehouse Code -->
												<col width="150" align="center"><!-- Pickup Customer Name -->
												<col width="70"  align="center"><!-- Pickup Customer State Code -->
												<col width="100" align="center"><!-- Pickup Customer Contact -->
												<col width="120" align="center"><!-- Merchandise Code -->
												<col width="145" align="center"><!-- Merchandise Name -->
												<col width="70"  align="center"><!-- Order Quantity -->
												<col width="100" align="center"><!-- Serial Number -->
												<col width="150" align="center"><!-- Booked Date -->
												<col width="110" align="center"><!-- Aging Bucket -->
												<col width="250" align="center"><!-- Assigned to Group -->
<!-- START 2023/12/07 K.Ishizuka [QC#61300,ADD] -->
                                                <col width="80"  align="center"><!--/FSR Number -->
<!-- END   2023/12/07 K.Ishizuka [QC#61300,ADD] -->
												<col width="200" align="center"><!-- Scheduled Pickup Date -->
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
												<col width="100" align="center"><!-- Tech Meet The Truck -->
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
												<col width="110" align="center"><!-- Carrier Reason -->
												<col width="110" align="center"><!-- Actual Pickup Date -->
												<col width="110" align="center"><!-- Inspection Completion Date -->
												<col width="150" align="center"><!-- RMA Close Date -->
												<col width="70"  align="center"><!-- Out Of Territory -->
												<col width="250" align="center"><!-- Carrier -->
												<col width="70"  align="center"><!-- Carrier Request -->
												<col width="80"  align="center"><!-- Delivery Instruction -->
												<col width="100" align="center"><!-- Order Category Name -->
												<col width="100" align="center"><!-- Order Reason Name -->
												<col width="100" align="center"><!-- Order Line Category Name -->
												<col width="100" align="center"><!-- Order Line Status -->
												<col width="100" align="center"><!-- Return Tracking Status -->
												<col width="100" align="center"><!-- HDD Removal Date -->
												<col width="100" align="center"><!-- HDD Hold Released -->
												<col width="100" align="center"><!-- RWS Status -->
												<col width="150" align="center"><!-- RWS Close Date -->
												<col width="80"  align="center"><!-- Request Date -->
												<col width="100" align="center"><!-- Pickup Service Level -->
												<col width="100" align="center"><!-- Coordinator Name -->
												<col width="100" align="center"><!-- Scheduling Status Name -->
												<col width="70"  align="center"><!-- Model Name -->
												<col width="80"  align="center"><!-- Pickup Customer Code -->
												<col width="70"  align="center"><!-- Pickup Customer City Name -->
												<tr>
													<td id="AH0"  class="pClothBs"></td>
													<td id="AH1"  class="pClothBs"></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcConfigMstrPk_A1')">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cpoOrdNum_A1')">RMA Order<img id="sortIMG.cpoOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dplyLineNum_A1')">Line<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rwsNum_A1')">RWS Number<img id="sortIMG.rwsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rwsDtlLineNum_A1')">RWS<br>Line<img id="sortIMG.rwsDtlLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlWhNm_A1')">Warehouse<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtlSwhCd_A1')">Sub WH<img id="sortIMG.rtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A1')">Customer Pickup From<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fromLocStCd_A1')">Installed At<img id="sortIMG.fromLocStCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fromLocPsnNm_A1')">Customer<br>Contact<img id="sortIMG.fromLocPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A1')">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A1')">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rwsQty_A1')">Qty<img id="sortIMG.rwsQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A1')">Serial<img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTsDsp19Txt_A1')">Booked Date<img id="sortIMG.xxTsDsp19Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ordAgingBcktDescTxt_A1')">Aging Bucket<img id="sortIMG.ordAgingBcktDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtrnTrkNtfyGrpCd_A1')">Assigned to Group<img id="sortIMG.rtrnTrkNtfyGrpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- START 2023/12/06 K.Ishizuka [QC#61300,ADD] -->
                                                    <td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fsrNum_A1')">FSR#<img id="sortIMG.fsrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- END   2023/12/06 K.Ishizuka [QC#61300,ADD] -->
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'schdPickUpDt_A1')">Scheduled<br>Pickup Date<img id="sortIMG.schdPickUpDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
													<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techMeetTruckFlg_A1')">Tech Meet The<br>Truck<img id="sortIMG.techMeetTruckFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
													<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'carrRsnCd_A1' )">Carrier Reason<img id="sortIMG.carrRsnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'actlPickUpDt_A1')">Actual<br>Pickup Date<img id="sortIMG.actlPickUpDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'inspCpltDt_A1')">Inspection<br>Completion Date<img id="sortIMG.inspCpltDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTsDsp19Txt_OC')">RMA Close Date<img id="sortIMG.xxTsDsp19Txt_OC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'outOfWhInvtyProcFlg_A1')">Out Of<br>Territory<img id="sortIMG.outOfWhInvtyProcFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'carrCd_A1')">Carrier<img id="sortIMG.carrCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'sendRqstFlg_A1')">Carr<br>Rqst<img id="sortIMG.sendRqstFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH29" class="pClothBs">Delivery<br>Instruction</td>
													<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsOrdCatgDescTxt_A1')">Order Category<img id="sortIMG.dsOrdCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsOrdTpDescTxt_A1')">Order Reason<img id="sortIMG.dsOrdTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsOrdLineCatgDescTxt_A1')">Line Category<img id="sortIMG.dsOrdLineCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtrnLineDplyStsDescTxt_A1')">Line Status<img id="sortIMG.rtrnLineDplyStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rtrnTrkStsCd_A1')">Pickup Status<img id="sortIMG.rtrnTrkStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rmvDt_A1')">HDD<br>Removal Date<img id="sortIMG.rmvDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'relMemoTxt_A1')">HDD<br>Hold Released<img id="sortIMG.relMemoTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rwsStsDescTxt_A1')">RWS Status<img id="sortIMG.rwsStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTsDsp19Txt_RC')">RWS Close Date<img id="sortIMG.xxTsDsp19Txt_RC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rqstPickUpDt_A1')">Request<br>Pickup Date<img id="sortIMG.rqstPickUpDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shpgSvcLvlCd_A1')">Pickup Service<br>Level<img id="sortIMG.shpgSvcLvlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'schdCoordPsnNm_A1')">Coordinator<img id="sortIMG.schdCoordPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'schdCoordStsDescTxt_A1')">Scheduling  Status<img id="sortIMG.schdCoordStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH43" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A1')">Model<img id="sortIMG.t_MdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH44" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipLocCd_A1')">Customer<br>Pickup Code<img id="sortIMG.shipLocCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH45" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fromLocCtyAddr_A1')">City<img id="sortIMG.fromLocCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>
										<%-- ******************************** --%>
										<%-- *** Right Table Area(Detail) *** --%>
										<%-- ******************************** --%>
										<div  id="rightTbl" style="width:1117px; height:168px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1956px">
												<col width="40"  align="center"><!-- Expand / Collapse (Lock) -->
												<col width="30"  align="center"><!-- Check Box (Lock) -->
												<col width="70"  align="center"><!-- Configuration PK -->
												<col width="70"  align="left"  ><!-- RMA Order Number  -->
												<col width="60"  align="center"><!-- RMA Order Line Number -->
												<col width="70"  align="left"  ><!-- RWS Number -->
												<col width="40"  align="center"><!-- RWS Line Number -->
												<col width="100" align="center"><!-- Retail Warehouse Name -->
												<col width="70"  align="center"><!-- Retail Sub Warehouse Code -->
												<col width="150" align="center"><!-- Pickup Customer Name -->
												<col width="70"  align="center"><!-- Pickup Customer State Code -->
												<col width="100" align="center"><!-- Pickup Customer Contact -->
												<col width="120" align="center"><!-- Merchandise Code -->
												<col width="145" align="center"><!-- Merchandise Name -->
												<col width="70"  align="center"><!-- Order Quantity -->
												<col width="100" align="center"><!-- Serial Number -->
												<col width="150" align="center"><!-- Booked Date -->
												<col width="110" align="center"><!-- Aging Bucket -->
												<col width="250" align="center"><!-- Assigned to Group -->
<!-- START 2023/12/07 K.Ishizuka [QC#61300,ADD] -->
                                                <col width="80"  align="center"><!-- FSR Number -->
<!-- END   2023/12/07 K.Ishizuka [QC#61300,ADD] -->
												<col width="200" align="center"><!-- Scheduled Pickup Date -->
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
												<col width="100" align="center"><!-- Tech Meet The Truck -->
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
												<col width="110" align="center"><!-- Carrier Reason -->
												<col width="110" align="center"><!-- Actual Pickup Date -->
												<col width="110" align="center"><!-- Inspection Completion Date -->
												<col width="150" align="center"><!-- RMA Close Date -->
												<col width="70"  align="center"><!-- Out Of Territory -->
												<col width="250" align="center"><!-- Carrier -->
												<col width="70"  align="center"><!-- Carrier Request -->
												<col width="80"  align="center"><!-- Delivery Instruction -->
												<col width="100" align="center"><!-- Order Category Name -->
												<col width="100" align="center"><!-- Order Reason Name -->
												<col width="100" align="center"><!-- Order Line Category Name -->
												<col width="100" align="center"><!-- Order Line Status -->
												<col width="100" align="center"><!-- Return Tracking Status -->
												<col width="100" align="center"><!-- HDD Removal Date -->
												<col width="100" align="center"><!-- HDD Hold Released -->
												<col width="100" align="center"><!-- RWS Status -->
												<col width="150" align="center"><!-- RWS Close Date -->
												<col width="80"  align="center"><!-- Request Date -->
												<col width="100" align="center"><!-- Pickup Service Level -->
												<col width="100" align="center"><!-- Coordinator Name -->
												<col width="100" align="center"><!-- Scheduling Status Name -->
												<col width="70"  align="center"><!-- Model Name -->
												<col width="80"  align="center"><!-- Pickup Customer Code -->
												<col width="70"  align="center"><!-- Pickup Customer City Name -->
												<% int i=0; %>
												<ezf:row ezfHyo="A">
													<% NLAL1100_ABMsg lineMsg = bMsg.A.no(i++); %>
													<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
														<td>
															<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_A1.getValue()); %>
															<% if (isSmryLine) { %>
																	<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="A" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
															<% } else if (!isSmryLine) { %>
																	<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('ContractHolds',{EZF_ROW_NUMBER})" ezfhyo="A" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
															<% } %>
											            </td>
											            <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
											            <td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="1000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"28\" id=\"svcConfigMstrPk#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" ezfanchortext=\"\" id=\"Open_OrdEntry#{EZF_ROW_NUMBER}\""><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											            <td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											            <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_RcvOrd" otherAttr=" ezfanchortext=\"\" id=\"Open_RcvOrd#{EZF_ROW_NUMBER}\""><ezf:label name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											            <td><ezf:label name="rwsDtlLineNum_A1" ezfName="rwsDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											            <td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="MONROE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
											            <td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\""/></td>
											            <td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="MEGARAM COMPUTER " ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"360\""/></td>
											            <td><ezf:inputText name="fromLocStCd_A1" ezfName="fromLocStCd_A1" value="MI" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\""/></td>
											            <td><ezf:inputText name="fromLocPsnNm_A1" ezfName="fromLocPsnNm_A1" value="GARY HIMMERT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"25\""/></td>
											            <td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="8456B003AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\""/></td>
											            <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IR-ADV C350IF 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/></td>
											            <td><ezf:inputText name="rwsQty_A1" ezfName="rwsQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"10\""/></td>
											            <td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
											            <td><ezf:inputText name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" value="06/09/2015 10:40:40" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											            <td><ezf:inputText name="ordAgingBcktDescTxt_A1" ezfName="ordAgingBcktDescTxt_A1" value="91 - 120 DAYS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
											            <td>
											            	<ezf:inputButton name="OpenWin_NtfyGrp" value="Grp" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_NtfyGrp#{EZF_ROW_NUMBER}\""/>
											                <ezf:inputText name="rtrnTrkNtfyGrpCd_A1" ezfName="rtrnTrkNtfyGrpCd_A1" value="INVTY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
											                <ezf:inputButton name="Search_NtfyGrp" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Search_NtfyGrp#{EZF_ROW_NUMBER}\""/>
											                <ezf:inputText name="rtrnTrkNtfyGrpDescTxt_A1" ezfName="rtrnTrkNtfyGrpDescTxt_A1" value="Inventory Control" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/>
											            </td>
<!-- START 2023/12/07 K.Ishizuka [QC#61300,ADD] -->
											            <td><ezf:inputText name="fsrNum_A1" ezfName="fsrNum_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
<!-- END   2023/12/07 K.Ishizuka [QC#61300,ADD] -->
											            <td>
											                <ezf:inputText name="schdPickUpDt_A1" ezfName="schdPickUpDt_A1" value="05/09/2019" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdPickUpDt_A1', 4, {EZF_ROW_NUMBER} );" ezfhyo="A">
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
															<ezf:inputText name="rcvTsDplyTxt_A1" ezfName="rcvTsDplyTxt_A1" value="11:00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
															<ezf:select name="rqstRcvDtTxt_A1" ezfName="rqstRcvDtTxt_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" otherAttr=" style=\"width:39;\""/>
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
											            </td>
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
														<td><ezf:inputCheckBox name="techMeetTruckFlg_A1" ezfName="techMeetTruckFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"techMeetTruckFlg_A1{EZF_ROW_NUMBER}\""/></td>
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
														<td>
															<ezf:select name="carrRsnCd_A1" ezfName="carrRsnCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="carrRsnCd_L1" ezfDispName="carrRsnDescTxt_L1" otherAttr=" style=\"width:100;\""/>
														</td>
											            <td>
											                <ezf:inputText name="actlPickUpDt_A1" ezfName="actlPickUpDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlPickUpDt_A1', 4, {EZF_ROW_NUMBER} );" ezfhyo="A">
											            </td>
											            <td>
											                <ezf:inputText name="inspCpltDt_A1" ezfName="inspCpltDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'inspCpltDt_A1', 4, {EZF_ROW_NUMBER} );" ezfhyo="A">
											            </td>
											            <td><ezf:inputText name="xxTsDsp19Txt_OC" ezfName="xxTsDsp19Txt_OC" value="06/09/2015 10:40:40" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											            <td><ezf:label name="outOfWhInvtyProcFlg_A1" ezfName="outOfWhInvtyProcFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											            <td>
											            	<ezf:inputButton name="OpenWin_CarrInfo" value="Carr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CarrInfo#{EZF_ROW_NUMBER}\""/>
											                <ezf:inputText name="carrCd_A1" ezfName="carrCd_A1" value="UPSN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
											                <ezf:inputButton name="Search_CarrLineInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Search_CarrLineInfo#{EZF_ROW_NUMBER}\""/>
											                <ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="UNITED PARCEL SER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\""/>
											            </td>
											            <td><ezf:label name="sendRqstFlg_A1" ezfName="sendRqstFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											            <td><ezf:inputButton name="Open_DelyInstn" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"Open_DelyInstn{EZF_ROW_NUMBER}\""/></td>
											            <td><ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" value="Sales Order" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td><ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td><ezf:inputText name="dsOrdLineCatgDescTxt_A1" ezfName="dsOrdLineCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td><ezf:inputText name="rtrnLineDplyStsDescTxt_A1" ezfName="rtrnLineDplyStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td>
											                <ezf:select name="rtrnTrkStsCd_A1" ezfName="rtrnTrkStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rtrnTrkStsCd_L1" ezfDispName="rtrnTrkStsDescTxt_L1" otherAttr=" style=\"width:93px;\""/>
											            </td>
											            <td><ezf:inputText name="svcMachRmvDt_A1" ezfName="svcMachRmvDt_A1" value="06/03/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
											            <td><ezf:inputText name="relMemoTxt_A1" ezfName="relMemoTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
											            <td><ezf:inputText name="rwsStsDescTxt_A1" ezfName="rwsStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td><ezf:inputText name="xxTsDsp19Txt_RC" ezfName="xxTsDsp19Txt_RC" value="06/09/2015 10:40:40" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											            <td><ezf:inputText name="rqstPickUpDt_A1" ezfName="rqstPickUpDt_A1" value="06/03/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
											            <td>
											                <ezf:select name="shpgSvcLvlCd_A1" ezfName="shpgSvcLvlCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_L1" ezfDispName="shpgSvcLvlDescTxt_L1" otherAttr=" style=\"width:93px;\""/>
											            </td>
											            <td><ezf:inputText name="schdCoordPsnNm_A1" ezfName="schdCoordPsnNm_A1" value="YASUHIKO IMAZU" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"61\""/></td>
											            <td><ezf:inputText name="schdCoordStsDescTxt_A1" ezfName="schdCoordStsDescTxt_A1" value="Scheduled" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
											            <td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="IPF8300" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"50\" id=\"t_MdlNm#{EZF_ROW_NUMBER}\""/></td>
											            <td><ezf:inputText name="shipLocCd_A1" ezfName="shipLocCd_A1" value="A13380" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
											            <td><ezf:inputText name="fromLocCtyAddr_A1" ezfName="fromLocCtyAddr_A1" value="UTICA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"25\""/></td>
											        </tr>
											    </ezf:row>
											    <ezf:skip>
		                                        </ezf:skip>
		                                    </table>
		                                </div>
	                                </div><!-- right table -->
	                            </td>
	                        </tr>
	                    </table>
					</div><!-- parentBoxA -->

					<!-- ######################################## Footer - [START] #################################### -->
					<table>
						<colgroup>
							<col width="800px" align="left">
							<col width="250px" align="right">
						</colgroup>
						<tr>
							<td>
								<%-- ######################################## Apply to Detail Lines #################################### --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<fieldset>
												<legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<col align="left" width="">
														<tr>
			 												<td class="stab">Apply to Detail Lines</td>
														</tr>
													</table>
												</legend>
												<table border="0" cellpadding="0" cellspacing="0" height="">
													<col width="100" align="left">
													<col width="110" align="left">
													<col width="110" align="left">
													<col width="120" align="left">
													<col width="140" align="left">
													<col width="120" align="left">
													<col width="195" align="left">
													<col width="90" align="left">
													<!-- 1 -->
													<tr>
														<td class="stab"><ezf:anchor name="xxLinkAncr_GP" ezfName="xxLinkAncr_GP" ezfEmulateBtn="1" ezfGuard="OpenWin_NtfyGrp_Apply" >Assigned to Group</ezf:anchor></td>
														<td>
															<ezf:inputText name="rtrnTrkNtfyGrpCd_G" ezfName="rtrnTrkNtfyGrpCd_G" value="INVTY" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
														</td>
														<td class="stab">Pickup Service Level</td>
														<td>
															<ezf:select name="shpgSvcLvlCd_G" ezfName="shpgSvcLvlCd_G" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_L1" ezfDispName="shpgSvcLvlDescTxt_L1" otherAttr=" style=\"width:100px;\""/>
														</td>
														<td class="stab" colspan="2">Scheduled Pickup Date</td>
														<td>
															<ezf:inputText name="schdPickUpDt_G" ezfName="schdPickUpDt_G" otherAttr=" size=\"10\" maxlength=\"10\" ezftouppe=\"\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdPickUpDt_G', 4);">
<!-- START 2019/05/09 T.Ogura [QC#50027,ADD] -->
															<ezf:inputText name="rcvTsDplyTxt_G" ezfName="rcvTsDplyTxt_G" value="11:00" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
															<ezf:select name="rqstRcvDtTxt_G" ezfName="rqstRcvDtTxt_G" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" otherAttr=" style=\"width:39;\""/>
<!-- END   2019/05/09 T.Ogura [QC#50027,ADD] -->
														</td>
													</tr>
													<!-- 2 -->
													<tr>
														<td class="stab"><ezf:anchor name="xxLinkAncr_CA" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrInfo_Apply" >Carrier</ezf:anchor></td>
														<td>
															<ezf:inputText name="carrCd_G" ezfName="carrCd_G" value="UPSN" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
														</td>
														<td class="stab">Carrier Reason</td>
														<td>
															<ezf:select name="carrRsnCd_G" ezfName="carrRsnCd_G" ezfBlank="1" ezfCodeName="carrRsnCd_L1" ezfDispName="carrRsnDescTxt_L1" otherAttr=" style=\"width:100;\""/>
														</td>
														<td class="stab" colspan="2">Inspection Completion Date</td>
														<td>
															<ezf:inputText name="inspCpltDt_G" ezfName="inspCpltDt_G" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'inspCpltDt_G', 4);">
														</td>
													</tr>
													<!-- 3 -->
													<tr>
														<td class="stab">Pickup Status</td>
														<td>
															<ezf:select name="rtrnTrkStsCd_G" ezfName="rtrnTrkStsCd_G" ezfBlank="1" ezfCodeName="rtrnTrkStsCd_L1" ezfDispName="rtrnTrkStsDescTxt_L1" otherAttr=" style=\"width:93px;\""/>
														</td>
														<td class="stab">Actual Pickup Date</td>
														<td>
															<ezf:inputText name="actlPickUpDt_G" ezfName="actlPickUpDt_G" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlPickUpDt_G', 4);">
														</td>
														<td colspan="3" style="padding-right:3px;" align="right"><ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn4" otherAttr=" style=\"width:100px;\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<%-- ################################################################################################### --%>
							</td>
							<td>
								<table border="0">
									<col width="">
									<tr>
										<td valign="center"><ezf:inputButton name="Print" value="Print" htmlClass="pBtn10" /></td>
										<td valign="center"><ezf:inputButton name="Send_Rqst" value="Send Carrier Request" htmlClass="pBtn10" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <!-- ######################################## Footer - [E N D] #################################### -->
				</div>
            </td>
        </tr>
    </table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>

<%-- **** Task specific area ends here **** --%>
