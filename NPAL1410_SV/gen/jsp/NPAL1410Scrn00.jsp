<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180717110947 --%>
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
			<input type="hidden" name="pageID" value="NPAL1410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reman Workbench">

      <%-- ********************** --%>
      <%-- *** Upper Tab Area *** --%>
      <%-- ********************** --%>
      <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
		<ezf:skip>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
			<tr>
				<td width="1070px" height="28px" valign="bottom">
					<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
						<tr title="PR Entry Screen">
							<td width="107px" align="center" class="same">Reman WrkBnch</td>
						</tr>
					</table>
				</td>
				<td width="10px" valign="bottom" align="center">
					<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
				</td>
				<td width="10px" valign="bottom" align="center">
					<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
				</td>
			</tr>
		</table>
		</ezf:skip>

			<%-- ######################################## HEADER ######################################## --%>
			<center>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="pTab_BODY">

								<%-- ###TAB - HEAD --%>
								<table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
									<tr>
										<col width="360"  align="left">
										<col width="200"  align="left">
										<col width="180"  align="left">
										<col width=""  align="left">
										<col width=""  align="left">
									</tr>
									<tr>
										<td valign="top">
											<table border="0">
												<tr>
													<col width="110" align="left">
													<col width="250"  align="left">
												</tr>
												<!-- 1 -->
												<tr height="25">
													<td class="stab">Reman Order#</td>
													<td><ezf:inputText name="rmnfOrdNum_H1" ezfName="rmnfOrdNum_H1" value="1000" otherAttr=" size=\"23\" maxlength=\"8\" ezftoupper=\"\""/>
													<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
												</tr>
												<!-- 2 -->
												<tr height="25">
													<td class="stab"><ezf:anchor name="" ezfName="rmnfRtlWhCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" >Reman Warehouse</ezf:anchor></td>
													<td>
														<ezf:inputText name="rmnfRtlWhCd_H1" ezfName="rmnfRtlWhCd_H1" value="I01" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
														<ezf:inputButton name="GetWhName" value=">>" />
														<ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="I01 - ITASCA" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab"><ezf:anchor name="" ezfName="ownrTechTocCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" >Reman Tech Owner</ezf:anchor></td>
													<td>
														<ezf:inputText name="ownrTechTocCd_H1" ezfName="ownrTechTocCd_H1" value="055649" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
														<ezf:inputButton name="GetTechName" value=">>" />
														<ezf:inputText name="techNm_H1" ezfName="techNm_H1" value="Rick Stutzman" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab"><ezf:anchor name="" ezfName="rmnfMainUnitSerNum_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" >Main Unit Serial</ezf:anchor></td>
													<td><ezf:inputText name="rmnfMainUnitSerNum_H1" ezfName="rmnfMainUnitSerNum_H1" value="SK1000000" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/>
														<ezf:inputButton name="getConfig" value="Get Config" htmlClass="pBtn5" />
													</td>
												</tr>
												<tr height="25">
													<td class="stab">Reman Status</td>
													<td><ezf:inputText name="rmnfOrdStsNm_H1" ezfName="rmnfOrdStsNm_H1" value="In Process" otherAttr=" size=\"33\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="25">
													<td class="stab">Order Type</td>
													<td>
														<ezf:select name="rmnfOrdTpCd_SE" ezfName="rmnfOrdTpCd_SE" ezfCodeName="rmnfOrdTpCd_IX" ezfDispName="rmnfOrdTpDescTxt_TX" otherAttr=" style=\"width:235\""/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab"><ezf:anchor name="" ezfName="whLoctrCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Locator" >Locator</ezf:anchor></td>
													<td><ezf:inputText name="whLoctrCd_H1" ezfName="whLoctrCd_H1" value="U40" otherAttr=" size=\"33\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<table border="0">
												<tr>
													<col width="80" align="left">
													<col width="120"  align="left">
													<col width="80" align="left">
												</tr>
												<tr height="25">
													<td class="stab">Sub WH</td>
													<td><ezf:inputText name="rmnfRtlSwhCd_H1" ezfName="rmnfRtlSwhCd_H1" value="NEW" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="25">
													<td class="stab">Start Date</td>
													<td class="stab" colspan="2">
														<ezf:inputText name="rmnfStartDt_H1" ezfName="rmnfStartDt_H1" value="20/01/2016" otherAttr=" maxlength=\"11\" size=\"12\""/>
														<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rmnfStartDt_H1', 4);"/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab">End Date</td>
													<td class="stab" colspan="2">
														<ezf:inputText name="rmnfEndDt_H1" ezfName="rmnfEndDt_H1" otherAttr=" maxlength=\"11\" size=\"12\""/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab">Model</td>
													<td colspan="2">
														<ezf:inputText name="t_MdlNm_H1" ezfName="t_MdlNm_H1" value="IPC7000" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="25">
													<td class="stab">Original Model</td>
													<td colspan="2">
														<ezf:inputText name="t_MdlNm_H2" ezfName="t_MdlNm_H2" value="IPC7000" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<table border="0">
												<tr>
													<col width="80" align="left">
													<col width="120" align="left">
												</tr>
												<tr>
													<td class="stab" valign="top">Notes</td>
													<td>
														<ezf:textArea name="rmnfOrdCmntTxt_H1" ezfName="rmnfOrdCmntTxt_H1" otherAttr=" cols=\"20\" rows=\"9\""/>
														<ezf:skip>
														<textarea name="descreption" rows="10" cols="15">aaaaa&#13;bbbbb</textarea>
														</ezf:skip>
													</td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<table border="0">
												<tr>
													<col width="80" align="left">
													<col width="100" align="left">
													<col width="70" align="left">
													<col width="100" align="left">
												</tr>
												<!-- 1 -->
												<tr height="25">
													<td class="stab">Parts</td>
													<td><ezf:inputText name="rmnfPrtUsgCostAmt_H1" ezfName="rmnfPrtUsgCostAmt_H1" value="1,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
													<td class="stab">New Value</td>
													<td><ezf:inputText name="rmnfTotCostAmt_H2" ezfName="rmnfTotCostAmt_H2" value="45,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<!-- 2 -->
												<tr height="25">
													<td class="stab">Labor</td>
													<td colspan="3"><ezf:inputText name="rmnfTotLborCostAmt_H1" ezfName="rmnfTotLborCostAmt_H1" value="2,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<!-- 2 -->
												<tr height="25">
													<td class="stab">Merchandise</td>
													<td colspan="3"><ezf:inputText name="rmnfMachCostAmt_H1" ezfName="rmnfMachCostAmt_H1" value="32,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<tr height="25">
													<td class="stab">RR Credits</td>
													<td colspan="3"><ezf:inputText name="assetRecovCostAmt_H1" ezfName="assetRecovCostAmt_H1" value="40,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<!-- 2 -->
												<tr height="25">
													<td class="stab">Total Cost</td>
													<td colspan="3"><ezf:inputText name="rmnfTotCostAmt_H1" ezfName="rmnfTotCostAmt_H1" value="35,000.00" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<tr height="50">
													<td colspan="4" align="right" valign="bottom">
		                                                <ezf:inputButton name="Create" value="Create" htmlClass="pBtn6" />
		                                                <!--<input onclick="sendServer(this)" type="button" class="pBtn6" value="Complete" name="Completion">-->
		                                                <ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn6" />
		                                                <ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />
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
									<table width="998" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Configration" id="Config" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_11" ezfEmulateBtn="1" ezfGuard="TAB_Config" >Configration</ezf:anchor></li>
													<li title="Parts" id="Parts" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_21" ezfEmulateBtn="1" ezfGuard="TAB_Parts" >Parts</ezf:anchor></li>
													<li title="Task" id="Task" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_31" ezfEmulateBtn="1" ezfGuard="TAB_Task" >Task</ezf:anchor></li>
													<li title="SoRws" id="SoRws" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_41" ezfEmulateBtn="1" ezfGuard="TAB_SoRws" >SO/RWS</ezf:anchor></li>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>

							<div class="pTab_BODY_In">

								<div style="height: 320px" >
								<c:choose>

									<%-- ADDED FROM HERE --%>

									<%-- ---------------------------------- TAB -------------------------------- --%>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Config'}">

									<div id="TabContent-Config"><!-- Added -->

										<script type="text/javascript">
											document.getElementById( "Config"    ).className = "pTab_ON";
											document.getElementById( "Parts"   ).className = "pTab_OFF";
											document.getElementById( "Task"      ).className = "pTab_OFF";
											document.getElementById( "SoRws"      ).className = "pTab_OFF";
										</script>

										<table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
											<tr>
												<col width="80" align="left">
												<col width="180" align="left">
												<col width="80" align="left">
												<col width="180" align="left">
												<col width="130" align="left">
												<col width="180" align="left">
												<col width="300" align="right">
											</tr>
											<!-- 1 -->
											<tr height="20">
												<td class="stab">Configration ID</td>
												<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="1000" otherAttr=" size=\"22\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align: left;\""/></td>
												<td class="stab">RWS Number</td>
												<td><ezf:inputText name="rwsNum_A1" ezfName="rwsNum_A1" value="IPC7000" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
												<td class="stab">Shipping Order Number</td>
												<td><ezf:inputText name="soNum_A1" ezfName="soNum_A1" value="IPC7000" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
												<td>
							                          <TABLE cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
							                              <TBODY>
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
							                              </TBODY>
							                          </TABLE>
												</td>
											</tr>
										</table>

                                        <div id="bottomTBL" style="overflow-x:hidden; overflow-y:hidden; width:1105;">
                                            <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed"  style="MARGIN-LEFT: 5px; MARGIN-TOP: 10px">
                                                <col width="30">
                                                <col width="183">
                                                <col width="200">
                                                <col width="120">
                                                <col width="40">
                                                <col width="150">
                                                <col width="130">
                                                <col width="183">
                                                <col width="180">
                                                <col width="130">
                                                <tr height="30">
                                                    <td align="center" class="pClothBs">Del</td>
                                                    <td align="center" class="pClothBs">Item Number</td>
                                                    <td align="center" class="pClothBs">Description</td>
                                                    <td align="center" class="pClothBs">Source SWH</td>
                                                    <td align="center" class="pClothBs">Main<br>Unit</td>
                                                    <td align="center" class="pClothBs">Serial Number<br>/ Licence Key</td>
                                                    <td align="center" class="pClothBs">Stock Status</td>
                                                    <td align="center" class="pClothBs">Convert to Material</td>
                                                    <td align="center" class="pClothBs">Description</td>
                                                    <td align="center" class="pClothBs">Convert to<br>Serial / Licence Key</td>
                                                </tr>
                                            </table>
                                        </div>
	                                    <div id="RightTBL" style="overflow-y:scroll; height:210; overflow-x:scroll; width:1117; MARGIN-LEFT: 5px;" 
	                                        onScroll="synchroScrollTop( this.id, new Array( 'bottomTBL' ) );synchroScrollLeft( this.id, new Array( 'bottomTBL' ) );">
	                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="1057">
                                                <col width="30" align="center">
                                                <col width="183" align="center">
                                                <col width="200" align="center">
                                                <col width="120">
                                                <col width="40" align="center">
                                                <col width="150">
                                                <col width="130">
                                                <col width="183" align="center">
                                                <col width="180">
                                                <col width="130">
                                                <ezf:row ezfHyo="A">
                                                <tr id="id_row{EZF_ROW_NUMBER}"  height="25">
                                                    <td><ezf:inputCheckBox name="rmvConfigFlg_A1" ezfName="rmvConfigFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td class="stab">
                                                        <ezf:inputButton name="OpenWin_Item_CO" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
	                                                    <ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="1000B001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/>
                                                        <ezf:inputButton name="GetItemName_CO" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
	                                                </td>
                                                    <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IPC7000 MAIN ENGINE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"250\""/></td>
                                                    <td>
                                                    	<ezf:inputButton name="OpenWin_SWH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                        <ezf:inputText name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                    </td>
                                                    <td><ezf:label name="mainUnitLineFlg_A1" ezfName="mainUnitLineFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td class="stab">
                                                        <ezf:inputButton name="OpenWin_Serial_CO" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                        <ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="SK1000000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
                                                    </td>
                                                    <td>
														<ezf:select name="stkStsCd_A1" ezfName="stkStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_PD" ezfDispName="stkStsDescTxt_PD" otherAttr=" style=\"width:125px\""/>
													</td>
                                                    <td class="stab">
                                                        <ezf:inputButton name="OpenWin_Item_CC" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                    	<ezf:inputText name="rmnfToCmptMdseCd_A1" ezfName="rmnfToCmptMdseCd_A1" value="1000B001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/>
                                                        <ezf:inputButton name="GetItemName_CC" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
													</td>
                                                    <td><ezf:inputText name="mdseDescShortTxt_A2" ezfName="mdseDescShortTxt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"250\""/></td>
                                                    <td><ezf:inputText name="rmnfToCmptSerNum_A1" ezfName="rmnfToCmptSerNum_A1" value="SK10000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                </tr>
	                                            </ezf:row>
                                            </table>
                                        </div>
	                                        <table style="width:1100; margin-left: 5px;" >
												<tr height="20">
													<td align="right">
		                                                <ezf:inputButton name="AddComponent" value="Add (F11)" htmlClass="pBtn9" />
														<ezf:inputButton name="DeleteComponent" value="Delete" htmlClass="pBtn9" />
													</td>
												</tr>
	                                        </table>
                                    </div>
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Parts'}">

										<div id="TabContent-Parts"><!-- Added -->

											<script type="text/javascript">
												document.getElementById( "Config"    ).className = "pTab_OFF";
												document.getElementById( "Parts"   ).className = "pTab_ON";
												document.getElementById( "Task"      ).className = "pTab_OFF";
												document.getElementById( "SoRws"      ).className = "pTab_OFF";
											</script>
	
											<table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
												<tr height="10">
													<td class="stab">Standard Parts</td>
												</tr>
											</table>

											<div id="topPTBL" style="overflow-y:none; overflow-x:hidden; margin-left: 5px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="20" width="">
	                                                <col width="25">
	                                                <col width="170">
	                                                <col width="220">
	                                                <col width="60">
	                                                <col width="80">
	                                                <col width="65">
	                                                <col width="55">
	                                                <col width="80">
	                                                <col width="100">
	                                                <col width="110">
	                                                <col width="60">
	                                                <col width="75">
	                                                <tr height="30">
	                                                    <td align="center" class="pClothBs">&nbsp;</td>
	                                                    <td align="center" class="pClothBs">Item Number</td>
	                                                    <td align="center" class="pClothBs">Description</td>
	                                                    <td align="center" class="pClothBs">Qty</td>
	                                                    <td align="center" class="pClothBs">Cost</td>
	                                                    <td align="center" class="pClothBs">Avail Qty</td>
	                                                    <td align="center" class="pClothBs">RR Parts</td>
	                                                    <td align="center" class="pClothBs">RR Credit</td>
	                                                    <td align="center" class="pClothBs">Source Type</td>
	                                                    <td align="center" class="pClothBs">Source</td>
	                                                    <td align="center" class="pClothBs">Received</td>
	                                                    <td align="center" class="pClothBs">Consumed</td>
	                                                </tr>
	                                            </table>
	                                        </div>
		                                    <div id="PTBL" style="overflow-y:scroll; height:80; overflow-x:hidden; width:1117; margin-left: 5px;" onScroll="synchroScrollTop( this.id, new Array( 'topPTBL' ) );">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="" id="B_Right">
	                                                <col width="25" align="center">
	                                                <col width="170" align="center">
	                                                <col width="220" align="center">
	                                                <col width="60" align="center">
	                                                <col width="80" align="center">
	                                                <col width="65" align="center">
	                                                <col width="55" align="center">
	                                                <col width="80" align="center">
	                                                <col width="100" align="center">
	                                                <col width="110" align="center">
	                                                <col width="60" align="center">
	                                                <col width="75" align="center">
	                                                <ezf:row ezfHyo="B">
	                                                <tr id="id_row{EZF_ROW_NUMBER}"  height="24">
	                                                    <td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
	                                                    <td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="FM1-234-456" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"16\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="ROLLER" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="prtRqstQty_B1" ezfName="prtRqstQty_B1" value="4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="thisMthTotStdCostAmt_B1" ezfName="thisMthTotStdCostAmt_B1" value="10.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="invtyAvalQty_B1" ezfName="invtyAvalQty_B1" value="10" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td><ezf:label name="rtrnReqPrtFlg_B1" ezfName="rtrnReqPrtFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
	                                                    <td class="stab"><ezf:inputText name="assetRecovCostAmt_B1" ezfName="assetRecovCostAmt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="procrTpNm_B1" ezfName="procrTpNm_B1" value="Warehouse" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="dplyVndNm_B1" ezfName="dplyVndNm_B1" value="I01-ITASCA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:label name="xxFlgNm_B1" ezfName="xxFlgNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfPrtQty_B1" ezfName="rmnfPrtQty_B1" value="4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                </tr>
		                                            </ezf:row>
	                                            </table>
											</div><!-- Added -->

											<table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 10px" border="0">
												<tr height="10">
													<td class="stab">Non Standard Parts</td>
												</tr>
											</table>

											<div id="topPTBL" style="overflow-y:none; overflow-x:hidden; margin-left: 5px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="20" width="">
	                                                <col width="25">
	                                                <col width="170">
	                                                <col width="220">
	                                                <col width="60">
	                                                <col width="80">
	                                                <col width="65">
	                                                <col width="55">
	                                                <col width="80">
	                                                <col width="100">
	                                                <col width="110">
	                                                <col width="60">
	                                                <col width="75">
	                                                <tr height="30">
	                                                    <td align="center" class="pClothBs">&nbsp;</td>
	                                                    <td align="center" class="pClothBs">Item Number</td>
	                                                    <td align="center" class="pClothBs">Description</td>
	                                                    <td align="center" class="pClothBs">Qty</td>
	                                                    <td align="center" class="pClothBs">Cost</td>
	                                                    <td align="center" class="pClothBs">Avail Qty</td>
	                                                    <td align="center" class="pClothBs">RR Parts</td>
	                                                    <td align="center" class="pClothBs">RR Credit</td>
	                                                    <td align="center" class="pClothBs">Source Type</td>
	                                                    <td align="center" class="pClothBs">Source</td>
	                                                    <td align="center" class="pClothBs">Received</td>
	                                                    <td align="center" class="pClothBs">Consumed</td>
	                                                </tr>
	                                            </table>
	                                        </div>
		                                    <div id="PTBL" style="overflow-y:scroll; height:80; overflow-x:hidden; width:1117; margin-left: 5px;" onScroll="synchroScrollTop( this.id, new Array( 'topPTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="" id="C_Right">
	                                                <col width="25" align="center">
	                                                <col width="170" align="center">
	                                                <col width="220" align="center">
	                                                <col width="60" align="center">
	                                                <col width="80" align="center">
	                                                <col width="65" align="center">
	                                                <col width="55" align="center">
	                                                <col width="80" align="center">
	                                                <col width="100" align="center">
	                                                <col width="110" align="center">
	                                                <col width="60" align="center">
	                                                <col width="75" align="center">
	                                                <ezf:row ezfHyo="C">
	                                                <tr id="id_row{EZF_ROW_NUMBER}"  height="24">
	                                                    <td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
	                                                    <td class="stab">
	                                                        <ezf:inputButton name="OpenWin_Item_P" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
															<ezf:inputText name="mdseCd_C1" ezfName="mdseCd_C1" value="FM1-234-999" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
	                                                        <ezf:inputButton name="GetItemName_P" value=">>" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
	                                                    </td>
	                                                    <td><ezf:inputText name="mdseDescShortTxt_C1" ezfName="mdseDescShortTxt_C1" value="FIXING UNIT" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="prtRqstQty_C1" ezfName="prtRqstQty_C1" value="4" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="thisMthTotStdCostAmt_C1" ezfName="thisMthTotStdCostAmt_C1" value="10.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="invtyAvalQty_C1" ezfName="invtyAvalQty_C1" value="10" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"4\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td><ezf:label name="rtrnReqPrtFlg_C1" ezfName="rtrnReqPrtFlg_C1" ezfHyo="C" ezfArrayNo="0" /></td>
	                                                    <td class="stab"><ezf:inputText name="assetRecovCostAmt_C1" ezfName="assetRecovCostAmt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="procrTpNm_C1" ezfName="procrTpNm_C1" value="Warehouse" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="dplyVndNm_C1" ezfName="dplyVndNm_C1" value="I01-ITASCA" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"320\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:label name="xxFlgNm_C1" ezfName="xxFlgNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfPrtQty_C1" ezfName="rmnfPrtQty_C1" value="4" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                </tr>
		                                            </ezf:row>
	                                            </table>
											</div><!-- Added -->


	                                        <table style="width:1115; margin-left: 5px; MARGIN-TOP: 10px" >
												<tr height="20">
													<td align="right">
		                                                <ezf:inputButton name="AddParts" value="Add(F11)" htmlClass="pBtn8" />
		                                                <ezf:inputButton name="DeleteParts" value="Delete" htmlClass="pBtn8" />
													</td>
												</tr>
	                                        </table>

									    </div><!-- Added -->
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Task'}">

										<div id="TabContent-Task"><!-- Added -->

											<script type="text/javascript">
												document.getElementById( "Config"    ).className = "pTab_OFF";
												document.getElementById( "Parts"   ).className = "pTab_OFF";
												document.getElementById( "Task"      ).className = "pTab_ON";
												document.getElementById( "SoRws"      ).className = "pTab_OFF";
											</script>

											<table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
												<tr height="10">
													<td class="stab">Task Summary</td>
												</tr>
											</table>

											<div id="topPTBL" style="overflow-y:none; overflow-x:hidden; margin-left: 5px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="20" width="">
	                                                <col width="30">
	                                                <col width="110">
	                                                <col width="170">
	                                                <col width="80">
	                                                <col width="80">
	                                                <col width="80">
	                                                <col width="150">
	                                                <col width="100">
	                                                <col width="100">
	                                                <col width="100">
	                                                <col width="100">
	                                                <tr height="30">
	                                                    <td align="center" class="pClothBs">&nbsp;</td>
	                                                    <td align="center" class="pClothBs">Task#</td>
	                                                    <td align="center" class="pClothBs">Task Description</td>
	                                                    <td align="center" class="pClothBs">Start Date</td>
	                                                    <td align="center" class="pClothBs">End Date</td>
	                                                    <td align="center" class="pClothBs">Tech#</td>
	                                                    <td align="center" class="pClothBs">Tech Name</td>
	                                                    <td align="center" class="pClothBs">Labor Hours</td>
	                                                    <td align="center" class="pClothBs">Labor Total Cost</td>
	                                                    <td align="center" class="pClothBs">Parts Total Cost</td>
	                                                    <td align="center" class="pClothBs">Total Cost</td>
	                                                </tr>
	                                            </table>
                                            </div>
		                                    <div id="PTBL" style="overflow-y:scroll; height:225; overflow-x:hidden; width:1121; margin-left: 5px;" onScroll="synchroScrollTop( this.id, new Array( 'topPTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="" id="D_Right">
	                                                <col width="30" align="center">
	                                                <col width="110" align="left">
	                                                <col width="170" align="center">
	                                                <col width="80" align="center">
	                                                <col width="80" align="center">
	                                                <col width="80" align="center">
	                                                <col width="150" align="center">
	                                                <col width="100" align="center">
	                                                <col width="100" align="center">
	                                                <col width="100" align="center">
	                                                <col width="100" align="center">
	                                                <ezf:row ezfHyo="D">
	                                                <tr id="id_row{EZF_ROW_NUMBER}">
	                                                    <td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
	                                                    <td><ezf:anchor name="" ezfName="xxPopPrm_D1" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_TaskEntryMod" ><ezf:label name="xxPopPrm_D1" ezfName="xxPopPrm_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer;\""/></ezf:anchor></td>
	                                                    <td><ezf:inputText name="rmnfTaskDescTxt_D1" ezfName="rmnfTaskDescTxt_D1" value="Reinstall Drum" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"20\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:label name="xxPopPrm_DF" ezfName="xxPopPrm_DF" ezfHyo="D" ezfArrayNo="0" /></td>
	                                                    <td><ezf:label name="xxPopPrm_DT" ezfName="xxPopPrm_DT" ezfHyo="D" ezfArrayNo="0" /></td>
	                                                    <td class="stab"><ezf:inputText name="techTocCd_D1" ezfName="techTocCd_D1" value="3456" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="techNm_D1" ezfName="techNm_D1" value="Ron Durkee" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"45\" ezftoupper=\"\""/></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfLborAot_D1" ezfName="rmnfLborAot_D1" value="2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"4\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfLborCostAmt_D1" ezfName="rmnfLborCostAmt_D1" value="500.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfPrtUsgCostAmt_D1" ezfName="rmnfPrtUsgCostAmt_D1" value="200.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                    <td class="stab"><ezf:inputText name="rmnfTotCostAmt_D1" ezfName="rmnfTotCostAmt_D1" value="700.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
	                                                </tr>
		                                            </ezf:row>
	                                            </table>
											</div>


	                                        <table style="width:1108; margin-left: 5px; margin-top: 10px;" >
												<tr height="30">
													<td align="right">
		                                                <ezf:inputButton name="OpenWin_TaskEntryNew" value="Add(F11)" htmlClass="pBtn8" />
		                                                <ezf:inputButton name="DeleteTask" value="Delete" htmlClass="pBtn8" />
													</td>
												</tr>
	                                        </table>

										</div>
									</c:when>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SoRws'}">

										<div id="TabContent-SoRws"><!-- Added -->

											<script type="text/javascript">
												document.getElementById( "Config"    ).className = "pTab_OFF";
												document.getElementById( "Parts"   ).className = "pTab_OFF";
												document.getElementById( "Task"      ).className = "pTab_OFF";
												document.getElementById( "SoRws"      ).className = "pTab_ON";
											</script>

											<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
												<tr>
													<col width="80" align="left">
													<col width="235" align="left">
													<col width="80" align="left">
												</tr>
												<tr height="20">
													<td class="stab">Source Type</td>
													<td>
														<ezf:select name="sceOrdTpCd_ES" ezfName="sceOrdTpCd_ES" ezfBlank="1" ezfCodeName="sceOrdTpCd_EC" ezfDispName="sceOrdTpNm_EN" otherAttr=" style=\"width:230\""/>
													</td>
													<td style="text-align: right;">
														<ezf:inputButton name="SearchSoRws" value="Search" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>

											<hr>
											<center>
											<table>
											<tr>
											<td>
											<div id="bottomTBL" style="overflow-x:hidden; width:920; overflow-y:hidden;">
												<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout:fixed" style="MARGIN-LEFT: 5px; MARGIN-TOP: 10px" width="900">
													<col width="95"  align="center"><!-- Shipping order Or RWS Number -->
													<col width="130" align="center"><!-- Source Type -->
													<col width="140" align="center"><!-- WareHouse -->
													<col width="45" align="center"><!-- Sub WareHouse -->
													<col width="100" align="center"><!-- Status -->
													<col width="80"  align="center"><!-- Creation Date -->
													<tr height="30">
														<td class="pClothBs">Shipping Order# / RWS#</td>
														<td class="pClothBs">Source Type</td>
														<td class="pClothBs">Warehouse</td>
														<td class="pClothBs">Sub-WH</td>
														<td class="pClothBs">Status</td>
														<td class="pClothBs">Creation Date</td>
													</tr>
												</table>
											</div>
											<div id="RightTBL" style="overflow-y:scroll; height:210; overflow-x:hidden; width:920; MARGIN-LEFT: 5px;" 
												onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E_Right" width="900">
													<col width="95"  align="center"><!-- Shipping order Or RWS Number -->
													<col width="130" align="center"><!-- Source Type -->
													<col width="140" align="center"><!-- WareHouse -->
													<col width="45" align="center"><!-- Sub WareHouse -->
													<col width="100" align="center"><!-- Status -->
													<col width="80"  align="center"><!-- Creation Date -->
													<ezf:row ezfHyo="E">
													<%-- ----- line 1 ----- --%>
													<tr height="28px" id="id_row{EZF_ROW_NUMBER}">
														<td><ezf:label name="rwsNum_E1" ezfName="rwsNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="sceOrdTpNm_E1" ezfName="sceOrdTpNm_E1" value="ReMan Locator Transfer" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="rtlWhNm_E1" ezfName="rtlWhNm_E1" value="Itasca" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"29\" maxlength=\"20\""/></td>
														<td><ezf:inputText name="rtlSwhNm_E1" ezfName="rtlSwhNm_E1" value="NEW" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"20\""/></td>
														<td><ezf:inputText name="shpgStsDescTxt_E1" ezfName="shpgStsDescTxt_E1" value="Hard Allocated" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
														<td><ezf:label name="xxCratDt_E1" ezfName="xxCratDt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													<%-- ----- line 2 ----- --%>
													<tr height="28px">
														<td><LABEL>RWS050694</LABEL></td>
														<td><input type="text" size="27"  class="pPro" maxlength="50" value="ReMan Parts Back Inventory"></td>
														<td><input type="text" size="29"  class="pPro" maxlength="20" value="651-DCCOM-IRS LANHAM-CUBE B4-163@ELLIN RD-H.HYOUNG"></td>
														<td><input type="text" size="7"  class="pPro" maxlength="20" value="U00"></td>
														<td><input type="text" size="20"  class="pPro" maxlength="50" value="Partially Received"></td>
														<td><LABEL>07/18/2017</LABEL></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
											</td>
											</tr>
											</table>
											</center>
										</div>
									</c:when>

								</c:choose>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
<script type="text/javascript">
             setKeyDownHandler();

    function setKeyDownHandler() {

        if( window.addEventListener ) {
            window.addEventListener("keyup", emulateFuncKeyDown, false);
        } else if( document.attachEvent ) {
            document.attachEvent("onkeyup", emulateFuncKeyDown);
        } else {
            document.onkeyup = emulateFuncKeyDown;
        }
    }

    function emulateFuncKeyDown() {

        var code = event.keyCode;
        //alert("keyCode:["+event.keyCode+"]");

        if(event.keyCode == 122 ) {
            event.keyCode = null;
            event.returnValue = false;
        }

        switch(code) {
        // F11
        case 122:
            //sendServer("Line_Add");
            emulateOnClickEvent("btn11");
            break;
        default:
            break;
        }
        return;
    }

    function emulateOnClickEvent(elementId) {
        var elem = document.getElementById(elementId);
        
        if( /*@cc_on ! @*/ false ) {
            elem.fireEvent("onclick");
        } else {
            var evt = document.createEvent("MouseEvents");
            evt.initEvent("click", false, true);
            elem.dispatchEvent(evt);
        }
    }


</script>


<%-- **** Task specific area ends here **** --%>
