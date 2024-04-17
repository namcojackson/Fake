<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200625135058 --%>
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
			<input type="hidden" name="pageID" value="NLCL0620Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech PI Entry">

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
                            <div class="pTab_HEAD">
                                <ul>
                                    <li class="pTab_ON"><a href="./NLCL0620Scrn00.html">Tech PI Entry</a></li>
                                </ul>
                            </div>

							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                            </ezf:skip>

							<div class="pTab_BODY">
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px"border="0">
									<tr>
										<col width="500" align="left">
										<col width="590" align="left">
									</tr>
									<tr>
										<td valign="top">
                                            <table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<col width="120" align="left">
													<col width="190"  align="left">
													<col width="190"  align="left">
												</tr>
												<!-- 1 -->
												<tr height="20">
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" otherAttr=" ezfanchortext=\"\"">Technician Name</ezf:anchor></td>
													<td colspan="2">
													    <ezf:inputText name="techTocCd" ezfName="techTocCd" value="----+---" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/>
													    <ezf:inputButton name="SearchTechnician" value=">>" htmlClass="pBtn0" />
													    <ezf:inputText name="techNm" ezfName="techNm" value="----+----1----+----2----+----3----+----4----+" otherAttr=" size=\"20\" maxlength=\"45\""/>
													</td>
												</tr>
												<!-- 2 -->
												<tr height="20">
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Location" otherAttr=" ezfanchortext=\"\"">Location</ezf:anchor></td>
													<td colspan="2">
													    <ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+---" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/>
													    <ezf:inputButton name="SearchLocation" value=">>" htmlClass="pBtn0" />
													    <ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3----+----4----+" otherAttr=" size=\"20\" maxlength=\"45\""/>
													</td>
												</tr>
												<!-- 3 -->
												<tr height="20">
													<td class="stab">Scheduled Date</td>
													<td>
													    <ezf:inputText name="physInvtyDt" ezfName="physInvtyDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
													    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('physInvtyDt', 4);"/>
													</td>
													<td>
													    <ezf:inputButton name="Search" value="Check Receipts" htmlClass="pBtn10" />
													</td>
												</tr>
												<!-- 4 -->
												<tr height="20">
													<td class="stab">Tech Physical</td>
													<td colspan="2"><ezf:inputText name="physInvtyCtrlNm" ezfName="physInvtyCtrlNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" otherAttr=" size=\"35\" maxlength=\"100\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top">
                                            <table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<col width="100" align="left">
													<col width="250" align="left">
													<col width="240"  align="left">
												</tr>
												<!-- 1 -->
												<tr height="20">
													<td></td>
													<td class="stab">Last Physical Inventory</td>
													<td>
													    <ezf:inputText name="shipDt" ezfName="shipDt" value="01/01/2015" otherAttr=" size=\"33\" maxlength=\"10\""/>
													</td>
												</tr>
												<!-- 2 -->
												<tr height="20">
													<td></td>
													<td class="stab">Last Physical Inventory Adjustment(Gross)</td>
													<td>
													    <ezf:inputText name="adjGrsAmt" ezfName="adjGrsAmt" value="999,999,999,999,999.99" otherAttr=" size=\"33\" maxlength=\"22\" style=\"text-align:right;\""/>
													</td>
												</tr>
												<!-- 3 -->
												<tr height="20">
													<td></td>
													<td class="stab">Last Physical Inventory Adjustment(Net)</td>
													<td>
													    <ezf:inputText name="adjNetAmt" ezfName="adjNetAmt" value="999,999,999,999,999.99" otherAttr=" size=\"33\" maxlength=\"22\" style=\"text-align:right;\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

                            <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                            <table style="margin-left: 5px; width: 1108;">
									<col width="180px" align="left"><%-- Detail Title --%>
									<col width="371px" align="left"><%-- ===== space ===== --%>
									<col width="550px" align="right"><%-- Pagenation View --%>
									<tr>
										<td class="stab">Open Receipts</td>
										<td>&nbsp;</td>
										<td>
											<div align="right">
												<ezf:skip>
		                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 5px;">
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
		                                                        <td><input onclick="sendServer(this)" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
		                                                        <td></td>
		                                                        <td><input onclick="sendServer(this)" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
		                                                    </tr>
		                                                </tbody>
		                                            </table>
												</ezf:skip>
		                                        <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 5px;">
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
											</div>
										</td>
									</tr>
                            </table>
                            <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

							<%-- ######################################## DETAIL ######################################## --%>
                            <div>
                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
                                            <div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                    <col width="70"  align="center">
                                                    <col width="110"  align="center">
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                    <col width="130"  align="center"><!-- Item# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                    <col width="70"  align="center">
                                                    <col width="80"  align="center">
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                    <col width="70"  align="center"><!-- RWS# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                    <col width="160"  align="center">
                                                    <col width="220"  align="center">
                                                    <col width="130"  align="center">
                                                    <col width="60"  align="center">
                                                    <tr height="35">
                                                        <td class="pClothBs">Part<br>Request#</td>
                                                        <td class="pClothBs">Part Request Type</td>
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                        <td class="pClothBs">Item#</TD>
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                        <td class="pClothBs">Shipment#</td>
                                                        <td class="pClothBs">Shipment<br>Date</td>
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                        <td class="pClothBs">RWS#</TD>
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                        <td class="pClothBs">Warehouse/Supplier</td>
                                                        <td class="pClothBs">Tracking Number</td>
                                                        <td class="pClothBs">Shipment Status</td>
                                                        <td class="pClothBs">Age<br>(days)</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="vertical-align:top;">
                                            <div id="btmTBL" style="overflow-y:scroll; height:390; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
                                                <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
                                                <col width="70"   align="left">
                                                <col width="110"  align="left">
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                <col width="130"  align="left"><!-- Item# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                <col width="70"  align="left">
                                                <col width="80"  align="left">
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                <col width="70"  align="left"><!-- RWS# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                <col width="160"  align="left">
                                                <col width="220"  align="left">
                                                <col width="130"  align="left">
                                                <col width="60"  align="right">
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="23">
                                                            <td><ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="prchReqTpDescTxt_A1" ezfName="prchReqTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                            <td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td><!-- Item# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                            <td><ezf:label name="rwsRefNum_A1" ezfName="rwsRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="shipDt_A1" ezfName="shipDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- START 2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                            <td><ezf:label name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" /></td><!-- RWS# -->
<!-- END   2019/12/17 T.Ogura [QC#54986,ADD] -->
                                                            <td><ezf:inputText name="xxScrItem61Txt_A1" ezfName="xxScrItem61Txt_A1" value="----+----1----+----2----+----3----+----4----+----5----+----6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
<!-- START 2020/06/24 [QC#56914,MOD] -->
                                                            <td>
                                                                <ezf:anchor name="carrTrkUrl_A1" ezfName="carrTrkUrl_A1" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk{EZF_ROW_NUMBER}\"">
                                                                    <ezf:label name="proNum_A1" ezfName="proNum_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                </ezf:anchor>
                                                            </td>
<!-- END   2020/06/24 [QC#56914,MOD] -->
                                                            <td><ezf:inputText name="shpgStsDescTxt_A1" ezfName="shpgStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"15\""/></td>
                                                            <td><ezf:label name="xxScrItem19Txt_A1" ezfName="xxScrItem19Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        </tr>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
						</td>
					</tr>
				</table>
			</center>



<%-- **** Task specific area ends here **** --%>
