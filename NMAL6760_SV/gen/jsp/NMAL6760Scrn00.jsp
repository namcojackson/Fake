<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180705103941 --%>
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
			<input type="hidden" name="pageID" value="NMAL6760Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Account Search Popup">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top" width="980">
								<div style="border-style: solid ; border-width: 1px; width:100%; margin-top:8px; margin-bottom:8px; ">
								<table border="0" align="left">
									<col width="80"><!-- Search Mode -->
									<col width="115"><!-- Search Mode -->
									<col width="30"><!-- Status -->
									<col width="155"><!-- Status -->
									<col width="95"><!-- Display Address -->
									<col width="20"><!-- Display Address -->
									<col width="140"><!-- Display Hierarchy Accounts -->
									<col width="110"><!-- Display Hierarchy Accounts -->
									<tr>
										</tr>
									<tr>
										<td class="stab">Search Mode:</td>
										<td>
											<ezf:select name="xxAcctSrchModeCd_D1" ezfName="xxAcctSrchModeCd_D1" ezfCodeName="xxAcctSrchModeCd_01" ezfDispName="xxModeNm23Txt_01" />
										</td>
										<td class="stab">Status:</td>
										<td>
											<ezf:select name="xxAcctSrchStsCd_D2" ezfName="xxAcctSrchStsCd_D2" ezfCodeName="xxAcctSrchStsCd_02" ezfDispName="xxModeNm23Txt_02" otherEvent1=" onchange=\"sendServer('OnChange_SearchSts')\"" />
										</td>
										<td class="stab">Display Addresses:</td>
										<td><ezf:inputCheckBox name="xxChkBox_01" ezfName="xxChkBox_01" value="Y" /></td>
										
										<td class="stab">Display Hierarchy Account:</td>
										<td>
											<ezf:select name="xxAcctSrchDplyHrchCd_D3" ezfName="xxAcctSrchDplyHrchCd_D3" ezfCodeName="xxAcctSrchDplyHrchCd_03" ezfDispName="xxModeNm23Txt_03" />
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
						<tr>
							<td valign="left" width="980">
							<table border="0" align="left">
								<td valign="top" width="980">
								<div style="border-style: solid ; border-width: 1px; width:100%;">
								<table border="0" align="center">
								<col width="120">
								<col width="130">
								<col width="30">
								<col width="30">
								<col width="10">
								<col width="130">
								<col width="170">
								<col width="130">
								<col width="130">
									<tr>
										<td class="stab">Account Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsAcctNm_01" ezfName="dsAcctNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\""/></td>
										<td></td>
										<td  class="stab">DUNS#(*):</td>
										<td><ezf:inputText name="dsAcctDunsNum_01" ezfName="dsAcctDunsNum_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">External Ref#(*):</td>
										<td><ezf:inputText name="dsXtrnlRefTxt_01" ezfName="dsXtrnlRefTxt_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab">Address(*):</td>
										<td colspan="3"><ezf:inputText name="xxAllLineAddr_01" ezfName="xxAllLineAddr_01" value="XX" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">UDUNS#(*):</td>
										<td><ezf:inputText name="dsUltDunsNum_01" ezfName="dsUltDunsNum_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">Category:</td>
										<td><ezf:select name="dsAcctTpCd_DP" ezfName="dsAcctTpCd_DP" ezfBlank="1" ezfCodeName="dsAcctTpCd_01" ezfDispName="dsAcctTpNm_01" />
										</td>
									</tr>
									<tr>
										<td class="stab">City(*):</td>
										<td><ezf:inputText name="ctyAddr_01" ezfName="ctyAddr_01" value="XX" otherAttr=" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/></td>
										<td class="stab">State:</td>
										<td>
											<ezf:select name="stCd_DP" ezfName="stCd_DP" ezfBlank="1" ezfCodeName="stCd_01" ezfDispName="stNm_01" />
										</td>
										<td></td>
										<td class="stab">Industry(*)</td>
										<td><ezf:inputText name="dsCustSicDescTxt_01" ezfName="dsCustSicDescTxt_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
										<td class="stab">Internal / External:</td>
										<td><ezf:select name="dsAcctItrlFlg_C1" ezfName="dsAcctItrlFlg_C1" ezfBlank="1" ezfCodeName="dsAcctItrlFlg_01" ezfDispName="xxCtlNm_01" />
										</td>
									<tr>
										<td class="stab">Postal Code(*):</td>
										<td colspan="3"><ezf:inputText name="postCd_01" ezfName="postCd_01" value="XX" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Ctac Last Name(*):</td>
										<td><ezf:inputText name="ctacPsnLastNm_01" ezfName="ctacPsnLastNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="dsAcctNum_L1" ezfName="dsAcctNum_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctNum" otherAttr=" id=\"dsAcctNum_L1\" ezfanchortext=\"\"">Account#(*):</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="dsAcctNum_01" ezfName="dsAcctNum_01" value="XXXXXXX8" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Ctac First Name(*):</td>
										<td><ezf:inputText name="ctacPsnFirstNm_01" ezfName="ctacPsnFirstNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
										<td class="stab">Display Related Accts:</td>
										<td>
											<ezf:select name="xxAcctSrchDplyRelnCd_D4" ezfName="xxAcctSrchDplyRelnCd_D4" ezfBlank="1" ezfCodeName="xxAcctSrchDplyRelnCd_04" ezfDispName="xxModeNm23Txt_04" />
										</td>
									</tr>
									<tr>
										<td class="stab">Location#(*):</td>
										<td colspan="3"><ezf:inputText name="locNum_01" ezfName="locNum_01" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Ctac Phone#(*):</td>
										<td><ezf:inputText name="ctacPsnTelNum_01" ezfName="ctacPsnTelNum_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">Related Acct Name(*):</td>
										<td><ezf:inputText name="dsAcctNm_RT" ezfName="dsAcctNm_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab">DBA Name(*):</td>
										<td colspan="3"><ezf:inputText name="dbaNm_01" ezfName="dbaNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Ctac Email#(*):</td>
										<td><ezf:inputText name="ctacPsnEmlAddr_01" ezfName="ctacPsnEmlAddr_01" value="XX" otherAttr=" size=\"20\" maxlength=\"320\" ezftoupper=\"\""/></td>
										<td class="stab">Related Acct#(*):</td>
										<td><ezf:inputText name="dsAcctNum_RT" ezfName="dsAcctNum_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab">Acct Legal Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsAcctLegalNm_01" ezfName="dsAcctLegalNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Cross Ref Category:</td>
										<td>
											<ezf:select name="dsXrefAcctTpCd_DP" ezfName="dsXrefAcctTpCd_DP" ezfBlank="1" ezfCodeName="dsXrefAcctTpCd_01" ezfDispName="dsXrefAcctTpNm_01" otherAttr=" style=\"width:145px\""/>
										</td>
										<td class="stab">Related Address:</td>
										<td><ezf:inputText name="xxAllLineAddr_RT" ezfName="xxAllLineAddr_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"62\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="dsAcctGrp_L1" ezfName="dsAcctGrp_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctGrp" otherAttr=" ezfanchortext=\"\"">Acct Group Name(*):</ezf:anchor></td>
										<td colspan="3">
											<ezf:inputText name="dsAcctGrpCd_DP" ezfName="dsAcctGrpCd_DP" value="XX" otherAttr=" size=\"10\" maxlength=\"28\" ezftoupper=\"\""/>
											<ezf:inputText name="dsAcctGrpDescTxt_DP" ezfName="dsAcctGrpDescTxt_DP" value="XX" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td class="stab">Cross Ref#(*):</td>
										<td><ezf:inputText name="dsXrefAcctCd_01" ezfName="dsXrefAcctCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td colspan="2" class="stab">
											<ezf:inputCheckBox name="xxChkBox_02" ezfName="xxChkBox_02" value="Y" />Display Inactive Location
										</td>
									</tr>	
									<tr>
										<td class="stab">Acct Classification:</td>
										<td colspan="3">
											<ezf:select name="dsAcctClsCd_DP" ezfName="dsAcctClsCd_DP" ezfBlank="1" ezfCodeName="dsAcctClsCd_01" ezfDispName="dsAcctClsNm_01" otherAttr=" style=\"width:210px\""/>
										</td>
										<td></td>
										<td class="stab">Bill To Code(*):</td>
										<td><ezf:inputText name="billToCustCd_01" ezfName="billToCustCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td colspan="2" class="stab">
											<ezf:inputCheckBox name="xxChkBox_03" ezfName="xxChkBox_03" value="Y" />Display Inactive Bill to / Ship to
											<span style="width:85px;"></span>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" id=\"btn11\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Location Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsLocNm_01" ezfName="dsLocNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Ship To Code(*):</td>
										<td><ezf:inputText name="shipToCustCd_01" ezfName="shipToCustCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td></td>
									</tr>
								</table>
								</div>
							</td>
							</table>
							</td>
							
							
						</tr>
						
					</table>
