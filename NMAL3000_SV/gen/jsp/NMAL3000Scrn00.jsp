<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161116130245 --%>
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
			<input type="hidden" name="pageID" value="NMAL3000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Dealer Authorization Maintenance">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Dealer Authorization Maintenance" class="pTab_ON"><a href="#">Prc Grp Cond</a></li>
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
							<div class="pTab_BODY_In">
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" height="100">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" >
									<col align="left" width="5">
									<col align="left" width="100">
									<col align="left" width="300">
									<col align="left" width="21">
									<col align="left" width="32">
									<col align="left" width="68">
									<col align="left" width="40">
									<col align="left" width="360">
									
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchAccount" >Account Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="dsAcctCustNum" ezfName="dsAcctCustNum" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
										    <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
										</td>
										<td>&nbsp;</td>
										<td class="stab" colspan="2">Effective Date From</td>
										<td colspan="2"><ezf:inputText name="effFromDt" ezfName="effFromDt" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
										</td>
										
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="OpenWin_SearchModel" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchModel" >Marketiong Model(*)</ezf:anchor></td>
										<td><ezf:inputText name="mktMdlCd" ezfName="mktMdlCd" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab" colspan="2">Effective Date To</td>
										<td colspan="2"><ezf:inputText name="effThruDt" ezfName="effThruDt" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
										</td>
										
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Dealer Code</td>
										<td>
											<ezf:select name="dsAcctDlrCd" ezfName="dsAcctDlrCd" ezfBlank="1" ezfCodeName="dsAcctDlrCd_L" ezfDispName="dsAcctDlrDescTxt_L" otherAttr=" style=\"width:117px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Sales</td>
										<td><ezf:inputCheckBox name="slsAuthFlg_SA" ezfName="slsAuthFlg_SA" value="Y" /></td>
										<td class="stab">Service</td>
										<td><ezf:inputCheckBox name="slsAuthFlg_SE" ezfName="slsAuthFlg_SE" value="Y" /></td>
									</tr>
								</table>
								
								<table cellpadding="1" border="0" style="table-layout:fixed; margin-left:436px; margin-top:10px;">
									<col width="150">
									<col width="150">
									<col width="120">
									<col width="100">
									<tr>
										<td class="stab">Account Number</td>
										<td class="stab">Marketing Model</td>
										<td class="stab">Effective Date From</td>
										<td></td>
									</tr>
									<tr>
										<td><ezf:inputText name="dsAcctCustNum_CO" ezfName="dsAcctCustNum_CO" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="mktMdlCd_CO" ezfName="mktMdlCd_CO" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputText name="effFromDt_CO" ezfName="effFromDt_CO" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_CO', 4);">
										</td>
										<td><ezf:inputButton name="Copy_DelrAM" value="Copy" htmlClass="pBtn6" /></td>
									</tr>
								</table>

							</td>
						</tr>
					</table>
								<hr style="height: 0px;" cellpadding="0">

<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>

								<table>
									<tr>
										<td align="top" width="1130">
										
										
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="100">
												<col align="left" width="100">
												<col align="left" width="100">
												<col align="left" width="132">
												<col align="right" width="550">
												<tr>
													<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="InsertRow_DelrAM" value="Insert Row" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteRow_DelrAM" value="Delete Row" htmlClass="pBtn6" /></td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="0" width="100%">
															<tr>
																<td align="left">
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
																			<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																			<td class="stab">/</td>
																			<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																			<td class="stab">page</td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
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
											
											
											
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:487; height:34; float:left;word-break: break-all;">
												<table border="1" cellpadding="1" cellspacing="0"  height="34" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="130">
													<col align="center" width="85">
													<col align="center" width="88">
													<col align="center" width="74">
													<col align="center" width="88">
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctCustNum_A' )">Account<br>Number<img id="sortIMG.dsAcctCustNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctDlrCd_A' )">Dealer Code<img id="sortIMG.dsAcctDlrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A' )">Account<br>Name<img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A' )">Primary<br>Location#<img id="sortIMG.locNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxAllLineAddr_A' )">Primary<br>Address<img id="sortIMG.xxAllLineAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:615; height:34; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" height="34" style="table-layout: fixed;">
													<col align="center" width="88">
													<col align="center" width="40">
													<col align="center" width="74">
													<col align="center" width="110">
													<col align="center" width="44">
													<col align="center" width="54">
													<col align="center" width="108">
													<col align="center" width="108">
													<col align="center" width="110">
													<col align="center" width="110">
													
													<tr height="18">
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctyAddr_A' )">City<img id="sortIMG.ctyAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stCd_A' )">State<img id="sortIMG.stCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'postCd_A' )">Zip<img id="sortIMG.postCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktMdlCd_A' )">Marketing Model<img id="sortIMG.mktMdlCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'slsAuthFlg_A' )">Sales<img id="sortIMG.slsAuthFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcAuthFlg_A' )">Service<img id="sortIMG.svcAuthFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Effective Date<br>From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">Effective Date<br>To<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldUserId_A' )">Update By<img id="sortIMG.upldUserId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldDt_A' )">Date Updated<img id="sortIMG.upldDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:291; width:487; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0"  id="A1" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="130">
													<col align="center" width="85">
													<col align="center" width="88">
													<col align="center" width="74">
													<col align="center" width="88">
													<ezf:row ezfHyo="A">
														<tr height="28" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="dsAcctCustNum_A" ezfName="dsAcctCustNum_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\""/>
																<ezf:inputButton name="Search_Account" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width:20px;\""/>
																<ezf:inputButton name="OpenWin_SearchAccount" value=".." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width:15px;\""/>
															</td>
															<td>
																<ezf:select name="dsAcctDlrCd_A" ezfName="dsAcctDlrCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsAcctDlrCd_L" ezfDispName="dsAcctDlrDescTxt_L" otherAttr=" style=\"width:70px\""/>
															</td>
															<td>
															    <ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
															    <ezf:inputText name="locNum_A" ezfName="locNum_A" value="123456789012" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
															    <ezf:inputText name="xxAllLineAddr_A" ezfName="xxAllLineAddr_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:308; width:632; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed;">
													<col align="center" width="88"><!--city -->
													<col align="center" width="40"><!--State -->
													<col align="center" width="74"><!--Zip -->
													<col align="center" width="110"><!--Marketing Model -->
													<col align="center" width="44"><!--Sales -->
													<col align="center" width="54"><!--Service -->
													<col align="center" width="108"><!--Effective Date From -->
													<col align="center" width="108"><!--Effective Date To -->
													<col align="center" width="110"><!--Update By -->
													<col align="center" width="110"><!--Date Updated -->
													
													<ezf:row ezfHyo="A">
														<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
															<td>
																<ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" value="12345678901234" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																
																<ezf:inputText name="stCd_A" ezfName="stCd_A" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;text-align:center\""/>
															</td>
															<td>
																<ezf:inputText name="postCd_A" ezfName="postCd_A" value="12345678901234" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="mktMdlCd_A" ezfName="mktMdlCd_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/>
																<ezf:inputButton name="OpenWin_SearchModel" value=".." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width:15px;\""/>
															</td>
															<td><ezf:inputCheckBox name="slsAuthFlg_A" ezfName="slsAuthFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															
															</td>
															<td><ezf:inputCheckBox name="svcAuthFlg_A" ezfName="svcAuthFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
																
															</td>
															<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<ezf:inputText name="upldUserId_A" ezfName="upldUserId_A" value="12345678901234" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="upldDt_A" ezfName="upldDt_A" value="12345678901234" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
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

<%-- **** Task specific area ends here **** --%>
