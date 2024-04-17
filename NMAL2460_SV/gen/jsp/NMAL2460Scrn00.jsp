<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180612152637 --%>
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
			<input type="hidden" name="pageID" value="NMAL2460Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Account Owner Lookup">
<style type="text/css">
</style>
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
										<li title="Counter Setup" class="pTab_ON"><a href="#">AcctOwnrLookup</a></li>
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
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="147">
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
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" width="960" border="0" cellpadding="0" cellspacing="0">
									<col width="100%" align="left">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="120">
												<col width="200">
												<col width="10">
												<col width="120">
												<col width="220">
												<col width="10">
												<col width="100">
												<col width="100">
												<tr>
													<td class="stab">Business Area</td>
													<td class="stab">
														<ezf:select name="bizAreaOrgCd_H" ezfName="bizAreaOrgCd_H" ezfCodeName="bizAreaOrgCd_L" ezfDispName="bizAreaOrgDescTxt_L" otherAttr=" style=\"width:150px\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Account Category</td>
													<td>
														<ezf:select name="dsAcctTpCd_H" ezfName="dsAcctTpCd_H" ezfCodeName="dsAcctTpCd_L" ezfDispName="dsAcctTpDescTxt_L" otherAttr=" style=\"width:90px\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Search Results</td>
													<td class="stab">
														<ezf:select name="srchLyotTpCd_H" ezfName="srchLyotTpCd_H" ezfCodeName="srchLyotTpCd_L" ezfDispName="srchLyotTpDescTxt_L" otherAttr=" style=\"width:100px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSrch" otherAttr=" id=\"dsAcctNm_LK\" ezfanchortext=\"\"">Account Name(*)</ezf:anchor></td>
													<td class="stab"><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td class="stab"><ezf:anchor name="dsAcctNum_LK" ezfName="dsAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSrch" otherAttr=" id=\"dsAcctNum_LK\" ezfanchortext=\"\"">Account Number(*)</ezf:anchor></td>
													<td class="stab" colspan="4"><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" otherAttr=" size=\"25\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="orgNm_LK" ezfName="orgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Territory" otherAttr=" id=\"orgNm_LK\" ezfanchortext=\"\"">Territory Name(*)</ezf:anchor></td>
													<td class="stab"><ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"25\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Territory Group(*)</td>
													<td class="stab" colspan="4">
														<ezf:select name="trtyGrpTpCd_H" ezfName="trtyGrpTpCd_H" ezfBlank="1" ezfCodeName="trtyGrpTpCd_L" ezfDispName="trtyGrpTpDescTxt_L" otherAttr=" style=\"width:140px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="fullPsnNm_LK" ezfName="fullPsnNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Resrc" otherAttr=" id=\"fullPsnNm_LK\" ezfanchortext=\"\"">Resource Name(*)</ezf:anchor></td>
													<td class="stab"><ezf:inputText name="fullPsnNm_H" ezfName="fullPsnNm_H" otherAttr=" size=\"25\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Employee ID</td>
													<td class="stab" colspan="4"><ezf:inputText name="psnCd_H" ezfName="psnCd_H" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="orgNm_L2" ezfName="orgNm_L2" ezfEmulateBtn="1" ezfGuard="OpenWin_Organization" otherAttr=" id=\"orgNm_L2\" ezfanchortext=\"\"">Organization Name(*)</ezf:anchor></td>
													<td class="stab"><ezf:inputText name="orgNm_H2" ezfName="orgNm_H2" otherAttr=" size=\"25\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Source Business Unit</td>
													<td class="stab" colspan="4">
														<ezf:select name="lineBizTpCd_H" ezfName="lineBizTpCd_H" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:90px\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="780">
												<col width="100">
												<tr>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="OpenWin_RqstHist" value="Request History" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="680">
												<col width="100">
												<tr>
													<td>
														<fieldset>
															<legend>Advanced Search</legend>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																<col width="120">
																<col width="200">
																<col width="10">
																<col width="120">
																<col width="220">
																<tr>
																	<td class="stab">Account Create From</td>
																	<td class="stab">
																		<ezf:inputText name="xxCratDt_HF" ezfName="xxCratDt_HF" otherAttr=" size=\"10\""/>
																		<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_HF', 4);">
																	</td>
																	<td>&nbsp;</td>
																	<td class="stab">Account Create To</td>
																	<td class="stab">
																		<ezf:inputText name="xxCratDt_HT" ezfName="xxCratDt_HT" otherAttr=" size=\"10\""/>
																		<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_HT', 4);">
																	</td>
																</tr>
																<tr>
																	<td class="stab">Address(*)</td>
																	<td class="stab"><ezf:inputText name="xxLineCatgSrchTxt_H" ezfName="xxLineCatgSrchTxt_H" otherAttr=" size=\"25\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab"><ezf:anchor name="locNum_LK" ezfName="locNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_locSrch" otherAttr=" id=\"locNum_LK\" ezfanchortext=\"\"">Location ID(*)</ezf:anchor></td>
																	<td class="stab"><ezf:inputText name="locNum_H" ezfName="locNum_H" otherAttr=" size=\"30\""/></td>
																</tr>
																<tr>
																	<td class="stab">City(*)</td>
																	<td class="stab"><ezf:inputText name="ctyAddr_H" ezfName="ctyAddr_H" otherAttr=" size=\"25\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab">State</td>
																	<td class="stab">
																		<ezf:select name="stCd_H" ezfName="stCd_H" ezfBlank="1" ezfCodeName="stCd_L" ezfDispName="stDescTxt_L" otherAttr=" style=\"width:70px\""/>
																	</td>
																	</tr>
																<tr>
																	<td class="stab">Postal Code From(*)</td>
																	<td class="stab"><ezf:inputText name="postCd_HF" ezfName="postCd_HF" otherAttr=" size=\"15\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab">Postal Code To(*)</td>
																	<td class="stab"><ezf:inputText name="postCd_HT" ezfName="postCd_HT" otherAttr=" size=\"15\""/></td>
																</tr>
																</table>
														</fieldset>
													</td>
													<td>&nbsp;</td>
													<td valign="bottom"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr>
							</td>
						</tr>
