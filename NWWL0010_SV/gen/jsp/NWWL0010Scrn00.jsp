<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160901112308 --%>
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
			<input type="hidden" name="pageID" value="NWWL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Notification Center Search">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<%-- #################################################### FROM HEADER ################################################### --%>
				<div class="pTab_BODY">
					<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="85">
						<col width="300">
						<col width="200">
						<col width="100">
						<tr>
							<td class="stab">Name(*)</td>
							<td class="stab"><ezf:inputText name="ntfyHdrNm" ezfName="ntfyHdrNm" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Description(*)</td>
							<td colspan="2" class="stab"><ezf:inputText name="ntfyHdrDescTxt" ezfName="ntfyHdrDescTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Business Area</td>
							<td>
								<ezf:select name="ntfyBizAreaTpCd_SL" ezfName="ntfyBizAreaTpCd_SL" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_L0" ezfDispName="ntfyBizAreaTpDescTxt_L0" otherEvent1=" onchange=\"sendServer('OnChange_BizArea')\"" otherAttr=" style=\"width:200px\" id=\"ntfyBizAreaTpCd_SL\""/>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Sub Area</td>
							<td>
								<ezf:select name="ntfySubAreaTpCd_SL" ezfName="ntfySubAreaTpCd_SL" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_L0" ezfDispName="ntfySubAreaTpDescTxt_L0" otherAttr=" style=\"width:200px\" id=\"ntfySubAreaTpCd_SL\""/>
							</td>
							<td class="stab">Enabled Only<ezf:inputCheckBox name="ntfyHdrActvFlg" ezfName="ntfyHdrActvFlg" value="Y" otherAttr=" id=\"ntfyHdrActvFlg\""/></td>
							<td>
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<br>
<%-- #################################################### TO HEADER ################################################### --%>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="1120" height="35">
						<tr>
							<td width="3">&nbsp;</td>
							<td width="550">
								<ezf:inputButton name="MoveWin_Setup" value="Create New" htmlClass="pBtn8" />
							</td>
							<td align="right" style="padding-right:10px;">
<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
								<table width="990" border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td align="right">
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
												<col width="10">
												<col>
												<col width="20">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td><ezf:inputText name="xxPageShowCurNum" ezfName="xxPageShowCurNum" value="9999" otherAttr=" size=\"4\" maxlength=\"4\" style=\"text-align:right\""/></td>
													<td class="stab">/</td>
													<td><ezf:inputText name="xxPageShowTotNum" ezfName="xxPageShowTotNum" value="9999" otherAttr=" size=\"4\" maxlength=\"4\" style=\"text-align:right\""/></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="PageJump" value="Jump" htmlClass="pBtn3" otherAttr=" id=\"btnJump\""/></td>
													<td></td>
													<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" otherAttr=" id=\"btnPrev\""/></td>
													<td></td>
													<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" otherAttr=" id=\"btnNext\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
										</td>
									</tr>
								</table>
  After Convert to JSP, this area suppose to be deleted [TO] --%>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"        value="FULL" />
								</jsp:include>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
<%--						<col align="left" valign="top" width="1107"> --%>
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1105px" style="margin-right:20px" >
											<col align="center" width="047">	<!-- Notif ID -->
											<col align="center" width="153">	<!-- Name -->
											<col align="center" width="200">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->

											<tr id="id_row${EZF_ROW_NUMBER}" height="30">
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrId_A0' )">Notif ID
														<img id="sortIMG.ntfyHdrId_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrNm_A0' )">Name
														<img id="sortIMG.ntfyHdrNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrDescTxt_A0' )">Description
														<img id="sortIMG.ntfyHdrDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyBizAreaTpNm_A0' )">Business Area
														<img id="sortIMG.ntfyBizAreaTpNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfySubAreaTpNm_A0' )">Sub Area
														<img id="sortIMG.ntfySubAreaTpNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A0' )">Start Date
														<img id="sortIMG.effFromDt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A0' )">End Date
														<img id="sortIMG.effThruDt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1122px; height:410px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1105" style="table-layout:fixed;">
											<col align="center" width="047">	<!-- Notif ID -->
											<col align="center" width="153">	<!-- Name -->
											<col align="center" width="200">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->
											<tbody>
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td align="left">
														<ezf:anchor name="ntfyHdrId_A0" ezfName="ntfyHdrId_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Setup" otherAttr=" id=\"ntfyHdrId_A0#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<ezf:label name="ntfyHdrId_A0" ezfName="ntfyHdrId_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td align="center"><ezf:inputText ezfHyo="A" name="ntfyHdrNm_A0" ezfName="ntfyHdrNm_A0" otherAttr=" size=\"36\" style=\"border:none; background-color:transparent\""/></td>
													<td align="center"><ezf:inputText ezfHyo="A" name="ntfyHdrDescTxt_A0" ezfName="ntfyHdrDescTxt_A0" otherAttr=" size=\"48\" style=\"border:none; background-color:transparent\""/></td>
													<td align="center"><ezf:inputText ezfHyo="A" name="ntfyBizAreaTpNm_A0" ezfName="ntfyBizAreaTpNm_A0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent\""/></td>
													<td align="center"><ezf:inputText ezfHyo="A" name="ntfySubAreaTpNm_A0" ezfName="ntfySubAreaTpNm_A0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent\""/></td>
													<td><ezf:label name="effFromDt_A0" ezfName="effFromDt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A0#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:label name="effThruDt_A0" ezfName="effThruDt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A0#{EZF_ROW_NUMBER}\""/></td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
											</ezf:skip>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
