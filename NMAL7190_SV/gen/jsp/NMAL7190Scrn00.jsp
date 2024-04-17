<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230424155951 --%>
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
			<input type="hidden" name="pageID" value="NMAL7190Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Group Setup">
<center>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Price Group Set Up" class="pTab_ON"><a href="#">Prc Grp Set</a></li>
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
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<div class="pTab_BODY">
					<fieldset>
						<table align="left" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<table cellpadding="0" border="0">
										<col width="100" align="left">
										<col width="120"  align="left">
										<col width="80"  align="left">
										<col width="100"   align="left">
										<col width="100"  align="left">
										<col width="78"  align="left">
										<col width="122" align="left">
										<col width="100" align="left">
										<col width="80" align="left">
										<tr>
											<td class="stab">Pricing Group ID</td>
											<td><ezf:inputText name="prcGrpPk" ezfName="prcGrpPk" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
											<td class="stab">Pricing Group Name</td>
											<td colspan="3"><ezf:inputText name="prcGrpNm" ezfName="prcGrpNm" value="Commercial Double Click Corporate Advantage Plan" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td>&nbsp;&nbsp;Active<ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td></td>
											<td><ezf:inputButton name="OpenWin_PrcGrpUsage" value="View Usage" htmlClass="pBtn8" /></td>
										</tr>
										<tr>
											<td class="stab">Group Description</td>
											<td colspan="2"><ezf:inputText name="prcGrpDescTxt" ezfName="prcGrpDescTxt" value="Commercial Double Click Corporate Advantage Plan" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td class="stab"  align="left">Effective Date From</td>
											<td align="left">
											    <ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/2012" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
                                            </td>
											<td class="stab"  align="left">Effective Date To</td>
											<td align="left">
											    <ezf:inputText name="effThruDt" ezfName="effThruDt" value="01/01/2012" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
                                            </td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>

										<tr>
											<td class="stab">Pricing Group Type</td>
											<td colspan="3">
												<ezf:select name="prcGrpTpCd" ezfName="prcGrpTpCd" ezfBlank="1" ezfCodeName="prcGrpTpCd_P" ezfDispName="prcGrpTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_PricingGroupType')\"" otherAttr=" style=\"width:320;\""/></td>
											<td>&nbsp;</td>
										</tr>

										<tr>
											<td class="stab">Transaction Type</td>
											<td colspan="3">
												<ezf:select name="prcGrpTrxTpCd" ezfName="prcGrpTrxTpCd" ezfBlank="1" ezfCodeName="prcGrpTrxTpCd_P" ezfDispName="prcGrpTrxTpDescTxt_P" otherAttr=" style=\"width:320;\""/></td>
											<td colspan="6" align="right">
												<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="right">
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																	<tr>
																		<td>Results 999 - 999 of 999</td>
																	</tr>
															</table>
														</td>
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
																	<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum"></td>
																	<td class="stab">/</td>
																	<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'F')"></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'F')"></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'F')"></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												</ezf:skip>
												<table width="100%">
													<tr align="right">
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"           value="A" />
																<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"          value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
											</td>
										</tr>

									</table>
								</td>
							</tr>
						</table>
					</fieldset>
					<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
						<col width="230" align="left">
						<col width="090" align="left">
						<col width="090" align="left">
						<col width="060" align="left">
						<col width=""    align="left">
						<col width="100" align="right">
						<col width="060" align="right" valign="top">
						<col width="240" align="right" valign="top">
						<col width="075" align="right" valign="top">
						<col width="075" align="right" valign="top">
						<col width="075" align="right" valign="top">
						<tr>
							<td class="stab">Assignments This Group : TARGET AUDIENCE</td>
							<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="UnSelect_All" value="UnSelect All" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="OpenWin_Filter" value="Filter" htmlClass="pBtn5" /></td>
							<td>&nbsp;</td>
							<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_DW" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Download Template</ezf:anchor></td>
							<td class="stab" valign="middle">File Upload</td>
							<td><ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"20\" maxlength=\"9999\""/></td>
							<td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
							<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" /></td>
							<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
						<col width="" align="left">
						<col width="075" align="right">
						<col width="105" align="right"">
						<col width="090" align="right">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">Date To</td>
							<td>
								<ezf:inputText name="effThruDt_MU" ezfName="effThruDt_MU" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_MU', 4);" >
							</td>
							<td><ezf:inputButton name="MassUpdate" value="MASS Update" htmlClass="pBtn7" /></td>
						</tr>
					</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
					<fieldset>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="5"></td>
								<td>
									<div style="float:left">
										<%-- LEFT TBL - TOP --%>
										<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:365; float:left;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="365px">
												<col align="center" width="025">	<!-- Select						-->
												<col align="center" width="145">	<!-- Target Context				-->
												<col align="center" width="195">	<!-- Attribute Name				-->
												<tr>
													<td class="pClothBs">&nbsp;</td>
													<td class="pClothBs">Target Context</td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpTrgtAttrbCd_A1' )" tabindex="-1">Target Attribute Name</a><img id="sortIMG.prcGrpTrgtAttrbCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
										<%-- RIGHT TBL - TOP --%>
										<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:733; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="840px">
												<col align="center" width="080">	<!-- Operator					-->
												<col align="center" width="175">	<!-- Target From				-->
												<col align="center" width="150">	<!-- Description				-->
												<col align="center" width="175">	<!-- Target To					-->
												<col align="center" width="050">	<!-- Precedence#				-->
												<col align="center" width="105">	<!-- Date From					-->
												<col align="center" width="105">	<!-- Date To					-->
												<tr>
													<td class="pClothBs">Operator</td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpFromTxt_A1' )" tabindex="-1">Target From</a><img id="sortIMG.prcGrpFromTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )" tabindex="-1">Description</a><img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpThruTxt_A1' )" tabindex="-1">Target To</a><img id="sortIMG.prcGrpThruTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpPrcdNum_A1' )" tabindex="-1">Prcd#</a><img id="sortIMG.prcGrpPrcdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A1' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A1' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
										<%-- LEFT TBL - BOTTOM --%>
										<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:365; width:367; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="365px">
												<col align="center" width="025">	<!-- Select						-->
												<col align="center" width="145">	<!-- Target Context				-->
												<col align="center" width="195">	<!-- Attribute Name				-->
												<ezf:row ezfHyo="A">
													<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
														<td>
															<ezf:select name="prcGrpTrgtTpCd_A1" ezfName="prcGrpTrgtTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcGrpTrgtTpCd_P" ezfDispName="prcGrpTrgtTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_TargetContext', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 137px\" id=\"prcGrpTrgtTpCd_A1{EZF_ROW_NUMBER}\""/>
														</td>
														<td>
															<ezf:select name="prcGrpTrgtAttrbCd_A1" ezfName="prcGrpTrgtAttrbCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcGrpTrgtAttrbCd_P" ezfDispName="prcGrpTrgtAttrbDescTxt_P" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_AttributeName', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:187px\" id=\"prcGrpTrgtAttrbCd_A1#{EZF_ROW_NUMBER}\""/>
														</td>
													</tr>
												</ezf:row>
											</table>
										</div>
										<%-- RIGHT TBL - BOTTOM --%>
										<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:382; width:750; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="840px">
												<col align="center" width="080">	<!-- Operator					-->
												<col align="center" width="175">	<!-- Target From				-->
												<col align="center" width="150">	<!-- Description				-->
												<col align="center" width="175">	<!-- Target To					-->
												<col align="center" width="050">	<!-- Precedence#				-->
												<col align="center" width="105">	<!-- Date From					-->
												<col align="center" width="105">	<!-- Date To					-->
												<ezf:row ezfHyo="A">
													<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
														<td>
															<ezf:select name="prcGrpOpCd_A1" ezfName="prcGrpOpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcGrpOpCd_P" ezfDispName="prcGrpOpDescTxt_P" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_Operator', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:70px\" id=\"prcGrpOpCd_A1#{EZF_ROW_NUMBER}\""/>
														</td>
														<td>
															<ezf:inputButton name="OpenWin_TargetFrom" value="Target" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"OpenWin_TargetFrom{EZF_ROW_NUMBER}\""/>
															<ezf:inputText name="prcGrpFromTxt_A1" ezfName="prcGrpFromTxt_A1" value="Commercial Double Click Corporate Advantage Plan" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"prcGrpFromTxt_A1{EZF_ROW_NUMBER}\" size=\"14\" maxlength=\"50\" ezftoupper=\"\" ezftoupper=\"\""/>
														</td>
														<td>
															<ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A1{EZF_ROW_NUMBER}\" size=\"19\" maxlength=\"360\" style=\"border:none;background-color:transparent;padding:0px;\""/>
														</td>
														<td>
															<ezf:inputButton name="OpenWin_TargetTo" value="Target" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"OpenWin_TargetTo{EZF_ROW_NUMBER}\""/>
															<ezf:inputText name="prcGrpThruTxt_A1" ezfName="prcGrpThruTxt_A1" value="Commercial Double Click Corporate Advantage Plan" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"prcGrpThruTxt_A1{EZF_ROW_NUMBER}\" size=\"14\" maxlength=\"50\" ezftoupper=\"\" ezftoupper=\"\""/>
														</td>
														<td>
															<ezf:inputText name="prcGrpPrcdNum_A1" ezfName="prcGrpPrcdNum_A1" value="9" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"prcGrpThruTxt_A1{EZF_ROW_NUMBER}\" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
														</td>
														<td>
														    <ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="01/01/2012" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A1{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" ezftoupper=\"\" ezftoupper=\"\""/>
			                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" >
														</td>
														<td>
														    <ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="01/01/2012" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" ezftoupper=\"\" ezftoupper=\"\""/>
			                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" >
														</td>
													</tr>
												</ezf:row>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
