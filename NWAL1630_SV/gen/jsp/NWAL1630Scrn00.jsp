<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190124141005 --%>
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
			<input type="hidden" name="pageID" value="NWAL1630Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Additional Line Detail">

			<center>
				<div class="pTab_BODY">
					<table style="width:500px;">
						<tr>
							<td>
								<fieldset>
									<legend style="font-size:12px;">CSMP</legend>
									<table style="table-layout:fixed;">
										<col width="120">
										<col width="80">
										<col width="20">
										<col width="100">
										<col width="150">
										<tr>
											<td class="stab">Order Number</td>
											<td class="pOut"><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" otherAttr=" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td class="stab">CSMP #</td>
											<td><ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="ABCDS-1234" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td class="stab">Configuration Number#</td>
											<td class="pOut"><ezf:label name="dsOrdPosnNum" ezfName="dsOrdPosnNum" otherAttr=" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td class="stab">CSA DEALER REF#</td>
											<td><ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="ABCDS-1234" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td class="stab">Line Number</td>
											<td class="pOut"><ezf:label name="xxDtlLineNum" ezfName="xxDtlLineNum" /></td>
											<td>&nbsp</td>
											<td class="stab">CSMP Price List</td>
											<td><ezf:inputText name="csmpPrcListCd" ezfName="csmpPrcListCd" value="ABCDS-1234" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/><!-- input type="button" class="pBtn0" value="Price" name="search_PriceList" onclick="sendServer(this)" --></td>
										</tr>
									</table>
								</fieldset>
								<fieldset>
									<legend style="font-size:12px;">Contract Supplies</legend>
									<table style="table-layout:fixed;">
										<col width="120">
										<col width="80">
										<col width="20">
										<col width="100">
										<col width="150">
										<tr>
											<td class="stab">Supply Enforcement</td>
											<td class="pOut"><ezf:label name="splyAbuseNodePrflCd" ezfName="splyAbuseNodePrflCd" otherAttr=" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td class="stab">Capped Supplies</td>
											<td class="pOut"><ezf:label name="splyCdTxt" ezfName="splyCdTxt" otherAttr=" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SerNum" >Serial#</ezf:anchor></td>
											<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherEvent1="OnBlur_SerNum" otherAttr=" size=\"30\" maxlength=\"30\" ezffocusout=\"OnBlur_SerNum\""/></td>
											<td>&nbsp</td>
											<td class="stab">B/W Cap</td>
											<td class="pOut"><ezf:label name="annTermCapNum_BW" ezfName="annTermCapNum_BW" otherAttr=" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Contract" >Contract#</ezf:anchor></td>
											<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherEvent1="OnBlur_ContrNum" otherAttr=" size=\"30\" maxlength=\"30\" ezffocusout=\"OnBlur_ContrNum\""/></td>
											<td>&nbsp</td>
											<td class="stab">Color Cap</td>
											<td class="pOut"><ezf:label name="annTermCapNum_CL" ezfName="annTermCapNum_CL" otherAttr=" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<!--<td class="stab">Fleet#</td>
											<td class="pOut"><label ezfout name="dlrFleetNum" ezfname="dlrFleetNum" ezftoupper>1770501</label></td>-->
											<td colspan="3"></td>
											<td class="stab">Total Cap</td>
											<td class="pOut"><ezf:label name="annTermCapNum_TT" ezfName="annTermCapNum_TT" otherAttr=" ezftoupper=\"\""/></td>
										</tr>
									</table>
								</fieldset>
								<!--<fieldset> 2019/01/22 S21_NA#29446 Del
									<legend style="font-size:12px;">Rental</legend>
									<table style="table-layout:fixed;">
										<col width="120">
										<col width="200">
										<col width="150">
										<tr>
											<td class="stab">Rental Termination Date</td>
											<td><input type="text" class="pHsu" size="11" maxlength="10" value="MM/DD/YYYY" name="rntlTrmnDt" ezfname="rntlTrmnDt" ezftoupper>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('rntlTrmnDt', 4);"></td>
										</tr>
									</table>
								</fieldset>-->
							</td>
						</tr>
					</table>
				</div>
			</center>


<%-- **** Task specific area ends here **** --%>
