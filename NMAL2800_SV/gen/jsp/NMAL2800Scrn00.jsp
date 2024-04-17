<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180328155353 --%>
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
			<input type="hidden" name="pageID" value="NMAL2800Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="New Prospects Review">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ######################################## HEADER ######################################## --%>
				<%-- ### TAB - HEAD ### --%>
				<div class="pTab_HEAD">
					<!-- include S21BusinessProcessTAB -->
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
					<ezf:skip>
					<ul>
						<li class="pTab_ON"><a href="./NMAL2800Scrn00.html">New Pros Rev</a></li>
					</ul>
					</ezf:skip>
				</div>
				<%-- ### TAB - BODY ### --%>
				<div class="pTab_BODY">
					<%-- =============== Save Search =============== --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:20px;">
						<col width="115" align="left">
						<col width="305" align="left">
						<col width="105" align="left">
						<col width="300" align="left">
						<col width="83"  align="left">
						<col width="83"  align="left">
						<col width="">
						<tr height="25">
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:290px;\" tabindex=\"1\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td>
								<ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/>
							</td>
							<td>
								<ezf:inputButton name="Save_Search" ezfName="Save_Search" value="Save Search" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/>
								 </td>
							<td>
								<ezf:inputButton name="Delete_Search" ezfName="Delete_Search" value="Delete Search" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<hr>
					<%-- =============== Search =============== --%>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:20px;">
						<col width="125" align="left">
						<col width="170" align="left">
						<col width="155" align="left">
						<col width="225" align="left">
						<col width="390" align="left">
						<col width="">
						<tr>
							<td class="stab">Mode</td>
							<td>
								<ezf:select name="xxTpCd_H1" ezfName="xxTpCd_H1" ezfCodeName="xxTpCd_L" ezfDispName="xxTpNm_L" otherEvent1=" onchange=\"sendServer('Select_Mode')\"" otherAttr=" style=\"width:100px;\" tabindex=\"1\""/>
							</td>
							<td class="stab">Account Source Business Unit</td>
							<td colspan="2">
								<ezf:select name="lineBizTpCd_H1" ezfName="lineBizTpCd_H1" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:380px;\" tabindex=\"1\""/>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr height="28">
							<td class="stab">Prospects Created From</td>
							<td>
								<ezf:inputText name="xxDt10Dt_H1" ezfName="xxDt10Dt_H1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"1\""/>
								<img src="./img/calendar.gif" onclick="calendar('xxDt10Dt_H1',4);" tabindex="1">
							</td>
							<td class="stab">Prospects Created To</td>
							<td>
								<ezf:inputText name="xxDt10Dt_H2" ezfName="xxDt10Dt_H2" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"1\""/>
								<img src="./img/calendar.gif" onclick="calendar('xxDt10Dt_H2',4);" tabindex="1">
							</td>
							<td class="stab" align="right" style="padding-right:5px;"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="GoTo_DunsUpload" otherAttr=" tabindex=\"1\"">Prospect DUNS Upload</ezf:anchor></td>
						</tr>
						<tr>
							<td class="stab">Prospect Name(*)</td>
							<td><ezf:inputText name="befDsAcctNm_H1" ezfName="befDsAcctNm_H1" otherAttr=" size=\"20\" maxlength=\"360\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td class="stab">Prospect#(*)</td>
							<td><ezf:inputText name="rvwProsNum_H1" ezfName="rvwProsNum_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td rowspan="4">
								<table  border="0" cellpadding="0" cellspacing="0" width="100%">
									<col width="300">
									<col width="10">
									<col width="" align="left">
									<tr>
										<td class="stab">
											<fieldset>
												<legend>Advance Search</legend>
												<table  border="0" cellpadding="0" cellspacing="0" width="300">
													<col width="80" align="left">
													<col width="220" align="left">
													<tr>
														<td class="stab">Address(*)</td>
														<td><ezf:inputText name="shipAddrAllTxt_H1" ezfName="shipAddrAllTxt_H1" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\" tabindex=\"2\""/></td>
													</tr>
													<tr>
														<td class="stab">City(*)</td>
														<td><ezf:inputText name="befShipToCtyAddr_H1" ezfName="befShipToCtyAddr_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"2\""/></td>
													</tr>
													<tr>
														<td class="stab">State</td>
														<td>
															<ezf:select name="befShipToStNm_H1" ezfName="befShipToStNm_H1" ezfBlank="1" ezfCodeName="stCd_L" ezfDispName="stDescTxt_L" otherAttr=" style=\"width:140px;\" tabindex=\"2\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Postal Code(*)</td>
														<td><ezf:inputText name="befShipToPostCd_H1" ezfName="befShipToPostCd_H1" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"2\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td></td>
										<td valign="bottom" style="padding-right:5px;"><ezf:inputButton name="Search" ezfName="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"2\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OwnerInfo" otherAttr=" tabindex=\"1\"">Owner Name(*)</ezf:anchor></td>
							<td><ezf:inputText name="xxScrItem61Txt_H1" ezfName="xxScrItem61Txt_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_EmpInfo" otherAttr=" tabindex=\"1\"">Owner Employee ID(*)</ezf:anchor></td>
							<td><ezf:inputText name="befPsnNum_H1" ezfName="befPsnNum_H1" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/></td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OwnerTrtyInfo" otherAttr=" tabindex=\"1\"">Owner Territory Name(*)</ezf:anchor></td>
							<td><ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td class="stab">Processed</td>
							<td>
								<ezf:select name="prosRvwStsCd_H1" ezfName="prosRvwStsCd_H1" ezfCodeName="prosRvwStsCd_L" ezfDispName="prosRvwStsDescTxt_L" otherAttr=" style=\"width:200px;\" tabindex=\"1\""/>
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td class="stab">Potential Duplicate</td>
							<td>
								<ezf:select name="dupAcctLocFlg_H1" ezfName="dupAcctLocFlg_H1" ezfBlank="1" ezfCodeName="xxYesNoCd_L" ezfDispName="xxYesNoNm_L" otherAttr=" style=\"width:70px;\" tabindex=\"1\""/>
							</td>
						</tr>
					</table>
					<hr>
					<%-- ######################################## DETAIL ######################################## --%>
					<%-- =============== Paging =============== --%>
					<table border="0" cellpadding="0" cellspacing="0" width="1075" style="margin-left:20px;">
						<col width="535">
						<col align="right">
						<tr height="30">
							<td class="stab">
								<!-- Mod Start 2018/01/16 Hd.Sugawara QC#23148 -->
								<!-- <input type="button" class="pBtn6" value="Upload File" name="Upload" ezfName="Upload" onclick="sendServer(this)"> -->
								<!-- <input type="file" size="30" maxlength="999" name="xxFileData_U" ezfName="xxFileData_U"> -->
								<!-- &nbsp;&nbsp;<a href="#" onclick="sendServer('Download_Template')">Template</a> -->
								<ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"30\" maxlength=\"999\""/>
								<ezf:inputButton name="Upload" ezfName="Upload" value="Upload File" htmlClass="pBtn6" />
								<!-- Mod End   2018/01/16 Hd.Sugawara QC#23148 -->
							</td>
							<td style="padding-right:10px;">
								<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col width="196" align="right">
										<col width="5">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="16"  align="center">
										<col width="16"  align="center">
										<col width="16"  align="center">
										<col width="5">
										<col width="40"  align="right">
										<col width="5">
										<col width="40"  align="right">
										<col width="40"  align="right">
										<tr>
											<td class="stab">Results 1 - 40 of 200</td>
											<td></td>
											<td class="stab">Showing</td>
											<td><input type="text" size="2" maxlength="2" value="1" name="rtlWhCd_H1" ezfname="rtlWhCd_H1" ezftoupper></td>
											<td class="stab">/</td>
											<td class="pOut">20</td>
											<td class="stab">Page</td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										  </tr>
									</table>
								</ezf:skip>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"       value="FULL" />
								</jsp:include>
							</td>
						</tr>
					</table>
					<%-- =============== Search Result =============== --%>
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="top">
								<%-- ******************************* --%>
								<%-- *** Left Table Area(Header) *** --%>
								<%-- ******************************* --%>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; margin-left: 5px;">
									<col width="55"  align="center"><!-- Process Flag -->
									<col width="115"  align="center"><!-- Prospect Number -->
									<col width="223" align="center"><!-- Prospect Name -->
									<col width="157" align="center"><!-- Address1 -->
									<tr height="30">
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prosRvwStsCd_A1' )">Process Flag<img id="sortIMG.prosRvwStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rvwProsNum_A1' )">Prospect Number<img id="sortIMG.rvwProsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befDsAcctNm_A1' )">Prospect Name<img id="sortIMG.befDsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befLocFirstLineAddr_A1' )">Address1<img id="sortIMG.befLocFirstLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
								<%-- ******************************* --%>
								<%-- *** Left Table Area(Detail) *** --%>
								<%-- ******************************* --%>
								<div id="LeftTBL" style="overflow-y:hidden; overflow-x:hidden; height:283; width:; margin-left: 5px;" onScroll="synchroScrollTop(this.id, new Array('RightTBL'));">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A_Left">
										<col width="55"  align="center"><!-- Process Flag -->
										<col width="115"  align="center"><!-- Prospect Number -->
										<col width="223" align="center"><!-- Prospect Name -->
										<col width="157" align="center"><!-- Address1 -->
										<ezf:row ezfHyo="A">
										<tr height="25">
											<td>
												<ezf:select name="prosRvwStsCd_A1" ezfName="prosRvwStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prosRvwStsCd_L2" ezfDispName="prosRvwStsNm_L2" otherAttr=" style=\"width:45;\""/>
											</td>
											<td><ezf:inputText name="rvwProsNum_A1" ezfName="rvwProsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befDsAcctNm_A1" ezfName="befDsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befLocFirstLineAddr_A1" ezfName="befLocFirstLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										</tr>
										</ezf:row>
									</table>
								</div>
							</td>
							<td valign="top">
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Header) *** --%>
								<%-- ******************************** --%>
								<div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:555;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; width:6962;">
										<col width="157" align="center"><!-- Address2 -->
										<col width="157" align="center"><!-- Address3 -->
										<col width="157" align="center"><!-- Address4 -->
										<col width="157" align="center"><!-- City -->
										<col width="150" align="center"><!-- State Code -->
										<col width="115" align="center"><!-- Postal Code -->
										<col width="157" align="center"><!-- County -->
										<col width="223" align="center"><!-- Current Owner Name -->
										<col width="157" align="center"><!-- Line of business type -->
										<col width="263" align="center"><!-- Territory Candidates (Resource Base) -->
										<col width="263" align="center"><!-- Territory Candidates (Rule Base) -->
										<col width="90"  align="center"><!-- Creation Date -->
										<col width="263" align="center"><!-- Potential Duplicate LocationID -->
										<col width="120" align="center"><!-- Assign To Territory -->
										<col width="80"  align="center"><!-- Assign To Sales Rep -->
										<col width="237" align="center"><!-- Merge To Location ID -->
										<col width="237" align="center"><!-- Merge To Prospect# -->
										<col width="237" align="center"><!-- New Address1 -->
										<col width="157" align="center"><!-- New Address2 -->
										<col width="157" align="center"><!-- New Address3 -->
										<col width="157" align="center"><!-- New Address4 -->
										<col width="157" align="center"><!-- New City -->
										<col width="150" align="center"><!-- New State Code -->
										<col width="115" align="center"><!-- New Postal Code -->
										<col width="157" align="center"><!-- New County -->
										<col width="210" align="center"><!-- New Country -->
										<col width="115" align="center"><!-- Phone -->
										<col width="115" align="center"><!-- New Phone -->
										<col width="115" align="center"><!-- Fax -->
										<col width="115" align="center"><!-- New Fax -->
										<col width="185" align="center"><!-- Web Site -->
										<col width="90"  align="center"><!-- Last Update Date -->
										<col width="90"  align="center"><!-- DUNS Number -->
										<col width="80"  align="center"><!-- SIC Code -->
										<col width="90"  align="center"><!-- Ultimate DUNS Number -->
										<col width="157" align="center"><!-- New DNB Name -->
										<col width="90"  align="center"><!-- New Annual US Sales -->
										<col width="90"  align="center"><!-- New DUNS Number -->
										<col width="157" align="center"><!-- New Line Of Business Name -->
										<col width="80"  align="center"><!-- New Employees Total -->
										<col width="157" align="center"><!-- New SIC Code -->
										<col width="90"  align="center"><!-- New Ult DUNS Number -->
										<col width="90"  align="center"><!-- New Parent DUNS Number -->
										<col width="90"  align="center"><!-- New HQ DUNS Number -->
										<col width="157" align="center"><!-- New Company SIC -->
										<col width="223" align="center"><!-- New Company SIC Description -->
										<col width="90"  align="center"><!-- S21 Account Number -->
										<col width="90"  align="center"><!-- S21 Location ID -->
										<tr height="30">
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befLocScdLineAddr_A1' )">Address2<img id="sortIMG.befLocScdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befLocThirdLineAddr_A1' )">Address3<img id="sortIMG.befLocThirdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befLocFrthLineAddr_A1' )">Address4<img id="sortIMG.befLocFrthLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befShipToCtyAddr_A1' )">City<img id="sortIMG.befShipToCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befShipToStNm_A1' )">State Code<img id="sortIMG.befShipToStNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befShipToPostCd_A1' )">Postal Code<img id="sortIMG.befShipToPostCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befShipToCntyNm_A1' )">County<img id="sortIMG.befShipToCntyNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_A1' )">Current Owner Name<img id="sortIMG.xxScrItem61Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'lineBizTpDescTxt_A1' )">Line Of Business Type<img id="sortIMG.lineBizTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'resrcTrtyOrgNm_A1' )">Territory Candidates (Resource Base)<img id="sortIMG.resrcTrtyOrgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'candiTrtyNm_A1' )">Territory Candidates (Rule Base)<img id="sortIMG.candiTrtyNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTxt_A1' )">Creation Date<img id="sortIMG.xxDtTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'matchAcctLocNum_A1' )">Potential Duplicate Location ID<img id="sortIMG.matchAcctLocNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trtyOrgNm_A1' )">Assign To Territory<img id="sortIMG.trtyOrgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_A2' )">Assign To Sales Rep<img id="sortIMG.xxScrItem61Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocNum_A1' )">Merge To Location ID<img id="sortIMG.aftLocNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsXrefAcctCd_A1' )">Merge To Prospect#<img id="sortIMG.aftDsXrefAcctCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocFirstLineAddr_A1' )">New Address1<img id="sortIMG.aftLocFirstLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocScdLineAddr_A1' )">New Address2<img id="sortIMG.aftLocScdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocThirdLineAddr_A1' )">New Address3<img id="sortIMG.aftLocThirdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocFrthLineAddr_A1' )">New Address4<img id="sortIMG.aftLocFrthLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocCtyAddr_A1' )">New City<img id="sortIMG.aftLocCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocStCd_A1' )">New State Code<img id="sortIMG.aftLocStCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftLocPostCd_A1' )">New Postal Code<img id="sortIMG.aftLocPostCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cntyNm_A1' )">New County<img id="sortIMG.cntyNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftCtryCd_A1' )">New Country<img id="sortIMG.aftCtryCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befTelNum_A1' )">Phone<img id="sortIMG.befTelNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftTelNum_A1' )">New Phone<img id="sortIMG.aftTelNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befFaxNum_A1' )">Fax<img id="sortIMG.befFaxNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftFaxNum_A1' )">New Fax<img id="sortIMG.aftFaxNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctUrl_A1' )">Web Site<img id="sortIMG.dsAcctUrl_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTxt_A2' )">Last Update Date<img id="sortIMG.xxDtTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befDunsNum_A1' )">DUNS Number<img id="sortIMG.befDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befDsCustSicCd_A1' )">SIC Code<img id="sortIMG.befDsCustSicCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'befDsUltDunsNum_A1' )">Ultimate DUNS Number<img id="sortIMG.befDsUltDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsAcctDunsNm_A1' )">New DNB Name<img id="sortIMG.aftDsAcctDunsNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsLocRevAmt_A1' )">New Annual US Sales<img id="sortIMG.aftDsLocRevAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDunsNum_A1' )">New DUNS Number<img id="sortIMG.aftDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsCustSicDescTxt_A1' )">New Line Of Business<img id="sortIMG.aftDsCustSicDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsLocEmpNum_A1' )">New Employees Total<img id="sortIMG.aftDsLocEmpNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsCustSicCd_A1' )">New SIC Code<img id="sortIMG.aftDsCustSicCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsUltDunsNum_A1' )">New Ult DUNS Number<img id="sortIMG.aftDsUltDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsPrntDunsNum_A1' )">New Parent DUNS Number<img id="sortIMG.aftDsPrntDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftHqDunsNum_A1' )">New HQ DUNS Number<img id="sortIMG.aftHqDunsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsCmpySicCd_A1' )">New Company SIC<img id="sortIMG.aftDsCmpySicCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aftDsCmpySicDescTxt_A1' )">New Company SIC Description<img id="sortIMG.aftDsCmpySicDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">S21 Account Number<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A1' )">S21 Location ID<img id="sortIMG.locNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Detail) *** --%>
								<%-- ******************************** --%>
								<div id="RightTBL" style="overflow-y:scroll; overflow-x:scroll; height:300; width:572; word-break:break-all;" onScroll="synchroScrollTop(this.id, new Array('LeftTBL'));synchroScrollLeft(this.id, new Array('topRightTBL'));">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; width:6962;" id="A_Right">
										<col width="157" align="center"><!-- Address2 -->
										<col width="157" align="center"><!-- Address3 -->
										<col width="157" align="center"><!-- Address4 -->
										<col width="157" align="center"><!-- City -->
										<col width="150" align="center"><!-- State Code -->
										<col width="115" align="center"><!-- Postal Code -->
										<col width="157" align="center"><!-- County -->
										<col width="223" align="center"><!-- Current Owner Name -->
										<col width="157" align="center"><!-- Line of business type -->
										<col width="263" align="center"><!-- Territory Candidates (Resource Base) -->
										<col width="263" align="center"><!-- Territory Candidates (Rule Base) -->
										<col width="90"  align="center"><!-- Creation Date -->
										<col width="263" align="center"><!-- Potential Duplicate LocationID -->
										<col width="120" align="center"><!-- Assign To Territory -->
										<col width="80"  align="center"><!-- Assign To Sales Rep -->
										<col width="237" align="center"><!-- Merge To Location ID -->
										<col width="237" align="center"><!-- Merge To Prospect# -->
										<col width="237" align="center"><!-- New Address1 -->
										<col width="157" align="center"><!-- New Address2 -->
										<col width="157" align="center"><!-- New Address3 -->
										<col width="157" align="center"><!-- New Address4 -->
										<col width="157" align="center"><!-- New City -->
										<col width="150" align="center"><!-- New State Code -->
										<col width="115" align="center"><!-- New Postal Code -->
										<col width="157" align="center"><!-- New County -->
										<col width="210" align="center"><!-- New Country -->
										<col width="115" align="center"><!-- Phone -->
										<col width="115" align="center"><!-- New Phone -->
										<col width="115" align="center"><!-- Fax -->
										<col width="115" align="center"><!-- New Fax -->
										<col width="185" align="center"><!-- Web Site -->
										<col width="90"  align="center"><!-- Last Update Date -->
										<col width="90"  align="center"><!-- DUNS Number -->
										<col width="80"  align="center"><!-- SIC Code -->
										<col width="90"  align="center"><!-- Ultimate DUNS Number -->
										<col width="157" align="center"><!-- New DNB Name -->
										<col width="90"  align="center"><!-- New Annual US Sales -->
										<col width="90"  align="center"><!-- New DUNS Number -->
										<col width="157" align="center"><!-- New Line Of Business Name -->
										<col width="80"  align="center"><!-- New Employees Total -->
										<col width="157" align="center"><!-- New SIC Code -->
										<col width="90"  align="center"><!-- New Ult DUNS Number -->
										<col width="90"  align="center"><!-- New Parent DUNS Number -->
										<col width="90"  align="center"><!-- New HQ DUNS Number -->
										<col width="157" align="center"><!-- New Company SIC -->
										<col width="223" align="center"><!-- New Company SIC Description -->
										<col width="90"  align="center"><!-- S21 Account Number -->
										<col width="90"  align="center"><!-- S21 Location ID -->
										<ezf:row ezfHyo="A">
										<tr height="25" id="id_rightA_row{EZF_ROW_NUMBER}">
											<td><ezf:inputText name="befLocScdLineAddr_A1" ezfName="befLocScdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befLocThirdLineAddr_A1" ezfName="befLocThirdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befLocFrthLineAddr_A1" ezfName="befLocFrthLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befShipToCtyAddr_A1" ezfName="befShipToCtyAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td>
												<ezf:select name="befShipToStNm_A1" ezfName="befShipToStNm_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stCd_L" ezfDispName="stDescTxt_L" otherAttr=" style=\"width:140;\""/>
											</td>
											<td><ezf:inputText name="befShipToPostCd_A1" ezfName="befShipToPostCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befShipToCntyNm_A1" ezfName="befShipToCntyNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="xxScrItem61Txt_A1" ezfName="xxScrItem61Txt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"61\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="lineBizTpDescTxt_A1" ezfName="lineBizTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td>
												<ezf:inputText name="resrcTrtyOrgNm_A1" ezfName="resrcTrtyOrgNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"2000\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_CandiTrtyResrc" ezfName="OpenWin_CandiTrtyResrc" value="T" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td>
												<ezf:inputText name="candiTrtyNm_A1" ezfName="candiTrtyNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"4000\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_CandiTrtyRule" ezfName="OpenWin_CandiTrtyRule" value="T" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td><ezf:inputText name="xxDtTxt_A1" ezfName="xxDtTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
											<td>
												<ezf:inputText name="matchAcctLocNum_A1" ezfName="matchAcctLocNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"4000\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_CandiLoc" ezfName="OpenWin_CandiLoc" value="L" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td>
												<ezf:inputText name="trtyOrgNm_A1" ezfName="trtyOrgNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_TrtyToAssign" ezfName="OpenWin_TrtyToAssign" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td><ezf:inputText name="xxScrItem61Txt_A2" ezfName="xxScrItem61Txt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"61\" ezftoupper=\"\""/></td>
											<td>
												<ezf:inputText name="aftLocNum_A1" ezfName="aftLocNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_LocInfo" ezfName="OpenWin_LocInfo" value="L" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
												<ezf:inputButton name="OpenWin_MergeProsToBe" ezfName="OpenWin_MergeProsToBe" value="MF" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td>
												<ezf:inputText name="aftDsXrefAcctCd_A1" ezfName="aftDsXrefAcctCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_ProsRvwInfo" ezfName="OpenWin_ProsRvwInfo" value="P" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
												<ezf:inputButton name="OpenWin_MergeProsAsIs" ezfName="OpenWin_MergeProsAsIs" value="MF" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td>
											    <ezf:inputButton name="Copy_Address" ezfName="Copy_Address" value="Copy" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
												<ezf:inputText name="aftLocFirstLineAddr_A1" ezfName="aftLocFirstLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_AddrInput" ezfName="OpenWin_AddrInput" value="A" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td><ezf:inputText name="aftLocScdLineAddr_A1" ezfName="aftLocScdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftLocThirdLineAddr_A1" ezfName="aftLocThirdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftLocFrthLineAddr_A1" ezfName="aftLocFrthLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftLocCtyAddr_A1" ezfName="aftLocCtyAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"25\" ezftoupper=\"\""/></td>
											<td>
												<ezf:select name="aftLocStCd_A1" ezfName="aftLocStCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stCd_LL" ezfDispName="stDescTxt_LL" ezfCodeDispHyo="A" otherAttr=" style=\"width:140;\""/>
											</td>
											<td><ezf:inputText name="aftLocPostCd_A1" ezfName="aftLocPostCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cntyNm_A1" ezfName="cntyNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td>
												<ezf:select name="aftCtryCd_A1" ezfName="aftCtryCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ctryCd_L" ezfDispName="ctryDescTxt_L" otherEvent1=" onchange=\"sendServer('Select_Country')\"" otherAttr=" style=\"width:200;\""/>
											</td>
											<td><ezf:inputText name="befTelNum_A1" ezfName="befTelNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"51\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftTelNum_A1" ezfName="aftTelNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befFaxNum_A1" ezfName="befFaxNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"51\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftFaxNum_A1" ezfName="aftFaxNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="dsAcctUrl_A1" ezfName="dsAcctUrl_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"4000\""/></td>
											<td><ezf:inputText name="xxDtTxt_A2" ezfName="xxDtTxt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befDunsNum_A1" ezfName="befDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befDsCustSicCd_A1" ezfName="befDsCustSicCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="befDsUltDunsNum_A1" ezfName="befDsUltDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsAcctDunsNm_A1" ezfName="aftDsAcctDunsNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsLocRevAmt_A1" ezfName="aftDsLocRevAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDunsNum_A1" ezfName="aftDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsCustSicDescTxt_A1" ezfName="aftDsCustSicDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsLocEmpNum_A1" ezfName="aftDsLocEmpNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsCustSicCd_A1" ezfName="aftDsCustSicCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsUltDunsNum_A1" ezfName="aftDsUltDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsPrntDunsNum_A1" ezfName="aftDsPrntDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftHqDunsNum_A1" ezfName="aftHqDunsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsCmpySicCd_A1" ezfName="aftDsCmpySicCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="aftDsCmpySicDescTxt_A1" ezfName="aftDsCmpySicDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"200\" ezftoupper=\"\""/></td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctMain" ><ezf:label name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></ezf:anchor></td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_LocMain" ><ezf:label name="locNum_A1" ezfName="locNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></ezf:anchor></td>
										</tr>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<%-- ### TAB - BODY ### --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
