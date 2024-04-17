<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181213152740 --%>
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
			<input type="hidden" name="pageID" value="NLBL3130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Delivery Instruction">
			
			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%">
					<tr>
						<td>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON">
											<a>Dely Instn</a>
									</li>
								</ul>
							</div>

							<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">
								<table border="0" cellspacing="0">
									<tr>
										<td valign="top">
											<fieldset>
												<table border="0" cellspacing="0">
													<col width="100">
													<col width="50">
													<col width="5">
													<col width="180">
													<col width="40">
													<col width="5">
													<col width="80">
													<col width="10">
													<col width="5">
													<col width="75">
													<col width="50">
													<col width="5">
													<col width="90">
													<col width="10">
													<col width="5">
													<col width="103">
													<col width="120">
													<tr>
														<td>Order Number</td>
														<td><ezf:inputText name="trxHdrNum_H1" ezfName="trxHdrNum_H1" value="CP000001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"1\""/></td>
														<td></td>
														<td>Shipping Order Number</td>
														<td><ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="SO000001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"2\""/></td>
														<td></td>
														<td>RWS Number</td>
														<td><ezf:inputText name="rwsNum_H1" ezfName="rwsNum_H1" value="RWS00001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"2\""/></td>
														<td></td>
														<td>Time Zone</td>
														<td><ezf:inputText name="tmZoneCd_H1" ezfName="tmZoneCd_H1" value="EDT" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" tabindex=\"3\""/></td>
														<td></td>
														<td>Site Survey</td>
														<td><ezf:anchor name="xxLinkAncr_H1" ezfName="xxLinkAncr_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_DelyDetails" otherAttr=" tabindex=\"4\"">Link</ezf:anchor></td>
														<td></td>
														<td>Customer Name</td>
														<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\" tabindex=\"5\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>

								<%-- ##### BODY ##### --%>
								<div id="parentBoxA">
									<fieldset style="width:820px;vertical-align:top;float:left;">
										<legend style="font-size:12px;">Site Information</legend>
										<table style="table-layout:fixed;" cellspacing="0" height="290">
											<col width="220">
											<col width="90">
											<col width="10">
											<col width="90">
											<col width="220">
											<col width="100">
											<col width="90">
											<tr height="20">
												<td>Hours</td>
												<td>
													<ezf:inputText name="xxScrItem7Txt_OF" ezfName="xxScrItem7Txt_OF" value="09:00" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" tabindex=\"6\""/>
													<ezf:select name="xxTmFrameTxt_OF" ezfName="xxTmFrameTxt_OF" ezfCodeName="xxTmFrameTxt_CD" ezfDispName="xxTmFrameTxt_VL" otherAttr=" style=\"width:39px;\" tabindex=\"7\""/>
												</td>
												<td>-</td>
												<td><ezf:inputText name="xxScrItem7Txt_OT" ezfName="xxScrItem7Txt_OT" value="05:00" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" tabindex=\"8\""/>
													<ezf:select name="xxTmFrameTxt_OT" ezfName="xxTmFrameTxt_OT" ezfCodeName="xxTmFrameTxt_CD" ezfDispName="xxTmFrameTxt_VL" otherAttr=" style=\"width:39px;\" tabindex=\"9\""/>
												</td>
												<td>Door Front</td>
												<td><ezf:inputCheckBox name="frontDoorAvalTxt_Y" ezfName="frontDoorAvalTxt_Y" value="Y" otherAttr=" tabindex=\"30\""/>YES</td>
												<td><ezf:inputCheckBox name="frontDoorAvalTxt_N" ezfName="frontDoorAvalTxt_N" value="Y" otherAttr=" tabindex=\"31\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Security Clearance Required</td>
												<td><ezf:inputCheckBox name="secClncReqTxt_Y" ezfName="secClncReqTxt_Y" value="Y" otherAttr=" tabindex=\"10\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="secClncReqTxt_N" ezfName="secClncReqTxt_N" value="Y" otherAttr=" tabindex=\"11\""/>NO</td>
												<td>Door Back</td>
												<td><ezf:inputCheckBox name="backDoorAvalTxt_Y" ezfName="backDoorAvalTxt_Y" value="Y" otherAttr=" tabindex=\"32\""/>YES</td>
												<td><ezf:inputCheckBox name="backDoorAvalTxt_N" ezfName="backDoorAvalTxt_N" value="Y" otherAttr=" tabindex=\"33\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Insurance Certificate Required</td>
												<td><ezf:inputCheckBox name="insCertReqTxt_Y" ezfName="insCertReqTxt_Y" value="Y" otherAttr=" tabindex=\"12\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="insCertReqTxt_N" ezfName="insCertReqTxt_N" value="Y" otherAttr=" tabindex=\"13\""/>NO</td>
												<td>Power Outlet Configured</td>
												<td><ezf:inputCheckBox name="pwrOtltConfigTxt_Y" ezfName="pwrOtltConfigTxt_Y" value="Y" otherAttr=" tabindex=\"34\""/>YES</td>
												<td><ezf:inputCheckBox name="pwrOtltConfigTxt_N" ezfName="pwrOtltConfigTxt_N" value="Y" otherAttr=" tabindex=\"35\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Floor</td>
												<td colspan="3"><ezf:inputText name="cmpyInfoFlNm_H1" ezfName="cmpyInfoFlNm_H1" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"14\""/></td>
												<td>Sign On Building Roadside</td>
												<td><ezf:inputCheckBox name="sgnOnBldgRdsdTxt_Y" ezfName="sgnOnBldgRdsdTxt_Y" value="Y" otherAttr=" tabindex=\"36\""/>YES</td>
												<td><ezf:inputCheckBox name="sgnOnBldgRdsdTxt_N" ezfName="sgnOnBldgRdsdTxt_N" value="Y" otherAttr=" tabindex=\"37\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Floor Covering</td>
												<td colspan="3"><ezf:inputText name="flCovTxt_H1" ezfName="flCovTxt_H1" otherAttr=" size=\"25\" maxlength=\"250\" tabindex=\"15\""/></td>
												<td>Building # of Storeys</td>
												<td colspan="2"><ezf:inputText name="bldgStryNum_H1" ezfName="bldgStryNum_H1" otherAttr=" size=\"25\" maxlength=\"20\" tabindex=\"38\""/></td>
											</tr>
											<tr height="20">
												<td>Tractor Trailer Accessible?</td>
												<td><ezf:inputCheckBox name="trctrAndTrailAccsTxt_Y" ezfName="trctrAndTrailAccsTxt_Y" value="Y" otherAttr=" tabindex=\"16\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="trctrAndTrailAccsTxt_N" ezfName="trctrAndTrailAccsTxt_N" value="Y" otherAttr=" tabindex=\"17\""/>NO</td>
												<td>Building Guard Notified</td>
												<td><ezf:inputCheckBox name="bldgGurdNtfyTxt_Y" ezfName="bldgGurdNtfyTxt_Y" value="Y" otherAttr=" tabindex=\"39\""/>YES</td>
												<td><ezf:inputCheckBox name="bldgGurdNtfyTxt_N" ezfName="bldgGurdNtfyTxt_N" value="Y" otherAttr=" tabindex=\"40\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Loading Dock?</td>
												<td><ezf:inputCheckBox name="loadDockAvalTxt_Y" ezfName="loadDockAvalTxt_Y" value="Y" otherAttr=" tabindex=\"18\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="loadDockAvalTxt_N" ezfName="loadDockAvalTxt_N" value="Y" otherAttr=" tabindex=\"19\""/>NO</td>
												<td>Industrial Park</td>
												<td><ezf:inputCheckBox name="indlParkTxt_Y" ezfName="indlParkTxt_Y" value="Y" otherAttr=" tabindex=\"41\""/>YES</td>
												<td><ezf:inputCheckBox name="indlParkTxt_N" ezfName="indlParkTxt_N" value="Y" otherAttr=" tabindex=\"42\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Dock Height(in.)</td>
												<td colspan="3"><ezf:inputText name="loadDockHgt_H1" ezfName="loadDockHgt_H1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"20\""/></td>
												<td>Business Park</td>
												<td><ezf:inputCheckBox name="bizParkTxt_Y" ezfName="bizParkTxt_Y" value="Y" otherAttr=" tabindex=\"43\""/>YES</td>
												<td><ezf:inputCheckBox name="bizParkTxt_N" ezfName="bizParkTxt_N" value="Y" otherAttr=" tabindex=\"44\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Ramp?</td>
												<td><ezf:inputCheckBox name="rampAvalTxt_Y" ezfName="rampAvalTxt_Y" value="Y" otherAttr=" tabindex=\"21\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="rampAvalTxt_N" ezfName="rampAvalTxt_N" value="Y" otherAttr=" tabindex=\"22\""/>NO</td>
												<td>Professional Building</td>
												<td><ezf:inputCheckBox name="proBldgTxt_Y" ezfName="proBldgTxt_Y" value="Y" otherAttr=" tabindex=\"45\""/>YES</td>
												<td><ezf:inputCheckBox name="proBldgTxt_N" ezfName="proBldgTxt_N" value="Y" otherAttr=" tabindex=\"46\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Steps?</td>
												<td><ezf:inputCheckBox name="stepAvalTxt_Y" ezfName="stepAvalTxt_Y" value="Y" otherAttr=" tabindex=\"23\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="stepAvalTxt_N" ezfName="stepAvalTxt_N" value="Y" otherAttr=" tabindex=\"24\""/>NO</td>
												<td>Shopping Center</td>
												<td><ezf:inputCheckBox name="shpngCtrTxt_Y" ezfName="shpngCtrTxt_Y" value="Y" otherAttr=" tabindex=\"47\""/>YES</td>
												<td><ezf:inputCheckBox name="shpngCtrTxt_N" ezfName="shpngCtrTxt_N" value="Y" otherAttr=" tabindex=\"48\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Step Inside Count</td>
												<td colspan="3"><ezf:inputText name="insdStepNum_H1" ezfName="insdStepNum_H1" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"25\""/></td>
												<td>Private Residence</td>
												<td><ezf:inputCheckBox name="pvtResTxt_Y" ezfName="pvtResTxt_Y" value="Y" otherAttr=" tabindex=\"49\""/>YES</td>
												<td><ezf:inputCheckBox name="pvtResTxt_N" ezfName="pvtResTxt_N" value="Y" otherAttr=" tabindex=\"50\""/>NO</td>
											</tr>
											<tr height="20">
												<td>Step Outside Count</td>
												<td colspan="3"><ezf:inputText name="otsdStepNum_H1" ezfName="otsdStepNum_H1" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"26\""/></td>
												<td>Transport Option</td>
                                                <td colspan="2">
													<ezf:select name="delyTrnspOptCd_H1" ezfName="delyTrnspOptCd_H1" ezfBlank="1" ezfCodeName="delyTrnspOptCd_CD" ezfDispName="delyTrnspOptDescTxt_VL" otherAttr=" style=\"width:180px;\" tabindex=\"27\""/>
                                                </td>
											</tr>
											<tr height="20">
												<td>Step Crawler Required</td>
												<td><ezf:inputCheckBox name="stairCrawReqFlg_Y" ezfName="stairCrawReqFlg_Y" value="Y" otherAttr=" tabindex=\"28\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="stairCrawReqFlg_N" ezfName="stairCrawReqFlg_N" value="Y" otherAttr=" tabindex=\"29\""/>NO</td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr height="25">
												<td>Width of stairs and landings(in.)</td>
												<td colspan="3"><ezf:inputText name="stairAndLdgWdt_H1" ezfName="stairAndLdgWdt_H1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"30\""/></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</table>
									</fieldset>
									<fieldset style="width:280px;vertical-align:top;">
										<legend style="font-size:12px;">Validation</legend>
										<table style="table-layout:fixed;" cellspacing="0" height="50">
											<col width="150">
											<col width="65">
											<col width="65">
											<tr>
												<td>Validated With</td>
												<td colspan="2"><ezf:inputText name="xxPsnNm_E1" ezfName="xxPsnNm_E1" value="YASUHIKO IMAZU" otherAttr=" size=\"17\" maxlength=\"65\""/></td>
											</tr>
											<tr>
												<td>Validation Date/Time</td>
												<td colspan="2"><ezf:inputText name="xxTsDsp19Txt_E1" ezfName="xxTsDsp19Txt_E1" value="MM/DD/YYYY hh:mm:ss" otherAttr=" size=\"17\" maxlength=\"19\""/></td>
											</tr>
											<tr>
												<td>Customer informed about B/O</td>
												<td><ezf:inputCheckBox name="custInfoBoFlg_Y" ezfName="custInfoBoFlg_Y" value="Y" otherAttr=" tabindex=\"70\""/>YES</td>
												<td><ezf:inputCheckBox name="custInfoBoFlg_N" ezfName="custInfoBoFlg_N" value="Y" otherAttr=" tabindex=\"71\""/>NO</td>
											</tr>
											<tr>
												<td>Pickup extra Toner</td>
												<td><ezf:inputCheckBox name="pickUpXtrTonerFlg_Y" ezfName="pickUpXtrTonerFlg_Y" value="Y" otherAttr=" tabindex=\"71\""/>YES</td>
												<td><ezf:inputCheckBox name="pickUpXtrTonerFlg_N" ezfName="pickUpXtrTonerFlg_N" value="Y" otherAttr=" tabindex=\"73\""/>NO</td>
											</tr>
											<tr>
												<td>Pickup at same time</td>
												<td><ezf:inputCheckBox name="pickUpAtSameTmFlg_Y" ezfName="pickUpAtSameTmFlg_Y" value="Y" otherAttr=" tabindex=\"74\""/>YES</td>
												<td><ezf:inputCheckBox name="pickUpAtSameTmFlg_N" ezfName="pickUpAtSameTmFlg_N" value="Y" otherAttr=" tabindex=\"75\""/>NO</td>
											</tr>
										</table>
									</fieldset>
									<fieldset style="width:292px;vertical-align:top;float:left;">
										<legend style="font-size:12px;">Additional Comments</legend>
										<table style="table-layout:fixed;" cellspacing="0" height="160">
											<tr>
												<td><ezf:textArea name="delyInstnAddlCmntTxt_D1" ezfName="delyInstnAddlCmntTxt_D1" otherAttr=" rows=\"13\" cols=\"36\" maxlength=\"180\" ezftoupper=\"\" tabindex=\"76\""/></td>
											</tr>
										</table>
									</fieldset>
									<fieldset style="width:630px;vertical-align:top;float:left;">
										<legend style="font-size:12px;">Elevator Information & Dimensions</legend>
										<table style="table-layout:fixed;" cellspacing="0" height="200">
											<col width="200">
											<col width="100">
											<col width="10">
											<col width="105">
											<col width="200">
											<tr>
												<td>Elevator Available?</td>
												<td><ezf:inputCheckBox name="elevAvalTxt_Y" ezfName="elevAvalTxt_Y" value="Y" otherAttr=" tabindex=\"51\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="elevAvalTxt_N" ezfName="elevAvalTxt_N" value="Y" otherAttr=" tabindex=\"52\""/>NO</td>
												<td rowspan="5">
													<fieldset style="width:190px;vertical-align:top;">
														<legend style="font-size:12px;">Elevator Dimensions</legend>
														<table style="table-layout:fixed;" cellspacing="0" height="80">
															<col width="100">
															<col width="90">
															<tr>
																<td>Width(in.)</td>
																<td colspan="2"><ezf:inputText name="elevWdt_C1" ezfName="elevWdt_C1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"65\""/></td>
															</tr>
															<tr>
																<td>Depth(in.)</td>
																<td colspan="2"><ezf:inputText name="elevDepthNum_C1" ezfName="elevDepthNum_C1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"66\""/></td>
															</tr>
															<tr>
																<td>Capacity(Lbs.)</td>
																<td colspan="2"><ezf:inputText name="elevCapWt_C1" ezfName="elevCapWt_C1" otherAttr=" size=\"10\" maxlength=\"8\" tabindex=\"67\""/></td>
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>
											<tr>
												<td>Elevator Hours</td>
												<td>
													<ezf:inputText name="xxScrItem7Txt_EF" ezfName="xxScrItem7Txt_EF" value="09:00" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" tabindex=\"53\""/>
													<ezf:select name="xxTmFrameTxt_EF" ezfName="xxTmFrameTxt_EF" ezfCodeName="xxTmFrameTxt_CD" ezfDispName="xxTmFrameTxt_VL" otherAttr=" style=\"width39px;\" tabindex=\"54\""/>
												</td>
												<td>-</td>
												<td><ezf:inputText name="xxScrItem7Txt_ET" ezfName="xxScrItem7Txt_ET" value="05:00" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\" tabindex=\"55\""/>
													<ezf:select name="xxTmFrameTxt_ET" ezfName="xxTmFrameTxt_ET" ezfCodeName="xxTmFrameTxt_CD" ezfDispName="xxTmFrameTxt_VL" otherAttr=" style=\"width:39px;\" tabindex=\"56\""/>
												</td>
											</tr>
											<tr>
												<td>Elevator Appointment Needed</td>
												<td><ezf:inputCheckBox name="elevApptReqTxt_Y" ezfName="elevApptReqTxt_Y" value="Y" otherAttr=" tabindex=\"57\""/>YES</td>
												<td></td>
												<td><ezf:inputCheckBox name="elevApptReqTxt_N" ezfName="elevApptReqTxt_N" value="Y" otherAttr=" tabindex=\"58\""/>NO</td>
											</tr>
											<tr>
												<td>Elevator Contact</td>
												<td colspan="3"><ezf:inputText name="elevCtacPsnNm_C1" ezfName="elevCtacPsnNm_C1" otherAttr=" size=\"25\" maxlength=\"20\" tabindex=\"59\""/></td>
											</tr>
											<tr>
												<td>Elevator Phone</td>
												<td colspan="3"><ezf:inputText name="elevCtacTelNum_C1" ezfName="elevCtacTelNum_C1" otherAttr=" size=\"25\" maxlength=\"20\" tabindex=\"60\""/></td>
											</tr>
											<tr>
												<td colspan="4">
													<fieldset style="width:400;">
														<legend style="font-size:12px;">Building Entrance Dimensions</legend>
														<table style="table-layout:fixed;" cellspacing="0" height="50">
															<col width="80">
															<col width="90">
															<col width="140">
															<col width="90">
															<tr>
																<td>Height(in.)</td>
																<td><ezf:inputText name="bldgEntDoorHgt_A1" ezfName="bldgEntDoorHgt_A1" otherAttr=" size=\"10\" maxlength=\"7\" tabindex=\"61\""/></td>
																<td>Corridor Width(in.)</td>
																<td><ezf:inputText name="crdrWdt_A1" ezfName="crdrWdt_A1" otherAttr=" size=\"10\" maxlength=\"7\" tabindex=\"63\""/></td>
															</tr>
															<tr>
																<td>Width(in.)</td>
																<td><ezf:inputText name="bldgEntDoorWdt_A1" ezfName="bldgEntDoorWdt_A1" otherAttr=" size=\"10\" maxlength=\"7\" tabindex=\"62\""/></td>
																<td>Door Width(in.)</td>
																<td><ezf:inputText name="doorWdt_A1" ezfName="doorWdt_A1" otherAttr=" size=\"10\" maxlength=\"7\" tabindex=\"64\""/></td>
															</tr>
														</table>
													</fieldset>
												</td>
												<td valign="top">
													<fieldset style="width:190px;">
														<legend style="font-size:12px;">Elevator Door Openings</legend>
														<table style="table-layout:fixed;" cellspacing="0" height="50">
															<col width="100">
															<col width="90">
															<tr>
																<td>Height(in.)</td>
																<td colspan="2"><ezf:inputText name="elevDoorHgt_C1" ezfName="elevDoorHgt_C1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"68\""/></td>
															</tr>
															<tr>
																<td>Width(in.)</td>
																<td colspan="2"><ezf:inputText name="elevDoorWdt_C1" ezfName="elevDoorWdt_C1" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"69\""/></td>
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>
										</table>
									</fieldset>
									<fieldset style="width:490px;vertical-align:top;">
										<legend style="font-size:12px;">Instructions</legend>
										<table style="table-layout:fixed;" cellspacing="0" height="200">
											<col width="230">
											<col width="10">
											<col width="230">
											<tr>
												<td>Shipping Instructions</td>
												<td></td>
												<td>Install Instructions</td>
											</tr>
											<tr>
												<td><ezf:textArea name="shpgInstnCmntTxt_B1" ezfName="shpgInstnCmntTxt_B1" otherAttr=" rows=\"7\" cols=\"30\" maxlength=\"200\" ezftoupper=\"\" tabindex=\"77\""/></td>
												<td></td>
												<td><ezf:textArea name="istlInstnCmntTxt_B1" ezfName="istlInstnCmntTxt_B1" otherAttr=" rows=\"7\" cols=\"30\" maxlength=\"200\" ezftoupper=\"\" tabindex=\"78\""/></td>
											</tr>
											<tr>
												<td>Delivery Instructions</td>
												<td></td>
												<td>Technician Instructions</td>
											</tr>
											<tr>
												<td><ezf:textArea name="delyInstnCmntTxt_B1" ezfName="delyInstnCmntTxt_B1" otherAttr=" rows=\"7\" cols=\"30\" maxlength=\"180\" ezftoupper=\"\" tabindex=\"79\""/></td>
												<td></td>
												<td><ezf:textArea name="techInstnCmntTxt_B1" ezfName="techInstnCmntTxt_B1" otherAttr=" rows=\"7\" cols=\"30\" maxlength=\"180\" ezftoupper=\"\" tabindex=\"80\""/></td>
											</tr>
										</table>
									</fieldset>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
