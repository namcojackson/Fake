<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171211094615 --%>
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
			<input type="hidden" name="pageID" value="NMAL2580Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Request History Header Popup">

			<center>
				<table width="100%">
					<col valign="top">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%" style="table-layout:fixed;" >
								<col align="left" width="90">
								<col width="160">
								<col align="left" width="80">
								<col width="160">
								<col align="right" width="175">
								<tr>
									<td class="stab">Request ID</td>
									<td class="stab"><ezf:inputText name="trtyUpdRqstHdrPk" ezfName="trtyUpdRqstHdrPk" value="100043" otherAttr=" size=\"30\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td class="stab">Employee ID(*)</td>
									<td class="stab"><ezf:inputText name="rqstUsrId" ezfName="rqstUsrId" value="123456" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">Employee Name(*)</td>
									<td class="stab"><ezf:inputText name="fill103Txt" ezfName="fill103Txt" value="TESTTESTTESTTEST" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td class="stab">Request Date From</td>
									<td>
										<ezf:inputText name="effFromDt" ezfName="effFromDt" value="05/25/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Request Status</td>
									<td class="stab">
										<ezf:select name="rqstRsltTpCd" ezfName="rqstRsltTpCd" ezfBlank="1" ezfCodeName="rqstRsltTpCd_PC" ezfDispName="rqstRsltTpDescTxt_PC" otherAttr=" style=\"width:215\""/>
									</td>
									<td class="stab">Request Date To</td>
									<td>
										<ezf:inputText name="effToDt" ezfName="effToDt" value="05/25/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Mass Update Reason(*)</td>
									<td class="stab"><ezf:inputText name="rqstRsltCmntTxt" ezfName="rqstRsltCmntTxt" value="AAAAAAAAAAA" otherAttr=" size=\"30\" maxlength=\"4000\" ezftoupper=\"\""/></td>
									<td class="stab">Request Type</td>
									<td class="stab">
										<ezf:select name="rqstCratSysTpCd" ezfName="rqstCratSysTpCd" ezfBlank="1" ezfCodeName="rqstCratSysTpCd_PC" ezfDispName="rqstCratSysTpDescTxt_PC" otherAttr=" style=\"width:215\""/>
									</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								</tr>
							</table>
							<hr>
							<ezf:skip>
							<table>
								<col width="650">
								<tr>
									<td></td>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col >
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
												<td></td>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">40</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>
							<table border="0" cellpadding="1" cellspacing="0" width="99%">
								<tr align="right">
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											<jsp:param name="ViewMode"    value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
				
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
							<div style="float:left"> <!-- right table -->
								<div id="LeftTable_A_Top" style="overflow:hidden; width:972;  display:block;">
										<table border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="table-layout: fixed;" width="1050">
											<col width="90" align="center" valign="center">
											<col width="70" align="center" valign="center">
											<col width="90" align="center" valign="center">
											<col width="120" align="center" valign="center">
											<col width="140" align="center" valign="center">
											<col width="100" align="center" valign="center">
											<col width="100" align="center" valign="center">
											<col width="260" align="center" valign="center">
											<col width="260" align="center" valign="center">
											<tr style="height:30">
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trtyUpdRqstHdrPk_A' )">Request ID<img id="sortIMG.trtyUpdRqstHdrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs">File</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstUsrId_A' )">Employee ID<img id="sortIMG.rqstUsrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fill103Txt_A' )">Employee Name<img id="sortIMG.fill103Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstDtTmTsTxt_A' )">Request Date<img id="sortIMG.rqstDtTmTsTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstRsltTpDescTxt_A' )">Request Status<img id="sortIMG.rqstRsltTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstCratSysTpDescTxt_A' )">Request Type<img id="sortIMG.rqstCratSysTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'massUpdRsnCmntTxt_A' )">Mass Update Reason<img id="sortIMG.massUpdRsnCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstRsltCmntTxt_A' )">Upload Result<img id="sortIMG.rqstRsltCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</tr>
										</table>
								</div>
								<div id="LeftTable_A" style="overflow:scroll; height:383; width:989; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'LeftTable_A_Top' ))">
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="1050">
												<col width="90" align="left"><!-- Request ID -->
												<col width="70" align="center"><!-- File -->
												<col width="90"  align="left"><!-- Employee ID -->
												<col width="120" align="left"><!-- Employee Name -->
												<col width="140" align="left"><!-- Request Date -->
												<col width="100" align="left"><!-- Request Status -->
												<col width="100" align="left"><!-- Request Type -->
												<col width="260" align="left"><!-- Mass Update Reason -->
												<col width="260" align="left"><!-- Upload Result -->

												<ezf:row ezfHyo="A">
													<tr id="id_row{EZF_ROW_NUMBER}" height="26">
														<td><ezf:label name="trtyUpdRqstHdrPk_A" ezfName="trtyUpdRqstHdrPk_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"28\""/></td>
														<td><ezf:anchor name="" ezfName="xxUpldCsvDnldFlg" ezfEmulateBtn="1" ezfGuard="Download_DetailData', '{EZF_ROW_NUMBER}" >Download</ezf:anchor></td>
														<td><ezf:label name="rqstUsrId_A" ezfName="rqstUsrId_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"16\""/></td>
														<td><ezf:label name="fill103Txt_A" ezfName="fill103Txt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"103\""/></td>
														<td><ezf:label name="rqstDtTmTsTxt_A" ezfName="rqstDtTmTsTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"19\""/></td>
														<td><ezf:label name="rqstRsltTpDescTxt_A" ezfName="rqstRsltTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\""/></td>
														<td><ezf:label name="rqstCratSysTpDescTxt_A" ezfName="rqstCratSysTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\""/></td>
														<td><ezf:label name="massUpdRsnCmntTxt_A" ezfName="massUpdRsnCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"4000\""/></td>
														<td><ezf:label name="rqstRsltCmntTxt_A" ezfName="rqstRsltCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"4000\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">100043</label></td>
														<td><A href="#" onclick="sendServer('Download_DetailData', '{EZF_ROW_NUMBER}')" ezfname="xxUpldCsvDnldFlg">Download</A></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">999080</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Test Name</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Submitted</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Online</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Aaaaaaaaa aaaaaaaa</label></td>
													</tr>
												</ezf:skip>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>