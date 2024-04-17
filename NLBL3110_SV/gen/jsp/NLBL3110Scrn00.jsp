<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180221144511 --%>
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
			<input type="hidden" name="pageID" value="NLBL3110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Distribution Scheduling History Popup">
<%@ page import="business.servlet.NLBL3110.NLBL3110BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NLBL3110BMsg bMsg = (NLBL3110BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%">
		<tr>
			<td valign="top">
<%-- ######################################## HEADER ######################################## --%>
				<table width="100%" cellpadding="1" cellspacing="1" align="center" border="0" bordercolor="red">
					<col width="15">
					<col width="100"  align="left">
					<col width="130"  align="right">
					<col width="118"  align="right">
					<col width="130"  align="right">
					<col width="50"  align="right">
					<col width="35"  align="right">
					<col width="70"  align="right">
					<col width="70"  align="right">
					<col width=""  align="left">
					<tr>
						<td></td>
						<td class="stab">Sales Order Number</td>
						<td><ezf:inputText name="cpoNum_H1" ezfName="cpoNum_H1" value="10908078" otherAttr=" size=\"\" maxlength=\"10\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<% if ("2".equals(bMsg.inbdOtbdCd.getValue())) { %>
						<td  class ="stab">Shipping Order Number</td>
						<% } else { %>
						<td  class ="stab">RWS Number</td>
						<% } %>
						<td><ezf:inputText name="trxHdrNum_H1" ezfName="trxHdrNum_H1" value="SH001001" otherAttr=" size=\"\" maxlength=\"10\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td  colspan="2" class="stab">Line Number</td>
						<td><ezf:inputText name="trxLineNum_H1" ezfName="trxLineNum_H1" value="001" otherAttr=" size=\"\" maxlength=\"10\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td class="stab">Customer</td>
						<td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" value="C89678" otherAttr=" size=\"\" maxlength=\"20\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td colspan="5"><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="CUSTOMER DESCROPTION" otherAttr=" size=\"360\" maxlength=\"7\" ezftoupper=\"\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td class="stab">Customer Address</td>
						<td colspan="6"><ezf:inputText name="xxAllLineAddr_H1" ezfName="xxAllLineAddr_H1" value="1-1-1 330 CUSTOMRT" otherAttr=" size=\"\" maxlength=\"360\" ezftoupper=\"\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td class="stab">City</td>
						<td colspan="4"><ezf:inputText name="ctyAddr_H1" ezfName="ctyAddr_H1" value="Port Washington" otherAttr=" size=\"\" maxlength=\"60\" ezftoupper=\"\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td class="stab">State</td>
						<td><ezf:inputText name="stCd_H1" ezfName="stCd_H1" value="NY" otherAttr=" size=\"\" maxlength=\"4\" ezftoupper=\"\" ezftoupper=\"\" style=\"width:100%\""/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<hr>
<%-- ######################################## PAGE ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="right">
							<ezf:skip>
									<table border="0" cellpadding="0" cellspacing="0">
										<col width="54"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col>
										<col width="1">
										<col>
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">6</td>
											<td class="stab">of</td>
											<td class="pOut">6</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev"  id="btnPrev" name="PagePrev" onclick="sendServer(this)" ></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next"  id="btnNext" name="PageNext" onclick="sendServer(this)" ></td>
										</tr>
									</table>
							</ezf:skip>
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
							</jsp:include>
						</td>
						<td width="22"></td>
					</tr>
				</table>
<%-- ######################################## DETAIL ######################################## --%>
			<div id="bottomTBL" style="word-break:break-all; width=1000; height:370; overflow:scroll;">
				<table border="1" cellpadding="1" cellspacing="0" id="A">
					<div style="padding-right:17px;">
					<col width="150" align="center">
					<col width="150" align="center">
					<col width="150" align="center">
					<col width="80" align="center">
					<col width="180" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="250" align="center">
					<tr height="34">
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrUpdUsrNm_A1' )">Updated By<img id="sortIMG.carrUpdUsrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A1' )">Updated Date<img id="sortIMG.xxTsDsp19Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordStsDescTxt_A1' )">Scheduling  Status<img id="sortIMG.schdCoordStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordPsnCd_A1' )">Coordinator<img id="sortIMG.schdCoordPsnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_AS' )">Schedule Date Time<img id="sortIMG.xxTsDsp19Txt_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgSvcLvlDescTxt_A1' )">Service Level<img id="sortIMG.shpgSvcLvlDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A1' )">Carrier<img id="sortIMG.carrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tempSchdCmntTxt_A1' )">Schedule Notes Comment<img id="sortIMG.tempSchdCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs">
							<% if ("2".equals(bMsg.inbdOtbdCd.getValue())) { %>
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'tempSchdRsnDescTxt_A1' )">Scheduling Notes<img id="sortIMG.tempSchdRsnDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
							<% } else { %>
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtrnTrkStsDescTxt_A1' )">Tracking Status<img id="sortIMG.rtrnTrkStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
							<% } %>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrCmntTxt_A1' )">Carrier Comment<img id="sortIMG.carrCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						</td>
					</tr>
				<!-- <div id="bottomTBL" style="word-break:break-all; height:370; overflow:scroll;"> -->
					<col width="150" align="center">
					<col width="150" align="center">
					<col width="150" align="center">
					<col width="80" align="center">
					<col width="180" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="132" align="center">
					<col width="250" align="center">
					<tbody>
					<ezf:row ezfHyo="A">
						<tr id="id_row{EZF_ROW_NUMBER}">
							<td><ezf:inputText name="carrUpdUsrNm_A1" ezfName="carrUpdUsrNm_A1" value="xxxxxaaafeee@mail.com@Carrier User A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"100\""/></td>
							<td><ezf:inputText name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" value="06/11/2015 21:30:30" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
							<td><ezf:inputText name="schdCoordStsDescTxt_A1" ezfName="schdCoordStsDescTxt_A1" value="Awaiting Customer Commitment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
							<td><ezf:inputText name="schdCoordPsnCd_A1" ezfName="schdCoordPsnCd_A1" value="M00166" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
							<td><ezf:inputText name="xxTsDsp19Txt_AS" ezfName="xxTsDsp19Txt_AS" value="06/20/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
							<td><ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="Ground Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
							<td><ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="UPSN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
							<td><ezf:inputText name="tempSchdCmntTxt_A1" ezfName="tempSchdCmntTxt_A1" value="UPSN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"180\""/></td>
							<% if ("2".equals(bMsg.inbdOtbdCd.getValue())) { %>
							<td><ezf:inputText name="tempSchdRsnDescTxt_A1" ezfName="tempSchdRsnDescTxt_A1" value="Ground Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
							<% } else { %>
							<td><ezf:inputText name="rtrnTrkStsDescTxt_A1" ezfName="rtrnTrkStsDescTxt_A1" value="Ground Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
							<% } %>
							<td><ezf:inputText name="carrCmntTxt_A1" ezfName="carrCmntTxt_A1" value="Carrier Comment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"300\""/></td>
						</tr>
					</ezf:row>
					<ezf:skip>
					</ezf:skip>
					</tbody>
				</table>
			</div>
<%-- ######################################## Footer ######################################## --%>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