<%-- #################################################### DETAIL ################################################### --%>
<%-- #################################################### Quick View Area ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="1000">
									<tr>
										<td align="right">
											<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="left">	
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																<tr>
																	<td>Results 990 - 1000 of 1000</td>
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
																			
																	<td><input type="text" name="xxPageShowCurNum_31" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" class="pPro" readOnly></td>
																	<td class="stab">/</td>
																	<td><input type="text" name="xxPageShowTotNum_31" ezfName="xxPageShowTotNum_31" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'C')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'C')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'C')" disabled></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ezf:skip>
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="C" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"	value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"	value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<div id="parentBoxA">
									<table border="0">
										<tr>
											<td valign="Top" width="1007">
										    <div style="float:left"> <!-- right table -->
												<div id='rightTblHead' style="width:980px; overflow-x:hidden; overflow-y:none; ">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD">
														<col align="center" width="75">
														<col align="center" width="70">
														<col align="center" width="150">
														<col align="center" width="155">
														<col align="center" width="60">
														<col align="center" width="40">
														<col align="center" width="60">
														<col align="center" width="100">
														<col align="center" width="82">
														<col align="center" width="75">
														<col align="center" width="55">
														<col align="center" width="55">
														<col align="center" width="60">
													<%
														if ("Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_03())) {
													%>
														<col align="center" width="60">
														<col align="center" width="65">
														<col align="center" width="75">
														<col align="center" width="80">
													<%
														}
													%>
														<tr height="24">
															<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNum_C1' )">Acct#<img id="sortIMG.dsAcctNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctRelnTpNm_C1' )">Relation<img id="sortIMG.dsAcctRelnTpNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNm_C1' )">Acct Name<img id="sortIMG.dsAcctNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxAllLineAddr_C1' )">Address<img id="sortIMG.xxAllLineAddr_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'locNum_C1' )">Loc#<img id="sortIMG.locNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxYesNoCd_C1' )">Prim<img id="sortIMG.xxYesNoCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctTpNm_C1' )">Type<img id="sortIMG.dsAcctTpNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxCtlNm_C1' )">Relationship(s)<img id="sortIMG.xxCtlNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'relnDsAcctNum_C1' )">Parent Acct#<img id="sortIMG.relnDsAcctNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNum_C2' )">Orig Acct#<img id="sortIMG.dsAcctNum_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'billToCustCd_C1' )">Bill To<img id="sortIMG.billToCustCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'shipToCustCd_C1' )">Ship To<img id="sortIMG.shipToCustCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C1' )">Acct Sts<img id="sortIMG.xxRgtnStsTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<%
														if ("Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_03())) {
													%>
															<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C2' )">Loc Sts<img id="sortIMG.xxRgtnStsTxt_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C3' )">Bill To Sts<img id="sortIMG.xxRgtnStsTxt_C3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C4' )">Ship To Sts<img id="sortIMG.xxRgtnStsTxt_C4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C5' )">Acct Loc Sts<img id="sortIMG.xxRgtnStsTxt_C5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<%
														}
													%>
														</tr>
													</table>
												</div><!-- rightTblHead -->
												<div  id="rightTbl" style="width:997px; height:187px; word-break:break-all; overflow-y:scroll; overflow-x:scroll;" onscroll="synchroBottomScroll();">
													<table style="table-layout:fixed;"  border="1" cellpadding="0" cellspacing="0" id="C">
														<col align="left" width="75">
														<col align="left" width="70">
														<col align="left" width="150">
														<col align="left" width="155">
														<col align="left" width="60">
														<col align="center" width="40">
														<col align="left" width="60">
														<col align="left" width="100">
														<col align="left" width="82">
														<col align="left" width="75">
														<col align="left" width="55">
														<col align="left" width="55">
														<col align="left" width="60">
													<%
														if ("Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_03())) {
													%>
														<col align="left" width="60">
														<col align="left" width="65">
														<col align="left" width="75">
														<col align="left" width="80">
													<%
														}
													%>
	                                                    <ezf:row ezfHyo="C">
														<tr height="24">
															<td><ezf:anchor name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectAcct" otherAttr=" ezfanchortext=\"\" id=\"dsAcctNum_C1#{EZF_ROW_NUMBER}\"">
																<ezf:label name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
															
															<td><ezf:inputText name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"21\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"21\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:anchor name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectAcct" otherAttr=" ezfanchortext=\"\" id=\"locNum_C1#{EZF_ROW_NUMBER}\"">
																<ezf:label name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
															<td><ezf:inputText name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:label name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" /></a></td>
															<td><ezf:label name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C" ezfArrayNo="0" /></a></td>
															<td><ezf:anchor name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectAcct" otherAttr=" ezfanchortext=\"\" id=\"billToCustCd_C1#{EZF_ROW_NUMBER}\"">
																<ezf:label name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
															<td><ezf:anchor name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectAcct" otherAttr=" ezfanchortext=\"\" id=\"shipToCustCd_C1#{EZF_ROW_NUMBER}\"">
																<ezf:label name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
															<td><ezf:label name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
													<%
														if ("Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6760.NMAL6760Bean)databean).getXxChkBox_03())) {
													%>
															<td><ezf:label name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxRgtnStsTxt_C5" ezfName="xxRgtnStsTxt_C5" ezfHyo="C" ezfArrayNo="0" /></td>
													<%
														}
													%>
														</tr>
														</ezf:row>
	                                                    <ezf:skip>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														<tr height="24">
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="dsAcctNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><input type="text" name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="C" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><a href="#" name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="locNum_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="locNum_C1" ezfName="locNum_C1" ezfHyo="C">12345678</label></a></td>
															<td><input type="text" name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWW" ezfHyo="C" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><input type="text" name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
															<td><label ezfout name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C">1234567890</label></a></td>
															<td><label ezfout name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C">1234567890</label></a></td>
															<td><a href="#" name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><a href="#" name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfanchortext onclick="sendServer('SelectAcct')" id="shipToCustCd_C1#{EZF_ROW_NUMBER}">
																<label ezfout name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C">1234567</label></a></td>
															<td><label ezfout name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C">Active</label></td>
															<td><label ezfout name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C">Inactive</label></td>
														</tr>
														</ezf:skip>
														
													</table>
												</div><!-- rightTbl -->
											</td>
										</tr>
									</table>
								</div><!-- parentBoxA -->
							</td>
						</tr>
					</table>
<%-- #################################################### Quick View Area ################################################### --%>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'rightTbl' );
		var topTBL    = document.getElementById( 'rightTblHead'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
