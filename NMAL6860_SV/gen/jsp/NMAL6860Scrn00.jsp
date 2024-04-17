<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200229105937 --%>
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
			<input type="hidden" name="pageID" value="NMAL6860Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supplier Entry">

            <%-- ********************** --%>
            <%-- *** Upper Tab Area *** --%>
            <%-- ********************** --%>
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
								<tr>
									<td width="1070px" height="28px" valign="bottom">
										<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
											<tr title="Supplier Entry Screen">
												<td width="107px" align="center" class="same">Supplier</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>

							<div class="pTab_BODY">
								<table  style="margin-left:5; margin-top:0;">
									<col width="1000"  align="left">
									<tr>
										<td>
											<table border="0">
                                                <col width="100"  align="left">
                                                <col width="60"  align="left">
                                                <col width="110"  align="right">
                                                <col width="150" align="left">
                                                <col width="5"   align="left">
												<col width="80"  align="left">
												<col width="60"  align="left">
												<col width="50"  align="left">
												<%-- =============== Warehouse Category =============== --%>
												<tr height="20">
													<td class="stab">Supplier Number</td>
													<td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="1284147" otherAttr=" size=\"10\""/></td>
													<td class="stab">Supplier Name</td>
													<td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="AZERTY INC." otherAttr=" size=\"25\" maxlength=\"240\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab"><ezf:anchor name="xxLinkAncr_ST" ezfName="xxLinkAncr_ST" ezfEmulateBtn="1" ezfGuard="OpenWin_SupplierType" >Supplier Type</ezf:anchor></td>
													<td><ezf:inputText name="prntVndTpDescTxt" ezfName="prntVndTpDescTxt" value="CANON COMPANY" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>
							<%-- ######################################## DETAIL ######################################## --%>

							<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
								<ul>
									<table width="900" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="General" id="General" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_H1" ezfEmulateBtn="1" ezfGuard="TAB_General" >General</ezf:anchor></li>
													<li title="Detail" id="Detail" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_H2" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor></li>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>

							<div class="pTab_BODY_In">
								<div style="height: 314px" >
								<c:choose>
									<%-- ADDED FROM HERE --%>
									<%-- ---------------------------------- TAB -------------------------------- --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'General'}">
										<div id="TabContent-General"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById("General").className = "pTab_ON";
                                                document.getElementById("Detail").className = "pTab_OFF";
                                            </script>
                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                                                <col width="238"  align="left">
                                                <col width="290"  align="left">
                                                <col width="310"  align="left">
                                                <col width="255"  align="left">
                                                <tr>
                                                    <td valign="top">
                                                        <table border="0">
                                                            <col width="120" align="left">
                                                            <col width="118" align="left">
                                                            <!-- 1 -->
                                                            <tr height="20">
                                                                <td class="stab">Tax Payer ID</td>
                                                                <td><ezf:inputText name="taxPayerId_H1" ezfName="taxPayerId_H1" value="75-2770316" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 2 -->
                                                            <tr height="20">
                                                                <td class="stab">Inactive On</td>
                                                                <td>
                                                                    <ezf:inputText name="inacDt_H1" ezfName="inacDt_H1" value="05/15/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'inacDt_H1', 4 );">
                                                                </td>
                                                            </tr>
                                                            <!-- 3 -->
                                                            <tr height="20">
                                                                <td class="stab">Tax Payer Reg No</td>
                                                                <td><ezf:inputText name="taxPayerRgNum_H1" ezfName="taxPayerRgNum_H1" value="2770316" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 4 -->
                                                            <tr height="20">
                                                                <td class="stab">ARCS Supplier Number</td>
                                                                <td><ezf:inputText name="arcsSplyNum_H1" ezfName="arcsSplyNum_H1" value="666926" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 5 -->
                                                           <tr height="20">
                                                                <td class="stab">ARCS Supplier ID</td>
                                                                <td><ezf:inputText name="arcsSplyId_H1" ezfName="arcsSplyId_H1" value="666926" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
 
                                                            <!-- 6 -->
                                                            <tr height="20">
                                                               <td class="stab"><ezf:anchor name="xxLinkAncr_CN" ezfName="xxLinkAncr_CN" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerNumber" >Customer Number</ezf:anchor></td>
                                                                <td><ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" value="101900" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 7 -->
                                                            <tr height="20">
                                                                <td class="stab"></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td valign="top">
                                                        <table border="0">
                                                            <col width="105" align="left">
                                                            <col width="185" align="left">
                                                            <!-- 1 -->
                                                            <tr height="20">
                                                                <td class="stab">SIC</td>
                                                                <td><ezf:inputText name="indyTpCd_H1" ezfName="indyTpCd_H1" value="100" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 2 -->
                                                            <tr height="20">
                                                                <td class="stab">Minority Owned</td>
                                                                <td>
                                                                    <ezf:select name="mnrtyOwndTpCd_H1" ezfName="mnrtyOwndTpCd_H1" ezfBlank="1" ezfCodeName="mnrtyOwndTpCd_L" ezfDispName="mnrtyOwndTpDescTxt_L" otherAttr=" style=\"width:180px;\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 3 -->
                                                            <tr height="20">
                                                                <td class="stab">Organization Type</td>
                                                                <td>
                                                                    <ezf:select name="splyOrgTpCd_H1" ezfName="splyOrgTpCd_H1" ezfBlank="1" ezfCodeName="splyOrgTpCd_L" ezfDispName="splyOrgTpDescTxt_L" otherAttr=" style=\"width:180px;\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 4 -->
                                                            <tr height="20">
                                                                <td class="stab">One Time Supplier</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="splyOneTmFlg_H1" ezfName="splyOneTmFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 5 -->
                                                            <tr height="20">
                                                                <td class="stab">Small Business</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="smBizFlg_H1" ezfName="smBizFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 6 -->
                                                            <tr height="20">
                                                                <td class="stab">Women Owned</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="womenOwndFlg_H1" ezfName="womenOwndFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 7 -->
                                                            <tr height="20">
                                                               <td class="stab"><ezf:anchor name="xxLinkAncr_IC" ezfName="xxLinkAncr_IC" ezfEmulateBtn="1" ezfGuard="OpenWin_LookupAffiliate" >Intercompany</ezf:anchor></td>
                                                                <td><ezf:inputText name="coaAfflCd_H1" ezfName="coaAfflCd_H1" value="AZC" otherAttr=" size=\"15\" maxlength=\"3\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td valign="top">
                                                        <table border="0">
                                                            <col width="125" align="left">
                                                            <col width="185" align="left">
                                                            <!-- 1 -->
                                                            <tr height="20">
                                                                <td class="stab">Pay Alone</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="payAloneFlg_H1" ezfName="payAloneFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 2 -->
                                                            <tr height="20">
                                                                <td class="stab">Always Take Discount</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="discTakeFlg_H1" ezfName="discTakeFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 3 -->
                                                            <tr height="20">
                                                                <td class="stab">Hold Flag</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="poPmtHldFlg_H1" ezfName="poPmtHldFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 4 -->
                                                            <tr height="20">
                                                                <td class="stab"><ezf:anchor name="xxLinkAncr_PT" ezfName="xxLinkAncr_PT" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerm" >Payment Terms</ezf:anchor></td>
                                                                <td><ezf:inputText name="vndPmtTermDescTxt_H1" ezfName="vndPmtTermDescTxt_H1" value="IMMEDIATE" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 5 -->
                                                            <tr height="20">
                                                                <td class="stab">Payment Method</td>
                                                                <td>
                                                                    <ezf:select name="vndPmtMethCd_H1" ezfName="vndPmtMethCd_H1" ezfBlank="1" ezfCodeName="vndPmtMethCd_L" ezfDispName="vndPmtMethDescTxt_L" otherAttr=" style=\"width:180px;\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 6 -->
                                                            <tr height="20">
                                                                <td class="stab">Pay Group</td>
                                                                <td>
                                                                    <ezf:select name="payGrpCd_H1" ezfName="payGrpCd_H1" ezfBlank="1" ezfCodeName="payGrpCd_L" ezfDispName="payGrpDescTxt_L" otherAttr=" style=\"width:180px;\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 7 -->
                                                            <tr height="20">
                                                                <td class="stab"></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td valign="top">
                                                        <table border="0">
                                                            <col width="100" align="left">
                                                            <col width="155" align="left">
                                                            <!-- 1 -->
                                                            <tr height="20">
                                                                <td class="stab">Federal Tax</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="fdRptFlg_H1" ezfName="fdRptFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 2 -->
                                                            <tr height="20">
                                                                <td class="stab">Income Tax Type</td>
                                                                <td>
                                                                    <ezf:select name="incTaxTpCd_H1" ezfName="incTaxTpCd_H1" ezfBlank="1" ezfCodeName="incTaxTpCd_L" ezfDispName="incTaxTpDescTxt_L" otherAttr=" style=\"width:153px;\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 3 -->
                                                            <tr height="20">
                                                                <td class="stab">State Tax</td>
                                                                <td>
                                                                    <ezf:inputCheckBox name="stTaxFlg_H1" ezfName="stTaxFlg_H1" value="Y" />
                                                                </td>
                                                            </tr>
                                                            <!-- 4 -->
                                                            <tr height="20">
                                                                <td class="stab">Reporting Name</td>
                                                                <td><ezf:inputText name="splyRptNm_H1" ezfName="splyRptNm_H1" value="CARD INTEGRATORS" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 5 -->
                                                            <tr height="20">
                                                                <td class="stab">Hub Zone</td>
                                                                <td><ezf:inputText name="splyHubZnCd_H1" ezfName="splyHubZnCd_H1" value="Yes" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 6 -->
                                                            <tr height="20">
                                                                <td class="stab"></td>
                                                                <td></td>
                                                            </tr>
                                                            <!-- 7 -->
                                                            <tr height="20">
                                                                <td class="stab"></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td><ezf:inputButton name="OnClick_AddSupplierSite" value="Add Supplier Site" htmlClass="pBtn9" /></td>
                                                </tr>
                                            </table>
                                            <table style="MARGIN-LEFT: 0px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <%-- =============== TABLE HEADER =============== --%>
                                                <tr>
                                                    <td align="left" valign="top">
                                                        <%-- ******************************* --%>
                                                        <%-- *** Left Table Area(Header) *** --%>
                                                        <%-- ******************************* --%>
                                                        <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0">
                                                            <col width="25"  align="center">
                                                            <col width="120"  align="center">
                                                            <col width="120" align="center">
                                                            <tr height="25">
                                                                <td class="pClothBs">#</td>
                                                                <td class="pClothBs">Site Code</td>
                                                                <td class="pClothBs">Site Name</td>
                                                            </tr>
                                                        </table>
                                                        <%-- ******************************* --%>
                                                        <%-- *** Left Table Area(Detail) *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id="LeftTBL" style="overflow-y:hidden; height:233; overflow-x:hidden; width:; margin-left: 5px;"
                                                            onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                                                <col width="25"  align="center">
                                                                <col width="120"  align="center">
                                                                <col width="120" align="center">
                                                                <ezf:row ezfHyo="A">
                                                                    <tr height="25">
                                                                        <td class="stab"><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
                                                                        <td><ezf:inputText name="vndCd_A" ezfName="vndCd_A" value="9125" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\" id=\"vndCd_A#{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="AZERTY Site01" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\" id=\"locNm_A#{EZF_ROW_NUMBER}\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="25">
                                                                        <td><input type="radio"  value="{EZF_ROW_NUMBER}" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" ezfhyo="A"></td>
                                                                        <td><input type="text" size="15" readonly value="27141" name="vndCd_A" ezfname="vndCd_A" ezfhyo="A"  ezftoupper class="pPro"></td>
                                                                        <td><input type="text" size="15" maxlength="15" value="AZERTY Site02" name="locNm_A" ezfname="locNm_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    </tr>
                                                                </ezf:skip>
                                                            </table>
                                                        </div>
                                                    </td>
                                                    <td valign="top">
                                                        <%-- ******************************** --%>
                                                        <%-- *** Right Table Area(Header) *** --%>
                                                        <%-- ******************************** --%>
                                                        <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:830;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                                <col width="70" align="center">
                                                                <col width="350" align="center">
                                                                <col width="350" align="center">
                                                                <col width="150"  align="center">
                                                                <col width="120"  align="center">
                                                                <col width="40"  align="center">
                                                                <col width="100"  align="center">
                                                                <col width="60"  align="center">
                                                                <col width="60"  align="center">
                                                                <col width="410"  align="center">
                                                                <col width="100"  align="center">
                                                                <tr height="25">
                                                                    <td class="pClothBs">Country</td>
                                                                    <td class="pClothBs">Address1</td>
                                                                    <td class="pClothBs">Address2</td>
                                                                    <td class="pClothBs">City</td>
                                                                    <td class="pClothBs">PostalCode</td>
                                                                    <td class="pClothBs">State</td>
                                                                    <td class="pClothBs">County</td>
                                                                    <td class="pClothBs">Pay Flag</td>
                                                                    <td class="pClothBs">Pur Flag</td>
                                                                    <td class="pClothBs">Liability Account</td>
                                                                    <td class="pClothBs">Inactive On</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                        <%-- ******************************** --%>
                                                        <%-- *** Right Table Area(Detail) *** --%>
                                                        <%-- ******************************** --%>
                                                        <div id="RightTBL" style="overflow-y:scroll; height:250; overflow-x:scroll; width:847;"
                                                            onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );"><!--769-->
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right">
                                                                <col width="70" align="center">
                                                                <col width="350" align="center">
                                                                <col width="350" align="center">
                                                                <col width="150"  align="center">
                                                                <col width="120"  align="center">
                                                                <col width="40"  align="center">
                                                                <col width="100"  align="center">
                                                                <col width="60"  align="center">
                                                                <col width="60"  align="center">
                                                                <col width="410"  align="center">
                                                                <col width="100"  align="center">
                                                                <ezf:row ezfHyo="A">
                                                                <tr height="25">
                                                                    <td><ezf:inputText name="ctryCd_A" ezfName="ctryCd_A" value="USA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"3\" ezftoupper=\"\" id=\"ctryCd_A#{EZF_ROW_NUMBER}\""/></td>
                                                                    <td><ezf:inputText name="xxComnScrFirstValTxt_A" ezfName="xxComnScrFirstValTxt_A" value="1708 SOLUTIONS CENTER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"120\" ezftoupper=\"\""/></td>
                                                                    <td><ezf:inputText name="xxComnScrScdValTxt_A" ezfName="xxComnScrScdValTxt_A" value="P.O. BOX 952418 ST. LOUIS, MO. 63195" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"120\" ezftoupper=\"\""/></td>
                                                                    <td>
                                                                        <ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" value="CHICAGO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                        <ezf:inputButton name="OpenWin_City" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                    </td>
                                                                    <td>
                                                                        <ezf:inputText name="postCd_A" ezfName="postCd_A" value="60677-1007" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"15\" ezftoupper=\"\""/>
                                                                        <ezf:inputButton name="GetAddress" value="Get" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                    </td>
                                                                    <td><ezf:inputText name="stCd_A" ezfName="stCd_A" value="IL" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"2\" ezftoupper=\"\""/></td>
                                                                    <td><ezf:inputText name="cntyNm_A" ezfName="cntyNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                    <td><ezf:inputCheckBox name="splyPmtFlg_A" ezfName="splyPmtFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                    <td><ezf:inputCheckBox name="splyPoFlg_A" ezfName="splyPoFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                    <td>
                                                                      <ezf:inputButton name="OnClick_LiabilityAccount" value="Acct" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                      <ezf:inputText name="xxComnScrFirstValTxt_AL" ezfName="xxComnScrFirstValTxt_AL" value="ADB.000.00000.00000.ZZ.000.000.0000.000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" ezftoupper=\"\""/>
                                                                    </td>
                                                                    <td><ezf:inputText name="inacDt_A" ezfName="inacDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" id=\"inacDt_A#{EZF_ROW_NUMBER}\""/></td>
                                                                </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                <tr height="25">
                                                                    <td><input type="text" size="8" maxlength="3" value="USA" name="ctryCd_A" ezfname="ctryCd_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    <td><input type="text" size="50" maxlength="120" value="PO BOX 952418" name="xxComnScrFirstValTxt_A" ezfname="xxComnScrFirstValTxt_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    <td><input type="text" size="50" maxlength="120" value="" name="xxComnScrScdValTxt_A" ezfname="xxComnScrScdValTxt_A" ezfhyo="A"></td>
                                                                    <td><input type="text" size="12" maxlength="30" value="ST. LOUIS" name="ctyAddr_A" ezfname="ctyAddr_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    <td><input type="text" size="8" maxlength="15" value="63195" name="postCd_A" ezfname="postCd_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    <td><input type="text" size="4" maxlength="2" value="MO" name="stCd_A" ezfname="stCd_A" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                                    <td><input type="text" size="12" maxlength="30" value="" name="cntyNm_A" ezfname="cntyNm_A" ezfhyo="A"></td>
                                                                    <td><input type="checkbox"  value="Y" name="splyPmtFlg_A" ezfname="splyPmtFlg_A" ezfhyo="A"></td>
                                                                    <td><input type="checkbox"  value="Y" name="splyPoFlg_A" ezfname="splyPoFlg_A" ezfhyo="A"></td>
                                                                    <td>
                                                                      <input type="button" value="Acct" class="pBtn2" name="OpenWin_LiabilityAccount" onclick="sendServer(this)" ezfhyo="A">
                                                                      <input type="text" class="pPro" readonly size="50" value="ADB.000.00000.00000.ZZ.000.000.0000.000" name="lbtyCoaCmpyCd_H1" ezfname="trdgPtnrId_H1" ezftoupper >
                                                                    </td>
                                                                    <td><input type="text" class="pPro" size="12" maxlength="30" value="05/18/2015" name="rtlSwhSortNum_A1" ezfname="rtlSwhSortNum_A1" ezfhyo="A"></td>
                                                                </tr>
                                                                </ezf:skip>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
										</div><!-- Added -->
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Detail'}">
										<div id="TabContent-Detail"><!-- Added -->
    										<script type="text/javascript">
    											document.getElementById("General").className = "pTab_OFF";
    											document.getElementById("Detail").className = "pTab_ON";
    										</script>

                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;">
                                                <col width="1093"  align="left">
                                                <tr>
                                                    <td valign="top">
                                                        <table border="0">
                                                            <col width="115" align="left">
                                                            <col width="255" align="left">
                                                            <col width="130" align="left">
                                                            <col width="150" align="left">
                                                            <col width="143" align="left">
                                                            <col width="140" align="left">
                                                            <!-- 1 -->
                                                            <tr height="22">
                                                                <td class="stab">Supplier Site Code</td>
                                                                <td><ezf:inputText name="vndCd_H2" ezfName="vndCd_H2" value="9125" otherAttr=" size=\"34\" maxlength=\"8\" tabindex=\"211\" ezftoupper=\"\""/></td>
                                                                <td class="stab">Supplier Site Name</td>
                                                                <td colspan="3"><ezf:inputText name="locNm_H2" ezfName="locNm_H2" value="AZERTY Site01" otherAttr=" size=\"61\" maxlength=\"30\" tabindex=\"221\" ezftoupper=\"\""/></td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                            <!-- 2 -->
                                                            <tr height="22">
                                                                <td class="stab">Invoice Match Option</td>
                                                                <td>
                                                                    <ezf:select name="invMatchTpCd_H2" ezfName="invMatchTpCd_H2" ezfBlank="1" ezfCodeName="invMatchTpCd_L" ezfDispName="invMatchTpDescTxt_L" otherAttr=" style=\"width:244px;\" tabindex=\"212\""/>
                                                                </td>
                                                                <td class="stab">EDI Location</td>
                                                                <td><ezf:inputText name="splyEdiLocCd_H2" ezfName="splyEdiLocCd_H2" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"222\" ezftoupper=\"\""/></td>
                                                                <td class="stab">External Reference</td>
                                                                <td><ezf:inputText name="xtrnlRefNum_H2" ezfName="xtrnlRefNum_H2" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"241\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 3 -->
                                                            <tr height="22">
                                                                <td class="stab">Invoice Tolerance %</td>
                                                                <td><ezf:inputText name="invTolRate_H2" ezfName="invTolRate_H2" otherAttr=" size=\"34\" maxlength=\"30\" tabindex=\"213\" ezftoupper=\"\""/></td>
                                                                <td class="stab">EDI ID Num</td>
                                                                <td><ezf:inputText name="splyEdiNum_H2" ezfName="splyEdiNum_H2" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"223\" ezftoupper=\"\""/></td>
                                                                <td class="stab">End Customer Num</td>
                                                                <td><ezf:inputText name="endCustNum_H2" ezfName="endCustNum_H2" value="10001" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"242\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 4 -->
                                                            <tr height="22">
                                                                <td class="stab">Receipt Tolerance %</td>
                                                                <td><ezf:inputText name="rcvTolRate_H2" ezfName="rcvTolRate_H2" otherAttr=" size=\"34\" maxlength=\"30\" tabindex=\"214\" ezftoupper=\"\""/></td>
                                                                <td class="stab">Inactive On</td>
                                                                <td>
                                                                    <ezf:inputText name="inacDt_H2" ezfName="inacDt_H2" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"224\" ezftoupper=\"\""/>
                                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'inacDt_H2', 4 );">
                                                                </td>
                                                                <td class="stab">ARCS Supplier Site Number</td>
                                                                <td><ezf:inputText name="arcsSplySiteCd_H2" ezfName="arcsSplySiteCd_H2" value="666926" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"243\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 5 -->
                                                            <tr height="22">
                                                                <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerm" >Payment Terms</ezf:anchor></td>
                                                                <td><ezf:inputText name="vndPmtTermDescTxt_H2" ezfName="vndPmtTermDescTxt_H2" value="IMMEDIATE" otherAttr=" size=\"34\" maxlength=\"50\" tabindex=\"215\" ezftoupper=\"\""/></td>
                                                                <td class="stab">CUSA Dealer Code</td>
                                                                <td><ezf:inputText name="splySiteDealCd_H2" ezfName="splySiteDealCd_H2" value="1000" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"225\" ezftoupper=\"\""/></td>
                                                                <td class="stab">ARCS Supplier Site ID</td>
                                                                <td><ezf:inputText name="arcsSplySiteId_H2" ezfName="arcsSplySiteId_H2" value="666926" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"244\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            </tr>
                                                            <!-- 6 -->
                                                            <tr height="22">
                                                                <td class="stab">Payment Method</td>
                                                                <td>
                                                                    <ezf:select name="vndPmtMethCd_H2" ezfName="vndPmtMethCd_H2" ezfBlank="1" ezfCodeName="vndPmtMethCd_L" ezfDispName="vndPmtMethDescTxt_L" otherAttr=" style=\"width:244px;\" tabindex=\"216\""/>
                                                                </td>
                                                                <td class="stab">Outb PO Trans Method</td>
                                                                <td>
                                                                    <ezf:select name="trsmtMethTpCd_H2" ezfName="trsmtMethTpCd_H2" ezfBlank="1" ezfCodeName="trsmtMethTpCd_L" ezfDispName="trsmtMethTpDescTxt_L" otherAttr=" style=\"width:132px;\" tabindex=\"226\""/>
                                                                </td>
                                                                <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerLocation" otherAttr=" tabindex=\"245\"">Customer Location</ezf:anchor></td>
                                                                <td><ezf:inputText name="locNum_H2" ezfName="locNum_H2" value="1001001" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"246\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 7 -->
                                                            <tr height="22">
                                                                <td class="stab">Pay Group</td>
                                                                <td>
                                                                    <ezf:select name="payGrpCd_H2" ezfName="payGrpCd_H2" ezfBlank="1" ezfCodeName="payGrpCd_L" ezfDispName="payGrpDescTxt_L" otherAttr=" style=\"width:244px;\" tabindex=\"217\""/>
                                                                </td>
                                                                <td colspan="2">
                                                                </td>
                                                                <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_InvoiceSupplier" otherAttr=" tabindex=\"247\"">Invoice Supplier Site</ezf:anchor></td>
                                                                <td><ezf:inputText name="invVndCd_H2" ezfName="invVndCd_H2" value="9125" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"248\" ezftoupper=\"\""/></td>
                                                            </tr>
                                                            <!-- 8 -->
                                                            <tr height="22">
                                                                <td colspan="2">
                                                                </td>
                                                                <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_PrePayAccount" otherAttr=" tabindex=\"227\"">PrePay Account</ezf:anchor></td>
                                                                <td colspan="3">
                                                                  <ezf:inputText name="xxComnScrFirstValTxt_H2" ezfName="xxComnScrFirstValTxt_H2" value="ADB.000.00000.00000.ZZ.000.000.0000.000" otherAttr=" size=\"50\" tabindex=\"228\" ezftoupper=\"\""/>
                                                                </td>
                                                            </tr>
                                                            <!-- 9 -->
                                                            <tr height="22">
                                                                <td colspan="2">
                                                                </td>
                                                                <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_VendorReturnAccount" otherAttr=" tabindex=\"229\"">Vnd Return Account</ezf:anchor></td>
                                                                <td colspan="3">
                                                                  <ezf:inputText name="xxComnScrScdValTxt_H2" ezfName="xxComnScrScdValTxt_H2" value="ADB.000.00000.00000.ZZ.000.000.0000.000" otherAttr=" size=\"50\" tabindex=\"230\" ezftoupper=\"\""/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td><ezf:inputButton name="OnClick_AddContactInfo" value="Add Contact Info" htmlClass="pBtn9" /></td>
                                                </tr>
                                            </table>
                                            <table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <%-- =============== TABLE HEADER =============== --%>
                                                <tr>
                                                    <td>
                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                            <col width="100" align="center">
                                                            <col width="100" align="center">
                                                            <col width="120" align="center">
                                                            <col width="100" align="center">
                                                            <col width="100" align="center">
                                                            <col width="120" align="center">
                                                            <col width="160" align="center">
                                                            <col width="190" align="center">
                                                            <col width="110" align="center">
                                                            <tr height="20">
                                                                <td class="pClothBs">Last Name</td>
                                                                <td class="pClothBs">First Name</td>
                                                                <td class="pClothBs">Title</td>
                                                                <td class="pClothBs">Telephone#</td>
                                                                <td class="pClothBs">Fax#</td>
                                                                <td class="pClothBs">Department</td>
                                                                <td class="pClothBs">Contact Type</td>
                                                                <td class="pClothBs">Email Address</td>
                                                                <td class="pClothBs">Inactive On</td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <%-- =============== TABLE BODY =============== --%>
                                                <tr>
                                                    <td>
                                                        <div style="overflow-x:none; overflow-y:scroll; height:200;">
                                                            <table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
                                                                <col width="100" align="center">
                                                                <col width="100" align="center">
                                                                <col width="120" align="center">
                                                                <col width="100" align="center">
                                                                <col width="100" align="center">
                                                                <col width="120" align="center">
                                                                <col width="160" align="center">
                                                                <col width="190" align="center">
                                                                <col width="110" align="center">
                                                                <ezf:row ezfHyo="B">
                                                                    <tr id="id_row_{EZF_ROW_NUMBER}" height="24">
                                                                        <td><ezf:inputText name="ctacPsnLastNm_B" ezfName="ctacPsnLastNm_B" value="Jack" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                        <td><ezf:inputText name="ctacPsnFirstNm_B" ezfName="ctacPsnFirstNm_B" value="Nicolson" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                        <td>
                                                                            <ezf:select name="dsCtacPsnTtlCd_B" ezfName="dsCtacPsnTtlCd_B" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCtacPsnTtlCd_L" ezfDispName="dsCtacPsnTtlDescTxt_L" otherAttr=" style=\"width:110px\""/>
                                                                        </td>
                                                                        <td><ezf:inputText name="dsCtacPntValTxt_BT" ezfName="dsCtacPntValTxt_BT" value="510-356-1019" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                        <td><ezf:inputText name="dsCtacPntValTxt_BF" ezfName="dsCtacPntValTxt_BF" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                        <td>
                                                                            <ezf:select name="dsCtacPsnDeptCd_B" ezfName="dsCtacPsnDeptCd_B" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCtacPsnDeptCd_L" ezfDispName="dsCtacPsnDeptDescTxt_L" otherAttr=" style=\"width:110px\""/>
                                                                        <td>
                                                                            <ezf:select name="ctacTpCd_B" ezfName="ctacTpCd_B" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ctacTpCd_L" ezfDispName="ctacTpDescTxt_L" otherAttr=" style=\"width:155px;\""/></td>
                                                                        <td><ezf:inputText name="dsCtacPntValTxt_BE" ezfName="dsCtacPntValTxt_BE" value="nicolson@azerty.com" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
                                                                        <td>
                                                                            <ezf:inputText name="inacDt_B" ezfName="inacDt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/>
                                                                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('inacDt_B', 4, '{EZF_ROW_NUMBER}');" >
                                                                        </td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="22">
                                                                        <td><input type="text" size="12" maxlength="30" value="GREG" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                                        <td><input type="text" size="12" maxlength="30" value="YOCCA" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                                        <td>
                                                                            <select style="width:110px">
                                                                                <option>ACCOUNTS PAYABLE</option>
                                                                                <option>ACCOUNTS RECEIVABLE</option>
                                                                                <option>EXECUTIVE</option>
                                                                                <option>IT</option>
                                                                                <option>MARKETING</option>
                                                                                <option>METER READS</option>
                                                                                <option>PURCHASING</option>
                                                                                <option>SALES</option>
                                                                                <option>KEY OPERATOR</option>
                                                                                <option>PRIMARY IB PHONE CONTACT</option>
                                                                                <option>PRIMARY IB FAX CONTACT</option>
                                                                                <option>PRIMARY IB EMAIL CONTACT</option>
                                                                            </select>
                                                                        </td>
                                                                        <td><input type="text" size="12" maxlength="30" value="310-356-2019" name="mdseInvtyCostPct_A1" ezfname="mdseInvtyCostPct_A1" ezfhyo="A"></td>
                                                                        <td><input type="text" size="12" maxlength="30" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                                        <td>
                                                                            <select style="width:110px" name="dsCtacPsnDeptCd_B" ezfname="dsCtacPsnTtlCd_B" ezfhyo="B" ezflist="dsCtacPsnTtlCd_L,dsCtacPsnTtlDescTxt_L,99, ,blank">
                                                                                <option>Manager</option>
                                                                            </select>                                                                    
                                                                        <td>
                                                                            <select style="width:155px;" name="invtyOwnrCd_H1" ezfname="invtyOwnrCd_H1" ezflist="invtyOwnrCd_L1,invtyOwnrNm_L1,99, ,blank" >
                                                                                <option></option>
                                                                                <option>Admin</option>
                                                                                <option>Service Operations</option>
                                                                            </select></td>
                                                                        <td><input type="text" size="25" maxlength="50" value="greg@azerty.com" name="rtlSwhSortNum_A1" ezfname="rtlSwhSortNum_A1" ezfhyo="A"></td>
                                                                        <td>
                                                                            <input type="text" size="10" maxlength="30" value="" name="rtlSwhSortNum_A1" ezfname="rtlSwhSortNum_A1" ezfhyo="A">
                                                                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_H1', 4 );">
                                                                        </td>
                                                                    </tr>
                                                                </ezf:skip>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px;" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </div>
									</c:when>
								</c:choose>
								</div>
                            </div>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
