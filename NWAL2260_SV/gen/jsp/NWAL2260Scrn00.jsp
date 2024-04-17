<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181130085505 --%>
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
			<input type="hidden" name="pageID" value="NWAL2260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Import Attribute Maintenance">


<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Import Attribute Maintenance" class="pTab_ON"><a href="#">Counter Setup</a></li>
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
				<div class="pTab_BODY">
				<%-- ######################################## HEADER ######################################## --%>
				<br/>
				<br/>
				<table border="0" cellpadding="1" cellspacing="0" align="left">
					<tr>
						<td valign="top">
							<fieldset>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="140">
									<col width="150">
									<tr>
										<td class="stab">Source Reference Number</td>
										<td class="stab"><ezf:inputText name="ordSrcRefNum_H" ezfName="ordSrcRefNum_H" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"20\""/></td>
									</tr>
									<tr>
										<td class="stab">Ref Line Number</td>
										<td class="stab"><ezf:inputText name="ordSrcRefLineNum_H" ezfName="ordSrcRefLineNum_H" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"20\""/></td>
									</tr>
									<tr>
										<td class="stab">Ref Line Sub Number</td>
										<td class="stab"><ezf:inputText name="ordSrcRefLineSubNum_H" ezfName="ordSrcRefLineSubNum_H" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"20\""/></td>
									</tr>
								</table>
						</fieldset>
						</td>
						<td>&nbsp;</td>
						<td valign="top">
							<fieldset>
								<table border="0" cellpadding="1" cellspacing="1">
									<col width="95">
									<col width="70">
									<tr>
										<td class="stab">Order Number</td>
										<td class="stab"><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="00000001" otherAttr=" size=\"9\""/></td>
									</tr>
									<tr>
										<td class="stab">Order Line Number</td>
										<td class="stab"><ezf:inputText name="xxRefNumTxt" ezfName="xxRefNumTxt" value="1.1" otherAttr=" size=\"9\""/></td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>&nbsp;</td>
						<td valign="top">
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="90">
								<col width="250">
								<tr>
									<td class="stab">Trading Partner</td>
									<td class="stab"><ezf:inputText name="ediTrdPtnrNm" ezfName="ediTrdPtnrNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"35\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NWAL2260.NWAL2260BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%  NWAL2260BMsg bMsg = (NWAL2260BMsg) databean.getEZDBMsg(); %>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td>
				<br/>
				<table border="0" cellpadding="1" cellspacing="0">
					<tr>
