<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190122180950 --%>
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
			<input type="hidden" name="pageID" value="NSAL0450Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Revenue Distributions">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
<!-- #################################################### HEADER [START] ################################################### -->
		<tr>
		<td>
		<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
		<div class="pTab_BODY">
			<table style="margin-top: 10px; margin-left: 5px" border="0">
			<colgroup>
			<col align="middle" width="5px">
			<col align="middle" width="100px">
			<col align="left" width="500px">
			</colgroup>
			<tr>
				<td></td>
				<td class="stab">Default Account</td>
				<td class="stab"><ezf:inputText name="coaAfflAcctNm_H" ezfName="coaAfflAcctNm_H" value="00000000010000000002000000000300000000040000000005" otherAttr=" maxlength=\"50\" size=\"50\" ezftoupper=\"\""/></td>
			</tr>
			</table>
<!-- #################################################### HEADER [E N D] ################################################### -->
		<hr/>
		<table width="1060px" border="0" cellpadding="1" cellspacing="0">
			<tr>
				<td align="right">
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
					<table border="0" cellpadding="0" width="100%">
						<tr align="right">
							<td>
								<ezf:skip>
										<table border="0" cellpadding="1" cellspacing="0">
											<col >
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col width="0">
											<col width="1">
											<col width="0">
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">3</td>
												<td class="stab">of</td>
												<td class="pOut">1000</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
											</tr>
										</table>
								</ezf:skip>

								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
						</tr>
			 		</table>
			 		
					<td>
				</tr>
			</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
