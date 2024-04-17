<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180828154143 --%>
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
			<input type="hidden" name="pageID" value="NLEL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="DS Asset Master Maintenance">

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="1" cellspacing="0" cellpadding="0">
					<tr>
						<td>
                            <div class="pTab_HEAD">
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                            <ezf:skip>
                                <ul>
                                    <li class="pTab_ON"><a href="./NLEL0060Scrn00.html">Asset Maint</a></li>
                                </ul>
                            </ezf:skip>
                            </div>
							<div class="pTab_BODY">
								<%-- ###TAB - HEAD --%>
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" width="100%">
									<tr>
										<col width="120" align="left">
										<col width="150" align="left">
										<col width="5" align="left">
										<col width="120" align="left">
										<col width="150" align="left">
										<col width="60"  align="left">
										<col width="60"  align="left">
										<col width="">
									</tr>
									<tr height="25">
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:200px;\""/>
                                        </td>
										<td class="stab">&nbsp;</td>
										<td class="stab">Search Option Name</td>
                                        <td><ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td align="right">
                                            <ezf:inputButton name="Save_Search" ezfName="Save_Search" value="Save Search" htmlClass="pBtn7" />
                                        </td>
										<td align="right">
                                            <ezf:inputButton name="Delete_Search" ezfName="Delete_Search" value="Delete Search" htmlClass="pBtn7" />
                                        </td>
                                        <td>&nbsp;</td>
									</tr>
								</table>

                                <hr>

								<table style="margin-left:5; margin-top:0;">
									<col align="left">
									<tr>
										<td>
											<table border="0">
                                                <col width="80"  align="left">
                                                <col width="125" align="left">
                                                <col width="60"  align="left">
                                                <col width="60"  align="left">
                                                <col width="20"  align="left">
                                                <col width="120" align="left">
                                                <col width="65"  align="left">
                                                <col width="165" align="left">
                                                <col width="90"  align="left">
                                                <col width="60"  align="left">
                                                <col width="20"  align="left">
                                                <col width="95" align="left">
												<tr height="20">
                                                    <td class="stab">Book Type</td>
													<td>
														<ezf:select name="assetTpCd_H1" ezfName="assetTpCd_H1" ezfBlank="1" ezfCodeName="assetTpCd_L" ezfDispName="assetTpDescTxt_L" otherAttr=" style=\"width:120px;\""/>
													</td>
                                                    <td class="stab">Status</td>
													<td colspan="3">
														<ezf:select name="assetStsCd_H1" ezfName="assetStsCd_H1" ezfBlank="1" ezfCodeName="assetStsCd_L" ezfDispName="assetStsDescTxt_L" otherAttr=" style=\"width:120px;\""/>
													</td>
													<td class="stab">Asset Num</td>
													<td><ezf:inputText name="dsAssetMstrPk_H1" ezfName="dsAssetMstrPk_H1" otherAttr=" size=\"10\" maxlength=\"28\" ezftoupper=\"\""/></td>
													<td class="stab">Tag Num</td>
													<td><ezf:inputText name="assetTagNum_H1" ezfName="assetTagNum_H1" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
												<tr height="20">
                                                    <td class="stab">Date in Service</td>
													<td class="stab" class="vertical-align: middle;">
														<ezf:inputText name="depcStartDt_H1" ezfName="depcStartDt_H1" value="01/20/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="./img/calendar.gif" onclick="calendar('depcStartDt_H1',4);" >
													</td>
													<td class="stab" class="vertical-align: middle;">
														&nbsp;&nbsp;-&nbsp;&nbsp;
													</td>
													<td class="stab" colspan="3" class="vertical-align: middle;">
														<ezf:inputText name="depcStartDt_H2" ezfName="depcStartDt_H2" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="./img/calendar.gif" onclick="calendar('depcStartDt_H2',4);" >
													</td>
													<td class="stab">Config Num</td>
													<td ><ezf:inputText name="svcConfigMstrPk_H1" ezfName="svcConfigMstrPk_H1" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemCode" >Item Number</ezf:anchor></td>
													<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="Search_ItemInfo" ezfName="Search_ItemInfo" value=">>" /></td>
													<td><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" otherAttr=" size=\"15\" maxlength=\"250\""/></td>
                                                </tr>
												<tr height="20">
                                                    <td class="stab">Order Num</td>
													<td><ezf:inputText name="cpoOrdNum_H1" ezfName="cpoOrdNum_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_WHCode" >Warehouse</ezf:anchor></td>
													<td class="stab"><ezf:inputText name="rtnWhCd_H1" ezfName="rtnWhCd_H1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="Search_WHInfo" ezfName="Search_WHInfo" value=">>" /></td>
													<td class="stab"><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
													<td class="stab">Location</td>
													<td class="stab">
														On-Site<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />
														Warehouse<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />
													</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CustCode" >Customer</ezf:anchor></td>
													<td><ezf:inputText name="shipToCustAcctCd_H1" ezfName="shipToCustAcctCd_H1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="Search_CustInfo" ezfName="Search_CustInfo" value=">>" /></td>
													<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"15\" maxlength=\"360\""/></td>
                                                </tr>

												<tr height="20">
                                                    <td class="stab">PO Num</td>
													<td><ezf:inputText name="custIssPoNum_H1" ezfName="custIssPoNum_H1" otherAttr=" size=\"8\" maxlength=\"35\""/></td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Vender" >Vendor</ezf:anchor></td>
													<td><ezf:inputText name="vndCd_H1" ezfName="vndCd_H1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="Search_VndInfo" ezfName="Search_VndInfo" value=">>" /></td>
													<td><ezf:inputText name="vndNm_H1" ezfName="vndNm_H1" otherAttr=" size=\"15\" maxlength=\"20\""/></td>
													<td class="stab">Serial Num</td>
													<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td class="stab">Description (*)</td>
													<td colspan="3"><ezf:inputText name="dsAssetDescTxt_H1" ezfName="dsAssetDescTxt_H1" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                </tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0">
											    <col width="100" align="left">
                                                <col width="60" align="left">
                                                <col width="6"  align="left">
                                                <col width="10" align="left">
                                                <col width="75" align="left">
                                                <col width="90" align="left">
                                                <col width="20" align="left">
                                                <col width="6"  align="left">
                                                <col width="10" align="left">
                                                <col width="20" align="left">
                                                <col width="42" align="left">
                                                <col width="92" align="left">
                                                <col width="38" align="left">
                                                <col width="6"  align="left">
                                                <col width="10" align="left">
                                                <col width="60" align="left">
                                                <col width="90" align="left">
                                                <col width="20" align="left">
                                                <col width="6"  align="left">
                                                <col width="10" align="left">
                                                <col width="20" align="left">
                                                <col width="50">
                                                <col width="">
												<tr height="20">
													<td class="stab">Expense Acct&nbsp;&nbsp;<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseAcctFrom" >From</ezf:anchor></td>
													<td><ezf:inputText name="depcCoaAcctCd_F" ezfName="depcCoaAcctCd_F" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
													<td>-</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseAcctTo" >To</ezf:anchor></td>
													<td><ezf:inputText name="depcCoaAcctCd_T" ezfName="depcCoaAcctCd_T" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
													
													<td class="stab">Expense Br&nbsp;&nbsp;<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseBrFrom" >From</ezf:anchor></td>
													<td><ezf:inputText name="expCoaBrCd_F" ezfName="expCoaBrCd_F" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
													<td>-</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseBrTo" >To</ezf:anchor></td>
													<td><ezf:inputText name="expCoaBrCd_T" ezfName="expCoaBrCd_T" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Expense CC&nbsp;<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseCcFrom" >From</ezf:anchor></td>
													<td><ezf:inputText name="expCoaCcCd_F" ezfName="expCoaCcCd_F" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
													<td>-</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseCcTo" >To</ezf:anchor></td>
													<td><ezf:inputText name="expCoaCcCd_T" ezfName="expCoaCcCd_T" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
													
													<td class="stab">Expense BU&nbsp;<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseExtnFrom" >From</ezf:anchor></td>
													<td><ezf:inputText name="expCoaExtnCd_F" ezfName="expCoaExtnCd_F" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
													<td>-</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ExpenseExtnTo" >To</ezf:anchor></td>
													<td><ezf:inputText name="expCoaExtnCd_T" ezfName="expCoaExtnCd_T" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
													<td></td>
                                                    <td align="center">
                                                        <ezf:inputButton name="Search" ezfName="Search" value="Search" htmlClass="pBtn6" />
                                                    </td>
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
													<li title="Detail" id="Detail" class="pTab_ON"><ezf:anchor name="xxTabProt_DT" ezfName="xxTabProt_DT" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor></li>
													<li title="Assignment" id="Assignment" class="pTab_OFF"><ezf:anchor name="xxTabProt_AS" ezfName="xxTabProt_AS" ezfEmulateBtn="1" ezfGuard="TAB_Assignment" >Assignment</ezf:anchor></li>
													<li title="Financial" id="Financial" class="pTab_OFF"><ezf:anchor name="xxTabProt_FC" ezfName="xxTabProt_FC" ezfEmulateBtn="1" ezfGuard="TAB_Financial" >Financial</ezf:anchor></li>
													<li title="Trx" id="Trx" class="pTab_OFF"><ezf:anchor name="xxTabProt_TX" ezfName="xxTabProt_TX" ezfEmulateBtn="1" ezfGuard="TAB_Trx" >Trx</ezf:anchor></li>
													<li title="Dep Sim" id="DepSim" class="pTab_OFF"><ezf:anchor name="xxTabProt_DS" ezfName="xxTabProt_DS" ezfEmulateBtn="1" ezfGuard="TAB_DepSim" >DepSim</ezf:anchor></li>
													<li title="Asset Hist" id="AssetHist" class="pTab_OFF"><ezf:anchor name="xxTabProt_DS" ezfName="xxTabProt_DS" ezfEmulateBtn="1" ezfGuard="TAB_AssetHist" >AssetHist</ezf:anchor></li>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>

							<div class="pTab_BODY_In">
								<c:choose>
									<%-- ADDED FROM HERE --%>
									<%-- ---------------------------------- TAB -------------------------------- --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Detail'}">
										<div id="TabContent-Detail"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_ON";
                                                document.getElementById( "Assignment" ).className = "pTab_OFF";
                                                document.getElementById( "Financial"  ).className = "pTab_OFF";
                                                document.getElementById( "Trx"        ).className = "pTab_OFF";
                                                document.getElementById( "DepSim"     ).className = "pTab_OFF";
                                                document.getElementById( "AssetHist"  ).className = "pTab_OFF";
                                            </script>
											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col width="300px"  align="left">
												<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
												<col width="540px"  align="right">
												<tr height="21px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<td>
													</td>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="196" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id="parentBoxA">
											<table border="0">
												<tr>
                                        			<td >
														<div style="float:left; display:block"> <!-- left table -->
			                                                <div id='leftTblHead' style="display:block;"></div>
			                                                <div id='leftTbl' style="float:left; display:block; height:213; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:1094; display:block; overflow:hidden; margin:0px;padding:0px;" >
			                                            <table style="table-layout: fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width = "1111" style="margin-right:20px" >
	                                                        <%-- ******************************* --%>
	                                                        <%-- *** Table Area(Header)      *** --%>
	                                                        <%-- ******************************* --%>
	                                                            <col width="25"  align="center">
	                                                            <col width="82"  align="center">
	                                                            <col width="220" align="center">
	                                                            <col width="100"  align="center">
	                                                            <col width="80"  align="center">
	                                                            <col width="45" align="center">
	                                                            <col width="35" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="115" align="center">
	                                                            <col width="125" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="70" align="center">
	                                                            <col width="50" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="90" align="center">
	                                                            <col width="95" align="center">
	                                                            <col width="170" align="center">
	                                                            <col width="85" align="center">
	                                                            <col width="120" align="center">
	                                                            <col width="70" align="center">
	                                                            <col width="45" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="120" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="290" align="center">
	                                                            <tr height="40">
	                                                                    <td id="AH0" class="pClothBs colFix">&nbsp;</td>
	                                                                    <td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAssetMstrPk_A1' )">Asset Num<img id="sortIMG.dsAssetMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH2" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAssetDescTxt_A1' )">Desc<img id="sortIMG.dsAssetDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH3" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'assetTpDescTxt_A1' )">Book Type<img id="sortIMG.assetTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH4" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'assetStsDescTxt_A1' )">Status<img id="sortIMG.assetStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH5" class="pClothBs" valign="top"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'depcMthNum_A1' )">Life in Months<img id="sortIMG.depcMthNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'totAssetQty_A1' )">Units<img id="sortIMG.totAssetQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'depcStartDt_A1' )">Date in Service<img id="sortIMG.depcStartDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config Num<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'serNum_A1' )">Serial Num<img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item Num <img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpCd_A1' )">Item Type<img id="sortIMG.coaMdseTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A1' )">COA Product<img id="sortIMG.coaProdCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'assetTagNum_A1' )">Tag Number<img id="sortIMG.assetTagNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAssetGrpInitBookAmt_A1' )">Asset Value<img id="sortIMG.dsAssetGrpInitBookAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'curValAmt_A1' )">NBV<img id="sortIMG.curValAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'amtChngRsnTpCd_A1' )">Value Change Reason<img id="sortIMG.amtChngRsnTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntDsAssetMstrPk_A1' )">Parent Asset<img id="sortIMG.prntDsAssetMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Customer Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cpoOrdNum_A1' )">Order Num<img id="sortIMG.cpoOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Order Line<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsContrNum_A1' )">Contract Num<img id="sortIMG.dsContrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'contrEffFromDt_A1' )">Contract Start<img id="sortIMG.contrEffFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'contrEffThruDt_A1' )">Contract End<img id="sortIMG.contrEffThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custIssPoNum_A1' )">PO Num<img id="sortIMG.custIssPoNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invNum_A1' )">Invoice Num<img id="sortIMG.invNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'vndCd_A1' )">Vendor Code<img id="sortIMG.vndCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntVndNm_A1' )">Vendor Name<img id="sortIMG.prntVndNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcInvNum_A1' )">Billing Num<img id="sortIMG.svcInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDt_A1' )">Last Bill Date<img id="sortIMG.invDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'lastDepcYrMth_A1' )">Last Deprn Date<img id="sortIMG.lastDepcYrMth_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                    <td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dtlCmntTxt_A1' )">Comment<img id="sortIMG.dtlCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                </tr>
	                                                        </table>
			                                            </div> <!--rightTblHead -->
                                                        <%-- ******************************* --%>
                                                        <%-- *** Table Area(Detail) *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id='rightTbl' style="height:230; width:1111; display:block; overflow:scroll; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
                                                                <col width="25"  align="center">
                                                                <col width="82"  align="center">
                                                                <col width="220" align="center">
                                                                <col width="100" align="center">
                                                                <col width="80"  align="center">
                                                                <col width="50" align="center">
                                                                <col width="35" align="center">
                                                                <col width="80" align="center">
                                                                <col width="115" align="center">
                                                                <col width="125" align="center">
                                                                <col width="80" align="center">
                                                                <col width="70" align="center">
                                                                <col width="45" align="center">
                                                                <col width="80" align="center">
                                                                <col width="90" align="center">
                                                                <col width="95" align="center">
                                                                <col width="170" align="center">
                                                                <col width="85" align="center">
                                                                <col width="120" align="center">
                                                                <col width="70" align="center">
                                                                <col width="45" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="120" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="80" align="center">
                                                                <col width="290" align="center">
                                                                <ezf:row ezfHyo="A">
                                                                    <tr height="25">
                                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="dsAssetMstrPk_A1" ezfName="dsAssetMstrPk_A1" value="0000329624" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
                                                                        <td><ezf:inputText name="dsAssetDescTxt_A1" ezfName="dsAssetDescTxt_A1" value="iMAGERUNNER ADVANCE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="assetTpDescTxt_A1" ezfName="assetTpDescTxt_A1" value="Rental Asset" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="assetStsDescTxt_A1" ezfName="assetStsDescTxt_A1" value="Posted" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"30\""/></td>

                                                                        <td><ezf:inputText name="depcMthNum_A1" ezfName="depcMthNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="totAssetQty_A1" ezfName="totAssetQty_A1" value="5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"5\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="depcStartDt_A1" ezfName="depcStartDt_A1" value="02/19/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                                        <td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="3210070" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"28\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="N1901X0910" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="2722B002AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:inputText name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>&nbsp;<ezf:inputButton name="OpenWin_CoaMdseTpCd" ezfName="OpenWin_CoaMdseTpCd" value="MT" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                        </td>
                                                                        <td><ezf:inputText name="coaProdCd_A1" ezfName="coaProdCd_A1" value="AZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="assetTagNum_A1" ezfName="assetTagNum_A1" value="FM0A0010" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="dsAssetGrpInitBookAmt_A1" ezfName="dsAssetGrpInitBookAmt_A1" value="10000000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="curValAmt_A1" ezfName="curValAmt_A1" value="9166667.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:select name="amtChngRsnTpCd_A1" ezfName="amtChngRsnTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="amtChngRsnTpCd_C" ezfDispName="amtChngRsnTpNm_D" otherAttr=" style=\"width:168px;\""/></td>
                                                                        <td><ezf:inputText name="prntDsAssetMstrPk_A1" ezfName="prntDsAssetMstrPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
                                                                        <td>
                                                                            <ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="10060A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"360\""/>&nbsp;<ezf:inputButton name="OpenWin_DsAcctNm" ezfName="OpenWin_DsAcctNm" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                        </td>
                                                                        <td><ezf:inputText name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" value="WARNER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="dplyLineNum_A1" ezfName="dplyLineNum_A1" value="1.1.1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="dsContrNum_A1" ezfName="dsContrNum_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="contrEffFromDt_A1" ezfName="contrEffFromDt_A1" value="02/19/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                                        <td><ezf:inputText name="contrEffThruDt_A1" ezfName="contrEffThruDt_A1" value="01/19/2018" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                                        <td><ezf:inputText name="custIssPoNum_A1" ezfName="custIssPoNum_A1" value="CU150204" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="invNum_A1" ezfName="invNum_A1" value="02192015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"13\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="vndCd_A1" ezfName="vndCd_A1" value="17071" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>&nbsp;<ezf:inputButton name="OpenWin_VndCode" ezfName="OpenWin_VndCode" value="V" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                        <td><ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="CUSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"240\""/></td>
                                                                        <td><ezf:inputText name="svcInvNum_A1" ezfName="svcInvNum_A1" value="02192015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"13\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="invDt_A1" ezfName="invDt_A1" value="02/19/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="lastDepcYrMth_A1" ezfName="lastDepcYrMth_A1" value="02/19/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="dtlCmntTxt_A1" ezfName="dtlCmntTxt_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                            </table>
                                                        </div> <!-- rightTbl-->
                                                    </td>
                                                </tr>
                                            </table>
                                            </div> <!-- right table -->
                                            </div> <!-- parentBoxA -->
											<table border="0">
												<tr height="2">
													<td></td>
												</tr>
												<tr>
													<td style="text-align:bottom;">
														<table border="0" cellpadding="1" cellspacing="0" width="100%">
															<col width="40">
															<col width="40">
															<col width="40">
															<col width="80">
															<col width="120">
															<col width=110>
															<tr>
																<td><ezf:inputButton name="Adjust" value="Adjust" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="Create" value="Create" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="Import" value="Import" htmlClass="pBtn7" /></td>
																<td align="right">Book Type</td>
																<td><ezf:select name="assetTpCd_T1" ezfName="assetTpCd_T1" ezfBlank="1" ezfCodeName="assetTpCd_L" ezfDispName="assetTpDescTxt_L" otherAttr=" style=\"width:120px;\""/>
																</td>
																<td><ezf:inputButton name="Run_Depreciation" value="Depreciation Simulation" htmlClass="pBtn11" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>

											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxA", "AHEAD", "A", 5, false);
											</script>
										</div><!-- Added -->
									</c:when>
                                    
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Assignment'}">
										<div id="TabContent-Assignment"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_OFF";
                                                document.getElementById( "Assignment" ).className = "pTab_ON";
                                                document.getElementById( "Financial"  ).className = "pTab_OFF";
                                                document.getElementById( "Trx"        ).className = "pTab_OFF";
                                                document.getElementById( "DepSim"     ).className = "pTab_OFF";
                                                document.getElementById( "AssetHist"  ).className = "pTab_OFF";
                                            </script>

											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col width="300px"  align="left">
												<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
												<col width="540px"  align="right">
												<tr height="21px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<td>
													</td>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="196" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="B" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id = "parentBoxB">
											<table border="0">
			                                    <tr>
			                                        <td>
			                                            <div style="float:left; display:block"> <!-- left table -->
			                                            <div id='leftTblHead' style="display:block;"></div>
			                                            <div id='leftTbl' style="float:left; display:block; height:229px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:1094; display:block; overflow:hidden; margin:0px;padding:0px;" >
	                                                        <%-- ******************************* --%>
	                                                        <%-- *** Table Area(Header) *** --%>
	                                                        <%-- ******************************* --%>
	                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width = "1111" id="BHEAD" style="margin-right:20px" >
	                                                            <col width="25"  align="center">
	                                                            <col width="80"  align="center">
	                                                            <col width="100"  align="center">
	                                                            <col width="90" align="center">
	                                                            <col width="90" align="center">
	                                                            <col width="90" align="center">
	                                                            <col width="90" align="center">
	                                                            <col width="100" align="center">
	                                                            <col width="360" align="center">
	                                                            <col width="120" align="center">
	                                                            <col width="290" align="center">
	                                                            <col width="190" align="center">
	                                                            <col width="40" align="center">
	                                                            <col width="80" align="center">
	                                                            <col width="290" align="center">
	                                                            <tr height="30">
	                                                                <td id="BH0" class="pClothBs colFix">&nbsp;</td>
	                                                                <td id="BH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prntDsAssetMstrPk_B1' )">Asset Num<img id="sortIMG.prntDsAssetMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH2" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'assetTpDescTxt_B1' )">Book Type<img id="sortIMG.assetTpDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'coaAcctCd_B' )">Asset Acct<img id="sortIMG.coaAcctCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'coaBrCd_B' )">Expense Br<img id="sortIMG.coaBrCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'coaCcCd_B' )">Expense CC<img id="sortIMG.coaCcCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'coaExtnCd_B' )">Expense BU<img id="sortIMG.coaExtnCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'psnNum_B' )">Sales Rep<img id="sortIMG.psnNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxScrItem50Txt_B2' )">Expense Account<img id="sortIMG.xxScrItem50Txt_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxScrItem10Txt_B1' )">Location<img id="sortIMG.xxScrItem10Txt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxAllLineAddr_B1' )">Address Line<img id="sortIMG.xxAllLineAddr_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ctyAddr_B1' )">City<img id="sortIMG.ctyAddr_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'stCd_B1' )">State<img id="sortIMG.stCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'postCd_B1' )">Zip Code<img id="sortIMG.postCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                                <td id="BH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'asgDtlCmntTxt_B1' )">Comment<img id="sortIMG.asgDtlCmntTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
	                                                            </tr>
	                                                        </table>
                                                        </div> <!-- rightTblHead -->
                                                        <%-- ******************************* --%>
                                                        <%-- *** Table Area(Detail)      *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id='rightTbl' style="display:block; height:246; width:1111; overflow:scroll; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B">
                                                                <col width="25"  align="center">
                                                                <col width="80"  align="center">
                                                                <col width="100"  align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="100" align="center">
                                                                <col width="360" align="center">
                                                                <col width="120" align="center">
                                                                <col width="290" align="center">
                                                                <col width="190" align="center">
                                                                <col width="40" align="center">
                                                                <col width="80" align="center">
                                                                <col width="290" align="center">
                                                                <ezf:row ezfHyo="B">
                                                                    <tr height="25">
                                                                        <td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="prntDsAssetMstrPk_B1" ezfName="prntDsAssetMstrPk_B1" value="0000329624" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
                                                                        <td><ezf:inputText name="assetTpDescTxt_B1" ezfName="assetTpDescTxt_B1" value="FM" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="coaAcctCd_B" ezfName="coaAcctCd_B" value="80021350" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\""/></td>
                                                                        <td><ezf:inputText name="coaBrCd_B" ezfName="coaBrCd_B" value="000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\""/></td>
                                                                        <td><ezf:inputText name="coaCcCd_B" ezfName="coaCcCd_B" value="000000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\""/></td>
                                                                        <td><ezf:inputText name="coaExtnCd_B" ezfName="coaExtnCd_B" value="000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\""/></td>
                                                                        <td><ezf:inputText name="psnNum_B" ezfName="psnNum_B" value="12345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" id=\"psnNum_B#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_SR" value="SR" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SR#{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="xxScrItem50Txt_B2" ezfName="xxScrItem50Txt_B2" value="000.000.00.80021350" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"44\" maxlength=\"44\""/>&nbsp;<ezf:inputButton name="OpenWin_ExpAcctGL" ezfName="OpenWin_ExpAcctGL" value="GL" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" /></td>
                                                                        <td>
                                                                        	<ezf:inputText name="xxScrItem10Txt_B1" ezfName="xxScrItem10Txt_B1" value="LAKE DR." ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>&nbsp;<ezf:inputButton name="OpenWin_RtrnWhCd" ezfName="OpenWin_RtrnWhCd" value="WH" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                        </td>
                                                                        <td><ezf:inputText name="xxAllLineAddr_B1" ezfName="xxAllLineAddr_B1" value="982 LAKE DR." ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"400\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="ctyAddr_B1" ezfName="ctyAddr_B1" value="NEW HYDE PARK" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="stCd_B1" ezfName="stCd_B1" value="NY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"3\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="postCd_B1" ezfName="postCd_B1" value="11040-1193" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="asgDtlCmntTxt_B1" ezfName="asgDtlCmntTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                                   </tr>
                                                                </ezf:row>
                                                            </table>
                                                        </div><!-- rightTbl -->
                                                        </div><!-- right table -->
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                            <table border="0">
												<tr height="2">
													<td></td>
												</tr>
												<tr>
													<td style="text-align:bottom;">
														<table border="0" cellpadding="1" cellspacing="0" width="100%">
															<col width="40">
															<tr>
																<td><ezf:inputButton name="Adjust" value="Adjust" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											</div> <!-- parentBoxB -->
											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxB", "BHEAD", "B", 3, false);
											</script>
										</div><!-- Added -->
									</c:when>
                                    
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Financial'}">
										<div id="TabContent-Financial"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_OFF";
                                                document.getElementById( "Assignment" ).className = "pTab_OFF";
                                                document.getElementById( "Financial"  ).className = "pTab_ON";
                                                document.getElementById( "Trx"        ).className = "pTab_OFF";
                                                document.getElementById( "DepSim"     ).className = "pTab_OFF";
                                                document.getElementById( "AssetHist"  ).className = "pTab_OFF";
                                            </script>

											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col width="300px"  align="left">
												<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
												<col width="540px"  align="right">
												<tr height="21px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<td>
													</td>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="196" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="C" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id = "parentBoxC">
											<table border="0">
			                                    <tr>
			                                        <td>
			                                            <div style="float:left; display:block"> <!-- left table -->
			                                            <div id='leftTblHead' style="display:block;"></div>
			                                            <div id='leftTbl' style="float:left; display:block; height:228px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:1094; display:block; overflow:hidden; margin:0px;padding:0px;" >
                                                        <%-- ******************************* --%>
                                                        <%-- *** Left Table Area(Header) *** --%>
                                                        <%-- ******************************* --%>
                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width = "1111" id="CHEAD" style="margin-right:20px" >
                                                            <col width="25"  align="center">
                                                            <col width="80"  align="center">
                                                            <col width="100" align="center">
                                                            <col width="80"  align="center">
                                                            <col width="90" align="center">
                                                            <col width="90" align="center">
                                                            <col width="90" align="center">
                                                            <col width="90" align="center">
                                                            <col width="80" align="center">
                                                            <col width="90" align="center">
                                                            <col width="85" align="center">
                                                            <col width="290" align="center">
                                                            <tr height="40">
                                                                <td id="CH0" class="pClothBs colFix">&nbsp;</td>
                                                                <td id="CH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAssetMstrPk_C1' )">Asset Num<img id="sortIMG.dsAssetMstrPk_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH2" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'assetTpDescTxt_C1' )">Book Type<img id="sortIMG.assetTpDescTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH3" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'assetStsDescTxt_C1' )">Status<img id="sortIMG.assetStsDescTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAssetGrpInitBookAmt_C1' )">Asset Value<img id="sortIMG.dsAssetGrpInitBookAmt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'curValAmt_C1' )">NBV<img id="sortIMG.curValAmt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRteActlFrtAmt_C1' )">Deprn Reserve<img id="sortIMG.xxRteActlFrtAmt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxTotPrcAmt_C1' )">Deprn YTD<img id="sortIMG.xxTotPrcAmt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'lastDepcYrMth_C1' )">Last Deprn Date<img id="sortIMG.lastDepcYrMth_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'curValAmt_C2' )">Remaining Life<img id="sortIMG.curValAmt_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prntDsAssetMstrPk_C1' )">Parent Asset<img id="sortIMG.prntDsAssetMstrPk_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="CH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'finDtlCmntTxt_C1' )">Comment<img id="sortIMG.finDtlCmntTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                            </tr>
                                                       </table>
                                                       </div> <!-- rightTblHead -->
                                                        <%-- ******************************* --%>
                                                        <%-- *** Table Area(Detail)      *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id='rightTbl' style="display:block; height:245; width:1111; overflow:scroll; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C">
                                                                <col width="25"  align="center">
                                                                <col width="80"  align="center">
                                                                <col width="100"  align="center">
                                                                <col width="80"  align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="80" align="center">
                                                                <col width="90" align="center">
                                                                <col width="85" align="center">
                                                                <col width="290" align="center">
                                                                <ezf:row ezfHyo="C">
                                                                    <tr height="25">
                                                                        <td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="dsAssetMstrPk_C1" ezfName="dsAssetMstrPk_C1" value="0000329624" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
                                                                        <td><ezf:inputText name="assetTpDescTxt_C1" ezfName="assetTpDescTxt_C1" value="FM" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="assetStsDescTxt_C1" ezfName="assetStsDescTxt_C1" value="Posted" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="dsAssetGrpInitBookAmt_C1" ezfName="dsAssetGrpInitBookAmt_C1" value="10000000.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="curValAmt_C1" ezfName="curValAmt_C1" value="9166667.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="xxRteActlFrtAmt_C1" ezfName="xxRteActlFrtAmt_C1" value="833333.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="xxTotPrcAmt_C1" ezfName="xxTotPrcAmt_C1" value="33333.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"29\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="lastDepcYrMth_C1" ezfName="lastDepcYrMth_C1" value="04/30/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                        <td><ezf:inputText name="curValAmt_C2" ezfName="curValAmt_C2" value="19" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="prntDsAssetMstrPk_C1" ezfName="prntDsAssetMstrPk_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="finDtlCmntTxt_C1" ezfName="finDtlCmntTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                            </table>
                                                        </div><!-- rightTbl -->
                                                        </div><!-- right table -->
                                                    </td>
                                                </tr>
                                            </table>
											<table border="0">
												<tr height="2">
													<td></td>
												</tr>
												<tr>
													<td style="text-align:bottom;">
														<table border="0" cellpadding="1" cellspacing="0" width="100%">
															<col width="40">
															<col width="40">
															<col width="410">
															<col width="100">
															<col width="200">
															<col width="40">
															<tr>
																<td><ezf:inputButton name="Adjust" value="Adjust" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="Merge" value="Merge" htmlClass="pBtn7" /></td>
																<td></td>
																<td align="right">Retire Reason</td>
																<td><ezf:inputText name="assetRtireRsnCmntTxt_T1" ezfName="assetRtireRsnCmntTxt_T1" otherAttr=" size=\"30\" maxlength=\"1000\" ezftoupper=\"\""/></td>
																<td><ezf:inputButton name="Retire" value="Retire" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											</div> <!-- parentBoxC -->
											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxC", "CHEAD", "C", 4, false);
											</script>
										</div><!-- Added -->
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Trx'}">
										<div id="TabContent-Trx"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_OFF";
                                                document.getElementById( "Assignment" ).className = "pTab_OFF";
                                                document.getElementById( "Financial"  ).className = "pTab_OFF";
                                                document.getElementById( "Trx"        ).className = "pTab_ON";
                                                document.getElementById( "DepSim"     ).className = "pTab_OFF";
                                                document.getElementById( "AssetHist"  ).className = "pTab_OFF";
                                            </script>

											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col width="300px"  align="left">
												<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
												<col width="540px"  align="right">
												<tr height="21px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<td>
													</td>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="196" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="D" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																	<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																	<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																	<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id = "parentBoxD">
											<table border="0">
                                                <tr>
                                                    <td>
			                                            <div style="float:left; display:block"> <!-- left table -->
			                                            <div id='leftTblHead' style="display:block;"></div>
			                                            <div id='leftTbl' style="float:left; display:block; height:230px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:1094; display:block; overflow:hidden; margin:0px;padding:0px;" >
			                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  width = "1111" id="DHEAD" style="margin-right:20px" >
                                                            <col width="80"  align="center">
                                                            <col width="100" align="center">
                                                            <col width="260" align="center">
                                                            <col width="90"  align="center">
                                                            <col width="90"  align="center">
                                                            <col width="220" align="center">
                                                            <col width="290" align="center">
                                                            <col width="220" align="center">
                                                            <col width="290" align="center">
                                                            <col width="45"  align="center">
                                                            <col width="290" align="center">
                                                            <tr height="40">
                                                                <td id="DH0" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'prntDsAssetMstrPk_D1' )">Asset Num<img id="sortIMG.prntDsAssetMstrPk_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'assetTpDescTxt_D1' )">Book Type<img id="sortIMG.assetTpDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'trxRsnNm_D1' )">Transaction Type<img id="sortIMG.trxRsnNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'assetTrxDt_D1' )">Period<img id="sortIMG.assetTrxDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'depcValAmt_D1' )">Amount<img id="sortIMG.depcValAmt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'coaAcctNm_D1' )">DR Desc<img id="sortIMG.coaAcctNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxScrItem50Txt_D1' )">DR Account<img id="sortIMG.xxScrItem50Txt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'coaAcctNm_D2' )">CR Desc<img id="sortIMG.coaAcctNm_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxScrItem50Txt_D2' )">CR Account<img id="sortIMG.xxScrItem50Txt_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'depcMthNum_D1' )">Life in Months<img id="sortIMG.depcMthNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="DH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'finDtlCmntTxt_D1' )">Comment<img id="sortIMG.finDtlCmntTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                            </tr>
                                                        </table>
                                                        </div> <!-- rightTblHead -->
                                                        <%-- ******************************* --%>
                                                        <%-- *** Table Area(Detail)      *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id='rightTbl' style="display:block; height:247; width:1111; overflow:scroll; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="D">
                                                                <col width="80"  align="center">
                                                                <col width="100"  align="center">
                                                                <col width="260" align="center">
                                                                <col width="90" align="center">
                                                                <col width="90" align="center">
                                                                <col width="220" align="center">
                                                                <col width="290" align="center">
                                                                <col width="220" align="center">
                                                                <col width="290" align="center">
                                                                <col width="45" align="center">
                                                                <col width="290" align="center">
                                                                <ezf:row ezfHyo="D">
                                                                    <tr height="25">
                                                                        <td><ezf:inputText name="prntDsAssetMstrPk_D1" ezfName="prntDsAssetMstrPk_D1" value="0000329624" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
                                                                        <td><ezf:inputText name="assetTpDescTxt_D1" ezfName="assetTpDescTxt_D1" value="FM" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                                                        <td><ezf:inputText name="trxRsnNm_D1" ezfName="trxRsnNm_D1" value="Regular Sales Asset for AJE Link" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="assetTrxDt_D1" ezfName="assetTrxDt_D1" value="Feb-2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                                        <td><ezf:inputText name="depcValAmt_D1" ezfName="depcValAmt_D1" value="10000000.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"19\" style=\"text-align: right; \""/></td>
                                                                        <td><ezf:inputText name="coaAcctNm_D1" ezfName="coaAcctNm_D1" value="Asset Cost" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"240\""/></td>
                                                                        <td><ezf:inputText name="xxScrItem50Txt_D1" ezfName="xxScrItem50Txt_D1" value="800.000.00000.21350.00.000.000.00.000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="coaAcctNm_D2" ezfName="coaAcctNm_D2" value="Asset Cost" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"240\""/></td>
                                                                        <td><ezf:inputText name="xxScrItem50Txt_D2" ezfName="xxScrItem50Txt_D2" value="800.000.00000.21350.00.000.000.00.000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                                                        <td><ezf:inputText name="depcMthNum_D1" ezfName="depcMthNum_D1" value="36" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
                                                                        <td><ezf:inputText name="finDtlCmntTxt_D1" ezfName="finDtlCmntTxt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"1000\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                            </table>
                                                        </div><!-- rightTbl -->
                                                        </div><!-- right table -->
                                                    </td>
                                                </tr>
                                            </table>
                                            <br/><br/>
                                            </div> <!-- parentBoxD -->
                                            <script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxD", "DHEAD", "D", 2, false);
											</script>
										</div><!-- Added -->
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'DepSim'}">
										<div id="TabContent-DepSim"><!-- Added -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_OFF";
                                                document.getElementById( "Assignment" ).className = "pTab_OFF";
                                                document.getElementById( "Financial"  ).className = "pTab_OFF";
                                                document.getElementById( "Trx"        ).className = "pTab_OFF";
                                                document.getElementById( "DepSim"     ).className = "pTab_ON";
                                                document.getElementById( "AssetHist"  ).className = "pTab_OFF";
                                            </script>

											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col align="left" width="238">
												<col align="left" width="5">
												<col align="right" width="520">
												<tr height="21px">
													<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
													<td></td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="265" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="E" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																	<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																	<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																	<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id = "parentBoxE">
											    <table border="0">
                                                    <tr>
                                                        <td>
                                                        <div style="float:left; display:block"> <!-- left table -->
			                                            <div id='leftTblHead' style="display:block;"></div>
			                                            <div id='leftTbl' style="float:left; display:block; height:272px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:765; display:block; overflow:hidden; margin:0px;padding:0px;" >
                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width = "783" id="EHEAD" style="margin-right:20px" >
                                                            <col width="120" align="center">
	                                                        <col width="155" align="center">
	                                                        <col width="90" align="center">
	                                                        <col width="120" align="center">
	                                                        <col width="80" align="center">
	                                                        <col width="120" align="center">
	                                                        <col width="80" align="center">
                                                            <tr height="30">
                                                                <td id="EH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prntDsAssetMstrPk_E1' )">Asset Num<img id="sortIMG.prntDsAssetMstrPk_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'assetTpNm_E1' )">Book Type<img id="sortIMG.assetTpNm_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'assetTrxDt_E1' )">Period<img id="sortIMG.assetTrxDt_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'depcValAmt_E1' )">DR Amount<img id="sortIMG.depcValAmt_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'drCoaAcctCd_E1' )">DR Account<img id="sortIMG.drCoaAcctCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'depcValAmt_E2' )">CR Amount<img id="sortIMG.depcValAmt_E2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="EH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'crCoaAcctCd_E1' )">CR Account<img id="sortIMG.crCoaAcctCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                            </tr>
                                                        </table>
                                                        </div><!-- rightTblHead -->
                                                        <div id='rightTbl' style="display:block; overflow-y:scroll; height:251; width:783; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E">
		                                                        <col width="120" align="center">
		                                                        <col width="155" align="center">
		                                                        <col width="90" align="center">
		                                                        <col width="120" align="center">
		                                                        <col width="80" align="center">
		                                                        <col width="120" align="center">
		                                                        <col width="80" align="center">
		                                                        <ezf:row ezfHyo="E">
	                                                            <tr height="25">
	                                                                    <td><ezf:inputText name="prntDsAssetMstrPk_E1" ezfName="prntDsAssetMstrPk_E1" value="0000329624" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\""/></td>
	                                                                    <td><ezf:inputText name="assetTpNm_E1" ezfName="assetTpNm_E1" value="FM" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
	                                                                    <td><ezf:inputText name="assetTrxDt_E1" ezfName="assetTrxDt_E1" value="Feb-2015" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
		                                                                <td><ezf:inputText name="depcValAmt_E1" ezfName="depcValAmt_E1" value="277778.00" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"19\" style=\"text-align: right; \""/></td>
		                                                                <td><ezf:inputText name="drCoaAcctCd_E1" ezfName="drCoaAcctCd_E1" value="800000" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
	                                                                    <td><ezf:inputText name="depcValAmt_E2" ezfName="depcValAmt_E2" value="277778.00" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"19\" style=\"text-align: right; \""/></td>
		                                                                <td><ezf:inputText name="crCoaAcctCd_E1" ezfName="crCoaAcctCd_E1" value="800000" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
	                                                                </tr>
		                                                        </ezf:row>
	                                                        </table>
                                                        </div><!-- rightTbl -->
                                                        </div><!-- right table -->
                                                    </td>
                                                </tr>
                                            </table>
                                            </div> <!-- parentBoxE-->
                                            <script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxE", "EHEAD", "E", 0, true);
											</script>
                                        </div><!-- Added -->
									</c:when>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'AssetHist'}">
										<div id="TabContent-AssetHist"><!-- Added2 -->
                                            <script type="text/javascript">
                                                document.getElementById( "Detail"     ).className = "pTab_OFF";
                                                document.getElementById( "Assignment" ).className = "pTab_OFF";
                                                document.getElementById( "Financial"  ).className = "pTab_OFF";
                                                document.getElementById( "Trx"        ).className = "pTab_OFF";
                                                document.getElementById( "DepSim"     ).className = "pTab_OFF";
                                                document.getElementById( "AssetHist"  ).className = "pTab_ON";
                                            </script>
											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
												<col width="300px"  align="left">
												<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
												<col width="540px"  align="right">
												<tr height="21px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<td>
													</td>
													<%-- =============== Paging Parts =============== --%>
													<td>
														<div align="right">
															<ezf:skip>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="191" align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="16"  align="center">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="5">
																	<col width="40"  align="right">
																	<col width="40"  align="right">
																	<col width="5">
																	<tr>
																		<td class="stab">Results 1 - 40 of 200</td>
																		<td></td>
																		<td class="stab">Showing</td>
																		<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
																		<td class="stab">/</td>
																		<td class="pOut">20</td>
																		<td class="stab">Page</td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																		<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																		<td></td>
																	  </tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="F" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</div>
													</td>
												</tr>
											</table>
											<div id = "parentBoxF">
											<table border="0">
                                                <tr>
                                                    <td>
                                                        <div style="float:left; display:block"> <!-- left table -->
			                                            <div id='leftTblHead' style="display:block;"></div>
			                                            <div id='leftTbl' style="float:left; display:block; height:250px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
			                                            </div>  <!-- left table -->
			                                            <div style="float:left"> <!-- right table -->
			                                            <div id='rightTblHead' style="width:1090; display:block; overflow:hidden; margin:0px;padding:0px;" >
                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width = "1107" id="FHEAD" style="margin-right:20px" >
                                                            <col width="90" align="center">
                                                            <col width="110" align="center">
	                                                        <col width="145" align="center">
	                                                        <col width="240" align="center">
	                                                        <col width="210" align="center">
	                                                        <col width="210" align="center">
	                                                        <col width="85" align="center">
                                                            <tr height="30">
                                                                <td id="FH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'dsAssetMstrPk_F1' )">Asset Num<img id="sortIMG.dsAssetMstrPk_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'procModeNm_F1' )">Event<img id="sortIMG.procModeNm_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxDtTm_F1' )">Transaction Date<img id="sortIMG.xxDtTm_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'updFldTxt_F1' )">Field Update<img id="sortIMG.updFldTxt_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'oldValTxt_F1' )">Old Value<img id="sortIMG.oldValTxt_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'newValTxt_F1' )">New Value<img id="sortIMG.newValTxt_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                <td id="FH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'updUsrId_F1' )">Update By<img id="sortIMG.updUsrId_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                            </tr>
                                                        </table>
                                                        </div> <!-- rightTblHead -->
                                                        <div id='rightTbl' style="display:block; overflow-y:scroll; height:250; width:1107; margin:0px; padding:0px;">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="F" width = "1107">
		                                                        <col width="90" align="center">
		                                                        <col width="110" align="center">
		                                                        <col width="145" align="center">
		                                                        <col width="240" align="center">
		                                                        <col width="210" align="center">
		                                                        <col width="210" align="center">
		                                                        <col width="85" align="center">
		                                                        <ezf:row ezfHyo="F">
	                                                            <tr height="25">
	                                                                    <td><ezf:inputText name="dsAssetMstrPk_F1" ezfName="dsAssetMstrPk_F1" value="1406" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\""/></td>
	                                                                    <td><ezf:inputText name="procModeNm_F1" ezfName="procModeNm_F1" value="---------1---------2-----" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"25\""/></td>
	                                                                    <td><ezf:inputText name="xxDtTm_F1" ezfName="xxDtTm_F1" value="10/04/2016 13:28:48" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\""/></td>
	                                                                    <td><ezf:inputText name="updFldTxt_F1" ezfName="updFldTxt_F1" value="---------1---------2---------3---------4---------5" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"50\""/></td>
		                                                                <td><ezf:inputText name="oldValTxt_F1" ezfName="oldValTxt_F1" value="---------1---------2---------3---------4---------5" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"300\" style=\"text-align: left; \""/></td>
		                                                                <td><ezf:inputText name="newValTxt_F1" ezfName="newValTxt_F1" value="---------1---------2---------3---------4---------5" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"300\""/></td>
	                                                                    <td><ezf:inputText name="updUsrId_F1" ezfName="updUsrId_F1" value="---------1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\" style=\"text-align: left; \""/></td>
	                                                                </tr>
		                                                        </ezf:row>
	                                                        </table>
                                                        </div><!-- rightTbl -->
                                                        </div><!-- right table -->
                                                    </td>
                                                </tr>
                                            </table>
                                            <br/><br/>
                                            </div> <!-- parentBoxF-->
                                            <script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxF", "FHEAD", "F", false);
											</script>
                                       </div><!-- Added2 -->
									</c:when>
								</c:choose>
                            </div>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
