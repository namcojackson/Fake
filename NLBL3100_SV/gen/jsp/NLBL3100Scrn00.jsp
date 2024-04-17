<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190326115527 --%>
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
			<input type="hidden" name="pageID" value="NLBL3100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Coordinator Search Popup">

<center>
	<table width="100%">
		<tr>
			<td valign="top">
                <%-- ######################################## HEADER ######################################## --%>
				<table width="100%" cellpadding="1" cellspacing="1" align="center" border="0">
					<tr>
						<td>
							<table border="0">
                                <col width="130" align="left">
                                <col width=""    align="left">
                                <col width="55"   align="left">
                                <col width="130" align="left">
                                <col width=""    align="left">
                                <col width="10"  align="left">
                                <col width=""    align="left">
                                <col width="355" align="right">
								<tr height="20">
									<td class ="stab">Warehouse Code(*)</td>
									<td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td></td>
									<td class ="stab">Warehouse Name(*)</td>
									<td colspan="3"><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                    <td></td>
								</tr>
								<tr height="20">
									<td class ="stab">Coordinator Code(*)</td>
									<td><ezf:inputText name="schdCoordPsnCd_H1" ezfName="schdCoordPsnCd_H1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                    <td></td>
									<td class ="stab">Coordinator Name(*)</td>
							<!-- Start 2019/03/11 H.Umeda (QC#30722,MOD) -->
							<!--	<td colspan="3"><input type="text" size="20" maxlength="61" value="" name="xxPsnNm_H1" ezfname="xxPsnNm_H1" ezftoupper ></td> -->
									<td colspan="3"><ezf:inputText name="xxPsnNm_H1" ezfName="xxPsnNm_H1" otherAttr=" size=\"20\" maxlength=\"61\""/></td>
							<!-- End   2019/03/11 H.Umeda (QC#30722,MOD) -->
                                    <td></td>
								</tr>
								<tr height="20">
									<td class ="stab">Manager Code(*)</td>
									<td><ezf:inputText name="mgrPsnCd_H1" ezfName="mgrPsnCd_H1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                    <td></td>
									<td class ="stab">Manager Name(*)</td>
							<!-- Start 2019/03/11 H.Umeda (QC#30722,MOD) -->
							<!--	<td colspan="3"><input type="text" size="20" maxlength="61" value="" name="xxPsnNm_H2" ezfname="xxPsnNm_H2" ezftoupper ></td> -->
									<td colspan="3"><ezf:inputText name="xxPsnNm_H2" ezfName="xxPsnNm_H2" otherAttr=" size=\"20\" maxlength=\"61\""/></td>
							<!-- End   2019/03/11 H.Umeda (QC#30722,MOD) -->
                                    <td></td>
								</tr>
								<tr height="20">
									<td class ="stab">Supervisor Code(*)</td>
									<td><ezf:inputText name="supvPsnCd_H1" ezfName="supvPsnCd_H1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                    <td></td>
									<td class ="stab">Supervisor Name(*)</td>
							<!-- Start 2019/03/11 H.Umeda (QC#30722,MOD) -->
							<!--	<td colspan="3"><input type="text" size="20" maxlength="61" value="" name="xxPsnNm_H3" ezfname="xxPsnNm_H3" ezftoupper ></td> -->
									<td colspan="3"><ezf:inputText name="xxPsnNm_H3" ezfName="xxPsnNm_H3" otherAttr=" size=\"20\" maxlength=\"61\""/></td>
							<!-- End   2019/03/11 H.Umeda (QC#30722,MOD) -->
                                    <td></td>
								</tr>
								<tr height="20">
									<td class ="stab">Carrier Code(*)</td>
									<td><ezf:inputText name="carrCd_H1" ezfName="carrCd_H1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td></td>
									<td class ="stab">Carrier Name(*)</td>
									<td colspan="3"><ezf:inputText name="xxPsnNm_H4" ezfName="xxPsnNm_H4" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                    <td></td>
								</tr>
								<tr height="20">
									<td class ="stab">State</td>
									<td><ezf:inputText name="stCd_H1" ezfName="stCd_H1" otherAttr=" size=\"10\" maxlength=\"2\" ezftoupper=\"\""/></td>
                                    <td></td>
                                    <td class="stab">Effective Date</td>
                                    <td>
                                        <ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_H1', 4 );">
                                    </td>
                                    <td class="stab">-</td>
                                    <td>
                                        <ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_H1', 4 );">
                                    </td>
                                    <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

                <hr>

                <%-- ######################################## (COMMON)PAGENATION #################################### --%>
                <table style="margin-left: 11px; width: 100%;" >
                    <colgroup>
                        <col align="right" width="">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>
                                <ezf:skip>
                                    <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 28px;">
                                        <colgroup>
                                            <col align="center" width="54">
                                            <col align="right" width="40">
                                            <col align="center" width="16">
                                            <col align="right" width="40">
                                            <col align="center" width="16">
                                            <col align="right" width="40">
                                            <col width="10">
                                            <col>
                                            <col width="1">
                                            <col>
                                        </colgroup>
                                        <tbody>
                                            <tr>
                                                <td class="stab"><label>Showing</label></td>
                                                <td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
                                                <td class="stab"><label>to</label></td>
                                                <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
                                                <td class="stab"><label>of</label></td>
                                                <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
                                                <td>&nbsp;</td>
                                                <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                <td></td>
                                                <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </ezf:skip>
                                <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
                                    <tbody>
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
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <!-- ################################################## Detail ################################################## -->
                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" valign="top">
                            <div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:980;">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
                                    <col width="68"   align="center"><!-- Warehouse Code -->
                                    <col width="136"  align="center"><!-- Warehouse Name -->
                                    <col width="68"   align="center"><!-- Manager Code -->
                                    <col width="136"  align="center"><!-- Manager Name -->
                                    <col width="68"   align="center"><!-- Supervisor Code -->
                                    <col width="136"  align="center"><!-- Supervisor Name -->
                                    <col width="68"   align="center"><!-- Coordinator Code -->
                                    <col width="136"  align="center"><!-- Coordinator Name -->
                                    <col width="46"   align="center"><!-- State -->
                                    <col width="88"   align="center"><!-- Effective Date From -->
                                    <col width="88"   align="center"><!-- Effective Date To -->
                                    <col width="68"   align="center"><!-- Carrier Code -->
                                    <col width="136"  align="center"><!-- Carrier Name -->
                                    <col width="180"  align="center"><!-- Carrier Contact -->
                                    <col width="108"  align="center"><!-- Carrier Phone -->
                                    <tr>
                                        <td class="pClothBs" colspan="2">Warehouse</td>
                                        <td class="pClothBs" colspan="2">Manager</td>
                                        <td class="pClothBs" colspan="2">Supervisor</td>
                                        <td class="pClothBs" colspan="2">Coordinator</td>
                                        <td class="pClothBs" rowspan="2">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">State</a>
                                            <img id="sortIMG.1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs" colspan="2">Effective Date</td>
                                        <td class="pClothBs" colspan="4">Carrier</td>
                                    </tr>
                                    <tr>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Code</a>
                                            <img id="sortIMG.2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Name</a>
                                            <img id="sortIMG.3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Code</a>
                                            <img id="sortIMG.4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Name</a>
                                            <img id="sortIMG.5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Code</a>
                                            <img id="sortIMG.6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Name</a>
                                            <img id="sortIMG.7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Code</a>
                                            <img id="sortIMG.8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Name</a>
                                            <img id="sortIMG.9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">From</a>
                                            <img id="sortIMG.10" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">To</a>
                                            <img id="sortIMG.11" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Code</a>
                                            <img id="sortIMG.12" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Name</a>
                                            <img id="sortIMG.13" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Contact</a>
                                            <img id="sortIMG.14" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd')">Contact Phone</a>
                                            <img id="sortIMG.15" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>
                            <div id="btmTBL" style="overflow-y:scroll; height:320; overflow-x:scroll; width:997;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                    <col width="68"   align="left"><!-- Warehouse Code -->
                                    <col width="136"  align="left"><!-- Warehouse Name -->
                                    <col width="68"   align="left"><!-- Manager Code -->
                                    <col width="136"  align="left"><!-- Manager Name -->
                                    <col width="68"   align="left"><!-- Supervisor Code -->
                                    <col width="136"  align="left"><!-- Supervisor Name -->
                                    <col width="68"   align="left"><!-- Coordinator Code -->
                                    <col width="136"  align="left"><!-- Coordinator Name -->
                                    <col width="46"   align="left"><!-- State -->
                                    <col width="88"   align="left"><!-- Effective Date From -->
                                    <col width="88"   align="left"><!-- Effective Date To -->
                                    <col width="68"   align="left"><!-- Carrier Code -->
                                    <col width="136"  align="left"><!-- Carrier Name -->
                                    <col width="180"  align="left"><!-- Carrier Contact -->
                                    <col width="108"  align="left"><!-- Carrier Phone -->
                                    <ezf:row ezfHyo="A">
                                        <!-- 1 -->
                                        <tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
                                            <td><ezf:label name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="MONROE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"61\""/></td>
                                            <td><ezf:anchor name="mgrPsnCdAncr_A1" ezfName="mgrPsnCdAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectManager" >
                                            <ezf:label name="mgrPsnCd_A1" ezfName="mgrPsnCd_A1" ezfHyo="A" ezfArrayNo="0" />
                                            </ezf:anchor></td>
                                            <td><ezf:inputText name="xxPsnNm_A2" ezfName="xxPsnNm_A2" value="KEVIN RICH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"61\""/></td>
                                            <td><ezf:anchor name="supvPsnCdAncr_A1" ezfName="supvPsnCdAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectSupervisor" >
                                            <ezf:label name="supvPsnCd_A1" ezfName="supvPsnCd_A1" ezfHyo="A" ezfArrayNo="0" />
                                            </ezf:anchor></td>
                                            <td><ezf:inputText name="xxPsnNm_A3" ezfName="xxPsnNm_A3" value="JONE VAUGHN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"61\""/></td>
                                            <td><ezf:anchor name="schdCoordPsnCdAncr_A1" ezfName="schdCoordPsnCdAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectCoordinator" >
                                            <ezf:label name="schdCoordPsnCd_A1" ezfName="schdCoordPsnCd_A1" ezfHyo="A" ezfArrayNo="0" />
                                            </ezf:anchor></td>
                                            <td><ezf:inputText name="xxPsnNm_A1" ezfName="xxPsnNm_A1" value="CHRIS PILKINGTON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"61\""/></td>
                                            <td><ezf:anchor name="stCdAncr_A1" ezfName="stCdAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectState" >
                                            <ezf:label name="stCd_A1" ezfName="stCd_A1" ezfHyo="A" ezfArrayNo="0" />
                                            </ezf:anchor></td>
                                            <td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:label name="carrCd_A1" ezfName="carrCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="FEDEX GROUND" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
                                            <td><ezf:inputText name="carrCtacEmlAddr_A1" ezfName="carrCtacEmlAddr_A1" value="FEDEX-EMAIL_MA@fedex.com" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"320\""/></td>
                                            <td><ezf:label name="carrCtacTelNum_A1" ezfName="carrCtacTelNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                        <!-- 2 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">ST</td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_CT@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">222-333-4444</label></td>
                                        </tr>
                                        <!-- 3 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NY</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_NY@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">333-444-5555</label></td>
                                        </tr>
                                        <!-- 4 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">A01</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MONROE</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >K00134</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">KEVIN RICH</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >P00344</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MIKE CORRELL</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >C88997</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">CHRIS PILKINGTON</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NJ</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_NJ@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">444-555-6666</label></td>
                                        </tr>
                                        <!-- 5 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">PA</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_PA@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">555-666-7777</label></td>
                                        </tr>
                                        <!-- 6 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">VA</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_VA@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">666-777-8888</label></td>
                                        </tr>
                                        <!-- 7 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">A01</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MONROE</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >K00134</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">KEVIN RICH</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >Q04565</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">YASUHIKO IMAZU</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >C88997</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">CHRIS PILKINGTON</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MA</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FDEG</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX GROUND</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">FEDEX-EMAIL_MA@fedex.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">777-888-9999</label></td>
                                        </tr>
                                        <!-- 8 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">DE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_DE@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">999-888-7777</label></td>
                                        </tr>
                                        <!-- 9 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MD</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_MD@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">888-777-6666</label></td>
                                        </tr>
                                        <!-- 10 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">A01</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MONROE</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >K00134</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">KEVIN RICH</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >J33456</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">JONE VAUGHN</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >E33424</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">ELIZABETH COURT</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NY</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_NY@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">777-666-5555</label></td>
                                        </tr>
                                        <!-- 11 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">DE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_DE@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">999-888-7777</label></td>
                                        </tr>
                                        <!-- 12 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MD</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_MD@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">888-777-6666</label></td>
                                        </tr>
                                        <!-- 13 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NY</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_MD@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">888-777-6666</label></td>
                                        </tr>
                                        <!-- 14 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">A01</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MONROE</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >K00134</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">KEVIN RICH</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >J33456</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">JONE VAUGHN</label></td>
                                            <td><a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_Tech')" >J89750</a></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">JOHN CORRELL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NY</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_NY@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">777-666-5555</label></td>
                                        </tr>
                                        <!-- 15 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">DE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_DE@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">999-888-7777</label></td>
                                        </tr>
                                        <!-- 16 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">MD</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_MD@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">888-777-6666</label></td>
                                        </tr>
                                        <!-- 17 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">NY</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">06/10/2000</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">12/31/9999</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">URE</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS RESIDENTIAL</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">UPS-EMAIL_MD@ups.com</label></td>
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">888-777-6666</label></td>
                                        </tr>
                                    </ezf:skip>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