<!-- ######################################## DETAIL ################################################### -->
<%@ page import="business.servlet.NSAL0450.NSAL0450BMsg" %>
<%@ page import="business.servlet.NSAL0450.NSAL0450_ABMsg" %>
<%  NSAL0450BMsg bMsg = (NSAL0450BMsg)databean.getEZDBMsg(); %>
		<table border="0" width="100%">
			<tr>
				<td>
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
						<col width=" 20" align="center"><!-- icon -->
						<col width="70" align="center"><!-- Contract# -->
						<col width="220" align="center"><!-- Serial# -->
						<col width="150" align="center"><!-- Service Program -->
						<col width="90" align="center"><!-- Base/Overage -->
						<col width="60" align="center"><!-- Default Branch -->
						<col width="40" align="center"><!-- Split % -->
						<col width="360" align="center"><!-- Default Account -->
						<col width="40" align="center"><!-- Split % -->
						<tr>
							<td class="pClothBs" rowspan="2">&nbsp;</td>
							<td class="pClothBs" rowspan="2">Contract#</td>
							<td class="pClothBs" rowspan="2">Serial#</td>
							<td class="pClothBs" rowspan="2">Service Program</td>
							<td class="pClothBs" rowspan="2">Base/Overage</td>
							<td class="pClothBs" colspan="2">Sales Credit</td>
							<td class="pClothBs" colspan="2">Revenue Distribution</td>
						</tr>
						<tr>
							<td class="pClothBs">Default Branch</td>
							<td class="pClothBs">Split %</td>
							<td class="pClothBs">Default Account</td>
							<td class="pClothBs">Split %</td>
						</tr>
					</table>
					<div style="width:1000; height:425; display:block; overflow-x:none; overflow-y:scroll;">
						<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
							<col width=" 20" align="center"><!-- icon -->
							<col width="70" align="center"><!-- Contract# -->
							<col width="220" align="center"><!-- Serial# -->
							<col width="150" align="center"><!-- Service Program -->
							<col width="90" align="center"><!-- Base/Overage -->
							<col width="60" align="center"><!-- Default Branch -->
							<col width="40" align="center"><!-- Split% -->
							<col width="360" align="center"><!-- Default Account -->
							<col width="40" align="center"><!-- Split% -->
							<% int i = 0; %>
							<ezf:row ezfHyo="A">
							<% NSAL0450_ABMsg abMsg = bMsg.A.no(i++); %>
							<tr height="25">
									<td>
										<% boolean isDplyCtrlFlg = "1".equals(abMsg.xxDplyCtrlFlg.getValue()); %>
										<% if(!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
											<% boolean isSmryLineFlg = "1".equals(abMsg.xxSmryLineFlg.getValue()); %>
											<a id="xxSmryLineFlg#{EZF_ROW_NUMBER}" href="#" onclick="clickImg(this, '{EZF_ROW_NUMBER}'); return false;">
												<% if(isSmryLineFlg)  out.println("<img src=\"./img/biz/rightarrow2.png\" border=\"0\" value=\"Expansion\">"); %>
												<% if(!isSmryLineFlg) out.println("<img src=\"./img/biz/downarrow2.png\" border=\"0\" value=\"Contraction\">"); %>
											</a>
										<% if(!isDplyCtrlFlg) out.println("</span>"); %>
									</td>
									<td><ezf:label name="dsContrNum" ezfName="dsContrNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="serNum" ezfName="serNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="RENTAL SUPPLY INCLUSIVE SERVIC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
									<td><ezf:label name="invConslNum" ezfName="invConslNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="coaBrCd_L" ezfName="coaBrCd_L" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:anchor name="baseChrgFlg" ezfName="baseChrgFlg" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_SlsCrSpl" otherAttr=" id=\"baseChrgFlg#{EZF_ROW_NUMBER}\"">
										<% boolean isBaseChrgFlg = "1".equals(abMsg.baseChrgFlg.getValue()); %>
										<% if(isBaseChrgFlg)  out.println("<img src=\"./img/biz/tick.png\" border=\"0\">"); %>
										<% if(!isBaseChrgFlg) out.println("<img src=\"./img/biz/cross.png\" border=\"0\">"); %>
										</ezf:anchor>
									</td>
									<td><ezf:label name="coaAfflAcctNm" ezfName="coaAfflAcctNm" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:anchor name="usgChrgFlg" ezfName="usgChrgFlg" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_RevDistSpl" otherAttr=" id=\"usgChrgFlg#{EZF_ROW_NUMBER}\"">
										<% boolean isUsgChrgFlg = "1".equals(abMsg.usgChrgFlg.getValue()); %>
										<% if(isUsgChrgFlg)  out.println("<img src=\"./img/biz/tick.png\" border=\"0\">"); %>
										<% if(!isUsgChrgFlg) out.println("<img src=\"./img/biz/cross.png\" border=\"0\">"); %>
										</ezf:anchor>
									</td>
								</tr>
							</ezf:row>
							<ezf:skip>
								<tr height="25">
									<td>&nbsp;</td>
									<td><label ezfout name="dsContrNum" ezfname="dsContrNum" ezfhyo="A">000000000100000000020000000003</label></td>
									<td><label ezfout name="serNum" ezfname="serNum" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
									<td><input type="text" size="20" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC" name="mdseDescShortTxt" ezfname="mdseDescShortTxt" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
									<td><label ezfout name="invConslNum" ezfname="invConslNum" ezfhyo="A">XXXXXXXXX1</label></td>
									<td><label ezfout name="coaBrCd_L" ezfname="coaBrCd_L" ezfhyo="A">XXX</label></td>
									<td><a href="#" onclick="sendServer('Open_SlsCrSpl')" id="baseChrgFlg#{EZF_ROW_NUMBER}" name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><label ezfout name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><font color="blue"><b>%</b></font></label></a></td>
									<td><label ezfout name="coaAfflAcctNm" ezfname="coaAfflAcctNm" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
									<td><a href="#" onclick="sendServer('Open_RevDistSpl')" id="usgChrgFlg#{EZF_ROW_NUMBER}" name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><label ezfout name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><font color="Red"><b>%</b></font></label></a></td>
								</tr>
								<tr height="25">
									<td>&nbsp;</td>
									<td><label ezfout name="dsContrNum" ezfname="dsContrNum" ezfhyo="A">000000000100000000020000000003</label></td>
									<td><label ezfout name="serNum" ezfname="serNum" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
									<td><input type="text" size="20" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC" name="mdseDescShortTxt" ezfname="mdseDescShortTxt" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
									<td><label ezfout name="invConslNum" ezfname="invConslNum" ezfhyo="A">XXXXXXXXX1</label></td>
									<td><label ezfout name="coaBrCd_L" ezfname="coaBrCd_L" ezfhyo="A">XXX</label></td>
									<td><a href="#" onclick="sendServer('Open_SlsCrSpl')" id="baseChrgFlg#{EZF_ROW_NUMBER}" name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><label ezfout name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><font color="blue"><b>%</b></font></label></a></td>
									<td><label ezfout name="coaAfflAcctNm" ezfname="coaAfflAcctNm" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
									<td><a href="#" onclick="sendServer('Open_RevDistSpl')" id="usgChrgFlg#{EZF_ROW_NUMBER}" name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><label ezfout name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><font color="Red"><b>%</b></font></label></a></td>
								</tr>
								<tr height="25">
									<td>&nbsp;</td>
									<td><label ezfout name="dsContrNum" ezfname="dsContrNum" ezfhyo="A">000000000100000000020000000003</label></td>
									<td><label ezfout name="serNum" ezfname="serNum" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
									<td><input type="text" size="20" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC" name="mdseDescShortTxt" ezfname="mdseDescShortTxt" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
									<td><label ezfout name="invConslNum" ezfname="invConslNum" ezfhyo="A">XXXXXXXXX1</label></td>
									<td><label ezfout name="coaBrCd_L" ezfname="coaBrCd_L" ezfhyo="A">XXX</label></td>
									<td><a href="#" onclick="sendServer('Open_SlsCrSpl')" id="baseChrgFlg#{EZF_ROW_NUMBER}" name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><label ezfout name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><font color="blue"><b>%</b></font></label></a></td>
									<td><label ezfout name="coaAfflAcctNm" ezfname="coaAfflAcctNm" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
									<td><a href="#" onclick="sendServer('Open_RevDistSpl')" id="usgChrgFlg#{EZF_ROW_NUMBER}" name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><label ezfout name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><font color="Red"><b>%</b></font></label></a></td>
								</tr>
								<tr height="25">
									<td>&nbsp;</td>
									<td><label ezfout name="dsContrNum" ezfname="dsContrNum" ezfhyo="A">000000000100000000020000000003</label></td>
									<td><label ezfout name="serNum" ezfname="serNum" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
									<td><input type="text" size="20" maxlength="250" value="RENTAL SUPPLY INCLUSIVE SERVIC" name="mdseDescShortTxt" ezfname="mdseDescShortTxt" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
									<td><label ezfout name="invConslNum" ezfname="invConslNum" ezfhyo="A">XXXXXXXXX1</label></td>
									<td><label ezfout name="coaBrCd_L" ezfname="coaBrCd_L" ezfhyo="A">XXX</label></td>
									<td><a href="#" onclick="sendServer('Open_SlsCrSpl')" id="baseChrgFlg#{EZF_ROW_NUMBER}" name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><label ezfout name="baseChrgFlg" ezfname="baseChrgFlg" ezfhyo="A"><font color="blue"><b>%</b></font></label></a></td>
									<td><label ezfout name="coaAfflAcctNm" ezfname="coaAfflAcctNm" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
									<td><a href="#" onclick="sendServer('Open_RevDistSpl')" id="usgChrgFlg#{EZF_ROW_NUMBER}" name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><label ezfout name="usgChrgFlg" ezfname="usgChrgFlg" ezfhyo="A"><font color="Red"><b>%</b></font></label></a></td>
								</tr>
							</ezf:skip>
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

<script type="text/javascript">
	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>

<%-- **** Task specific area ends here **** --%>
