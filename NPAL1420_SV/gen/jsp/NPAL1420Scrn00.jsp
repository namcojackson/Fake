<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180202101456 --%>
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
			<input type="hidden" name="pageID" value="NPAL1420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reman Task Entry">
			
<%-- ######################################## UPPER TAB AREA ######################################## --%>
<%-- ######################################## HEADER AREA ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="pTab_BODY">
								<table  style="margin-left:10px; margin-top:0px;">
									<col align="left">
									<tr>
										<td>
											<table border="0">
												<col width="100px"  align="left">
												<col width="250px"  align="left">
												<col width="100px"  align="left">
												<col width=""  align="left">
												<%-- =============== Warehouse Category =============== --%>
												<tr height="20px">
													<td class="stab">Reman Warehouse</td>
													<td style="text-align: left;">
														<ezf:inputText name="xxMsgTxt_WH" ezfName="xxMsgTxt_WH" value="I01 - ITASCA" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Main Unit Serial</td>
													<td style="text-align: left;">
														<ezf:inputText name="rmnfMainUnitSerNum" ezfName="rmnfMainUnitSerNum" value="SK1000000" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="20px">
													<td class="stab">Reman Order#</td>
													<td style="text-align: left;">
														<ezf:inputText name="rmnfOrdNum" ezfName="rmnfOrdNum" value="1000" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Reman Status</td>
													<td style="text-align: left;">
														<ezf:inputText name="rmnfOrdStsDescTxt" ezfName="rmnfOrdStsDescTxt" value="In Process" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="20px">
													<td class="stab">Reman Tech Owner</td>
													<td style="text-align: left;">
														<ezf:inputText name="techNm_H" ezfName="techNm_H" value="Mike Nagle" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

							<table  style="margin-left:10px; margin-top:0px;">
								<col align="left">
								<tr>
									<td>
										<table border="0">
											<col width="100px"  align="left">
											<col width="250px"  align="left">
											<col width="100px"  align="left">
											<col width="195px"  align="left">
											<col width="80px"  align="left">
											<col width="100px"  align="left">
											<col width="90px"  align="left">
											<col width=""  align="left">
											<%-- =============== Warehouse Category =============== --%>
											<tr height="20px">
												<td class="stab">Task#</td>
												<td style="text-align: left;">
													<ezf:inputText name="xxMsgTxt_TS" ezfName="xxMsgTxt_TS" value="1000-001" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
												</td>
												<td class="stab">Description</td>
												<td style="text-align: left;" colspan="5">
													<ezf:inputText name="rmnfTaskDescTxt" ezfName="rmnfTaskDescTxt" value="Reinstall Drum" otherAttr=" size=\"50\" maxlength=\"100\" ezftoupper=\"\""/>
												</td>
											</tr>
											<tr height="20px">
												<td class="stab">Start Date</td>
												<td style="text-align: left;">
													<ezf:inputText name="rmnfTaskStartDt" ezfName="rmnfTaskStartDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
													<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rmnfTaskStartDt', 4);"/>
												</td>
												<td class="stab">End Date</td>
												<td style="text-align: left;" colspan="5">
													<ezf:inputText name="rmnfTaskEndDt" ezfName="rmnfTaskEndDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
													<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rmnfTaskEndDt', 4);"/>
												</td>
											</tr>
											<tr height="20px">
												<td class="stab" valign="top">Notes</td>
												<td style="text-align: left;" colspan="5">
													<ezf:textArea name="spclInstnCmntTxt" ezfName="spclInstnCmntTxt" otherAttr=" rows=\"2\" cols=\"50\""/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<hr>

							<%-- ######################################## DETAIL AREA ######################################## --%>
							<table  style="margin-left:10px; margin-top:0px;"table border="0">
	                            <tr height="20px">
									<td class="stab" align="left" ><b>Labor</b></td>
	                            </tr>
	                            <tr>
									<%-- =============== TABLE HEADER =============== --%>
	                                <td valign="top">
										<table border="0">
											<col width="100px"  align="left">
											<col width="400px">
											<col width="80px"  align="left">
											<col width="140px">
											<col width="85px"  align="left">
											<col width="150px">
											<%-- =============== Warehouse Category =============== --%>
											<tr height="20px">
												<td class="stab"><ezf:anchor name="techTocCd_AC" ezfName="techTocCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" >Technician</ezf:anchor></td>
												<td style="text-align: left;">
													<ezf:inputText name="techTocCd" ezfName="techTocCd" value="1234" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
													<ezf:inputButton name="Search_Tech" value=">>" htmlClass="pBtn0" />
													<ezf:inputText name="techNm_L" ezfName="techNm_L" value="Mike Nagle" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
												</td>
												<td class="stab">Hours</td>
												<td style="text-align: left;">
													<ezf:inputText name="rmnfLborAot" ezfName="rmnfLborAot" value="8" otherAttr=" size=\"10\" maxlength=\"4\" ezftoupper=\"\" style=\"text-align: right;\""/>
												</td>
											</tr>
											<tr height="20px">
												<td class="stab">Notes</td>
												<td>
													<ezf:inputText name="rmnfLborCmntTxt" ezfName="rmnfLborCmntTxt" value="Installed additional part and cleaning" otherAttr=" size=\"52\" maxlength=\"100\""/>
												</td>
												<td class="stab">Cost per Hour</td>
												<td style="text-align: left;">
													<ezf:inputText name="rmnfCostPerHourAmt" ezfName="rmnfCostPerHourAmt" value="25.00" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\" style=\"text-align: right;\""/>
													<ezf:inputText name="stdCcyCd_PH" ezfName="stdCcyCd_PH" value="USD" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align: right;\""/>
												</td>
												<td class="stab">Labor Cost</td>
												<td style="text-align: left;">
													<ezf:inputText name="rmnfLborCostAmt" ezfName="rmnfLborCostAmt" value="200.00" otherAttr=" size=\"11\" maxlength=\"50\" ezftoupper=\"\" style=\"text-align: right;\""/>
													<ezf:inputText name="stdCcyCd_LC" ezfName="stdCcyCd_LC" value="USD" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align: right;\""/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<hr>

							<table  style="margin-left:10px; margin-top:0px; width:1000;">
	                            <tr>
									<td>
									<table border="0">
										<col width="200px">
										<col width="438px">
										<col width="120px">
										<col width="">
										<tr height="20px">
											<td class="stab" align="left" ><b>Parts Usage</b></td>
											<td class="stab">&nbsp;</td>
											<td class="stab">Source Location</td>
											<td class="stab">
												<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="Q05649" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="G" otherAttr=" size=\"5\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
										</tr>
									</table>
									</td>
	                            </tr>
	                            <tr>
									<%-- =============== TABLE HEADER =============== --%>
	                                <td valign="top">
	                                    <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:963;">
	                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="30" width="">
												<col width="30px" align="center">
												<col width="200px" align="center">
												<col width="220px" align="center">
												<col width="70px" align="center">
												<col width="100px" align="center">
												<col width="80px" align="center">
												<col width="80px" align="center">
												<col width="80px" align="center">
												<col width="100px" align="center">
												<tr>
													<td class="pClothBs">&nbsp;</td>
													<td class="pClothBs">Item Number</td>
													<td class="pClothBs">Description</td>
													<td class="pClothBs">Return<br>Control</td>
													<td class="pClothBs">Unit Cost</td>
													<td class="pClothBs">Available Qty</td>
													<td class="pClothBs">Used Qty</td>
													<td class="pClothBs">Release Qty</td>
													<td class="pClothBs">Used Cost</td>
												</tr>
											</table>
										</div>
										<%-- =============== TABLE BODY =============== --%>
		                                <div id="RightTBL" style="overflow-y:scroll; height:200; overflow-x:none; width:980;" >
	                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="">
												<col width="30px" align="left">
												<col width="200px" align="left">
												<col width="220px" align="left">
												<col width="70px" align="center">
												<col width="100px" align="left">
												<col width="80px" align="left">
												<col width="80px" align="left">
												<col width="80px" align="left">
												<col width="100px" align="left">
												<ezf:row ezfHyo="A">
												<tr id="id_row${EZF_ROW_NUMBER}" height="28px">
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<ezf:inputButton name="OpenWin_Item" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="FM1-234-456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
														<ezf:inputButton name="Search_Item" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
													</td>
													<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="ROLLER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td><ezf:label name="rtrnReqPrtFlg_A1" ezfName="rtrnReqPrtFlg_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="prtUnitCostAmt_A1" ezfName="prtUnitCostAmt_A1" value="30.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="invtyAvalQty_A1" ezfName="invtyAvalQty_A1" value="4" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="rmnfPrtQty_A1" ezfName="rmnfPrtQty_A1" value="4" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="rmnfPrtRelQty_A1" ezfName="rmnfPrtRelQty_A1" value="4" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="prtUnitCostAmt_T" ezfName="prtUnitCostAmt_T" value="120.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
												</ezf:row>
											</table>
										</div>
		                                <div id="TBL" >
	                                        <table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0" id="B_Right" width="">
												<col width="620px" align="left">
												<col width="150px" align="left">
												<col width="220px" align="left">
												<tr height="40px"style="vertical-align:bottom;">
													<td><ezf:inputButton name="AddLine" value="Add Line" htmlClass="pBtn6" />
																	 <ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn6" /></td>
													<td class="stab" style="text-align: right;">Total Parts Cost &nbsp;</td>
													<td><ezf:inputText name="prtUnitCostAmt" ezfName="prtUnitCostAmt" value="156.00" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
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
