<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181220164933 --%>
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
			<input type="hidden" name="pageID" value="NLCL0270Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supersession Inventory Inquiry Popup">

<center>
	<table width="100%">
		<tr>
			<td valign="top">
                <%-- ######################################## HEADER ######################################## --%>
				<table width="100%" cellpadding="1" cellspacing="1" align="center" border="0">
					<tr>
						<td>
							<table border="0">
                                <col width="80" align="left">
                                <col width="370" align="left">
                                <col width="100" align="left">
                                <col width="200" align="left">
                                <col width="213" align="right">
								<tr height="20">
									<td class ="stab"><ezf:anchor name="mdseCd_H2" ezfName="mdseCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemMaster" otherAttr=" id=\"itemLink\"">Original Item</ezf:anchor></td>
									<td class ="stab"><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="Q99999" otherAttr=" size=\"20\" maxlength=\"16\" ezftoupper=\"\""/>
													<ezf:inputButton name="SetItemDescription" value=">>" htmlClass="pBtn0" />
													<ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="ED PETNER" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
									<td class ="stab"><ezf:anchor name="rtlWhNm_H2" ezfName="rtlWhNm_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoForWh" otherAttr=" id=\"whLink\"">Warehouse</ezf:anchor></td>
									<td colspan="3"><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td></td>
								</tr>
								<tr height="20">
									<td class ="stab">Stock Status</td>
									<td>
										<ezf:select name="stkStsCd_H1" ezfName="stkStsCd_H1" ezfBlank="1" ezfCodeName="stkStsCd_L1" ezfDispName="stkStsNm_L1" otherAttr=" style=\"width:146\""/>
									</td>
									<td class ="stab"><ezf:anchor name="rtlSwhNm_H2" ezfName="rtlSwhNm_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoForSwh" otherAttr=" id=\"swhLink\"">Sub Warehouse</ezf:anchor></td>
									<td colspan="3"><ezf:inputText name="rtlSwhNm_H1" ezfName="rtlSwhNm_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
                                    <td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
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
                            <div id="topTBL" style="overflow-y:none; height:; width:953;">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38">
                                    <col width="150"  align="center"><!-- Original Item Number -->
                                    <col width="150"  align="center"><!-- SuperSession Item Number -->
                                    <col width="150"  align="center"><!-- SuperSession Description -->
                                    <col width="150"  align="center"><!-- Warehouse -->
                                    <col width="150"  align="center"><!-- Sub Warehouse -->
                                    <col width="40"   align="center"><!-- SS -->
                                    <col width="80"   align="center"><!-- Current Available -->
                                    <col width="80"   align="center"><!-- Current On-Hand -->
                                    <tr>
                                        <td class="pClothBs" rowspan="2">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_O1')">Original<br>Item Number</a>
                                            <img id="sortIMG.mdseCd_O1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs" colspan="2">SuperSession</td>
                                        <td class="pClothBs" rowspan="2">Warehouse</td>
                                        <td class="pClothBs" rowspan="2">Sub Warehouse</td>
                                        <td class="pClothBs" rowspan="2">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsCd_A1')">SS</a>
                                            <img id="sortIMG.stkStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs" rowspan="2">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'invtyAvalQty_A1')">Current<br>Available</a>
                                            <img id="sortIMG.invtyAvalQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs" rowspan="2">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'invtyQty_A1')">Current<br>On-Hand</a>
                                            <img id="sortIMG.invtyQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A1')">Item</a>
                                            <img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A1')">Description</a>
                                            <img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>
                            <div id="btmTBL" style="overflow-y:scroll; height:320; width:970;">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                    <col width="150"  align="left"><!-- Original Item Number -->
                                    <col width="150"  align="left"><!-- SuperSession Item Number -->
                                    <col width="150"  align="left"><!-- SuperSession Description -->
                                    <col width="150"  align="left"><!-- Warehouse -->
                                    <col width="150"  align="left"><!-- Sub Warehouse -->
                                    <col width="40"   align="center"><!-- SS -->
                                    <col width="80"   align="left"><!-- Current Available -->
                                    <col width="80"   align="left"><!-- Current On-Hand -->
                                    <ezf:row ezfHyo="A">
                                        <!-- 1 -->
                                        <tr height="28">
                                            <td><ezf:label name="mdseCd_O1" ezfName="mdseCd_O1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:anchor name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Item" otherAttr=" id=\"id_row_{EZF_ROW_NUMBER}\""><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                            <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IR-ADV C350IF 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/></td>
                                            <td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="MONROE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:label name="stkStsCd_A1" ezfName="stkStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="invtyAvalQty_A1" ezfName="invtyAvalQty_A1" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                            <td><ezf:inputText name="invtyQty_A1" ezfName="invtyQty_A1" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                        <!-- 2 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><a href="#" onclick="sendServer('SelectManager')"><label ezfout name="mgrPsnCd_A1" ezfname="mgrPsnCd_A1" ezfhyo="A">8456B003AB</label></a></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="70D1855+55250" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="MONROE" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">U90</label></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">1</label></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="0" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="0" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                        </tr>
                                        <!-- 3 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><a href="#" onclick="sendServer('SelectManager')"><label ezfout name="mgrPsnCd_A1" ezfname="mgrPsnCd_A1" ezfhyo="A">8456B003AC</label></a></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="VIXIA HF G20 A K" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="MONROE" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">U70</label></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">1</label></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="15" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="15" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                        </tr>
                                        <!-- 4 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><a href="#" onclick="sendServer('SelectManager')"><label ezfout name="mgrPsnCd_A1" ezfname="mgrPsnCd_A1" ezfhyo="A">8456B003AD</label></a></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="RFD 6D 24-105 S" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="ATRANTA" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">NEW</label></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">1</label></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="1" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="1" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                        </tr>
                                        <!-- 5 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><a href="#" onclick="sendServer('SelectManager')"><label ezfout name="mgrPsnCd_A1" ezfname="mgrPsnCd_A1" ezfhyo="A">8456B003BA</label></a></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="IR-ADV C2225 UL" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="ATRANTA" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">NEW</label></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">1</label></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="1" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="1" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                        </tr>
                                        <!-- 6 -->
                                        <tr height="28">
                                            <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
                                            <td><a href="#" onclick="sendServer('SelectManager')"><label ezfout name="mgrPsnCd_A1" ezfname="mgrPsnCd_A1" ezfhyo="A">8456B003BB</label></a></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="IR-ADV C350IF 1" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="20" maxlength="30" value="MONROE" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">NEW</label></td>
                                            <td><label ezfout name="rtlWhCd_A1" ezfname="rtlWhCd_A1" ezfhyo="A">1</label></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="4" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
                                            <td><input class="pPro" type="text" size="10" maxlength="10" value="4" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" ezfhyo="A"></td>
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