<%-- ########## Header Mode --%>
<% if (ZYPConstant.FLG_ON_1.equals(bMsg.xxEdtModeFlg.getValue())) { %>
						<td align="center" valign="top">
							<table border="1" cellpadding="1" cellspacing="0" width="100%" align="left" id="A">
								<col width="250">
								<col width="290">
								<tr align="center">
									<td class="pClothBs" align="center">Name</td>
									<td class="pClothBs" align="center">Value</td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_01" ezfName="scrLbNm_01" value="Import Attribute Text 01" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_01" ezfName="dsImptAttrbTxt_01" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_02" ezfName="scrLbNm_02" value="Import Attribute Text 02" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_02" ezfName="dsImptAttrbTxt_02" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_03" ezfName="scrLbNm_03" value="Import Attribute Text 03" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_03" ezfName="dsImptAttrbTxt_03" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_04" ezfName="scrLbNm_04" value="Import Attribute Text 04" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_04" ezfName="dsImptAttrbTxt_04" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_05" ezfName="scrLbNm_05" value="Import Attribute Text 05" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_05" ezfName="dsImptAttrbTxt_05" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_06" ezfName="scrLbNm_06" value="Import Attribute Text 06" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_06" ezfName="dsImptAttrbTxt_06" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_07" ezfName="scrLbNm_07" value="Import Attribute Text 07" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_07" ezfName="dsImptAttrbTxt_07" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_08" ezfName="scrLbNm_08" value="Import Attribute Text 08" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_08" ezfName="dsImptAttrbTxt_08" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_09" ezfName="scrLbNm_09" value="Import Attribute Text 09" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_09" ezfName="dsImptAttrbTxt_09" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_10" ezfName="scrLbNm_10" value="Import Attribute Text 10" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_10" ezfName="dsImptAttrbTxt_10" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_11" ezfName="scrLbNm_11" value="Import Attribute Text 11" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_11" ezfName="dsImptAttrbTxt_11" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_12" ezfName="scrLbNm_12" value="Import Attribute Text 12" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_12" ezfName="dsImptAttrbTxt_12" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_13" ezfName="scrLbNm_13" value="Import Attribute Text 13" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_13" ezfName="dsImptAttrbTxt_13" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_14" ezfName="scrLbNm_14" value="Import Attribute Text 14" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_14" ezfName="dsImptAttrbTxt_14" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_15" ezfName="scrLbNm_15" value="Import Attribute Text 15" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptAttrbTxt_15" ezfName="dsImptAttrbTxt_15" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td>
							<%-- ########## TOP ########## --%>
							<div id="top" style="overflow-y:hidden; height:; overflow-x:hidden; width:550; text-align:center;">
								<table border="1" cellpadding="1" cellspacing="0" align="left">
									<col width="280">
									<col width="260">
									<tr align="center">
										<td class="pClothBs" align="center">Name</td>
										<td class="pClothBs" align="center">Value</td>
									</tr>
								</table>
							</div>
							<%-- ########## DETAIL ########## --%>
							<div id="Detail" style="overflow-y:scroll; height:420; overflow-x:hidden; width:567;">
								<table border="1" cellpadding="1" cellspacing="0" align="left" id="B">
									<col width="280">
									<col width="260">
									<tr>
										<td>IDOC PO Document Number</td>
										<td><ezf:inputText name="idocPoDocNum" ezfName="idocPoDocNum" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Organization Value Text 01</td>
										<td><ezf:inputText name="idocPoOrgValTxt_01" ezfName="idocPoOrgValTxt_01" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Organization Value Text 02</td>
										<td><ezf:inputText name="idocPoOrgValTxt_02" ezfName="idocPoOrgValTxt_02" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Order Reason Code</td>
										<td><ezf:inputText name="idocPoOrdRsnCd" ezfName="idocPoOrdRsnCd" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Customer Reference Number</td>
										<td><ezf:inputText name="idocPoCustRefNum" ezfName="idocPoCustRefNum" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Customer Reference Date</td>
										<td><ezf:inputText name="idocPoCustRefDt" ezfName="idocPoCustRefDt" value="03/23/2016" otherAttr=" size=\"11\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('idocPoCustRefDt', 4);"></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 01</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_01" ezfName="idocPoPtnrTpCd_01" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 02</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_02" ezfName="idocPoPtnrTpCd_02" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 03</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_03" ezfName="idocPoPtnrTpCd_03" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 04</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_04" ezfName="idocPoPtnrTpCd_04" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 05</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_05" ezfName="idocPoPtnrTpCd_05" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Type Code 06</td>
										<td><ezf:inputText name="idocPoPtnrTpCd_06" ezfName="idocPoPtnrTpCd_06" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 01</td>
										<td><ezf:inputText name="idocPoPtnrNum_01" ezfName="idocPoPtnrNum_01" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 02</td>
										<td><ezf:inputText name="idocPoPtnrNum_02" ezfName="idocPoPtnrNum_02" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 03</td>
										<td><ezf:inputText name="idocPoPtnrNum_03" ezfName="idocPoPtnrNum_03" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 04</td>
										<td><ezf:inputText name="idocPoPtnrNum_04" ezfName="idocPoPtnrNum_04" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 05</td>
										<td><ezf:inputText name="idocPoPtnrNum_05" ezfName="idocPoPtnrNum_05" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner Number 06</td>
										<td><ezf:inputText name="idocPoPtnrNum_06" ezfName="idocPoPtnrNum_06" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 01</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_01" ezfName="idocPtnrCustRefTxt_01" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 02</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_02" ezfName="idocPtnrCustRefTxt_02" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 03</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_03" ezfName="idocPtnrCustRefTxt_03" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 04</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_04" ezfName="idocPtnrCustRefTxt_04" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 05</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_05" ezfName="idocPtnrCustRefTxt_05" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Customer Reference Text 06</td>
										<td><ezf:inputText name="idocPtnrCustRefTxt_06" ezfName="idocPtnrCustRefTxt_06" value="012345678901234567890123456789" otherAttr=" size=\"31\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 01</td>
										<td><ezf:inputText name="idocPoPtnrId_01" ezfName="idocPoPtnrId_01" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 02</td>
										<td><ezf:inputText name="idocPoPtnrId_02" ezfName="idocPoPtnrId_02" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 03</td>
										<td><ezf:inputText name="idocPoPtnrId_03" ezfName="idocPoPtnrId_03" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 04</td>
										<td><ezf:inputText name="idocPoPtnrId_04" ezfName="idocPoPtnrId_04" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 05</td>
										<td><ezf:inputText name="idocPoPtnrId_05" ezfName="idocPoPtnrId_05" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Partner ID 06</td>
										<td><ezf:inputText name="idocPoPtnrId_06" ezfName="idocPoPtnrId_06" value="01234567890123456" otherAttr=" size=\"18\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 01</td>
										<td><ezf:inputText name="idocPtnrCtacNm_01" ezfName="idocPtnrCtacNm_01" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 02</td>
										<td><ezf:inputText name="idocPtnrCtacNm_02" ezfName="idocPtnrCtacNm_02" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 03</td>
										<td><ezf:inputText name="idocPtnrCtacNm_03" ezfName="idocPtnrCtacNm_03" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 04</td>
										<td><ezf:inputText name="idocPtnrCtacNm_04" ezfName="idocPtnrCtacNm_04" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 05</td>
										<td><ezf:inputText name="idocPtnrCtacNm_05" ezfName="idocPtnrCtacNm_05" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Contact Name 06</td>
										<td><ezf:inputText name="idocPtnrCtacNm_06" ezfName="idocPtnrCtacNm_06" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 01</td>
										<td><ezf:inputText name="idocPtnrTelNum_01" ezfName="idocPtnrTelNum_01" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 02</td>
										<td><ezf:inputText name="idocPtnrTelNum_02" ezfName="idocPtnrTelNum_02" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 03</td>
										<td><ezf:inputText name="idocPtnrTelNum_03" ezfName="idocPtnrTelNum_03" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 04</td>
										<td><ezf:inputText name="idocPtnrTelNum_04" ezfName="idocPtnrTelNum_04" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 05</td>
										<td><ezf:inputText name="idocPtnrTelNum_05" ezfName="idocPtnrTelNum_05" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Telephone Number 06</td>
										<td><ezf:inputText name="idocPtnrTelNum_06" ezfName="idocPtnrTelNum_06" value="0123456789012345678901234" otherAttr=" size=\"26\""/></td>
									</tr>
									<tr>
										<td>IDOC First Line Address</td>
										<td><ezf:inputText name="idocFirstLineAddr" ezfName="idocFirstLineAddr" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Second Line Address</td>
										<td><ezf:inputText name="idocScdLineAddr" ezfName="idocScdLineAddr" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner City Name</td>
										<td><ezf:inputText name="idocPtnrCtyNm" ezfName="idocPtnrCtyNm" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner County Name</td>
										<td><ezf:inputText name="idocPtnrCntyNm" ezfName="idocPtnrCntyNm" value="01234567890123456789012345678901234" otherAttr=" size=\"30\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner State Code</td>
										<td><ezf:inputText name="idocPtnrStCd" ezfName="idocPtnrStCd" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Postal Code</td>
										<td><ezf:inputText name="idocPtnrPostCd" ezfName="idocPtnrPostCd" value="012345678" otherAttr=" size=\"10\""/><ezf:inputButton name="OpenWin_Address" value="GetAddress" htmlClass="pBtn6" otherAttr=" style=\"margin-left:100px;\""/></td>
									</tr>
									<tr>
										<td>IDOC Partner Country Code</td>
										<td><ezf:inputText name="idocPtnrCtryCd" ezfName="idocPtnrCtryCd" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Date Value Text</td>
										<td><ezf:inputText name="idocPoDtValTxt" ezfName="idocPoDtValTxt" value="01234567" otherAttr=" size=\"9\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Delivery Condition Code</td>
										<td><ezf:inputText name="idocPoDelyCondCd" ezfName="idocPoDelyCondCd" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Delivery Condition Name</td>
										<td><ezf:inputText name="idocPoDelyCondNm" ezfName="idocPoDelyCondNm" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Note Text</td>
										<td><ezf:inputText name="idocPoNoteTxt" ezfName="idocPoNoteTxt" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Recipient Partner Number</td>
										<td><ezf:inputText name="idocPoRcpntPtnrNum" ezfName="idocPoRcpntPtnrNum" value="0123456789" otherAttr=" size=\"11\""/></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
<% } else { %>
<%-- ########## Detail Mode --%>
					<tr>
						<td align="center" valign="top">
							<table border="1" cellpadding="1" cellspacing="0" width="100%" align="left" id="C">
								<col width="250">
								<col width="290">
								<tr align="center">
									<td class="pClothBs" align="center">Name</td>
									<td class="pClothBs" align="center">Value</td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_01" ezfName="scrLbNm_01" value="Import Detail Attribute Text 01" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_01" ezfName="dsImptDtlAttrbTxt_01" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_02" ezfName="scrLbNm_02" value="Import Detail Attribute Text 02" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_02" ezfName="dsImptDtlAttrbTxt_02" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_03" ezfName="scrLbNm_03" value="Import Detail Attribute Text 03" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_03" ezfName="dsImptDtlAttrbTxt_03" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_04" ezfName="scrLbNm_04" value="Import Detail Attribute Text 04" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_04" ezfName="dsImptDtlAttrbTxt_04" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_05" ezfName="scrLbNm_05" value="Import Detail Attribute Text 05" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_05" ezfName="dsImptDtlAttrbTxt_05" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_06" ezfName="scrLbNm_06" value="Import Detail Attribute Text 06" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_06" ezfName="dsImptDtlAttrbTxt_06" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_07" ezfName="scrLbNm_07" value="Import Detail Attribute Text 07" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_07" ezfName="dsImptDtlAttrbTxt_07" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_08" ezfName="scrLbNm_08" value="Import Detail Attribute Text 08" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_08" ezfName="dsImptDtlAttrbTxt_08" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_09" ezfName="scrLbNm_09" value="Import Detail Attribute Text 09" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_09" ezfName="dsImptDtlAttrbTxt_09" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_10" ezfName="scrLbNm_10" value="Import Detail Attribute Text 10" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_10" ezfName="dsImptDtlAttrbTxt_10" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_11" ezfName="scrLbNm_11" value="Import Detail Attribute Text 11" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_11" ezfName="dsImptDtlAttrbTxt_11" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_12" ezfName="scrLbNm_12" value="Import Detail Attribute Text 12" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_12" ezfName="dsImptDtlAttrbTxt_12" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_13" ezfName="scrLbNm_13" value="Import Detail Attribute Text 13" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_13" ezfName="dsImptDtlAttrbTxt_13" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_14" ezfName="scrLbNm_14" value="Import Detail Attribute Text 14" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_14" ezfName="dsImptDtlAttrbTxt_14" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
								<tr>
									<td><ezf:inputText name="scrLbNm_15" ezfName="scrLbNm_15" value="Import Detail Attribute Text 15" otherAttr=" size=\"35\""/></td>
									<td><ezf:inputText name="dsImptDtlAttrbTxt_15" ezfName="dsImptDtlAttrbTxt_15" value="01234567890123456789012345678901234567890123456789" otherAttr=" size=\"40\""/></td>
								</tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td>
							<%-- ########## TOP ########## --%>
							<div id="top" style="overflow-y:hidden; height:; overflow-x:hidden; width:550; text-align:center;">
								<table border="1" cellpadding="1" cellspacing="0" align="left">
									<col width="280">
									<col width="260">
									<tr align="center">
										<td class="pClothBs" align="center">Name</td>
										<td class="pClothBs" align="center">Value</td>
									</tr>
								</table>
							</div>
							<%-- ########## DETAIL ########## --%>
							<div id="Detail" style="overflow-y:scroll; height:420; overflow-x:hidden; width:567;">
								<table border="1" cellpadding="1" cellspacing="0" align="left" id="D">
									<col width="280">
									<col width="260">
									<tr>
										<td>IDOC PO Detail Line Reference Number</td>
										<td><ezf:inputText name="idocPoDtlLineRefNum" ezfName="idocPoDtlLineRefNum" value="012345" otherAttr=" size=\"7\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Detail Material Number 01</td>
										<td><ezf:inputText name="idocPoDtlMatNum_01" ezfName="idocPoDtlMatNum_01" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Detail Material Number 02</td>
										<td><ezf:inputText name="idocPoDtlMatNum_02" ezfName="idocPoDtlMatNum_02" value="01234567890123456789012345678901234" otherAttr=" size=\"36\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Detail Order Quantity</td>
										<td><ezf:inputText name="idocPoDtlOrdQty" ezfName="idocPoDtlOrdQty" value="1234567890" otherAttr=" size=\"11\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Detail UOM Code</td>
										<td><ezf:inputText name="idocPoDtlUomCd" ezfName="idocPoDtlUomCd" value="012" otherAttr=" size=\"4\""/></td>
									</tr>
									<tr>
										<td>IDOC PO Detail Delivery Priority Name</td>
										<td><ezf:inputText name="idocPoDtlDelyPrtyNm" ezfName="idocPoDtlDelyPrtyNm" value="012345678901234567890123456789" otherAttr=" size=\"21\""/></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
<% } %>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