<%@ page import="business.servlet.NMAL2460.NMAL2460BMsg" %>
<%  NMAL2460BMsg bMsg = (NMAL2460BMsg)databean.getEZDBMsg(); %>
<% if ("01".equals(bMsg.srchLyotTpCd.getValue())) { %>
<!-- ######################################## FROM LAYOUT 1 #################################### -->
						<tr>
<!-- ######################################## FROM (COMMON)PAGENATION A #################################### -->
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="5">
									<col width="170">
									<col width="285">
									<col width="85">
									<col width="560" align="right">
									<tr>
										<td>&nbsp;</td>
										<td>
										<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td class="stab">
											Update Reason&nbsp;
											<ezf:inputText name="rqstRsltCmntTxt" ezfName="rqstRsltCmntTxt" otherAttr=" size=\"28\" maxlength=\"4000\""/>
										</td>
										<td><ezf:inputButton name="MoveWin_UploadAccountOwner" value="Upload File" htmlClass="pBtn7" /></td>
										<td align="right" class="stab">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="A" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_A" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_A" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_A" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
											<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="left">	
															<table border="0" cellpadding="0" align="right" cellspacing="0">
																<col>
																<tr>
																	<td>Results 4961 - 5000 of 5000</td>
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
																	<td>150</td>
																	<td class="stab">/</td>
																	<td>150</td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ezf:skip>
										</td>
									</tr>
								</table>
							</td>
<!-- ######################################## TO (COMMON)PAGENATION A #################################### -->
						</tr>
<!-- ######################################## FROM DETAIL A #################################### -->
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"><!-- left table -->
										<div id='leftTblHead' style="display:block;"></div>
										<div id='leftTbl' style="float:left; display:block; height:206px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
									</div><!-- left table -->
									<div style="float:left;"><!-- right table -->
										<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<table width="2547px" border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="margin-right:20px" style="table-layout:fixed;">
												<col width="25" align="center"><!-- Radio Button -->
												<col width="85" align="center"><!-- Location ID -->
												<col width="85" align="center"><!-- Account # -->
												<col width="85" align="center"><!-- Account Category -->
												<col width="160" align="center"><!-- Account Name -->
												<col width="160" align="center"><!-- Address -->
												<col width="85" align="center"><!-- City -->
												<col width="85" align="center"><!-- State -->
												<col width="85" align="center"><!-- Postal Code -->
												<col width="160" align="center"><!-- Territory -->
												<col width="85" align="center"><!-- LOB -->
												<col width="110" align="center"><!-- LOB Role -->
												<col width="75" align="center"><!-- Resource # -->
												<col width="110" align="center"><!-- Resource Name -->
												<col width="85" align="center"><!-- Employee ID -->
												<col width="85" align="center"><!-- Creation Date -->
												<col width="100" align="center"><!-- SIC Code -->
												<col width="85" align="center"><!-- Classification -->
												<col width="100" align="center"><!-- Account Group Name 1 -->
												<col width="100" align="center"><!-- Account Group Name 2 -->
												<col width="100" align="center"><!-- Account Group Name 3 -->
												<col width="100" align="center"><!-- Account Group Name 4 -->
												<col width="100" align="center"><!-- Account Group Name 5 -->
												<col width="70" align="center"><!-- Manual Entry -->
												<col width="130" align="center"><!-- Organization Name -->
												<col width="80" align="center"><!-- Organization ID -->
												<tr height="37">
													<td id="AH0" class="pClothBs colFix"></td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'locNum_A')" ezfhyo="A">Location ID<img id="sortIMG.locNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A')" ezfhyo="A">Account #<img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctTpDescTxt_A')" ezfhyo="A">Account<br>Category<img id="sortIMG.dsAcctTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')" ezfhyo="A">Account Name<img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxAllLineAddr_A')" ezfhyo="A">Address<img id="sortIMG.xxAllLineAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ctyAddr_A')" ezfhyo="A">City<img id="sortIMG.ctyAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stCd_A')" ezfhyo="A">State<img id="sortIMG.stCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'postCd_A')" ezfhyo="A">Postal Code<img id="sortIMG.postCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgNm_A1')" ezfhyo="A">Territory<img id="sortIMG.orgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyGrpTpDescTxt_A')" ezfhyo="A">LOB<img id="sortIMG.trtyGrpTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'lineBizRoleTpDescTxt_A')" ezfhyo="A">LOB Role<img id="sortIMG.lineBizRoleTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnNum_A')" ezfhyo="A">Resource #<img id="sortIMG.psnNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnFirstMidLastNm_A')" ezfhyo="A">Resource Name<img id="sortIMG.xxPsnFirstMidLastNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnCd_A')" ezfhyo="A">Employee ID<img id="sortIMG.psnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCratDt_A')" ezfhyo="A">Creation Date<img id="sortIMG.xxCratDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsCustSicCd_A')" ezfhyo="A">SIC Code<img id="sortIMG.dsCustSicCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctClsDescTxt_A')" ezfhyo="A">Classification<img id="sortIMG.dsAcctClsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'firstDsAcctGrpTpCd_A')" ezfhyo="A">Account Group<br>Name 1<img id="sortIMG.firstDsAcctGrpTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'scdDsAcctGrpTpCd_A')" ezfhyo="A">Account Group<br>Name 2<img id="sortIMG.scdDsAcctGrpTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'thirdDsAcctGrpTpCd_A')" ezfhyo="A">Account Group<br>Name 3<img id="sortIMG.thirdDsAcctGrpTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'frthDsAcctGrpTpCd_A')" ezfhyo="A">Account Group<br>Name 4<img id="sortIMG.frthDsAcctGrpTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fifthDsAcctGrpTpCd_A')" ezfhyo="A">Account Group<br>Name 5<img id="sortIMG.fifthDsAcctGrpTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'manEntryFlg_A')" ezfhyo="A">Manual Entry<img id="sortIMG.manEntryFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgNm_A2')" ezfhyo="A">Organization<br>Name<img id="sortIMG.orgNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgCd_A')" ezfhyo="A">Organization ID<img id="sortIMG.orgCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1122px; height:223px; display:block; overflow:scroll; margin:0px; padding: 0px;">
											<table width="2530px" border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
												<col width="25"><!-- Radio Button -->
												<col width="85"><!-- Location ID -->
												<col width="85"><!-- Account # -->
												<col width="85"><!-- Account Category -->
												<col width="160"><!-- Account Name -->
												<col width="160"><!-- Address -->
												<col width="85"><!-- City -->
												<col width="85"><!-- State -->
												<col width="85"><!-- Postal Code -->
												<col width="160"><!-- Territory -->
												<col width="85"><!-- LOB -->
												<col width="110"><!-- LOB Role -->
												<col width="75"><!-- Resource # -->
												<col width="110"><!-- Resource Name -->
												<col width="85"><!-- Employee ID -->
												<col width="85"><!-- Account Location Creation Date -->
												<col width="100"><!-- SIC Code -->
												<col width="85"><!-- Classification -->
												<col width="100"><!-- Account Group Name 1 -->
												<col width="100"><!-- Account Group Name 2 -->
												<col width="100"><!-- Account Group Name 3 -->
												<col width="100"><!-- Account Group Name 4 -->
												<col width="100"><!-- Account Group Name 5 -->
												<col width="70" align="center"><!-- Manual Entry -->
												<col width="130"><!-- Organization Name -->
												<col width="80"><!-- Organization ID -->
												<ezf:row ezfHyo="A">
													<tr height="25">
														<td>
															<ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_A{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="locNum_A" ezfName="locNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctTpDescTxt_A" ezfName="dsAcctTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"360\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAllLineAddr_A" ezfName="xxAllLineAddr_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"400\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" value="XXXXXXXXX1XXXXXXXXX2XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"25\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="stNm_A" ezfName="stNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="postCd_A" ezfName="postCd_A" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="orgNm_A1" ezfName="orgNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_A" ezfName="trtyGrpTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="lineBizRoleTpDescTxt_A" ezfName="lineBizRoleTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="psnNum_A" ezfName="psnNum_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxPsnFirstMidLastNm_A" ezfName="xxPsnFirstMidLastNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="psnCd_A" ezfName="psnCd_A" value="XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxCratDt_A" ezfName="xxCratDt_A" value="04/25/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsCustSicCd_A" ezfName="dsCustSicCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctClsDescTxt_A" ezfName="dsAcctClsDescTxt_A" value="XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="firstDsAcctGrpTpCd_A" ezfName="firstDsAcctGrpTpCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="scdDsAcctGrpTpCd_A" ezfName="scdDsAcctGrpTpCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="thirdDsAcctGrpTpCd_A" ezfName="thirdDsAcctGrpTpCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="frthDsAcctGrpTpCd_A" ezfName="frthDsAcctGrpTpCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="fifthDsAcctGrpTpCd_A" ezfName="fifthDsAcctGrpTpCd_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="manEntryFlg_A" ezfName="manEntryFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="orgNm_A2" ezfName="orgNm_A2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="orgCd_A" ezfName="orgCd_A" value="XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
<!-- ######################################## TO DETAIL A #################################### -->
<!-- ######################################## TO LAYOUT 1 #################################### -->
<% } else { %>
<!-- ######################################## FROM LAYOUT 2 #################################### -->
						<tr>
<!-- ######################################## FROM (COMMON)PAGENATION B#################################### -->
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="5">
									<col width="170">
									<col width="285">
									<col width="85">
									<col width="560" align="right">
									<tr>
										<td>&nbsp;</td>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td class="stab">
											Update Reason&nbsp;
											<ezf:inputText name="rqstRsltCmntTxt" ezfName="rqstRsltCmntTxt" otherAttr=" size=\"28\" maxlength=\"4000\""/>
										</td>
										<td class="stab"><ezf:inputButton name="MoveWin_UploadAccountOwner" value="Upload File" htmlClass="pBtn7" /></td>
										<td align="right" class="stab">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="B" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_B" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_B" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_B" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_B" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_B" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
											<ezf:skip>
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
																	<td>150</td>
																	<td class="stab">/</td>
																	<td>150</td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'B')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'B')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'B')" disabled></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ezf:skip>
										</td>
									</tr>
								</table>
							</td>
