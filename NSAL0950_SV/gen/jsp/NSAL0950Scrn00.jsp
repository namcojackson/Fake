<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161129191418 --%>
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
			<input type="hidden" name="pageID" value="NSAL0950Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Validation Setup">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<%-- ######################################## SearchCriteria Start ############################################## --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract Validation Setup" class="pTab_ON"><a href="#">Vld Setup</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="100">
									<col width="15">
									<col width="90">
									<col width="90">
									<col width="90">
									<col width="90">
									<col width="40">
									<col width="140">
									<col width="15">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<tr>
										<td class="stab">Category Name:</td>
										<td></td>
										<td colspan="4"><ezf:select name="dsContrVldCatgCd_SS" ezfName="dsContrVldCatgCd_SS" ezfBlank="1" ezfCodeName="dsContrVldCatgCd_SC" ezfDispName="dsContrVldCatgDescTxt_SC" otherAttr=" style=\"width:355\""/></td>
										<td></td>
										<td class="stab">Start Date:</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="effFromDt_S" ezfName="effFromDt_S" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_S', 4);"></td>
										<td class="stab" colspan="2">End Date:</td>
										<td colspan="6"><ezf:inputText name="effToDt_S" ezfName="effToDt_S" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_S', 4);"></td>
									</tr>
									<tr>
										<td class="stab">Validation Name(*):</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="dsContrVldNm_S" ezfName="dsContrVldNm_S" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" otherAttr=" size=\"50\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab"><ezf:anchor name="vldCmptTxt_SL" ezfName="vldCmptTxt_SL" ezfEmulateBtn="1" ezfGuard="OpenWin_ComponentSearch" >Component Name(*):</ezf:anchor></td>
										<td></td>
										<td colspan="12"><ezf:inputText name="vldCmptTxt_S" ezfName="vldCmptTxt_S" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
									</tr>
									<tr>
										<td class="stab"">Description(*):</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="dsContrVldDescTxt_S" ezfName="dsContrVldDescTxt_S" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0xxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"50\" maxlength=\"120\""/></td>
										<td></td>
										<td class="stab">Primary Validation:</td>
										<td></td>
										<td colspan="12"><ezf:inputCheckBox name="primVldFlg_S" ezfName="primVldFlg_S" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Level:</td>
										<td></td>
										<td colspan="2"><ezf:inputCheckBox name="vldLvlContrFlg_S" ezfName="vldLvlContrFlg_S" value="Y" />Contract</td>
										<td colspan="2"><ezf:inputCheckBox name="vldLvlMachFlg_S" ezfName="vldLvlMachFlg_S" value="Y" />Machine</td>
										<td></td>
										<td class="stab">Validation Result Override:</td>
										<td></td>
										<td colspan="12"><ezf:inputCheckBox name="ovrdVldRsltAvalFlg_S" ezfName="ovrdVldRsltAvalFlg_S" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Contract Type:</td>
										<td></td>
										<td><ezf:inputCheckBox name="vldAggrFlg_S" ezfName="vldAggrFlg_S" value="Y" />Aggregate</td>
										<td><ezf:inputCheckBox name="vldFleetFlg_S" ezfName="vldFleetFlg_S" value="Y" />Fleet</td>
										<td><ezf:inputCheckBox name="vldRegFlg_S" ezfName="vldRegFlg_S" value="Y" />Non-Fleet</td>
										<td><ezf:inputCheckBox name="vldWtyFlg_S" ezfName="vldWtyFlg_S" value="Y" />Warranty</td>
										<td></td>
										<td colspan="14" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchCriteria End   ############################################## --%>
					<br>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" height="24">
						<tr>
							<td>
								<ezf:inputButton name="AddLine" value="Add Line" htmlClass="pBtn6" otherAttr=" id=\"AddLine\""/>
								<ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn6" otherAttr=" id=\"DeleteLine\""/>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchResultList Start ############################################## --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col align="left" valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-x:hidden; width:1090;">
									<table border="1" cellpadding="1" cellspacing="0" width="" height="37">
										<col width="25"  align="center">		<!-- Radio Button -->
										<col width="270"  align="center">		<!-- Category Name -->
										<col width="270"  align="center">		<!-- Validation Name -->
										<col width="31"  align="center">		<!-- Level - Contr -->
										<col width="31"  align="center">		<!-- Level - Mach -->
										<col width="31"  align="center">		<!-- Contact Type - AGG -->
										<col width="31"  align="center">		<!-- Contact Type - FLT -->
										<col width="31"  align="center">		<!-- Contact Type - NFLT -->
										<col width="31"  align="center">		<!-- Contact Type - WTY -->
										<col width="31"  align="center">		<!-- PRIM -->
										<col width="31"  align="center">		<!-- OVRD -->
										<col width="100"  align="center">		<!-- Start Date -->
										<col width="100"  align="center">		<!-- End Date -->
										<tr>
											<td class="pClothBs" rowspan="2"></td>
											<td class="pClothBs" rowspan="2">Category Name</td>
											<td class="pClothBs" rowspan="2">Validation Name</td>
											<td class="pClothBs" colspan="2">Level</td>
											<td class="pClothBs" colspan="4">Contract Type</td>
											<td class="pClothBs" rowspan="2">PRIM</td>
											<td class="pClothBs" rowspan="2">OVRD</td>
											<td class="pClothBs" rowspan="2">Start Date</td>
											<td class="pClothBs" rowspan="2">End Date</td>
										</tr>
										<tr>
											<td class="pClothBs">Contr</td>
											<td class="pClothBs">Mach</td>
											<td class="pClothBs">AGG</td>
											<td class="pClothBs">FLT</td>
											<td class="pClothBs">NFLT</td>
											<td class="pClothBs">WTY</td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="width:1090; overflow-y:scroll; height:220;">
									<table id="A" border="1" cellpadding="1" cellspacing="0">
										<col width="25"  align="center">		<!-- Radio Button -->
										<col width="270"  align="center">		<!-- Category Name -->
										<col width="270"  align="center">		<!-- Validation Name -->
										<col width="31"  align="center">		<!-- Level - Contr -->
										<col width="31"  align="center">		<!-- Level - Mach -->
										<col width="31"  align="center">		<!-- Contact Type - AGG -->
										<col width="31"  align="center">		<!-- Contact Type - FLT -->
										<col width="31"  align="center">		<!-- Contact Type - NFLT -->
										<col width="31"  align="center">		<!-- Contact Type - WTY -->
										<col width="31"  align="center">		<!-- PRIM -->
										<col width="31"  align="center">		<!-- OVRD -->
										<col width="100"  align="center">		<!-- Start Date -->
										<col width="100"  align="center">		<!-- End Date -->
										<ezf:row ezfHyo="A">
											<tr>
												<td>
													<ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" onClick=\"sendServer('OnClickList');\""/>
												</td>
												<td>
													<ezf:select name="contrIntfcSrcTpCd_AS" ezfName="dsContrVldCatgCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrVldCatgCd_SC" ezfDispName="dsContrVldCatgDescTxt_SC" otherAttr=" style=\"width:265\" style=\"width:130;\""/>
												</td>
												<td>
													<ezf:inputText name="dsContrVldNm_A" ezfName="dsContrVldNm_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsContrVldNm_A#{EZF_ROW_NUMBER}\" size=\"35\" maxlength=\"60\""/>
												</td>
												<td>
													<ezf:label name="vldLvlContrFlg_A" ezfName="vldLvlContrFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="vldLvlMachFlg_A" ezfName="vldLvlMachFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="vldAggrFlg_A" ezfName="vldAggrFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="vldFleetFlg_A" ezfName="vldFleetFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="vldRegFlg_A" ezfName="vldRegFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="vldWtyFlg_A" ezfName="vldWtyFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="primVldFlg_A" ezfName="primVldFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="ovrdVldRsltAvalFlg_A" ezfName="ovrdVldRsltAvalFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="effToDt_A" ezfName="effToDt_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												</td>
											</tr>
											<ezf:skip>
											<tr>
												<td>
													<input id="xxRadioBtn_H#{EZF_ROW_NUMBER}" type="radio" name="xxRadioBtn_H" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn_H">
												</td>
												<td>
													<select name="contrIntfcSrcTpCd_AS" class="pPro" style="width:265" ezfname="dsContrVldCatgCd_AS" ezflist="dsContrVldCatgCd_SC,dsContrVldCatgDescTxt_SC,99, ,blank" style="width:130;" ezfhyo="A"><option>Check Customer</option><option>Check Date</option><option>Check Fleet</option><option>Check FM</option><option>Check OPTIMA</option><option>Check Payment</option><option>Check Pricing</option><option>Check Start Read</option><option>Check Supplies</option><option>Check Vendor</option><option>Check Aggregate</option><option>Check Billing</option></select>
												</td>
												<td>
													<input id="dsContrVldNm_A#{EZF_ROW_NUMBER}" type="text" size="35" maxlength="60" class="pPro" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" name="dsContrVldNm_A" ezfname="dsContrVldNm_A" ezfhyo="A">
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlContrFlg_A" ezfname="vldLvlContrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlMachFlg_A" ezfname="vldLvlMachFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldAggrFlg_A" ezfname="vldAggrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldFleetFlg_A" ezfname="vldFleetFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldRegFlg_A" ezfname="vldRegFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldWtyFlg_A" ezfname="vldWtyFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="primVldFlg_A" ezfname="primVldFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="ovrdVldRsltAvalFlg_A" ezfname="ovrdVldRsltAvalFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effFromDt_A" ezfname="effFromDt_A">01/31/2016</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effToDt_A" ezfname="effToDt_A">01/31/2016</label>
												</td>
											</tr>
											<tr>
												<td>
													<input id="xxRadioBtn_H#{EZF_ROW_NUMBER}" type="radio" name="xxRadioBtn_H" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn_H">
												</td>
												<td>
													<select name="contrIntfcSrcTpCd_AS" class="pPro" style="width:265" ezfname="dsContrVldCatgCd_AS" ezflist="dsContrVldCatgCd_SC,dsContrVldCatgDescTxt_SC,99, ,blank" style="width:130;" ezfhyo="A"><option>Check Customer</option><option>Check Date</option><option>Check Fleet</option><option>Check FM</option><option>Check OPTIMA</option><option>Check Payment</option><option>Check Pricing</option><option>Check Start Read</option><option>Check Supplies</option><option>Check Vendor</option><option>Check Aggregate</option><option>Check Billing</option></select>
												</td>
												<td>
													<input id="dsContrVldNm_A#{EZF_ROW_NUMBER}" type="text" size="35" maxlength="60" class="pPro" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" name="dsContrVldNm_A" ezfname="dsContrVldNm_A" ezfhyo="A">
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlContrFlg_A" ezfname="vldLvlContrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlMachFlg_A" ezfname="vldLvlMachFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldAggrFlg_A" ezfname="vldAggrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldFleetFlg_A" ezfname="vldFleetFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldRegFlg_A" ezfname="vldRegFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldWtyFlg_A" ezfname="vldWtyFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="primVldFlg_A" ezfname="primVldFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="ovrdVldRsltAvalFlg_A" ezfname="ovrdVldRsltAvalFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effFromDt_A" ezfname="effFromDt_A">01/31/2016</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effToDt_A" ezfname="effToDt_A">01/31/2016</label>
												</td>
											</tr>
											<tr>
												<td>
													<input id="xxRadioBtn_H#{EZF_ROW_NUMBER}" type="radio" name="xxRadioBtn_H" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn_H">
												</td>
												<td>
													<select name="contrIntfcSrcTpCd_AS" class="pPro" style="width:265" ezfname="dsContrVldCatgCd_AS" ezflist="dsContrVldCatgCd_SC,dsContrVldCatgDescTxt_SC,99, ,blank" style="width:130;" ezfhyo="A"><option>Check Customer</option><option>Check Date</option><option>Check Fleet</option><option>Check FM</option><option>Check OPTIMA</option><option>Check Payment</option><option>Check Pricing</option><option>Check Start Read</option><option>Check Supplies</option><option>Check Vendor</option><option>Check Aggregate</option><option>Check Billing</option></select>
												</td>
												<td>
													<input id="dsContrVldNm_A#{EZF_ROW_NUMBER}" type="text" size="35" maxlength="60" class="pPro" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" name="dsContrVldNm_A" ezfname="dsContrVldNm_A" ezfhyo="A">
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlContrFlg_A" ezfname="vldLvlContrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlMachFlg_A" ezfname="vldLvlMachFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldAggrFlg_A" ezfname="vldAggrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldFleetFlg_A" ezfname="vldFleetFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldRegFlg_A" ezfname="vldRegFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldWtyFlg_A" ezfname="vldWtyFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="primVldFlg_A" ezfname="primVldFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="ovrdVldRsltAvalFlg_A" ezfname="ovrdVldRsltAvalFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effFromDt_A" ezfname="effFromDt_A">01/31/2016</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effToDt_A" ezfname="effToDt_A">01/31/2016</label>
												</td>
											</tr>
											<tr>
												<td>
													<input id="xxRadioBtn_H#{EZF_ROW_NUMBER}" type="radio" name="xxRadioBtn_H" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn_H">
												</td>
												<td>
													<select name="contrIntfcSrcTpCd_AS" class="pPro" style="width:265" ezfname="dsContrVldCatgCd_AS" ezflist="dsContrVldCatgCd_SC,dsContrVldCatgDescTxt_SC,99, ,blank" style="width:130;" ezfhyo="A"><option>Check Customer</option><option>Check Date</option><option>Check Fleet</option><option>Check FM</option><option>Check OPTIMA</option><option>Check Payment</option><option>Check Pricing</option><option>Check Start Read</option><option>Check Supplies</option><option>Check Vendor</option><option>Check Aggregate</option><option>Check Billing</option></select>
												</td>
												<td>
													<input id="dsContrVldNm_A#{EZF_ROW_NUMBER}" type="text" size="35" maxlength="60" class="pPro" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" name="dsContrVldNm_A" ezfname="dsContrVldNm_A" ezfhyo="A">
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlContrFlg_A" ezfname="vldLvlContrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldLvlMachFlg_A" ezfname="vldLvlMachFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldAggrFlg_A" ezfname="vldAggrFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldFleetFlg_A" ezfname="vldFleetFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldRegFlg_A" ezfname="vldRegFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="vldWtyFlg_A" ezfname="vldWtyFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="primVldFlg_A" ezfname="primVldFlg_A">Y</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="ovrdVldRsltAvalFlg_A" ezfname="ovrdVldRsltAvalFlg_A">N</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effFromDt_A" ezfname="effFromDt_A">01/31/2016</label>
												</td>
												<td>
													<label ezfout ezfhyo="A" name="effToDt_A" ezfname="effToDt_A">01/31/2016</label>
												</td>
											</tr>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchResultList End   ######################################## --%>
					<br>
					<%-- ######################################## InputArea Start ######################################## --%>
					<fieldset style="height:140;width:95%">
					<legend>New / Update</legend>
					<table border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="100">
									<col width="15">
									<col width="90">
									<col width="90">
									<col width="90">
									<col width="90">
									<col width="40">
									<col width="140">
									<col width="15">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<col width="30">
									<tr>
										<td class="stab">Category Name:</td>
										<td></td>
										<td colspan="4"><ezf:select name="dsContrVldCatgCd_DS" ezfName="dsContrVldCatgCd_DS" ezfBlank="1" ezfCodeName="dsContrVldCatgCd_SC" ezfDispName="dsContrVldCatgDescTxt_SC" otherAttr=" style=\"width:355\""/></td>
										<td></td>
										<td class="stab">Start Date:</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="effFromDt_D" ezfName="effFromDt_D" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D', 4);"></td>
										<td class="stab" colspan="2">End Date:</td>
										<td colspan="6"><ezf:inputText name="effToDt_D" ezfName="effToDt_D" value="12/31/2017" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_D', 4);"></td>
									</tr>
									<tr>
										<td class="stab">Validation Name:</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="dsContrVldNm_D" ezfName="dsContrVldNm_D" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" otherAttr=" size=\"50\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab"><ezf:anchor name="vldCmptTxt_DL" ezfName="vldCmptTxt_DL" ezfEmulateBtn="1" ezfGuard="OpenWin_ComponentDetail" >Component Name:</ezf:anchor></td>
										<td></td>
										<td colspan="12"><ezf:inputText name="vldCmptTxt_D" ezfName="vldCmptTxt_D" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
									</tr>
									<tr>
										<td class="stab"">Description:</td>
										<td></td>
										<td colspan="4"><ezf:inputText name="dsContrVldDescTxt_D" ezfName="dsContrVldDescTxt_D" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0xxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"50\" maxlength=\"120\""/></td>
										<td></td>
										<td class="stab">Primary Validation:</td>
										<td></td>
										<td colspan="12"><ezf:inputCheckBox name="primVldFlg_D" ezfName="primVldFlg_D" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Level:</td>
										<td></td>
										<td colspan="2"><ezf:inputCheckBox name="vldLvlContrFlg_D" ezfName="vldLvlContrFlg_D" value="Y" />Contract</td>
										<td colspan="2"><ezf:inputCheckBox name="vldLvlMachFlg_D" ezfName="vldLvlMachFlg_D" value="Y" />Machine</td>
										<td></td>
										<td class="stab">Validation Result Override:</td>
										<td></td>
										<td colspan="12"><ezf:inputCheckBox name="ovrdVldRsltAvalFlg_D" ezfName="ovrdVldRsltAvalFlg_D" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Contract Type:</td>
										<td></td>
										<td><ezf:inputCheckBox name="vldAggrFlg_D" ezfName="vldAggrFlg_D" value="Y" />Aggregate</td>
										<td><ezf:inputCheckBox name="vldFleetFlg_D" ezfName="vldFleetFlg_D" value="Y" />Fleet</td>
										<td><ezf:inputCheckBox name="vldRegFlg_D" ezfName="vldRegFlg_D" value="Y" />Non-Fleet</td>
										<td><ezf:inputCheckBox name="vldWtyFlg_D" ezfName="vldWtyFlg_D" value="Y" />Warranty</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</fieldset>
					<%-- ######################################## InputArea End   ######################################## --%>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
