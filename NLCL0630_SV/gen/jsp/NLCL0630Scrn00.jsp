<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181212093051 --%>
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
			<input type="hidden" name="pageID" value="NLCL0630Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech PI Inquiry">

			<%-- ********************** --%>
			<%-- *** Upper Tab Area *** --%>
			<%-- ********************** --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON"><a href="./NLCL0630Scrn00.html">Tech PI Inquiry</a></li>
								</ul>
							</div>

							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							</ezf:skip>

							<div class="pTab_BODY">
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px"border="0">
									<tr>
										<col width="1090" align="left">
									</tr>
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<col width="125" align="left">
													<col width="480"  align="left">
													<col width="125" align="left">
													<col width="360"  align="left">
												</tr>
												<!-- 1 -->
												<tr height="20">
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" otherAttr=" ezfanchortext=\"\"">Technician Name</ezf:anchor></td>
													<td>
														<ezf:inputText name="techTocCd" ezfName="techTocCd" value="----+---" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/>
														<ezf:inputButton name="SearchTechnician" value=">>" htmlClass="pBtn0" />
														<ezf:inputText name="techNm" ezfName="techNm" value="----+----1----+----2----+----3----+----4----+" otherAttr=" size=\"30\" maxlength=\"45\""/>
													</td>
													<td class="stab">Branch</td>
													<td>
														<ezf:inputText name="coaBrCd" ezfName="coaBrCd" value="---" otherAttr=" size=\"10\" maxlength=\"3\" ezftoupper=\"\""/>
														<ezf:inputButton name="SearchBranch" value=">>" htmlClass="pBtn0" />
														<ezf:inputText name="coaBrNm" ezfName="coaBrNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"30\""/>
													</td>
												</tr>
												<!-- 2 -->
												<tr height="20">