<!-- ######################################## TO (COMMON)PAGENATION B#################################### -->
						</tr>
<!-- ######################################## DETAIL B#################################### -->
						<tr>
							<td>
								<div id="parentBoxB">
									<div style="float:left; display:block"><!-- left table -->
										<div id='leftTblHead' style="display:block;"></div>
										<div id='leftTbl' style="float:left; display:block; height:206px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
									</div><!-- left table -->
									<div style="float:left;"><!-- right table -->
										<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<!-- Add Start 2017/11/28 Hd.Sugawara QC#21044 -->
											<ezf:inputHidden name="slsCrQuotFlg_01" ezfName="slsCrQuotFlg_01" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_01\""/>
											<ezf:inputHidden name="slsCrQuotFlg_02" ezfName="slsCrQuotFlg_02" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_02\""/>
											<ezf:inputHidden name="slsCrQuotFlg_03" ezfName="slsCrQuotFlg_03" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_03\""/>
											<ezf:inputHidden name="slsCrQuotFlg_04" ezfName="slsCrQuotFlg_04" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_04\""/>
											<ezf:inputHidden name="slsCrQuotFlg_05" ezfName="slsCrQuotFlg_05" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_05\""/>
											<ezf:inputHidden name="slsCrQuotFlg_06" ezfName="slsCrQuotFlg_06" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_06\""/>
											<ezf:inputHidden name="slsCrQuotFlg_07" ezfName="slsCrQuotFlg_07" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_07\""/>
											<ezf:inputHidden name="slsCrQuotFlg_08" ezfName="slsCrQuotFlg_08" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_08\""/>
											<ezf:inputHidden name="slsCrQuotFlg_09" ezfName="slsCrQuotFlg_09" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_09\""/>
											<ezf:inputHidden name="slsCrQuotFlg_10" ezfName="slsCrQuotFlg_10" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_10\""/>
											<ezf:inputHidden name="slsCrQuotFlg_11" ezfName="slsCrQuotFlg_11" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_11\""/>
											<ezf:inputHidden name="slsCrQuotFlg_12" ezfName="slsCrQuotFlg_12" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_12\""/>
											<ezf:inputHidden name="slsCrQuotFlg_13" ezfName="slsCrQuotFlg_13" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_13\""/>
											<ezf:inputHidden name="slsCrQuotFlg_14" ezfName="slsCrQuotFlg_14" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_14\""/>
											<ezf:inputHidden name="slsCrQuotFlg_15" ezfName="slsCrQuotFlg_15" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_15\""/>
											<ezf:inputHidden name="slsCrQuotFlg_16" ezfName="slsCrQuotFlg_16" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_16\""/>
											<ezf:inputHidden name="slsCrQuotFlg_17" ezfName="slsCrQuotFlg_17" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_17\""/>
											<ezf:inputHidden name="slsCrQuotFlg_18" ezfName="slsCrQuotFlg_18" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_18\""/>
											<ezf:inputHidden name="slsCrQuotFlg_19" ezfName="slsCrQuotFlg_19" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_19\""/>
											<ezf:inputHidden name="slsCrQuotFlg_20" ezfName="slsCrQuotFlg_20" otherAttr=" size=\"0\" id=\"slsCrQuotFlg_20\""/>
											<!-- Add End   2017/11/28 Hd.Sugawara QC#21044 -->
											<table width="18892px" border="1" cellpadding="1" cellspacing="0" id="BHEAD" style="margin-right:20px" style="table-layout:fixed;">
												<col width="25" align="center"><!-- Radio Button -->
												<col width="85" align="center"><!-- Location ID -->
												<col width="85" align="center"><!-- Account # -->
												<col width="85" align="center"><!-- Account Category -->
												<col width="160" align="center"><!-- Account Name -->
												<col width="230" align="center"><!-- Address -->
												<col width="85" align="center"><!-- City -->
												<col width="85" align="center"><!-- State -->
												<col width="85" align="center"><!-- Postal Code -->
												<col width="85" align="center"><!-- Creation Date -->
												<col width="110" align="center"><!-- SIC Code -->
												<col width="85" align="center"><!-- Classification -->
												<col width="110" align="center"><!-- Account Group Name 1 -->
												<col width="110" align="center"><!-- Account Group Name 2 -->
												<col width="110" align="center"><!-- Account Group Name 3 -->
												<col width="110" align="center"><!-- Account Group Name 4 -->
												<col width="110" align="center"><!-- Account Group Name 5 -->
												<col width="70" align="center"><!-- Manual Entry -->
												<col width="150" align="center"><!-- Organization Name -->
												<col width="80" align="center"><!-- Organization ID -->
												<col width="80" align="center"><!-- XXXXX Territory ID 1 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 1 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 1 -->
												<col width="150" align="center"><!-- XXXXX Resource # 1 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 1 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 1 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 2 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 2 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 2 -->
												<col width="150" align="center"><!-- XXXXX Resource # 2 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 2 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 2 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 3 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 3 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 3 -->
												<col width="150" align="center"><!-- XXXXX Resource # 3 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 3 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 3 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 4 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 4 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 4 -->
												<col width="150" align="center"><!-- XXXXX Resource # 4 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 4 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 4 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 5 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 5 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 5 -->
												<col width="150" align="center"><!-- XXXXX Resource # 5 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 5 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 5 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 6 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 6 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 6 -->
												<col width="150" align="center"><!-- XXXXX Resource # 6 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 6 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 6 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 7 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 7 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 7 -->
												<col width="150" align="center"><!-- XXXXX Resource # 7 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 7 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 7 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 8 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 8 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 8 -->
												<col width="150" align="center"><!-- XXXXX Resource # 8 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 8 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 8 -->
												<col width="80" align="center"><!-- XXXXX Territory ID 9 -->
												<col width="150" align="center"><!-- XXXXX Territory Group 9 -->
												<col width="230" align="center"><!-- XXXXX Territory Name 9 -->
												<col width="150" align="center"><!-- XXXXX Resource # 9 -->
												<col width="150" align="center"><!-- XXXXX Resource Name 9 -->
												<col width="80" align="center"><!-- XXXXX Employee ID 9 -->
												<col width="80" align="center"><!-- XXXXX Territory ID A -->
												<col width="150" align="center"><!-- XXXXX Territory Group A -->
												<col width="230" align="center"><!-- XXXXX Territory Name A -->
												<col width="150" align="center"><!-- XXXXX Resource # A -->
												<col width="150" align="center"><!-- XXXXX Resource Name A -->
												<col width="80" align="center"><!-- XXXXX Employee ID A -->
												<col width="80" align="center"><!-- XXXXX Territory ID B -->
												<col width="150" align="center"><!-- XXXXX Territory Group B -->
												<col width="230" align="center"><!-- XXXXX Territory Name B -->
												<col width="150" align="center"><!-- XXXXX Resource # B -->
												<col width="150" align="center"><!-- XXXXX Resource Name B -->
												<col width="80" align="center"><!-- XXXXX Employee ID B -->
												<col width="80" align="center"><!-- XXXXX Territory ID C -->
												<col width="150" align="center"><!-- XXXXX Territory Group C -->
												<col width="230" align="center"><!-- XXXXX Territory Name C -->
												<col width="150" align="center"><!-- XXXXX Resource # C -->
												<col width="150" align="center"><!-- XXXXX Resource Name C -->
												<col width="80" align="center"><!-- XXXXX Employee ID C -->
												<col width="80" align="center"><!-- XXXXX Territory ID D -->
												<col width="150" align="center"><!-- XXXXX Territory Group D -->
												<col width="230" align="center"><!-- XXXXX Territory Name D -->
												<col width="150" align="center"><!-- XXXXX Resource # D -->
												<col width="150" align="center"><!-- XXXXX Resource Name D -->
												<col width="80" align="center"><!-- XXXXX Employee ID D -->
												<col width="80" align="center"><!-- XXXXX Territory ID E -->
												<col width="150" align="center"><!-- XXXXX Territory Group E -->
												<col width="230" align="center"><!-- XXXXX Territory Name E -->
												<col width="150" align="center"><!-- XXXXX Resource # E -->
												<col width="150" align="center"><!-- XXXXX Resource Name E -->
												<col width="80" align="center"><!-- XXXXX Employee ID E -->
												<col width="80" align="center"><!-- XXXXX Territory ID F -->
												<col width="150" align="center"><!-- XXXXX Territory Group F -->
												<col width="230" align="center"><!-- XXXXX Territory Name F -->
												<col width="150" align="center"><!-- XXXXX Resource # F -->
												<col width="150" align="center"><!-- XXXXX Resource Name F -->
												<col width="80" align="center"><!-- XXXXX Employee ID F -->
												<col width="80" align="center"><!-- XXXXX Territory ID G -->
												<col width="150" align="center"><!-- XXXXX Territory Group G -->
												<col width="230" align="center"><!-- XXXXX Territory Name G -->
												<col width="150" align="center"><!-- XXXXX Resource # G -->
												<col width="150" align="center"><!-- XXXXX Resource Name G -->
												<col width="80" align="center"><!-- XXXXX Employee ID G -->
												<col width="80" align="center"><!-- XXXXX Territory ID H -->
												<col width="150" align="center"><!-- XXXXX Territory Group H -->
												<col width="230" align="center"><!-- XXXXX Territory Name H -->
												<col width="150" align="center"><!-- XXXXX Resource # H -->
												<col width="150" align="center"><!-- XXXXX Resource Name H -->
												<col width="80" align="center"><!-- XXXXX Employee ID H -->
												<col width="80" align="center"><!-- XXXXX Territory ID I -->
												<col width="150" align="center"><!-- XXXXX Territory Group I -->
												<col width="230" align="center"><!-- XXXXX Territory Name I -->
												<col width="150" align="center"><!-- XXXXX Resource # I -->
												<col width="150" align="center"><!-- XXXXX Resource Name I -->
												<col width="80" align="center"><!-- XXXXX Employee ID I -->
												<col width="80" align="center"><!-- XXXXX Territory ID J -->
												<col width="150" align="center"><!-- XXXXX Territory Group J -->
												<col width="230" align="center"><!-- XXXXX Territory Name J -->
												<col width="150" align="center"><!-- XXXXX Resource # J -->
												<col width="150" align="center"><!-- XXXXX Resource Name J -->
												<col width="80" align="center"><!-- XXXXX Employee ID J -->
												<col width="80" align="center"><!-- XXXXX Territory ID K -->
												<col width="150" align="center"><!-- XXXXX Territory Group K -->
												<col width="230" align="center"><!-- XXXXX Territory Name K -->
												<col width="150" align="center"><!-- XXXXX Resource # K -->
												<col width="150" align="center"><!-- XXXXX Resource Name K -->
												<col width="100" align="center"><!-- XXXXX Employee ID K -->
												<tr height="37">
													<td id="BH0" class="pClothBs colFix"></td>
													<td id="BH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'locNum_B')" ezfhyo="B">Location ID</a><img id="sortIMG.locNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctNum_B')" ezfhyo="B">Account #</a><img id="sortIMG.dsAcctNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctTpDescTxt_B')" ezfhyo="B">Account<br>Category</a><img id="sortIMG.dsAcctTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctNm_B')" ezfhyo="B">Account Name</a><img id="sortIMG.dsAcctNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAllLineAddr_B')" ezfhyo="B">Address</a><img id="sortIMG.xxAllLineAddr_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'ctyAddr_B')" ezfhyo="B">City</a><img id="sortIMG.ctyAddr_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'stCd_B')" ezfhyo="B">State</a><img id="sortIMG.stCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'postCd_B')" ezfhyo="B">Postal Code</a><img id="sortIMG.postCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxCratDt_B')" ezfhyo="B">Creation Date</a><img id="sortIMG.xxCratDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsCustSicCd_B')" ezfhyo="B">SIC Code</a><img id="sortIMG.dsCustSicCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctClsDescTxt_B')" ezfhyo="B">Classification</a><img id="sortIMG.dsAcctClsDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctGrpNm_B1')" ezfhyo="B">Account Group<br>Name 1</a><img id="sortIMG.dsAcctGrpNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctGrpNm_B2')" ezfhyo="B">Account Group<br>Name 2</a><img id="sortIMG.dsAcctGrpNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctGrpNm_B3')" ezfhyo="B">Account Group<br>Name 3</a><img id="sortIMG.dsAcctGrpNm_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctGrpNm_B4')" ezfhyo="B">Account Group<br>Name 4</a><img id="sortIMG.dsAcctGrpNm_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'dsAcctGrpNm_B5')" ezfhyo="B">Account Group<br>Name 5</a><img id="sortIMG.dsAcctGrpNm_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'manEntryFlg_B')" ezfhyo="B">Manual Entry</a><img id="sortIMG.manEntryFlg_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B')" ezfhyo="B">Organization<br>Name</a><img id="sortIMG.orgNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgCd_B')" ezfhyo="B">Organization ID</a><img id="sortIMG.orgCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B1')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_01.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B2')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_02.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B3')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_03.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH43" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B4')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_04.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH44" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH45" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH46" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH47" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH48" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH49" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B5')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_05.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH50" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH51" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH52" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH53" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH54" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH55" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B6')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_06.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH56" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH57" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH58" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH59" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH60" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH61" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B7')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_07.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B7" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH62" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH63" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH64" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH65" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH66" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH67" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B8')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_08.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B8" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH68" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH69" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH70" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH71" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH72" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH73" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_B9')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_09.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_B9" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH74" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH75" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH76" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH77" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH78" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH79" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BA')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_10.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH80" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH81" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH82" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH83" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH84" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH85" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BB')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_11.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH86" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH87" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH88" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH89" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH90" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH91" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BC')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_12.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH92" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH93" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH94" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH95" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH96" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH97" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BD')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_13.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH98" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH99" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH100" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH101" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH102" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH103" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BE')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_14.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH104" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH105" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH106" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH107" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH108" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH109" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BF')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_15.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH110" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH111" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH112" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH113" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH114" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH115" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BG')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_16.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH116" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH117" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH118" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH119" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH120" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH121" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BH')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_17.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH122" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH123" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH124" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH125" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH126" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH127" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BI')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_18.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH128" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH129" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH130" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH131" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH132" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH133" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BJ')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_19.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH134" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'acctTrtyOrgCd_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Territory ID</a><img id="sortIMG.acctTrtyOrgCd_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH135" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'trtyGrpTpDescTxt_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Territory Group</a><img id="sortIMG.trtyGrpTpDescTxt_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH136" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'orgNm_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Territory Name</a><img id="sortIMG.orgNm_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH137" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNum_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Resource #</a><img id="sortIMG.xxAcctTrtyPsnNum_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH138" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnNm_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Resource Name</a><img id="sortIMG.xxAcctTrtyPsnNm_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="BH139" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxAcctTrtyPsnCd_BK')" ezfhyo="B"><%= bMsg.lineBizRoleTpNm_20.getValue() %><br>Employee ID</a><img id="sortIMG.xxAcctTrtyPsnCd_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1122px; height:223px; display:block; overflow:scroll; margin:0px; padding: 0px;">
											<table width="18875px" border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;">
												<col width="25"><!-- Radio Button -->
												<col width="85"><!-- Location ID -->
												<col width="85"><!-- Account # -->
												<col width="85"><!-- Account Category -->
												<col width="160"><!-- Account Name -->
												<col width="230"><!-- Address -->
												<col width="85"><!-- City -->
												<col width="85"><!-- State -->
												<col width="85"><!-- Postal Code -->
												<col width="85"><!-- Account Location Creation Date -->
												<col width="110"><!-- SIC Code -->
												<col width="85"><!-- Classification -->
												<col width="110"><!-- Account Group Name 1 -->
												<col width="110"><!-- Account Group Name 2 -->
												<col width="110"><!-- Account Group Name 3 -->
												<col width="110"><!-- Account Group Name 4 -->
												<col width="110"><!-- Account Group Name 5 -->
												<col width="70" align="center"><!-- Manual Entry -->
												<col width="150"><!-- Organization Name -->
												<col width="80"><!-- Organization ID -->
												<col width="80"><!-- XXXXX Territory ID 1 -->
												<col width="150"><!-- XXXXX Territory Group 1 -->
												<col width="230"><!-- XXXXX Territory Name 1 -->
												<col width="150"><!-- XXXXX Resource # 1 -->
												<col width="150"><!-- XXXXX Resource Name 1 -->
												<col width="80"><!-- XXXXX Employee ID 1 -->
												<col width="80"><!-- XXXXX Territory ID 2 -->
												<col width="150"><!-- XXXXX Territory Group 2 -->
												<col width="230"><!-- XXXXX Territory Name 2 -->
												<col width="150"><!-- XXXXX Resource # 2 -->
												<col width="150"><!-- XXXXX Resource Name 2 -->
												<col width="80"><!-- XXXXX Employee ID 2 -->
												<col width="80"><!-- XXXXX Territory ID 3 -->
												<col width="150"><!-- XXXXX Territory Group 3 -->
												<col width="230"><!-- XXXXX Territory Name 3 -->
												<col width="150"><!-- XXXXX Resource # 3 -->
												<col width="150"><!-- XXXXX Resource Name 3 -->
												<col width="80"><!-- XXXXX Employee ID 3 -->
												<col width="80"><!-- XXXXX Territory ID 4 -->
												<col width="150"><!-- XXXXX Territory Group 4 -->
												<col width="230"><!-- XXXXX Territory Name 4 -->
												<col width="150"><!-- XXXXX Resource # 4 -->
												<col width="150"><!-- XXXXX Resource Name 4 -->
												<col width="80"><!-- XXXXX Employee ID 4 -->
												<col width="80"><!-- XXXXX Territory ID 5 -->
												<col width="150"><!-- XXXXX Territory Group 5 -->
												<col width="230"><!-- XXXXX Territory Name 5 -->
												<col width="150"><!-- XXXXX Resource # 5 -->
												<col width="150"><!-- XXXXX Resource Name 5 -->
												<col width="80"><!-- XXXXX Employee ID 5 -->
												<col width="80"><!-- XXXXX Territory ID 6 -->
												<col width="150"><!-- XXXXX Territory Group 6 -->
												<col width="230"><!-- XXXXX Territory Name 6 -->
												<col width="150"><!-- XXXXX Resource # 6 -->
												<col width="150"><!-- XXXXX Resource Name 6 -->
												<col width="80"><!-- XXXXX Employee ID 6 -->
												<col width="80"><!-- XXXXX Territory ID 7 -->
												<col width="150"><!-- XXXXX Territory Group 7 -->
												<col width="230"><!-- XXXXX Territory Name 7 -->
												<col width="150"><!-- XXXXX Resource # 7 -->
												<col width="150"><!-- XXXXX Resource Name 7 -->
												<col width="80"><!-- XXXXX Employee ID 7 -->
												<col width="80"><!-- XXXXX Territory ID 8 -->
												<col width="150"><!-- XXXXX Territory Group 8 -->
												<col width="230"><!-- XXXXX Territory Name 8 -->
												<col width="150"><!-- XXXXX Resource # 8 -->
												<col width="150"><!-- XXXXX Resource Name 8 -->
												<col width="80"><!-- XXXXX Employee ID 8 -->
												<col width="80"><!-- XXXXX Territory ID 9 -->
												<col width="150"><!-- XXXXX Territory Group 9 -->
												<col width="230"><!-- XXXXX Territory Name 9 -->
												<col width="150"><!-- XXXXX Resource # 9 -->
												<col width="150"><!-- XXXXX Resource Name 9 -->
												<col width="80"><!-- XXXXX Employee ID 9 -->
												<col width="80"><!-- XXXXX Territory ID A -->
												<col width="150"><!-- XXXXX Territory Group A -->
												<col width="230"><!-- XXXXX Territory Name A -->
												<col width="150"><!-- XXXXX Resource # A -->
												<col width="150"><!-- XXXXX Resource Name A -->
												<col width="80"><!-- XXXXX Employee ID A -->
												<col width="80"><!-- XXXXX Territory ID B -->
												<col width="150"><!-- XXXXX Territory Group B -->
												<col width="230"><!-- XXXXX Territory Name B -->
												<col width="150"><!-- XXXXX Resource # B -->
												<col width="150"><!-- XXXXX Resource Name B -->
												<col width="80"><!-- XXXXX Employee ID B -->
												<col width="80"><!-- XXXXX Territory ID C -->
												<col width="150"><!-- XXXXX Territory Group C -->
												<col width="230"><!-- XXXXX Territory Name C -->
												<col width="150"><!-- XXXXX Resource # C -->
												<col width="150"><!-- XXXXX Resource Name C -->
												<col width="80"><!-- XXXXX Employee ID C -->
												<col width="80"><!-- XXXXX Territory ID D -->
												<col width="150"><!-- XXXXX Territory Group D -->
												<col width="230"><!-- XXXXX Territory Name D -->
												<col width="150"><!-- XXXXX Resource # D -->
												<col width="150"><!-- XXXXX Resource Name D -->
												<col width="80"><!-- XXXXX Employee ID D -->
												<col width="80"><!-- XXXXX Territory ID E -->
												<col width="150"><!-- XXXXX Territory Group E -->
												<col width="230"><!-- XXXXX Territory Name E -->
												<col width="150"><!-- XXXXX Resource # E -->
												<col width="150"><!-- XXXXX Resource Name E -->
												<col width="80"><!-- XXXXX Employee ID E -->
												<col width="80"><!-- XXXXX Territory ID F -->
												<col width="150"><!-- XXXXX Territory Group F -->
												<col width="230"><!-- XXXXX Territory Name F -->
												<col width="150"><!-- XXXXX Resource # F -->
												<col width="150"><!-- XXXXX Resource Name F -->
												<col width="80"><!-- XXXXX Employee ID F -->
												<col width="80"><!-- XXXXX Territory ID G -->
												<col width="150"><!-- XXXXX Territory Group G -->
												<col width="230"><!-- XXXXX Territory Name G -->
												<col width="150"><!-- XXXXX Resource # G -->
												<col width="150"><!-- XXXXX Resource Name G -->
												<col width="80"><!-- XXXXX Employee ID G -->
												<col width="80"><!-- XXXXX Territory ID H -->
												<col width="150"><!-- XXXXX Territory Group H -->
												<col width="230"><!-- XXXXX Territory Name H -->
												<col width="150"><!-- XXXXX Resource # H -->
												<col width="150"><!-- XXXXX Resource Name H -->
												<col width="80"><!-- XXXXX Employee ID H -->
												<col width="80"><!-- XXXXX Territory ID I -->
												<col width="150"><!-- XXXXX Territory Group I -->
												<col width="230"><!-- XXXXX Territory Name I -->
												<col width="150"><!-- XXXXX Resource # I -->
												<col width="150"><!-- XXXXX Resource Name I -->
												<col width="80"><!-- XXXXX Employee ID I -->
												<col width="80"><!-- XXXXX Territory ID J -->
												<col width="150"><!-- XXXXX Territory Group J -->
												<col width="230"><!-- XXXXX Territory Name J -->
												<col width="150"><!-- XXXXX Resource # J -->
												<col width="150"><!-- XXXXX Resource Name J -->
												<col width="80"><!-- XXXXX Employee ID J -->
												<col width="80"><!-- XXXXX Territory ID K -->
												<col width="150"><!-- XXXXX Territory Group K -->
												<col width="230"><!-- XXXXX Territory Name K -->
												<col width="150"><!-- XXXXX Resource # K -->
												<col width="150"><!-- XXXXX Resource Name K -->
												<col width="100"><!-- XXXXX Employee ID K -->
												<ezf:row ezfHyo="B">
													<tr height="25">
														<td>
															<ezf:inputRadio name="xxRadioBtn_B" ezfName="xxRadioBtn_B" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_B{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="locNum_B" ezfName="locNum_B" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctNum_B" ezfName="dsAcctNum_B" value="12345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctTpDescTxt_B" ezfName="dsAcctTpDescTxt_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctNm_B" ezfName="dsAcctNm_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"360\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAllLineAddr_B" ezfName="xxAllLineAddr_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"400\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="ctyAddr_B" ezfName="ctyAddr_B" value="XXXXXXXXX1XXXXXXXXX2XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"25\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="stNm_B" ezfName="stNm_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="postCd_B" ezfName="postCd_B" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxCratDt_B" ezfName="xxCratDt_B" value="04/25/2016" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsCustSicCd_B" ezfName="dsCustSicCd_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctClsDescTxt_B" ezfName="dsAcctClsDescTxt_B" value="XX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctGrpNm_B1" ezfName="dsAcctGrpNm_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctGrpNm_B2" ezfName="dsAcctGrpNm_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctGrpNm_B3" ezfName="dsAcctGrpNm_B3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctGrpNm_B4" ezfName="dsAcctGrpNm_B4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctGrpNm_B5" ezfName="dsAcctGrpNm_B5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="manEntryFlg_B" ezfName="manEntryFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="orgNm_B" ezfName="orgNm_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="orgCd_B" ezfName="orgCd_B" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B1" ezfName="acctTrtyOrgCd_B1" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B1" ezfName="trtyGrpTpDescTxt_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B1" ezfName="orgNm_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_01" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_01#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_01" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_01#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B1" ezfName="xxAcctTrtyPsnNum_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B1" ezfName="xxAcctTrtyPsnNm_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B1" ezfName="xxAcctTrtyPsnCd_B1" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B2" ezfName="acctTrtyOrgCd_B2" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B2" ezfName="trtyGrpTpDescTxt_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B2" ezfName="orgNm_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_02" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_02#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_02" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_02#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B2" ezfName="xxAcctTrtyPsnNum_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B2" ezfName="xxAcctTrtyPsnNm_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B2" ezfName="xxAcctTrtyPsnCd_B2" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B3" ezfName="acctTrtyOrgCd_B3" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B3" ezfName="trtyGrpTpDescTxt_B3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B3" ezfName="orgNm_B3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_03" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_03#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_03" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_03#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B3" ezfName="xxAcctTrtyPsnNum_B3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B3" ezfName="xxAcctTrtyPsnNm_B3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B3" ezfName="xxAcctTrtyPsnCd_B3" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B4" ezfName="acctTrtyOrgCd_B4" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B4" ezfName="trtyGrpTpDescTxt_B4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B4" ezfName="orgNm_B4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_04" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_04#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_04" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_04#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B4" ezfName="xxAcctTrtyPsnNum_B4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B4" ezfName="xxAcctTrtyPsnNm_B4" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B4" ezfName="xxAcctTrtyPsnCd_B4" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B5" ezfName="acctTrtyOrgCd_B5" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B5" ezfName="trtyGrpTpDescTxt_B5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B5" ezfName="orgNm_B5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_05" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_05#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_05" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_05#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B5" ezfName="xxAcctTrtyPsnNum_B5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B5" ezfName="xxAcctTrtyPsnNm_B5" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B5" ezfName="xxAcctTrtyPsnCd_B5" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B6" ezfName="acctTrtyOrgCd_B6" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B6" ezfName="trtyGrpTpDescTxt_B6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B6" ezfName="orgNm_B6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_06" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_06#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_06" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_06#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B6" ezfName="xxAcctTrtyPsnNum_B6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B6" ezfName="xxAcctTrtyPsnNm_B6" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B6" ezfName="xxAcctTrtyPsnCd_B6" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B7" ezfName="acctTrtyOrgCd_B7" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B7" ezfName="trtyGrpTpDescTxt_B7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B7" ezfName="orgNm_B7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_07" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_07#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_07" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_07#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B7" ezfName="xxAcctTrtyPsnNum_B7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B7" ezfName="xxAcctTrtyPsnNm_B7" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B7" ezfName="xxAcctTrtyPsnCd_B7" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B8" ezfName="acctTrtyOrgCd_B8" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B8" ezfName="trtyGrpTpDescTxt_B8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B8" ezfName="orgNm_B8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_08" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_08#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_08" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_08#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B8" ezfName="xxAcctTrtyPsnNum_B8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B8" ezfName="xxAcctTrtyPsnNm_B8" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B8" ezfName="xxAcctTrtyPsnCd_B8" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_B9" ezfName="acctTrtyOrgCd_B9" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_B9" ezfName="trtyGrpTpDescTxt_B9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_B9" ezfName="orgNm_B9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_09" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_09#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_09" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_09#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_B9" ezfName="xxAcctTrtyPsnNum_B9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_B9" ezfName="xxAcctTrtyPsnNm_B9" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_B9" ezfName="xxAcctTrtyPsnCd_B9" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BA" ezfName="acctTrtyOrgCd_BA" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BA" ezfName="trtyGrpTpDescTxt_BA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BA" ezfName="orgNm_BA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_10" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_10#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_10" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_10#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BA" ezfName="xxAcctTrtyPsnNum_BA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BA" ezfName="xxAcctTrtyPsnNm_BA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BA" ezfName="xxAcctTrtyPsnCd_BA" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BB" ezfName="acctTrtyOrgCd_BB" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BB" ezfName="trtyGrpTpDescTxt_BB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BB" ezfName="orgNm_BB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_11" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_11#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_11" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_11#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BB" ezfName="xxAcctTrtyPsnNum_BB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BB" ezfName="xxAcctTrtyPsnNm_BB" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BB" ezfName="xxAcctTrtyPsnCd_BB" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BC" ezfName="acctTrtyOrgCd_BC" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BC" ezfName="trtyGrpTpDescTxt_BC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BC" ezfName="orgNm_BC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_12" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_12#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_12" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_12#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BC" ezfName="xxAcctTrtyPsnNum_BC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BC" ezfName="xxAcctTrtyPsnNm_BC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BC" ezfName="xxAcctTrtyPsnCd_BC" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BD" ezfName="acctTrtyOrgCd_BD" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BD" ezfName="trtyGrpTpDescTxt_BD" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BD" ezfName="orgNm_BD" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_13" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_13#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_13" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_13#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BD" ezfName="xxAcctTrtyPsnNum_BD" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BD" ezfName="xxAcctTrtyPsnNm_BD" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BD" ezfName="xxAcctTrtyPsnCd_BD" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BE" ezfName="acctTrtyOrgCd_BE" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BE" ezfName="trtyGrpTpDescTxt_BE" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BE" ezfName="orgNm_BE" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_14" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_4#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_14" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_14#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BE" ezfName="xxAcctTrtyPsnNum_BE" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BE" ezfName="xxAcctTrtyPsnNm_BE" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BE" ezfName="xxAcctTrtyPsnCd_BE" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BF" ezfName="acctTrtyOrgCd_BF" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BF" ezfName="trtyGrpTpDescTxt_BF" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BF" ezfName="orgNm_BF" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_15" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_15#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_15" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_15#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BF" ezfName="xxAcctTrtyPsnNum_BF" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BF" ezfName="xxAcctTrtyPsnNm_BF" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BF" ezfName="xxAcctTrtyPsnCd_BF" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BG" ezfName="acctTrtyOrgCd_BG" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BG" ezfName="trtyGrpTpDescTxt_BG" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BG" ezfName="orgNm_BG" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_16" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_16#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_16" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_16#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BG" ezfName="xxAcctTrtyPsnNum_BG" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BG" ezfName="xxAcctTrtyPsnNm_BG" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BG" ezfName="xxAcctTrtyPsnCd_BG" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BH" ezfName="acctTrtyOrgCd_BH" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BH" ezfName="trtyGrpTpDescTxt_BH" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BH" ezfName="orgNm_BH" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_17" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_17#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_17" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_17#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BH" ezfName="xxAcctTrtyPsnNum_BH" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BH" ezfName="xxAcctTrtyPsnNm_BH" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BH" ezfName="xxAcctTrtyPsnCd_BH" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BI" ezfName="acctTrtyOrgCd_BI" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BI" ezfName="trtyGrpTpDescTxt_BI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BI" ezfName="orgNm_BI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_18" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_18#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_18" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_18#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BI" ezfName="xxAcctTrtyPsnNum_BI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BI" ezfName="xxAcctTrtyPsnNm_BI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BI" ezfName="xxAcctTrtyPsnCd_BI" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BJ" ezfName="acctTrtyOrgCd_BJ" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BJ" ezfName="trtyGrpTpDescTxt_BJ" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BJ" ezfName="orgNm_BJ" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_19" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_19#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_19" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_19#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BJ" ezfName="xxAcctTrtyPsnNum_BJ" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BJ" ezfName="xxAcctTrtyPsnNm_BJ" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BJ" ezfName="xxAcctTrtyPsnCd_BJ" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="acctTrtyOrgCd_BK" ezfName="acctTrtyOrgCd_BK" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="trtyGrpTpDescTxt_BK" ezfName="trtyGrpTpDescTxt_BK" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td>
															<ezf:inputText name="orgNm_BK" ezfName="orgNm_BK" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
															<ezf:inputButton name="OpenWin_TerritoryDtl_20" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TerritoryDtl_20#{EZF_ROW_NUMBER}\""/>
															<ezf:inputButton name="SearchTrtyInfo_20" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrtyInfo_20#{EZF_ROW_NUMBER}\""/>
														</td>
														<td><ezf:inputText name="xxAcctTrtyPsnNum_BK" ezfName="xxAcctTrtyPsnNum_BK" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnNm_BK" ezfName="xxAcctTrtyPsnNm_BK" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAcctTrtyPsnCd_BK" ezfName="xxAcctTrtyPsnCd_BK" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
<!-- ######################################## TO DETAIL B #################################### -->
<!-- ######################################## TO LAYOUT 2 #################################### -->
<% } %>
						<tr>
							<td align="right"><ezf:inputButton name="MoveWin_ViewAcct" value="View Account" htmlClass="pBtn8" /></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	<% if ("01".equals(bMsg.srchLyotTpCd.getValue())) { %>
		S21TableUI.initialize("parentBoxA", "AHEAD", "A");
	<% } else { %>
		S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, false, true);
	<% } %>
	</script>

<%-- **** Task specific area ends here **** --%>
