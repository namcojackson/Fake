<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160721161120 --%>
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
			<input type="hidden" name="pageID" value="NSAL1340Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Band List Popup">
<center>
	<table>
		<tr>
			<td align="center">
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" width="990">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" width="990">
								<col width="750" align="left">
								<col width="250" align="left">
								<tr>
									<td>
										<table border="1" cellpadding="0" cellspacing="0" width="750">
											<col width="300" align="left">
											<col width="150" align="left">
											<col width="150" align="left">
											<col width="150" align="left">
											<tr>
												<td class="pClothBs">Price List</td>
												<td class="pClothBs">Model</td>
												<td class="pClothBs">Package Name</td>
												<td class="pClothBs">Billing Counter Name</td>
												<td></td>
											</tr>
											<tr>
												<td align="center"><ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="PUBLISHED PRICE BOOK JAN 2013" otherAttr=" size=\"41\" maxlength=\"75\""/></td>
												<td align="center"><ezf:inputText name="mdlNm" ezfName="mdlNm" value="IRADV4251" otherAttr=" size=\"19\" maxlength=\"50\""/></td>
												<td align="center"><ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" value="SERVICE PACKAGE1" otherAttr=" size=\"19\" maxlength=\"50\""/></td>
												<td align="center"><ezf:inputText name="mtrLbNm" ezfName="mtrLbNm" value="BW Billing Counter" otherAttr=" size=\"19\" maxlength=\"50\""/></td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" width="990">
					<tr>
						<td>
							<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:730; float:left;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" height="18">
									<col align="left" width="30">
									<col align="left" width="50">
									<col align="left" width="100">
									<col align="left" width="100">
									<col align="left" width="100">
									<col align="left" width="150">
									<col align="left" width="100">
									<col align="left" width="100">
									<tr>
										<td class="pClothBs">&nbsp</td>
										<td class="pClothBs">Band</td>
										<td class="pClothBs">Price Book Item</td>
										<td class="pClothBs">Min Vol</td>
										<td class="pClothBs">Max Vol</td>
										<td class="pClothBs">Tire Type</td>
										<td class="pClothBs">Overage Rate</td>
										<td class="pClothBs">Base Price</td>
									</tr>
								</table>
							</div>
							<div id="LeftTable_A" style="overflow-y:scroll; height:440; overflow-x:hidden; width:750; float:left;">
								<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
									<col align="center" width="30">
									<col align="center" width="50">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="150">
									<col align="center" width="100">
									<col align="center" width="100">
									<ezf:row ezfHyo="A">
										<tr id="id_leftA_row">
											<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn\""/></td>
											<td><ezf:inputText name="prcListBandDescTxt" ezfName="prcListBandDescTxt" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="153ZZ800" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="minCopyVolCnt" ezfName="minCopyVolCnt" value="0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
											<td><ezf:inputText name="maxCopyVolCnt" ezfName="maxCopyVolCnt" value="999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
											<td><ezf:inputText name="prcSvcTierTpNm" ezfName="prcSvcTierTpNm" value="Tier1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cpcAmtRate" ezfName="cpcAmtRate" value="0.0126" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
											<td><ezf:inputText name="totBaseAmt" ezfName="totBaseAmt" value="50.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align: right; \""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td><input type="radio" name="" ezfname="" value="0" ezfHyo="A" ezfArrayNo="0" ></td>
											<td><input type="text" name="" value="LVL" size="3" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="153ZZ801" size="12" maxlength="12" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="999,999" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="Tier1" size="19" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0.0085" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="75.00" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
										</tr>
										<tr>
											<td><input type="radio" name="" ezfname="" value="0" ezfHyo="A" ezfArrayNo="0" ></td>
											<td><input type="text" name="" value="C" size="3" maxlength="3" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="153ZZ802" size="12" maxlength="12" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="3,000" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="7,999" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="Tier2" size="19" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0.0109" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="100.00" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
										</tr>
										<tr>
											<td><input type="radio" name="" ezfname="" value="0" ezfHyo="A" ezfArrayNo="0" ></td>
											<td><input type="text" name="" value="D" size="3" maxlength="3" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="153ZZ803" size="12" maxlength="12" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="8,000" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="14,999" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="Tier3" size="19" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0.0098" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="125.00" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
										</tr>
										<tr>
											<td><input type="radio" name="" ezfname="" value="0" ezfHyo="A" ezfArrayNo="0" ></td>
											<td><input type="text" name="" value="E" size="3" maxlength="3" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="153ZZ804" size="12" maxlength="12" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="15,000" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="19,999" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="Tier1" size="19" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0.0089" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="150.00" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
										</tr>
										<tr>
											<td><input type="radio" name="" ezfname="" value="0" ezfHyo="A" ezfArrayNo="0" ></td>
											<td><input type="text" name="" value="F" size="3" maxlength="3" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="153ZZ805" size="12" maxlength="12" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="20,000" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="999,999" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="Tier1" size="19" maxlength="50" ezftoupper="" ezfname="" class="pPro" readOnly></td>
											<td><input type="text" name="" value="0.0080" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
											<td><input type="text" name="" value="175.00" size="12" maxlength="12" ezftoupper="" ezfname="" style="text-align: right; " class="pPro" readOnly></td>
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