<!-- START 2018/12/11 T.Ogura [QC#28755,ADD] -->
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Location" otherAttr=" ezfanchortext=\"\"">Location</ezf:anchor></td>
													<td>
														<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1----+----2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
														<ezf:inputButton name="SearchLocation" value=">>" htmlClass="pBtn0" />
														<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\""/>
													</td>
<!-- END   2018/12/11 T.Ogura [QC#28755,ADD] -->
													<td class="stab">Scheduled Date</td>
													<td>
														<ezf:inputText name="physInvtyDt_DF" ezfName="physInvtyDt_DF" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('physInvtyDt_DF', 4);"/>
														-
														<ezf:inputText name="physInvtyDt_DT" ezfName="physInvtyDt_DT" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('physInvtyDt_DT', 4);"/>
													</td>
												</tr>
												<!-- 3 -->
												<tr height="20">
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_TechPhysical" otherAttr=" ezfanchortext=\"\"">Tech Physical (*)</ezf:anchor></td>
													<td><ezf:inputText name="physInvtyCtrlNm" ezfName="physInvtyCtrlNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" otherAttr=" size=\"35\" maxlength=\"100\" ezftoupper=\"\""/></td>
													<td></td>
													<td></td>
												</tr>
												<!-- 4 -->
												<tr height="20">
													<td class="stab"></td>
													<td></td>
													<td></td>
													<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

							<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
							<table style="margin-left: 5px; width: 1108;">
									<col width="180px" align="left"><%-- ===== space ===== --%>
									<col width="371px" align="left"><%-- ===== space ===== --%>
									<col width="550px" align="right"><%-- Pagenation View --%>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>
											<div align="right">
												<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col width="196px" align="right">
														<col width="5px"   align="left">
														<col width="40px"  align="right">
														<col width="16px"  align="center">
														<col width="16px"  align="center">
														<col width="16px"  align="center">
														<col width="16px"  align="center">
														<col width="5px"   align="left">
														<col width="40px"  align="right">
														<col width="5px"   align="left">
														<col width="40px"  align="right">
														<col width="40px"  align="right">
													<tr>
														<td class="stab">Results 1 - 40 of 200</td>
														<td>&nbsp;</td>
														<td class="stab">Showing</td>
														<td><input type="text" style="text-align:right;" size="2" maxlength="2" value="1" ></td>
														<td class="stab">/</td>
														<td class="pOut">20</td>
														<td class="stab">Page</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
													</tr>
													</table>
												</ezf:skip>
												<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 5px;">
													<tbody>
														<tr align="right">
															<td>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"	  value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"	  value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"	  value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"	  value="xxPageShowOfNum" />
															</jsp:include>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
							</table>
							<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

							<%-- ######################################## DETAIL ######################################## --%>
							<div>
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width="30"	  align="center">
													<col width="110"  align="center">
													<col width="90"	 align="center">
													<col width="260"  align="center">
													<col width="100"  align="center">
													<col width="260"  align="center">
													<col width="160"  align="center">
													<col width="260"  align="center">
													<col width="100"  align="center">
													<col width="180"  align="center">
													<col width="180"  align="center">
													<col width="260"  align="center">
													<tr height="35">
														<td class="pClothBs"></td>
														<td class="pClothBs">PI#</td>
														<td class="pClothBs">Technician<br>Code</td>
														<td class="pClothBs">Technician Name</td>
														<td class="pClothBs">Scheduled Date</td>
														<td class="pClothBs">Tech Physical</td>
														<td class="pClothBs">Tech Location</td>
														<td class="pClothBs">WH Category</td>
														<td class="pClothBs">Physical<br>Inventory</td>
														<td class="pClothBs">Physical Inventory<br>Adjustment (Gross)</td>
														<td class="pClothBs">Physical Inventory<br>Adjustment (Net)</td>
														<td class="pClothBs">Status</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td style="vertical-align:top;">
											<div id="btmTBL" style="overflow-y:scroll; height:360; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
												<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
												<col width="30"	  align="center">
												<col width="110"  align="left">
												<col width="90"	 align="left">
												<col width="260"  align="left">
												<col width="100"  align="left">
												<col width="260"  align="left">
												<col width="160"  align="left">
												<col width="260"  align="left">
												<col width="100"  align="left">
												<col width="180"  align="right">
												<col width="180"  align="right">
												<col width="260"  align="left">
													<ezf:row ezfHyo="A">
														<tr height="23">
															<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" otherAttr=" id=\"radio_button{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:label name="physInvtyNum_A1" ezfName="physInvtyNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="techTocCd_A1" ezfName="techTocCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="techNm_A1" ezfName="techNm_A1" value="----+----1----+----2----+----3----+----4----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"45\""/></td>
															<td><ezf:label name="physInvtyDt_A1" ezfName="physInvtyDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="physInvtyCtrlNm_A1" ezfName="physInvtyCtrlNm_A1" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"100\""/></td>
															<td><ezf:inputText name="whCd_A1" ezfName="whCd_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
															<td><ezf:inputText name="rtlWhCatgDescTxt_A1" ezfName="rtlWhCatgDescTxt_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
															<td><ezf:label name="shipDt_A1" ezfName="shipDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="adjGrsAmt_A1" ezfName="adjGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="adjNetAmt_A1" ezfName="adjNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="physInvtyStsNm_A1" ezfName="physInvtyStsNm_A1" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"70\""/></td>
														</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<%-- =============== Button Group =============== --%>
<!-- START 2018/12/03 T.Ogura [QC#27978,ADD] -->
								<%@ include file="../common/common.jsp" %>
<!-- END   2018/12/03 T.Ogura [QC#27978,ADD] -->
								<table>
									<col width="789">
									<col width="300" align="right">
									<tr>
										<td></td>
										<td>
											<ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn8" />
<!-- START 2018/12/03 T.Ogura [QC#27978,ADD] -->
											<a href="<%=getCustomAppURL("EXTNE437T030")%>" target="PhysicalInventory" onclick="f_open_window_max(this.href, 'PhysicalInventory')">
												<ezf:inputButton name="Begin" value="Begin" htmlClass="pBtn8" /></a>
<!-- END   2018/12/03 T.Ogura [QC#27978,ADD] -->
<!-- START 2018/12/03 T.Ogura [QC#27978,MOD] -->
<!--										<input type="button" class="pBtn8" value="Begin" name="Begin" onclick="sendServer(this)"> -->
											<ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn8" />
<!-- END   2018/12/03 T.Ogura [QC#27978,MOD] -->
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<!-- START 2018/12/03 T.Ogura [QC#27978,ADD] -->
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>
<!-- END   2018/12/03 T.Ogura [QC#27978,ADD] -->

<%-- **** Task specific area ends here **** --%>
