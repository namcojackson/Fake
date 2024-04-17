<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231113081244 --%>
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
			<input type="hidden" name="pageID" value="NMAL6700Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Account Info">
			
			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Account Info" class="pTab_ON"><a href="#">Acct Info</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table width="98%" border="0" align="center">
						<col>
						<tr>
							<td>
								<table border="0" style="table-layout:fixed" width="1">
									<col width="130">
									<col width="50">
									<col width="40">
									<col width="734">
									<col width="120">

									<tr height="21">
										<td class="stab">Account Name</td>
										<td colspan="3"><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"60\" maxlength=\"360\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr height="21">
										<td class="stab">Account Number</td>
										<td colspan="3"><ezf:label name="dsAcctNum_H1" ezfName="dsAcctNum_H1" /></td>
										<td><ezf:inputButton name="OpenWin_UploadLocation" value="Upload Locations" htmlClass="pBtn10" /></td>
									</tr>
									<tr height="21">
										<td class="stab">Active</td>
										<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('OnChange_activeFlg')" /></td>
										<td class="stab">Reason</td>
										<td>
											<ezf:select name="dsAcctInacRsnCd_H3" ezfName="dsAcctInacRsnCd_H3" ezfBlank="1" ezfCodeName="dsAcctInacRsnCd_H1" ezfDispName="dsAcctInacRsnNm_H2" otherAttr=" style=\"width: 700px\""/>
										</td>
										<td><ezf:inputButton name="OpenTrx_Download" value="Download Open Trx" htmlClass="pBtn10" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center">
						<col>
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="130">
									<col width="35">
									<col width="35">
									<col width="170">
									<tr height="21">
										<td class="stab">Category</td>
										<td colspan="3"><ezf:select name="dsAcctTpCd_H3" ezfName="dsAcctTpCd_H3" ezfBlank="1" ezfCodeName="dsAcctTpCd_H1" ezfDispName="dsAcctTpNm_H2" /></td>
									</tr>
									<tr height="21">
										<td class="stab">Internal/External</td>
										<td colspan="3"><ezf:select name="dsAcctItrlFlg_H3" ezfName="dsAcctItrlFlg_H3" ezfBlank="1" ezfCodeName="dsAcctItrlFlg_H1" ezfDispName="xxCtlNm_H2" otherEvent1=" onchange=\"sendServer('OnChange_dsAcctItrlFlg')\"" /></td>
									</tr>
									<tr height="21">
										<td class="stab">Classification</td>
										<td colspan="3"><ezf:select name="dsAcctClsCd_H3" ezfName="dsAcctClsCd_H3" ezfBlank="1" ezfCodeName="dsAcctClsCd_H1" ezfDispName="dsAcctClsNm_H2" /></td>
									</tr>
									<tr height="21">
										<td class="stab"><ezf:anchor name="coaChCd_H1" ezfName="coaChCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Acct" >GL Sales Channel</ezf:anchor></td>
										<td><ezf:inputText name="coaChCd_H1" ezfName="coaChCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="GetCoaChNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="coaChNm_H1" ezfName="coaChNm_H1" value="WWWWWWWWW1WWWWWWWWW2WWWW" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									</tr>
									<tr height="21">
										<td class="stab"><ezf:anchor name="coaAfflCd_H1" ezfName="coaAfflCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_InterCompany" >GL Intercompany Code</ezf:anchor></td>
										<td><ezf:inputText name="coaAfflCd_H1" ezfName="coaAfflCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="GetInterCompanyNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="coaAfflNm_H1" ezfName="coaAfflNm_H1" value="WWWWWWWWW1WWWWWWWWW2WWWW" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Dealer Code</td>
										<td colspan="3"><ezf:select name="dsAcctDlrCd_H3" ezfName="dsAcctDlrCd_H3" ezfBlank="1" ezfCodeName="dsAcctDlrCd_H1" ezfDispName="dsAcctDlrNm_H2" /></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="140">
									<col width="240">
									<tr height="21">
										<td class="stab">Account Legal Name</td>
										<td><ezf:inputText name="dsAcctLegalNm_H1" ezfName="dsAcctLegalNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Does Business As(DBA)</td>
										<td><ezf:inputText name="dbaNm_H1" ezfName="dbaNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">DNB Name</td>
										<td><ezf:inputText name="dsAcctDunsNm_H1" ezfName="dsAcctDunsNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Alternate Acct Name</td>
										<td><ezf:inputText name="dsAcctAltNm_H1" ezfName="dsAcctAltNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">External Reference</td>
										<td><ezf:inputText name="dsXtrnlRefTxt_H1" ezfName="dsXtrnlRefTxt_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Source</td>
										<td><ezf:inputText name="dsDataSrcTxt_H1" ezfName="dsDataSrcTxt_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="140">
									<col width="120">
									<tr height="21">
										<td class="stab">Inactivated Date</td>
										<td><ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr height="21">
										<td class="stab"></td>
										<td><br /></td>
									</tr>
									<tr height="21">
										<td class="stab"></td>
										<td><br /></td>
									</tr>
									<tr height="21">
										<td class="stab"></td>
										<td></td>
										<td><br /></td>
									</tr>
									<tr height="21">
										<td class="stab">Active Customer Activity</td>
										<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
<%-- #################################################### DETAIL ################################################### --%>
<%
    String AddresslFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_01();
    String HierarchyFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_02();
    String RelnshipsFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_03();
    String ContactsFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_04();
    String MarketingFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_05();
    String TransactionsFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_06();
    String CrCltFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_07();
    String InvBllgFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_08();
    String BankAcctFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_09();
    String MsgNoteFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_10();
    String ShippingFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_11();
    String TaxingFlg = ((business.servlet.NMAL6700.NMAL6700Bean)databean).getXxTabProt_12();
%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
							<div class="pTab_HEAD">
								<ul>
<%if(AddresslFlg.equals("Y")){%>
									<li class="pTab_ON" id="Addresses" title="Addresses"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Addresses" >Addresses</ezf:anchor></li>
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Hierarchy" title="Hierarchy"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Hierarchy" >Hierarchy</ezf:anchor></li>
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Relnships" title="Relationships"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Relnships" >Relationships</ezf:anchor></li>
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Contacts" title="Contacts"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_Contacts" >Contacts</ezf:anchor></li>
<%}%>
<%if(MarketingFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Marketing" title="Marketing"><ezf:anchor name="" ezfName="xxTabProt_05" ezfEmulateBtn="1" ezfGuard="TAB_Marketing" >Marketing</ezf:anchor></li>
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Transactions" title="Transactions"><ezf:anchor name="" ezfName="xxTabProt_06" ezfEmulateBtn="1" ezfGuard="TAB_Transactions" >Transactions</ezf:anchor></li>
<%}%>
<%if(CrCltFlg.equals("Y")){%>
									<li class="pTab_OFF" id="CrClt" title="Collections"><ezf:anchor name="" ezfName="xxTabProt_07" ezfEmulateBtn="1" ezfGuard="TAB_CrClt" >Collections</ezf:anchor></li>
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
									<li class="pTab_OFF" id="InvBllg" title="Invoice/Billing"><ezf:anchor name="" ezfName="xxTabProt_08" ezfEmulateBtn="1" ezfGuard="TAB_InvBllg" >Invoice/Billing</ezf:anchor></li>
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
									<li class="pTab_OFF" id="BankAcct" title="Bank Accounts"><ezf:anchor name="" ezfName="xxTabProt_09" ezfEmulateBtn="1" ezfGuard="TAB_BankAcct" >Bank Accounts</ezf:anchor></li>
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
									<li class="pTab_OFF" id="MsgNote" title="Instructions"><ezf:anchor name="" ezfName="xxTabProt_10" ezfEmulateBtn="1" ezfGuard="TAB_MsgNote" >Instructions</ezf:anchor></li>
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Shipping" title="Shipping"><ezf:anchor name="" ezfName="xxTabProt_11" ezfEmulateBtn="1" ezfGuard="TAB_Shipping" >Shipping</ezf:anchor></li>
<%}%>
<%if(TaxingFlg.equals("Y")){%>
									<li class="pTab_OFF" id="Taxing" title="Taxing"><ezf:anchor name="" ezfName="xxTabProt_12" ezfEmulateBtn="1" ezfGuard="TAB_Taxing" >Taxing</ezf:anchor></li>
<%}%>
								</ul>
							</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Addresses'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_ON";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In">
								<table border="0" style="table-layout:fixed; margin-left:5px;" width="1">
									<col width="175">
									<col width="72">
									<col width="25">
									<col width="80">
									<col width="60">
									<col width="110">
									<col align="right" width="550">
									<tr>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td class="stab">Show Inactive</td>
										<td><ezf:inputCheckBox name="xxChkBox_AX" ezfName="xxChkBox_AX" value="Y" onClick="sendServer('Search')" /></td>
										<td><ezf:inputButton name="AddLocation" value="Add Location" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="OpenWin_AddressSearch" value="Filter" htmlClass="pBtn4" /></td>
										<td><ezf:inputButton name="DownloadLocationTemplate" value="Download Template" htmlClass="pBtn10" /></td>
									<!-- Pagination & Navigation--START-->
										<td>
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
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="A" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_A1" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_A1" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_A1" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_A1" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_A1" />
												<jsp:param name="ViewMode"         value="FULL" />
												</jsp:include>
										</td>
									<!-- Pagination & Navigation--END-->
									</tr>
								</table>
								<table>
									<col valign="top">
									<tr>
										<td>
											<div id="parentBoxA">
												<div style="float:left; display:block"> <!-- left table -->
													<div id='leftTblHead' style="display:block;">
													</div>
													<div id='leftTbl' style="float:left; display:block; height:230px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
													</div>
												</div>  <!-- left table -->
												<div style="float:left"> <!-- right table -->
													<div id='rightTblHead' style="width:1109px; display:block; overflow:hidden; margin:0px; padding:0px;">
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="970px" style="margin-right:20px" >
															<col align="center" width="120">	<!-- Location# -->
															<col align="center" width="150">	<!-- ADDRESS1 -->
															<col align="center" width="150">	<!-- ADDRESS2 -->
															<col align="center" width="70">		<!-- CITY -->
															<col align="center" width="50">		<!-- STATE -->
															<col align="center" width="60">		<!-- POSTAL CODE -->
															<col align="center" width="40">		<!-- BILL TO -->
															<col align="center" width="40">		<!-- SHIP TO -->
															<col align="center" width="60">		<!-- PRIMARY -->
															<col align="center" width="120">	<!-- RELATED ACCT -->
															<col align="center" width="80">		<!-- START DATE -->
															<col align="center" width="80">		<!-- END DATE -->
															<col align="center" width="70">		<!-- STATUS -->
															<tr height="34px">
																<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A1' )">Location#<img id="sortIMG.locNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstLineAddr_A1' )">Address1<img id="sortIMG.firstLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdLineAddr_A1' )">Address2<img id="sortIMG.scdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctyAddr_A1' )">City<img id="sortIMG.ctyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stCd_A1' )">State<img id="sortIMG.stCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'postCd_A1' )">Postal<br />Code<img id="sortIMG.postCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_AB' )">Bill<br />To<img id="sortIMG.xxChkBox_AB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_AS' )">Ship<br />To<img id="sortIMG.xxChkBox_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_AP' )">Primary<img id="sortIMG.xxChkBox_AP" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'relnDsAcctNum_A1' )">Related Acct<img id="sortIMG.relnDsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A1' )">Start Date<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A1' )">End Date<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctStsNm_A1' )">Status<img id="sortIMG.dsAcctStsNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs">Location#</td>
																<td class="pClothBs">Address1</td>
																<td class="pClothBs">Address2</td>
																<td class="pClothBs">City</td>
																<td class="pClothBs">State</td>
																<td class="pClothBs">Postal<br />Code</td>
																<td class="pClothBs">Bill<br />To</td>
																<td class="pClothBs">Ship<br />To</td>
																<td class="pClothBs">Primary</td>
																<td class="pClothBs">Related Acct</td>
																<td class="pClothBs">Start Date</td>
																<td class="pClothBs">End Date</td>
																<td class="pClothBs">Status</td>
															</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:1109px; height:230px; display:block; overflow:scroll; margin:0px; padding:0px;" >
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="970px" >
															<col width="120">		<!-- Location# -->
															<col width="150">		<!-- ADDRESS1 -->
															<col width="150">		<!-- ADDRESS2 -->
															<col width="70">		<!-- CITY -->
															<col width="50">		<!-- STATE -->
															<col width="60">		<!-- POSTAL CODE -->
															<col align="center" width="40">		<!-- BILL TO -->
															<col align="center" width="40">		<!-- SHIP TO -->
															<col align="center" width="60">		<!-- PRIMARY -->
															<col width="120">		<!-- RELATED ACCT -->
															<col width="80">		<!-- START DATE -->
															<col width="80">		<!-- END DATE -->
															<col width="70">		<!-- STATUS -->
															<ezf:row ezfHyo="A">
																<tr height="23px" id="id_leftA_row{EZF_ROW_NUMBER}">
																	<td><ezf:anchor name="locNum_AC" ezfName="locNum_AC" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ShowLocation" otherAttr=" id=\"ShowLocation#{EZF_ROW_NUMBER}\""><ezf:label name="locNum_A1" ezfName="locNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																	<td><ezf:inputText name="firstLineAddr_A1" ezfName="firstLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="scdLineAddr_A1" ezfName="scdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="ctyAddr_A1" ezfName="ctyAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="stCd_A1" ezfName="stCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="postCd_A1" ezfName="postCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputCheckBox name="xxChkBox_AB" ezfName="xxChkBox_AB" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputCheckBox name="xxChkBox_AS" ezfName="xxChkBox_AS" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputCheckBox name="xxChkBox_AP" ezfName="xxChkBox_AP" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="xxRelnDsAcctTxt_A1" ezfName="xxRelnDsAcctTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="dsAcctStsNm_A1" ezfName="dsAcctStsNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	</td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr id="id_leftA_row{EZF_ROW_NUMBER}">
																	<td><a href="#" name="locNum_AC" ezfName="locNum_AC" onclick="sendServer('ShowLocation')" tabindex="-1" ezfhyo="A" ezfArrayNo="0" id="ShowLocation#{EZF_ROW_NUMBER}"><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></a></td>
																	<td><label ezfout name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="scdLineAddr_A1" ezfname="scdLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="ctyAddr_A1" ezfname="ctyAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="stCd_A1" ezfname="stCd_A1" ezfhyo="A">WW</label></td>
																	<td><label ezfout name="postCd_A1" ezfname="postCd_A1" ezfhyo="A">12345</label></td>
																	<td><input type="checkbox" name="xxChkBox_AB" value="Y" ezfname="xxChkBox_AB" ezfhyo="A"></td>
																	<td><input type="checkbox" name="xxChkBox_AS" value="Y" ezfname="xxChkBox_AS" ezfhyo="A"></td>
																	<td><input type="checkbox" name="xxChkBox_AP" value="Y" ezfname="xxChkBox_AP" ezfhyo="A"></td>
																	<td><label ezfout name="relnDsAcctNum_A1" ezfname="relnDsAcctNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A">2014/01/22</label></td>
																	<td><label ezfout name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A"></label></td>
																	<td><label ezfout name="dsAcctStsNm_A1" ezfname="dsAcctStsNm_A1" ezfhyo="A">WWWWWWWW</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div><!-- rightTbl -->
												</div> <!-- right table -->
											</div> <!-- parent box -->
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Hierarchy'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_ON";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="100">
												<col align="left" width="40">
												<col align="left" width="140">
												<col align="left" width="40">
												<col width="20">
												<col align="right">
												<tr>
													<td class="stab">Show Inactive</td>
													<td><ezf:inputCheckBox name="xxChkBox_BI" ezfName="xxChkBox_BI" value="Y" onClick="sendServer('Search')" /></td>
													<td class="stab">Show All Addresses</td>
													<td><ezf:inputCheckBox name="xxChkBox_BH" ezfName="xxChkBox_BH" value="Y" onClick="sendServer('Search')" /></td>
													<td><br /></td>
													<td><ezf:inputButton name="ShowAcctFromHierarchy" value="Show Account" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="1" cellpadding="0" cellspacing="0"  style="table-layout: fixed">
												<col width="290">
												<col width="190">
												<col width="175">
												<col width="70">
												<col width="40">
												<col width="80">
												<col width="60">
												<col width="50">
												<col width="65">
												<col width="60">
												<col width="20">
												<!--<col width="15">-->
												<tr height="24">
													<td align="center" class="pClothBs">Acct#</td>
													<td align="center" class="pClothBs">Acct Name</td>
													<td align="center" class="pClothBs">Address</td>
													<td align="center" class="pClothBs">City</td>
													<td align="center" class="pClothBs">State</td>
													<td align="center" class="pClothBs">PostalCode</td>
													<td align="center" class="pClothBs">Loc#</td>
													<td align="center" class="pClothBs">Primary</td>
													<td align="center" class="pClothBs">Account</td>
													<td align="center" class="pClothBs">Status</td>
													<td align="center" class="pClothBs">&nbsp;</td>
													<!--<td align=center class="pClothBs">&nbsp;</td>-->
												</tr>
											</table>
											<div style="overflow-y:scroll; height:260; width:1117; background-color:#FFFFFF">
											<%@ page import="business.servlet.NMAL6700.NMAL6700Bean" %>
											<% if (((NMAL6700Bean)databean).getTreeView() != null) { %>
												<uji:treeView
													bean				= "${__screenName__}"
													indentIcon			= "./img/treeView/child.gif;./img/treeView/lastchild.gif;./img/treeView/hasmorechild.gif;./img/treeView/nomorechild.gif;"
													insets				= "0"
													cellSpacing			= "0"
													background			= "#FFFFFF"
													stripeBackground	= "#D7E2E2"
													css					= "pTreeView pTreeViewLongDesc"
													noWrap				= "true"
													borderWidth			= "1"
													ruleWidth			= "1"
													rules				= "all"
													dataEscape			= "false;"

													property				= "treeView"
													dataCellType			= "data;data;data;data;data;data;data;data;data;data;radio;data;"
													columnWidth				= "290;190;175;70;40;80;60;50;65;60;20;0;"
													dataAlignmentHorizontal	= "left;left;left;left;left;left;left;center;left;left;center;left;"
													dataEditable			= ";true;true;true;;;;;;;;"
												/>
											<% } %>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Relnships'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_ON";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%" >
												<col width="100">
												<col width="20">
                                                <col width="150">
                                                <col width="20">
												<col width="75">
												<col width="75">
												<col width="75">
												<col  align="right">
												<tr>
													<td class="stab">Show Inactive</td>
													<td><ezf:inputCheckBox name="xxChkBox_CX" ezfName="xxChkBox_CX" value="Y" onClick="sendServer('Search')" /></td>
                                                    <td class="stab">Inactive Customer Check</td>
                                                    <td><ezf:inputCheckBox name="xxChkBox_CY" ezfName="xxChkBox_CY" value="Y" /></td>
													<td><ezf:inputButton name="AddRelnship" value="Add" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteRelnship" value="Delete" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="OpenWin_RelationshipFilter" value="Filter" htmlClass="pBtn6" /></td>
											
													<td align="right" width="645">
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"          value="C" />
															<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_C1" />
															<jsp:param name="ShowingTo"        value="xxPageShowToNum_C1" />
															<jsp:param name="ShowingOf"        value="xxPageShowOfNum_C1" />
															<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_C1" />
															<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_C1" />
															<jsp:param name="ViewMode"         value="FULL" />
														</jsp:include>
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
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_C_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="20">	<!-- Check Box -->
														<col align="center" width="150">	<!-- Relationship Type -->
														<col align="center" width="200">	<!-- Related Account# -->
														<col align="center" width="340">	<!-- Related Account Name -->
														<col align="center" width="50">		<!-- BILL TO -->
														<col align="center" width="50">		<!-- SHIP TO -->
														<col align="center" width="70">		<!-- Reciprocal -->
														<col align="center" width="105">	<!-- Start Date -->
														<col align="center" width="105">	<!-- End Date -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Relationship Type</td>
															<td class="pClothBs">Related Account#</td>
															<td class="pClothBs">Related Account Name</td>
															<td class="pClothBs">Bill To</td>
															<td class="pClothBs">Ship To</td>
															<td class="pClothBs">Reciprocal</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_C" style="overflow-y:scroll; height:250; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
														<col align="center" width="20">	<!-- Check Box -->
														<col align="center" width="150">	<!-- Relationship Type -->
														<col width="200">	<!-- Related Account# -->
														<col width="340">	<!-- Related Account Name -->
														<col align="center" width="50">		<!-- BILL TO -->
														<col align="center" width="50">		<!-- SHIP TO -->
														<col align="center" width="70">		<!-- Reciprocal -->
														<col align="center" width="105">	<!-- Start Date -->
														<col align="center" width="105">	<!-- End Date -->
													<ezf:row ezfHyo="C">
														<tr id="id_leftC_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:select name="dsAcctRelnTpCd_C3" ezfName="dsAcctRelnTpCd_C3" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsAcctRelnTpCd_C1" ezfDispName="dsAcctRelnTpNm_C2" ezfCodeDispHyo="C" /></td>
															<td>
															    <ezf:inputButton name="ShowAcct" value="R" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"ShowAcct{EZF_ROW_NUMBER}\""/>
															    <ezf:inputButton name="OpenWin_AcctSrch" value="A" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AcctSrch{EZF_ROW_NUMBER}\""/>
															    <ezf:inputText name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<ezf:inputButton name="GetDsAcctNm" value=">>" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"GetDsAcctNm{EZF_ROW_NUMBER}\""/>
															</td>
															<td><ezf:label name="dsAcctNm_C1" ezfName="dsAcctNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:inputCheckBox name="xxChkBox_CB" ezfName="xxChkBox_CB" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:inputCheckBox name="xxChkBox_CS" ezfName="xxChkBox_CS" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:inputCheckBox name="xxChkBox_CR" ezfName="xxChkBox_CR" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="effFromDt_C1" ezfName="effFromDt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"effFromDt_C1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="effThruDt_C1" ezfName="effThruDt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"effThruDt_C1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
															<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_leftC_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_C1" value="Y" ezfname="xxChkBox_C1" ezfhyo="C" id="xxChkBox_C1#{EZF_ROW_NUMBER}"></td>
															<td><select name="dsAcctRelnTpCd_C3" ezfname="dsAcctRelnTpCd_C3" ezflist="dsAcctRelnTpCd_C1,dsAcctRelnTpNm_C2,99, C,blank" ezfhyo="C"><option>Parent</option><option>lease</option></select></td>
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" onclick="sendServer('ShowAcct')" tabindex="-1" ezfhyo="C" ezfArrayNo="0" id="ShowAcct#{EZF_ROW_NUMBER}"><label ezfout name="dsAcctNum_C1" ezfname="dsAcctNum_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></a></td>
															<td><label ezfout name="dsAcctNm_C1" ezfname="dsAcctNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CB" ezfname="xxChkBox_CB" ezfhyo="C"></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CS" ezfname="xxChkBox_CS" ezfhyo="C"></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CR" ezfname="xxChkBox_CR" ezfhyo="C"></td>
															<td><input id="effFromDt_C1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_C1" ezfname="effFromDt_C1" ezfhyo="C"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input id="effThruDt_C1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_C1" ezfname="effThruDt_C1" ezfhyo="C"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
														</tr>
														<tr id="id_leftC_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_C1" value="Y" ezfname="xxChkBox_C1" ezfhyo="C" id="xxChkBox_C1#{EZF_ROW_NUMBER}"></td>
															<td><select name="dsAcctRelnTpCd_C3" ezfname="dsAcctRelnTpCd_C3" ezflist="dsAcctRelnTpCd_C1,dsAcctRelnTpNm_C2,99, C,blank" ezfhyo="C"><option>Parent</option><option>lease</option></select></td>
															<td><a href="#" name="dsAcctNum_C1" ezfName="dsAcctNum_C1" onclick="sendServer('ShowAcct')" tabindex="-1" ezfhyo="C" ezfArrayNo="0" id="ShowAcct#{EZF_ROW_NUMBER}"><label ezfout name="dsAcctNum_C1" ezfname="dsAcctNum_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></a></td>
															<td><label ezfout name="dsAcctNm_C1" ezfname="dsAcctNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CB" ezfname="xxChkBox_CB" ezfhyo="C"></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CS" ezfname="xxChkBox_CS" ezfhyo="C"></td>
															<td><input type="checkbox" value="Y" name="xxChkBox_CR" ezfname="xxChkBox_CR" ezfhyo="C"></td>
															<td><input id="effFromDt_C1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_C1" ezfname="effFromDt_C1" ezfhyo="C"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input id="effThruDt_C1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_C1" ezfname="effThruDt_C1" ezfhyo="C"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C1#{EZF_ROW_NUMBER}', 4);"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Contacts'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_ON";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
							
								<table border="0" style="table-layout:fixed; margin-left:5px;" width="1">
									<col width="185">
									<col width="72">
									<col width="25">
									<col width="80">
									<col width="80">
									<col width="80">
									<col align="right" width="550">
									<tr>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td class="stab">Show Inactive</td>
										<td><ezf:inputCheckBox name="xxChkBox_DX" ezfName="xxChkBox_DX" value="Y" onClick="sendServer('Search')" /></td>
										<td align="left"><ezf:inputButton name="AddContact" value="Add" htmlClass="pBtn6" /></td>
										<td align="left"><ezf:inputButton name="OpenWin_CtacSearch" value="Filter" htmlClass="pBtn6" /></td>
										<td align="left"><ezf:inputButton name="OpenWin_UploadContact" value="Upload" htmlClass="pBtn6" /></td>
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="D" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_D1" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_D1" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_D1" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_D1" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_D1" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<table>
									<col valign="top">
									<tr>
										<td>
											<div id="parentBoxD">
												<div style="float:left; display:block"><!-- left table -->
													<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
													<div id="leftTbl" style="float:left; display:block; height:230px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
												</div><!-- left table -->
												<div style="float:left"><!-- right table -->

													<div id="rightTblHead" style="width:1110px; display:block; overflow:hidden; margin:0px;padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="DHEAD" width="1100px" style="table-layout: fixed; margin-right:20px">
																<col align="center" width="80">		<!-- Contact ID -->
																<col align="center" width="100">	<!-- Type -->
																<col align="center" width="110">	<!-- First Name -->
																<col align="center" width="110">	<!-- Last Name -->
																<col align="center" width="100">	<!-- Department -->
																<col align="center" width="140">	<!-- Email-Work -->
																<col align="center" width="100">	<!-- Phone-Work -->
																<col align="center" width="50">		<!-- Ext -->
																<col align="center" width="90">	<!-- Start Date -->
																<col align="center" width="90">	<!-- End Date -->
																<col align="center" width="50">		<!-- Primary -->
																<col align="center" width="70">		<!-- Status -->
																<tr height="34">
																	<td class="pClothBs" id="DH0"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacPsnPk_D1' )">Contact ID<img id="sortIMG.ctacPsnPk_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td class="pClothBs" id="DH1"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacTpNm_D1' )">Relationship to CSA<img id="sortIMG.ctacTpNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td> -->
																	<td class="pClothBs" id="DH1"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacTpDescTxt_D1' )">Relationship to CSA<img id="sortIMG.ctacTpDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td class="pClothBs" id="DH2"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacPsnFirstNm_D1' )">First Name<img id="sortIMG.ctacPsnFirstNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH3"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacPsnLastNm_D1' )">Last Name<img id="sortIMG.ctacPsnLastNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH4"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsCtacPsnDeptNm_D1' )">Department<img id="sortIMG.dsCtacPsnDeptNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH5"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsCtacPntValTxt_D1' )">Email-Work<img id="sortIMG.dsCtacPntValTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH6"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsCtacPntValTxt_D2' )">Phone-Work<img id="sortIMG.dsCtacPntValTxt_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH7"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ctacPsnExtnNum_D1' )">Ext<img id="sortIMG.ctacPsnExtnNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH8"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effFromDt_D1' )">Start Date<img id="sortIMG.effFromDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH9"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effThruDt_D1' )">End Date<img id="sortIMG.effThruDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH10"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsPrimLocFlg_D1' )">Primary<img id="sortIMG.dsPrimLocFlg_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="DH11"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dplStsNm_D1' )">Status<img id="sortIMG.dplStsNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:1110px; height:230px; display:block; overflow:scroll; margin:0px; padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed">
																<col width="80">		<!-- Contact ID -->
																<col width="100">		<!-- Type -->
																<col width="110">		<!-- First Name -->
																<col width="110">		<!-- Last Name -->
																<col width="100">		<!-- Department -->
																<col width="140">		<!-- Email-Work -->
																<col width="100">		<!-- Phone-Work -->
																<col width="50">		<!-- Ext -->
																<col width="90">		<!-- Start Date -->
																<col width="90">		<!-- End Date -->
																<col align="center" width="50">		<!-- Primary -->
																<col width="70">		<!-- Status -->
															<ezf:row ezfHyo="D">
																<tr height="23" id="id_leftD_row{EZF_ROW_NUMBER}">
																	<td><ezf:anchor name="ctacPsnPk_D1" ezfName="ctacPsnPk_D1" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ShowContact" otherAttr=" id=\"ShowContact#{EZF_ROW_NUMBER}\""><ezf:label name="ctacPsnNum_D1" ezfName="ctacPsnNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><label ezfout name="ctacTpNm_D1" ezfname="ctacTpNm_D1" ezfhyo="D">WWWWWWWWW1WW</label></td> -->
																	<td><ezf:label name="ctacTpDescTxt_D1" ezfName="ctacTpDescTxt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><ezf:label name="ctacPsnFirstNm_D1" ezfName="ctacPsnFirstNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="ctacPsnLastNm_D1" ezfName="ctacPsnLastNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dsCtacPsnDeptNm_D1" ezfName="dsCtacPsnDeptNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dsCtacPntValTxt_D1" ezfName="dsCtacPntValTxt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dsCtacPntValTxt_D2" ezfName="dsCtacPntValTxt_D2" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="ctacPsnExtnNum_D1" ezfName="ctacPsnExtnNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="effFromDt_D1" ezfName="effFromDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="effThruDt_D1" ezfName="effThruDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:inputCheckBox name="dsPrimLocFlg_D1" ezfName="dsPrimLocFlg_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dplStsNm_D1" ezfName="dplStsNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
																	</td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr id="id_leftD_row{EZF_ROW_NUMBER}">
																	<td><a href="#" name="ctacPsnPk_D1" ezfName="ctacPsnPk_D1" onclick="sendServer('ShowContact')" tabindex="-1" ezfhyo="D" ezfArrayNo="0" id="ShowContact#{EZF_ROW_NUMBER}"><label ezfout name="ctacPsnPk_D1" ezfname="ctacPsnPk_D1" ezfhyo="D">WWWWWWWWW1WWWWWWWWW2</label></a></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><select name="ctacTpCd_D3" ezfname="ctacTpCd_D3" ezflist="ctacTpCd_D1,ctacTpNm_D2,99,D,blank" ezfhyo="D"><option>EQUIPMENT</option><option>CONTRACT</option><option>SERVICE</option><option>SUPPLIES</option></select></td> -->
																	<td><select name="ctacTpCd_D3" ezfname="ctacTpCd_D3" ezflist="ctacTpCd_D1,ctacTpDescTxt_D2,99,D,blank" ezfhyo="D"><option>EQUIPMENT</option><option>CONTRACT</option><option>SERVICE</option><option>SUPPLIES</option></select></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><input type="text" size="12" maxlength="30" name="ctacPsnFirstNm_D1" ezfname="ctacPsnFirstNm_D1" ezfhyo="D" ezftoupper></td>
																	<td><input type="text" size="12" maxlength="30" name="ctacPsnLastNm_D1" ezfname="ctacPsnLastNm_D1" ezfhyo="D" ezftoupper></td>
																	<td><select name="dsCtacPsnTtlCd_D3" ezfname="dsCtacPsnTtlCd_D3" ezflist="dsCtacPsnTtlCd_D1,dsCtacPsnTtlNm_D2,99,D,blank" ezfhyo="D"><option>EXECUTIVE</option><option>IT</option><option>MARKETING</option></select></td>
																	<td><input type="text" size="18" maxlength="320" name="ctacPsnEmlAddr_D1" ezfname="ctacPsnEmlAddr_D1" ezfhyo="D"></td>
																	<td><input type="text" size="13" maxlength="20" name="ctacPsnTelNum_D1" ezfname="ctacPsnTelNum_D1" ezfhyo="D"></td>
																	<td><input type="text" size="5" maxlength="10" name="ctacPsnExtnNum_D1" ezfname="ctacPsnExtnNum_D1" ezfhyo="D"></td>
																	<td><input id="effFromDt_D1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_D1" ezfname="effFromDt_D1" ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D1#{EZF_ROW_NUMBER}', 4);"></td>
																	<td><input id="effThruDt_D1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_D1" ezfname="effThruDt_D1" ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D1#{EZF_ROW_NUMBER}', 4);"></td>
																	<td><input type="checkbox" name="dsPrimLocFlg_D1" ezfname="dsPrimLocFlg_D1" value="Y" ezfhyo="D"></td>
																	<td><label ezfout name="dplStsNm_D1" ezfname="dplStsNm_D1" ezfhyo="D">INACTIVE</label></td>
																</tr>
																<tr id="id_leftD_row{EZF_ROW_NUMBER}">
																	<td><a href="#" name="ctacPsnPk_D1" ezfName="ctacPsnPk_D1" onclick="sendServer('ShowContact')" tabindex="-1" ezfhyo="D" ezfArrayNo="0" id="ShowContact#{EZF_ROW_NUMBER}"><label ezfout name="ctacPsnPk_D1" ezfname="ctacPsnPk_D1" ezfhyo="D">WWWWWWWWW1WWWWWWWWW2</label></a></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><select name="ctacTpCd_D3" ezfname="ctacTpCd_D3" ezflist="ctacTpCd_D1,ctacTpNm_D2,99,D,blank" ezfhyo="D"><option>EQUIPMENT</option><option>CONTRACT</option><option>SERVICE</option><option>SUPPLIES</option></select></td> -->
																	<td><select name="ctacTpCd_D3" ezfname="ctacTpCd_D3" ezflist="ctacTpCd_D1,ctacTpDescTxt_D2,99,D,blank" ezfhyo="D"><option>EQUIPMENT</option><option>CONTRACT</option><option>SERVICE</option><option>SUPPLIES</option></select></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><input type="text" size="12" maxlength="30" name="ctacPsnFirstNm_D1" ezfname="ctacPsnFirstNm_D1" ezfhyo="D" ezftoupper></td>
																	<td><input type="text" size="12" maxlength="30" name="ctacPsnLastNm_D1" ezfname="ctacPsnLastNm_D1" ezfhyo="D" ezftoupper></td>
																	<td><select name="dsCtacPsnTtlCd_D3" ezfname="dsCtacPsnTtlCd_D3" ezflist="dsCtacPsnTtlCd_D1,dsCtacPsnTtlNm_D2,99,D,blank" ezfhyo="D"><option>EXECUTIVE</option><option>IT</option><option>MARKETING</option></select></td>
																	<td><input type="text" size="18" maxlength="320" name="ctacPsnEmlAddr_D1" ezfname="ctacPsnEmlAddr_D1" ezfhyo="D"></td>
																	<td><input type="text" size="13" maxlength="20" name="ctacPsnTelNum_D1" ezfname="ctacPsnTelNum_D1" ezfhyo="D"></td>
																	<td><input type="text" size="5" maxlength="10" name="ctacPsnExtnNum_D1" ezfname="ctacPsnExtnNum_D1" ezfhyo="D"></td>
																	<td><input id="effFromDt_D1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_D1" ezfname="effFromDt_D1" ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D1#{EZF_ROW_NUMBER}', 4);"></td>
																	<td><input id="effThruDt_D1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_D1" ezfname="effThruDt_D1" ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D1#{EZF_ROW_NUMBER}', 4);"></td>
																	<td><input type="checkbox" name="dsPrimLocFlg_D1" ezfname="dsPrimLocFlg_D1" value="Y" ezfhyo="D"></td>
																	<td><label ezfout name="dplStsNm_D1" ezfname="dplStsNm_D1" ezfhyo="D">INACTIVE</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</div><!-- right table -->
											</div><!-- parent box -->
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Marketing'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_ON";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<div style="OVERFLOW-Y:scroll;height:313px" >
								<br />

								<table border="0" cellpadding="1" cellspacing="0"  style="margin-left:20;">
									<col width="360" align="top">
									<col width="10">
									<col width="480">
									<tr>
										<td valign="top">
											<fieldset style="height:150;">
												<legend style="font-size:13px;">DNB DATA</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="140">
													<col width="250">
													<tr>
														<td class="stab">DUNS#</td>
														<td><ezf:inputText name="dsAcctDunsNum_M1" ezfName="dsAcctDunsNum_M1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Ultimate DUNS#</td>
														<td><ezf:inputText name="dsUltDunsNum_M1" ezfName="dsUltDunsNum_M1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab"># of Employees</td>
														<td><ezf:inputText name="dsLocEmpNum_M1" ezfName="dsLocEmpNum_M1" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
													</tr>
													<tr>
														<td class="stab">Annual Revenue</td>
														<td><ezf:inputText name="dsLocRevAmt_M1" ezfName="dsLocRevAmt_M1" otherAttr=" size=\"22\" maxlength=\"22\""/></td>
													</tr>
													<tr>
														<td class="stab">SIC Code</td>
														<td><ezf:inputText name="dsCustSicCd_M1" ezfName="dsCustSicCd_M1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Industry</td>
														<td><ezf:inputText name="dsCustSicDescTxt_M1" ezfName="dsCustSicDescTxt_M1" otherAttr=" size=\"30\" maxlength=\"100\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Last DNB Update Date</td>
														<td><ezf:inputText name="dsLastUpdDunsDt_M1" ezfName="dsLastUpdDunsDt_M1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsLastUpdDunsDt_M1', 4);"></td>
													</tr>
												</table>
											</fieldset>
											<br />
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="100">
												<col width="290">
												<tr>
													<td class="stab">Website<td>
													<td><ezf:inputText name="dsAcctUrl_M1" ezfName="dsAcctUrl_M1" otherAttr=" size=\"40\" maxlength=\"4000\""/></td>
												</tr>
											</table>
											<br />
											<fieldset style="height:100;">
												<legend style="font-size:13px;">BUSINESS HOURS(in 24 local time (HH:MM))</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="140">
													<col width="140">
													<col width="250">
													<tr>
														<td class="stab">Day</td>
														<td class="stab">Time From</td>
														<td class="stab">Time To</td>
													</tr>
													<tr>
														<td class="stab">Sunday</td>
														<td><ezf:inputText name="xxScrItem7Txt_M1" ezfName="xxScrItem7Txt_M1" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="xxScrItem7Txt_M2" ezfName="xxScrItem7Txt_M2" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Monday-Friday</td>
														<td><ezf:inputText name="xxScrItem7Txt_M3" ezfName="xxScrItem7Txt_M3" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="xxScrItem7Txt_M4" ezfName="xxScrItem7Txt_M4" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Saturday</td>
														<td><ezf:inputText name="xxScrItem7Txt_M5" ezfName="xxScrItem7Txt_M5" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="xxScrItem7Txt_M6" ezfName="xxScrItem7Txt_M6" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										
										<td>
										<fieldset style="height:200;">
											<legend style="font-size:13px;">ACCOUNT GROUP ASSIGNMENTS</legend>
											<table border="0" style="table-layout:fixed;width:98%;">
												<col align="left" width="100">
												<col align="left" width="100">
												<col width="10">
												<col align="right" width="480">
												<tr>
													<td><ezf:inputButton name="AddGrpAsg" value="Add" ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteGrpAsg" value="Delete" ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td></td>
													<!-- Pagination & Navigation--START-->
													<td align="right" width="460">
														<ezf:skip>
															<table border="0" cellpadding="1" cellspacing="0">
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
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">200</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
															</tr>
															</table>
														</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="E" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_M1" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_M1" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_M1" />
															</jsp:include>
													</td>
													<!-- Pagination & Navigation--END-->
												</tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
												<tr>
													<td>
														<div id="LeftTable_E_Top" style="overflow-x:none; overflow-y:none; width:672; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="23">		<!-- Check Box -->
																	<col align="center" width="140">		<!-- Business Area -->
																	<col align="center" width="150">		<!-- Group Code -->
																	<col align="center" width="150">		<!-- Group Name -->
																	<col align="center" width="105">		<!-- START DATE -->
																	<col align="center" width="105">		<!-- END DATE -->
																	<tr>
																		<td class="pClothBs"></td>
																		<td class="pClothBs">Business Area</td>
																		<td class="pClothBs">Group Code</td>
																		<td class="pClothBs">Group Name</td>
																		<td class="pClothBs">Start Date</td>
																		<td class="pClothBs">End Date</td>
																	</tr>
															</table>
														</div>
														<div id="LeftTable_E" style="overflow-y:scroll; height:142; overflow-x:hidden; width:692; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout: fixed">
																	<col align="center" width="23">		<!-- Check Box -->
																	<col align="center" width="140">		<!-- Business Area -->
																	<col align="center" width="150">		<!-- Group Code -->
																	<col align="center" width="150">		<!-- Group Name -->
																	<col align="center" width="105">		<!-- START DATE -->
																	<col align="center" width="105">		<!-- END DATE -->
																<ezf:row ezfHyo="E">
																	<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_E1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:select name="dsBizAreaCd_E3" ezfName="dsBizAreaCd_E3" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsBizAreaCd_E1" ezfDispName="dsBizAreaNm_E2" ezfCodeDispHyo="E" otherAttr=" style=\"width: 130px\""/></td>
																		<td>
																			<ezf:inputText name="dsAcctGrpCd_E3" ezfName="dsAcctGrpCd_E3" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"28\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_AcctGrp" value="…" ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AcctGrp{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="dsAcctGrpDescTxt_E3" ezfName="dsAcctGrpDescTxt_E3" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"50\""/>
																		</td>
																		<td><ezf:inputText name="effFromDt_E1" ezfName="effFromDt_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"effFromDt_E1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1#{EZF_ROW_NUMBER}', 4);"></td>
																		<td><ezf:inputText name="effThruDt_E1" ezfName="effThruDt_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"effThruDt_E1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_E1#{EZF_ROW_NUMBER}', 4);"></td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_E1" ezfName="xxRecHistCratTs_E1" ezfHyo="E" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_E1" ezfName="xxRecHistCratByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_E1" ezfName="xxRecHistUpdTs_E1" ezfHyo="E" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_E1" ezfName="xxRecHistUpdByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_E1" ezfName="xxRecHistTblNm_E1" ezfHyo="E" ezfArrayNo="0" />
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_E1" value="Y" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1#{EZF_ROW_NUMBER}"></td>
																		<td><select name="dsBizAreaCd_E3" ezfname="dsBizAreaCd_E3" ezflist="dsBizAreaCd_E1,dsAcctNum_E2,99,E,blank" ezfhyo="E" style="width: 140px"><option>AAAAAA</option><option>BBBBBB</option></select></td>
																		<td><select name="dsAcctGrpCd_E3" ezfname="dsAcctGrpCd_E3" ezflist="dsAcctGrpCd_E1,dsAcctGrpNm_E2,99,E,blank" ezfhyo="E" style="width: 140px"><option>AAAAAA</option><option>BBBBBB</option></select></td>
																		<td><input type="text" size="10" maxlength="10" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4);"></td>
																		<td><input type="text" size="10" maxlength="10" name="effThruDt_E1" ezfname="effThruDt_E1" ezfhyo="E"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_E1', 4);"></td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</fieldset>
										
										<fieldset style="height:200; width:550;">
											<legend style="font-size:13px;">CERTIFICATION REQUIRED</legend>
											<table border="0" style="table-layout:fixed;width:98%;">
												<col align="left" width="100">
												<col align="left" width="100">
												<col width="10">
												<col align="left" width="330">
												<tr>
													<td><ezf:inputButton name="AddCertificationReq" value="Add" ezfHyo="N" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteCertificationReq" value="Delete" ezfHyo="N" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
												<tr>
													<td>
														<div id="LeftTable_E_Top" style="overflow-x:none; overflow-y:none; width:528; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="23">		<!-- Check Box -->
																	<col align="center" width="100">		<!-- No -->
																	<col align="center" width="185">		<!-- Access Permit Description -->
																	<col align="center" width="110">		<!-- START DATE -->
																	<col align="center" width="110">		<!-- END DATE -->
																	<tr>
																		<td class="pClothBs"></td>
																		<td class="pClothBs">No</td>
																		<td class="pClothBs">Access Permit Description</td>
																		<td class="pClothBs">Start Date</td>
																		<td class="pClothBs">End Date</td>
																	</tr>
															</table>
														</div>
														<div id="LeftTable_E" style="overflow-y:scroll; height:200; overflow-x:hidden; width:547; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="N" style="table-layout: fixed">
																	<col align="center" width="23">		<!-- Check Box -->
																	<col align="center" width="100">		<!-- No -->
																	<col align="center" width="185">		<!-- Access Permit Description -->
																	<col align="center" width="110">		<!-- START DATE -->
																	<col align="center" width="110">		<!-- END DATE -->
																<ezf:row ezfHyo="N">
																	<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_N1" ezfName="xxChkBox_N1" value="Y" ezfHyo="N" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_N1#{EZF_ROW_NUMBER}\""/></td>
																		<td>
																			<ezf:inputText name="svcAccsPmitNum_N1" ezfName="svcAccsPmitNum_N1" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\""/>
																			<ezf:inputButton name="OpenWin_AccsPmit" value="AP" ezfHyo="N" ezfArrayNo="0" htmlClass="pBtn8" otherAttr=" id=\"OpenWin_AccsPmit{EZF_ROW_NUMBER}\" style=\"width=40px\""/>
																		</td>
																		<td><ezf:inputText name="svcAccsPmitDescTxt_N1" ezfName="svcAccsPmitDescTxt_N1" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"100\""/></td>
																		<td><ezf:inputText name="effFromDt_N1" ezfName="effFromDt_N1" ezfHyo="N" ezfArrayNo="0" otherAttr=" id=\"effFromDt_N1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_N1#{EZF_ROW_NUMBER}', 4);"></td>
																		<td><ezf:inputText name="effToDt_N1" ezfName="effToDt_N1" ezfHyo="N" ezfArrayNo="0" otherAttr=" id=\"effToDt_N1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_N1#{EZF_ROW_NUMBER}', 4);"></td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_N1" ezfName="xxRecHistCratTs_N1" ezfHyo="N" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_N1" ezfName="xxRecHistCratByNm_N1" ezfHyo="N" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_N1" ezfName="xxRecHistUpdTs_N1" ezfHyo="N" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_N1" ezfName="xxRecHistUpdByNm_N1" ezfHyo="N" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_N1" ezfName="xxRecHistTblNm_N1" ezfHyo="N" ezfArrayNo="0" />
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_N1" value="Y" ezfname="xxChkBox_N1" ezfhyo="N" id="xxChkBox_N1#{EZF_ROW_NUMBER}"></td>
																		<td>
																			<input type="text" size="6" maxlength="6" name="svcAccsPmitNum_N1" ezfname="svcAccsPmitNum_N1" ezfhyo="N">
																			<input type="button" class="pBtn8" value="AP" id="OpenWin_AccsPmit{EZF_ROW_NUMBER}" name="OpenWin_AccsPmit" onclick="sendServer(this)" ezfhyo="N" style="width=40px">
																		</td>
																		<td><input type="text" size="25" maxlength="100" name="svcAccsPmitDescTxt_N1" ezfname="svcAccsPmitDescTxt_N1" ezfhyo="N"></td>
																		<td><input id="effFromDt_N1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_N1" ezfname="effFromDt_N1" ezfhyo="N"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_N1#{EZF_ROW_NUMBER}', 4);"></td>
																		<td><input id="effToDt_N1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effToDt_N1" ezfname="effToDt_N1" ezfhyo="N"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_N1#{EZF_ROW_NUMBER}', 4);"></td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</fieldset>
										</td>
									</tr>
								</table>
								<br />
								</div>
							</div>

							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Transactions'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_ON";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
														<table>
												<col>
												<col>
												<tr>
												<td align="left"><ezf:inputButton name="AddTransactionRule" value="Add" htmlClass="pBtn6" />
													<ezf:inputButton name="DeleteTransactionRule" value="Delete" htmlClass="pBtn6" />
												</td>
													
												</tr>
											</table>
							<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									
									<tr>
										<td>
											<div id="LeftTable_F_Top" style="overflow-x:none; overflow-y:none; width:1080; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="20">	<!-- Check Box -->
														<col align="center" width="110">	<!-- Transaction Type -->
														<col align="center" width="60">		<!-- PO Required -->
														<col align="center" width="60">		<!-- Hard Copy Required -->
														<col align="center" width="130">	<!-- Blanket PO# -->
														<col align="center" width="100">	<!-- PO Expire Date -->
														<col align="center" width="140">	<!-- Default Bill To -->
														<col align="center" width="140">	<!-- Default Ship To -->
														<col align="center" width="160">	<!-- Effective Level -->
														<col align="center" width="80">		<!-- Credit Card Reqd -->
														<col align="center" width="80">		<!-- Overnight Allowed -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Transaction Type</td>
															<td class="pClothBs">PO<br />Required</td>
															<td class="pClothBs">Hard Copy<br />Required</td>
															<td class="pClothBs">Blanket PO#</td>
															<td class="pClothBs">PO Expire Date</td>
															<td class="pClothBs">Default Bill To</td>
															<td class="pClothBs">Default Ship To</td>
															<td class="pClothBs">Effective Level</td>
															<td class="pClothBs">Credit Card<br />Required</td>
															<td class="pClothBs">Overnight Allowed</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_F" style="overflow-y:scroll; height:85; overflow-x:hidden; width:1099; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="F" style="table-layout: fixed">
														<col width="20">		<!-- Check Box -->
														<col width="110">		<!-- Transaction Type -->
														<col align="center" width="60">		<!-- PO Required -->
														<col align="center" width="60">		<!-- Hard Copy Required -->
														<col width="130">		<!-- Blanket PO# -->
														<col align="center" width="100">		<!-- PO Expire Date -->
														<col width="140">		<!-- Default Bill To -->
														<col width="140">		<!-- Default Ship To -->
														<col width="160">		<!-- Effective Level -->
														<col align="center" width="80">		<!-- Credit Card Reqd -->
														<col align="center" width="80">		<!-- Overnight Allowed -->
													<ezf:row ezfHyo="F">
														<tr id="id_leftF_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:select name="dsTrxRuleTpCd_F3" ezfName="dsTrxRuleTpCd_F3" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsTrxRuleTpCd_F1" ezfDispName="dsTrxRuleTpNm_F2" ezfCodeDispHyo="F" otherAttr=" style=\"width: 105px\""/></td>
															<td><ezf:inputCheckBox name="dsPoReqFlg_F1" ezfName="dsPoReqFlg_F1" value="Y" ezfHyo="F" ezfArrayNo="0" onClick="sendServer('OnChange_HardCopyReqFlgActive')" otherAttr=" width=\"10\""/></td>
															<td><ezf:inputCheckBox name="hardCopyReqFlg_F1" ezfName="hardCopyReqFlg_F1" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" width=\"10\""/></td>
															<td><ezf:inputText name="dsBlktPoNum_F1" ezfName="dsBlktPoNum_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" ezftoupper=\"\""/></td>
															<td><ezf:inputText name="dsPoExprDt_F1" ezfName="dsPoExprDt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"dsPoExprDt_F1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsPoExprDt_F1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="dsDefBillToCd_F1" ezfName="dsDefBillToCd_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"14\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_BillTo" value="BILL" ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn8" otherAttr=" id=\"OpenWin_BillTo{EZF_ROW_NUMBER}\" style=\"width=40px\""/>
															</td>
															<td><ezf:inputText name="dsDefShipToCd_F1" ezfName="dsDefShipToCd_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"14\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_ShipTo" value="SHIP" ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn8" otherAttr=" id=\"OpenWin_ShipTo{EZF_ROW_NUMBER}\" style=\"width=40px\""/>
															
															</td>
															<td><ezf:select name="custEffLvlCd_F3" ezfName="custEffLvlCd_F3" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_F1" ezfDispName="custEffLvlNm_F2" ezfCodeDispHyo="F" otherAttr=" style=\"width: 155px\""/></td>
															<td><ezf:inputCheckBox name="dsCrCardReqFlg_F1" ezfName="dsCrCardReqFlg_F1" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:inputCheckBox name="dsOvngtAllwFlg_F1" ezfName="dsOvngtAllwFlg_F1" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" ezfHyo="F" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" style="margin-left:20;">
									<col valign="top" width="685">
									<col valign="top" width="5">
									<col valign="top" width="280">
									<tr>
										<td>
											<fieldset style="height:120;">
												<legend>SPECIAL HANDLING</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0" width="100%">
																<col align="left">
																<col align="center">
																<col align="center">
																<col align="right">
																<tr>
																	<td><br /></td>
																	<td><br /></td>
																	<td><br /></td>
																	<td><ezf:inputButton name="AddSpecialHandling" value="Add" ezfHyo="S" ezfArrayNo="0" htmlClass="pBtn6" />
																		<ezf:inputButton name="DeleteSpecialHandling" value="Delete" ezfHyo="S" ezfArrayNo="0" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<div id="LeftTable_K_Top" style="overflow-x:none; overflow-y:none; width:575; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																		<col align="center" width="20"><!-- Check Box -->
																		<col align="center" width="155"><!-- Type -->
																		<col align="center" width="115"><!-- Value -->
																		<col align="center" width="100"><!-- Start Date -->
																		<col align="center" width="100"><!-- End Date -->
																		<col align="center" width="165"><!-- Effective -->
																		<tr>
																			<td class="pClothBs"></td>
																			<td class="pClothBs">Type</td>
																			<td class="pClothBs">Value</td>
																			<td class="pClothBs">Start Date</td>
																			<td class="pClothBs">End Date</td>
																			<td class="pClothBs">Effective</td>
																		</tr>
																</table>
															</div>
															<div id="LeftTable_S" style="overflow-y:scroll; height:80; overflow-x:hidden; width:675; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" id="S" style="table-layout: fixed">
																		<col align="center" width="20"><!-- Check Box -->
																		<col align="center" width="155"><!-- Type -->
																		<col align="center" width="115"><!-- Value -->
																		<col align="center" width="100"><!-- Start Date -->
																		<col align="center" width="100"><!-- End Date -->
																		<col align="center" width="165"><!-- Effective -->
																	<ezf:row ezfHyo="S">
																		<tr id="id_leftS_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputCheckBox name="xxChkBox_S1" ezfName="xxChkBox_S1" value="Y" ezfHyo="S" ezfArrayNo="0" /></td>
																			<td><ezf:select name="dsSpclHdlgTpCd_S3" ezfName="dsSpclHdlgTpCd_S3" ezfHyo="S" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsSpclHdlgTpCd_S1" ezfDispName="dsSpclHdlgTpNm_S2" ezfCodeDispHyo="S" otherEvent1=" onchange=\"sendServer('OnChange_dsSpclHdlgTp', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 150px\""/></td>
																			<td><ezf:select name="dsSpclHdlgTpValCd_S3" ezfName="dsSpclHdlgTpValCd_S3" ezfHyo="S" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsSpclHdlgTpValCd_S1" ezfDispName="dsSpclHdlgTpValNm_S2" ezfCodeDispHyo="S" otherAttr=" style=\"width: 110px\""/></td>
																			<td><ezf:inputText name="effFromDt_S1" ezfName="effFromDt_S1" ezfHyo="S" ezfArrayNo="0" otherAttr=" id=\"effFromDt_S1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_S1#{EZF_ROW_NUMBER}', 4);"></td>
																			<td><ezf:inputText name="effThruDt_S1" ezfName="effThruDt_S1" ezfHyo="S" ezfArrayNo="0" otherAttr=" id=\"effThruDt_S1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_S1#{EZF_ROW_NUMBER}', 4);"></td>
																			<td><ezf:select name="custEffLvlCd_S3" ezfName="custEffLvlCd_S3" ezfHyo="S" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_S1" ezfDispName="custEffLvlNm_S2" ezfCodeDispHyo="S" otherAttr=" style=\"width: 160px\""/></td>
																			<td class="pAuditInfo">
																				<ezf:inputHidden name="xxRecHistCratTs_S1" ezfName="xxRecHistCratTs_S1" ezfHyo="S" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistCratByNm_S1" ezfName="xxRecHistCratByNm_S1" ezfHyo="S" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdTs_S1" ezfName="xxRecHistUpdTs_S1" ezfHyo="S" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_S1" ezfName="xxRecHistUpdByNm_S1" ezfHyo="S" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistTblNm_S1" ezfName="xxRecHistTblNm_S1" ezfHyo="S" ezfArrayNo="0" />
																			</td>
																		</tr>
																	</ezf:row>
																	
																</table>
															</div>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td>
											<fieldset style="height:120;">
												<legend>GL DEPT OVERRIDE</legend>
												<table border="0" cellpadding="1" cellspacing="0">
										<col width="35">
										<col width="170">
										<col width="50">
										<col width="100">
										<tr>
											<td class="stab"><ezf:anchor name="coaCcCd_F1" ezfName="coaCcCd_F1" ezfEmulateBtn="1" ezfGuard="OpenWin_Coa" >DEPT#</ezf:anchor></td>
										
											<td><ezf:inputText name="coaCcCd_F1" ezfName="coaCcCd_F1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="GetCoaCcNm" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="coaCcNm_F1" ezfName="coaCcNm_F1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
											
										</tr>
									</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<br />
							</div>

							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'CrClt'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_ON";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" style="table-layout: fixed">
									<col width="370">
									<col width="10">
									<col width="370">
									<col width="10">
									<col width="330">
									<tr>
										<td colspan="5" align="right">
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td class="stab"><ezf:anchor name="xxLinkAncr_U1" ezfName="xxLinkAncr_U1" ezfEmulateBtn="1" ezfGuard="OpenWin_Template" >Template</ezf:anchor></td>
													<td><ezf:inputText name="dsCustArTmplNm_U1" ezfName="dsCustArTmplNm_U1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="ApplyTemplate" value="Apply" htmlClass="pBtn3" />
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<fieldset style="height:200;">
												<legend>CREDIT</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="130">
													<col width="240">
													<tr>
														<td class="stab">Currency</td>
														<td><ezf:select name="ccyCd_U3" ezfName="ccyCd_U3" ezfBlank="1" ezfCodeName="ccyCd_U1" ezfDispName="ccyNm_U2" />
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Credit Rating</td>
														<td><ezf:select name="custCrRtgCd_U3" ezfName="custCrRtgCd_U3" ezfBlank="1" ezfCodeName="custCrRtgCd_U1" ezfDispName="custCrRtgNm_U2" />
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Credit Limit</td>
														<td><ezf:inputText name="crLimitAmt_U1" ezfName="crLimitAmt_U1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/>
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Credit Review</td>
														<td><ezf:select name="crChkReqTpCd_U3" ezfName="crChkReqTpCd_U3" ezfBlank="1" ezfCodeName="crChkReqTpCd_U1" ezfDispName="crChkReqTpNm_U2" />
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Grace Period (Days)</td>
														<td><ezf:select name="crRiskClsCd_U3" ezfName="crRiskClsCd_U3" ezfBlank="1" ezfCodeName="crRiskClsCd_U1" ezfDispName="crRiskClsNm_U2" otherAttr=" style=\"width: 230px\""/></td>
													</tr>
													<tr>
														<td class="stab">Contract Grace Period</td>
														<td><ezf:select name="contrCrRiskClsCd_U3" ezfName="contrCrRiskClsCd_U3" ezfBlank="1" ezfCodeName="contrCrRiskClsCd_U1" ezfDispName="contrCrRiskClsNm_U2" otherAttr=" style=\"width: 230px\""/></td>
													</tr>
													<tr>
														<td class="stab">Payment Term</td>
														<td><ezf:select name="pmtTermCashDiscCd_U3" ezfName="pmtTermCashDiscCd_U3" ezfBlank="1" ezfCodeName="pmtTermCashDiscCd_U1" ezfDispName="pmtTermCashDiscNm_U2" otherAttr=" style=\"width: 230px\""/>
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Override Payment Term</td>
														<td><ezf:inputCheckBox name="ovrdPmtTermFlg_U1" ezfName="ovrdPmtTermFlg_U1" value="Y" />
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">CWO or CC Required</td>
														<td><ezf:inputCheckBox name="cashOrCcReqFlg_U1" ezfName="cashOrCcReqFlg_U1" value="Y" />(Order Payment Method)
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
													<tr>
														<td class="stab">Hard Hold</td>
														<td><ezf:inputCheckBox name="custHardHldFlg_U1" ezfName="custHardHldFlg_U1" value="Y" />
															<ezf:inputHidden name="xxRecHistCratTs_CR" ezfName="xxRecHistCratTs_CR" />
															<ezf:inputHidden name="xxRecHistCratByNm_CR" ezfName="xxRecHistCratByNm_CR" />
															<ezf:inputHidden name="xxRecHistUpdTs_CR" ezfName="xxRecHistUpdTs_CR" />
															<ezf:inputHidden name="xxRecHistUpdByNm_CR" ezfName="xxRecHistUpdByNm_CR" />
															<ezf:inputHidden name="xxRecHistTblNm_CR" ezfName="xxRecHistTblNm_CR" />
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td valign="top">
											<fieldset style="height:200;">
												<legend>COLLECTIONS</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="130">
													<col width="40">
													<col width="130">
													<col width="40">
													<tr>
														<td class="stab">Send Statements</td>
														<td><ezf:inputCheckBox name="arStmtFlg_U1" ezfName="arStmtFlg_U1" value="Y" /></td>
														<td class="stab">Send Credit Balance</td>
														<td><ezf:inputCheckBox name="sendCrBalStmtFlg_U1" ezfName="sendCrBalStmtFlg_U1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Statements Issue Day</td>
														<td colspan="3"><ezf:select name="arStmtIssCycleCd_U3" ezfName="arStmtIssCycleCd_U3" ezfBlank="1" ezfCodeName="arStmtIssCycleCd_U1" ezfDispName="arStmtIssCycleNm_U2" /></td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="cltCustTpCd_U1" ezfName="cltCustTpCd_U1" ezfEmulateBtn="1" ezfGuard="OpenWin_CltCustTp" >Customer Collection Type</ezf:anchor></td>
														<td><ezf:inputText name="cltCustTpCd_U1" ezfName="cltCustTpCd_U1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
														<td colspan="2"><ezf:inputButton name="GetCltCustTpNm" value=">>" htmlClass="pBtn0" />
															<ezf:inputText name="cltCustTpNm_U1" ezfName="cltCustTpNm_U1" otherAttr=" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/></td>
														
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="cltPtfoCd_U1" ezfName="cltPtfoCd_U1" ezfEmulateBtn="1" ezfGuard="OpenWin_CltPtfo" >Default Collector</ezf:anchor></td>
														<td><ezf:inputText name="cltPtfoCd_U1" ezfName="cltPtfoCd_U1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
														<td colspan="2"><ezf:inputButton name="GetCltPtfoNm" value=">>" htmlClass="pBtn0" />
															<ezf:inputText name="cltPtfoDescTxt_U1" ezfName="cltPtfoDescTxt_U1" otherAttr=" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/></td>
														
													</tr>
													<tr>
														<td colspan="4"><br /></td>
														
													</tr>
													<tr>
														<td class="stab">Account Status</td>
														<td colspan="3"><ezf:select name="dsCltAcctStsCd_U3" ezfName="dsCltAcctStsCd_U3" ezfBlank="1" ezfCodeName="dsCltAcctStsCd_U1" ezfDispName="dsCltAcctStsNm_U2" /></td>
													</tr>
													<tr>
														<td class="stab">Calculate Late Fee</td>
														<td colspan="3"><ezf:inputCheckBox name="lateFeeFlg_U1" ezfName="lateFeeFlg_U1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Minimum Balance to Calculate Late Fee</td>
														<td colspan="3"><ezf:inputText name="lateFeeAmt_U1" ezfName="lateFeeAmt_U1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td valign="top">
											<fieldset style="height:90;">
												<legend>TAXING</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<col width="100">
													<col width="220">
													<tr>
														<td class="stab">Tax Exempt</td>
														<td colspan="3"><ezf:inputCheckBox name="dsTaxExemFlg_U1" ezfName="dsTaxExemFlg_U1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Exempt Exp Date</td>
														<td><ezf:inputText name="dsExemExprDt_U1" ezfName="dsExemExprDt_U1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsExemExprDt_U1', 4);"></td>
													</tr>
													<tr>
														<td class="stab">Tax Printing</td>
														<td><ezf:select name="dsTaxPrntTpCd_U3" ezfName="dsTaxPrntTpCd_U3" ezfBlank="1" ezfCodeName="dsTaxPrntTpCd_U1" ezfDispName="dsTaxPrntTpNm_U2" /></td>
													</tr>
												</table>
											</fieldset>
											<fieldset style="height:40;">
												<legend>RECEIPTS</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<col width="100">
													<col width="220">
													<tr>
														<td class="stab">Auto Cash Rule</td>
														<td><ezf:select name="autoCashHrchCd_U3" ezfName="autoCashHrchCd_U3" ezfBlank="1" ezfCodeName="autoCashHrchCd_U1" ezfDispName="autoCashHrchNm_U2" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvBllg'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_ON";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table>
									<tr>
										<td align="left"> <ezf:inputButton name="AddInvRule" value="Add" htmlClass="pBtn6" /></td>
										<td align="left"> <ezf:inputButton name="DeleteInvRule" value="Delete" htmlClass="pBtn6" /></td>

									</tr>
</table>
<table>
									<tr>
										<td>
										<div style="float:left"> <!-- right table -->
											<div id="LeftTable_G_Top" style="overflow:hidden; width:1090;  display:block;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" width="1380px">
														<col align="center" width="20">	<!-- Check Box -->
														<col align="center" width="145">	<!-- Invoice Source -->
														<col align="center" width="115">	<!-- Bill Type -->
														<col align="center" width="115">	<!-- Consolidated Term -->
														<col align="center" width="130">	<!-- Bill Vehicle -->
														<col align="center" width="120">	<!-- Internal Email Review -->
														<col align="center" width="120">	<!-- Internal Email Review Name-->
														<col align="center" width="120">	<!-- External Email Review -->
														<col align="center" width="120">	<!-- External Email Review Name-->
														<col align="center" width="120">	<!-- Custom Email Subject -->
														<col align="center" width="150">	<!-- Default Grouping -->
														<col align="center" width="50">		<!-- Invoice Group# -->
														<col align="center" width="160">	<!-- Effective Level -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Invoice Source</td>
															<td class="pClothBs">Bill Type</td>
															<td class="pClothBs">Consolidated<br />Term</td>
															<td class="pClothBs">Bill Vehicle</td>
															<td class="pClothBs">Internal Email Review</td>
															<td class="pClothBs">Internal Email Review Name</td>
															<td class="pClothBs">External Email Contact</td>
															<td class="pClothBs">External Email Contact Name</td>
															<td class="pClothBs">Custom Email<br />Subject</td>
															<td class="pClothBs">Default Grouping</td>
															<td class="pClothBs">Invoice<br />Group#</td>
															<td class="pClothBs">Effective<br />Level</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_G" style="overflow:scroll; height:80; width:1107; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'LeftTable_G_Top' ))">
												<table border="1" cellpadding="1" cellspacing="0" id="G" style="table-layout: fixed" width="1380px">
														<col align="center" width="20">		<!-- Check Box -->
														<col align="center" width="145">	<!-- Invoice Source -->
														<col align="center" width="115">	<!-- Bill Type -->
														<col align="center" width="115">	<!-- Consolidated Term -->
														<col align="center" width="130">	<!-- Bill Vehicle -->
														<col align="center" width="120">	<!-- Internal Email Review -->
														<col align="center" width="120">	<!-- Internal Email Review Name-->
														<col align="center" width="120">	<!-- External Email Review -->
														<col align="center" width="120">	<!-- External Email Review Name -->
														<col align="center" width="120">	<!-- Custom Email Subject -->
														<col align="center" width="150">	<!-- Default Grouping -->
														<col align="center" width="50">		<!-- Invoice Group# -->
														<col align="center" width="160">		<!-- Effective Level -->
													<ezf:row ezfHyo="G">
														<tr id="id_leftG_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_G1" ezfName="xxChkBox_G1" value="Y" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:select name="custInvSrcCd_G3" ezfName="custInvSrcCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custInvSrcCd_G1" ezfDispName="custInvSrcNm_G2" ezfCodeDispHyo="G" otherAttr=" style=\"width: 140px\""/></td>
															<td><ezf:select name="custBllgTpCd_G3" ezfName="custBllgTpCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custBllgTpCd_G1" ezfDispName="custBllgTpNm_G2" ezfCodeDispHyo="G" otherEvent1=" onchange=\"sendServer('OnChange_CustBllgTp', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 110px\""/></td>
															<td><ezf:select name="custConslTermCd_G3" ezfName="custConslTermCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pmtTermCashDiscCd_G1" ezfDispName="pmtTermCashDiscNm_G2" ezfCodeDispHyo="G" otherAttr=" style=\"width: 110px\""/></td>
															<td><ezf:select name="custBllgVcleCd_G3" ezfName="custBllgVcleCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custBllgVcleCd_G1" ezfDispName="custBllgVcleDescTxt_G2" ezfCodeDispHyo="G" otherAttr=" style=\"width: 125px\""/></td>
															<td><ezf:inputText name="xxGenlFldAreaTxt_G1" ezfName="xxGenlFldAreaTxt_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"200\""/>
																<ezf:inputButton name="OpenWin_Resrc" value="R" ezfHyo="G" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Resrc{EZF_ROW_NUMBER}\" style=\"width=15px\""/>
															</td>
															<td><ezf:inputText name="xxCustInvRuleRcpntTxt_G1" ezfName="xxCustInvRuleRcpntTxt_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"62\""/>
															</td>
															<td><ezf:inputText name="xxGenlFldAreaTxt_G2" ezfName="xxGenlFldAreaTxt_G2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"400\""/>
																<ezf:inputButton name="OpenWin_CtacPsn" value="C" ezfHyo="G" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_CtacPsn{EZF_ROW_NUMBER}\" style=\"width=15px\""/>
															</td>
															<td><ezf:inputText name="xxCustInvRuleRcpntTxt_G2" ezfName="xxCustInvRuleRcpntTxt_G2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"62\""/>
															</td>
															<td><ezf:inputText name="custEmlMsgTxt_G1" ezfName="custEmlMsgTxt_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"240\""/></td>
															<td><ezf:select name="defInvGrpCd_G3" ezfName="defInvGrpCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="defInvGrpCd_G1" ezfDispName="defInvGrpNm_G2" ezfCodeDispHyo="G" /></td>
															<td><ezf:inputText name="invGrpNum_G1" ezfName="invGrpNum_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
															<td><ezf:select name="custEffLvlCd_G3" ezfName="custEffLvlCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_G1" ezfDispName="custEffLvlNm_G2" ezfCodeDispHyo="G" otherAttr=" style=\"width: 145px\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_G1" ezfName="xxRecHistCratTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_G1" ezfName="xxRecHistCratByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_G1" ezfName="xxRecHistUpdTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_G1" ezfName="xxRecHistUpdByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_G1" ezfName="xxRecHistTblNm_G1" ezfHyo="G" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
												</table>
											</div>
										</div>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" style="margin-left:20;">
									<col valign="top" width="180">
									<col valign="top" width="10">
									<col valign="top" width="280">
									<tr>
										<td>
											<fieldset style="height:120;">
												<legend>CUSTOMER REFERENCE ATTRIBUTES</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0" width="100%">
																<col align="left">
																<col align="center">
																<col align="center">
																<col align="right">
																<tr>
																	<td><br /></td>
																	<td><br /></td>
																	<td><br /></td>
																	<td><ezf:inputButton name="AddAttribute" value="Add" htmlClass="pBtn6" />
																	<ezf:inputButton name="DeleteAttribute" value="Delete" ezfHyo="K" ezfArrayNo="0" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<div id="LeftTable_K_Top" style="overflow-x:none; overflow-y:none; width:680; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																		<col align="center" width="20">		<!-- Chek Box -->
																		<col align="center" width="70">		<!-- Control -->
																		<col align="center" width="150">	<!-- Billing Attribute Name -->
																		<col align="center" width="150">	<!-- Default Value -->
																		<col align="center" width="60">		<!-- Enabled -->
																		<col align="center" width="60">		<!-- Required -->
																		<col align="center" width="160">		<!-- Effective Level -->
																		<tr>
																			<td class="pClothBs"></td>							<!-- Chek Box -->
																			<td class="pClothBs">Control</td>					<!-- Control -->
																			<td class="pClothBs">Billing Attribute Name</td>	<!-- Billing Attribute Name -->
																			<td class="pClothBs">Default Value</td>				<!-- Default Value -->
																			<td class="pClothBs">Enabled</td>					<!-- Enabled -->
																			<td class="pClothBs">Required</td>					<!-- Required -->
																			<td class="pClothBs">Effective</td>					<!-- Effective Level -->
																		</tr>
																</table>
															</div>
															<div id="LeftTable_K" style="overflow-y:scroll; height:80; overflow-x:hidden; width:690; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" id="K" style="table-layout: fixed">
																		<col align="center" width="20">
																		<col align="center" width="70">
																		<col align="center" width="150">
																		<col align="center" width="150">
																		<col align="center" width="60">
																		<col align="center" width="60">
																		<col align="center" width="160">
																	<ezf:row ezfHyo="K">
																		<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputCheckBox name="xxChkBox_K1" ezfName="xxChkBox_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																			<td><ezf:label name="xxCtlNm_K1" ezfName="xxCtlNm_K1" ezfHyo="K" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="bllgAttrbNm_K1" ezfName="bllgAttrbNm_K1" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																			<td><ezf:inputText name="bllgAttrbValTxt_K1" ezfName="bllgAttrbValTxt_K1" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																			<td><ezf:inputCheckBox name="bllgAttrbEnblFlg_K1" ezfName="bllgAttrbEnblFlg_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																			<td><ezf:inputCheckBox name="bllgAttrbReqFlg_K1" ezfName="bllgAttrbReqFlg_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																			<td><ezf:select name="custEffLvlCd_K3" ezfName="custEffLvlCd_K3" ezfHyo="K" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_K1" ezfDispName="custEffLvlNm_K2" ezfCodeDispHyo="K" otherAttr=" style=\"width: 155px\""/></td>
																			<td class="pAuditInfo">
																				<ezf:inputHidden name="xxRecHistCratTs_K1" ezfName="xxRecHistCratTs_K1" ezfHyo="K" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistCratByNm_K1" ezfName="xxRecHistCratByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdTs_K1" ezfName="xxRecHistUpdTs_K1" ezfHyo="K" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_K1" ezfName="xxRecHistUpdByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistTblNm_K1" ezfName="xxRecHistTblNm_K1" ezfHyo="K" ezfArrayNo="0" />
																			</td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																		<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																			<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																			<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																			<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																			<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																			<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																			<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
																			<td><select name="custEffLvlCd_K3" ezfname="custEffLvlCd_K3" ezfhyo="K" style="width: 95px" ezflist="custEffLvlCd_K1,custEffLvlNm_K2,99,K,blank"><option>Account Only</option><option>Account & Children</option></select></td>
															
																		</tr>
																		<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																			<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																			<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																			<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																			<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																			<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																			<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
																			<td><select name="custEffLvlCd_K3" ezfname="custEffLvlCd_K3" ezfhyo="K" style="width: 95px" ezflist="custEffLvlCd_K1,custEffLvlNm_K2,99,K,blank"><option>Account Only</option><option>Account & Children</option></select></td>
															
																		</tr>
																	</ezf:skip>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td>
											<fieldset style="height:120;">
												<legend>DEFAULT CONTRACT PREFERENCES</legend>
												<table border="0" style="table-layout:fixed;">
													<col width="50">
													<col width="300">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col width="40">
																<tr height="23">
																	<td><br /></td>
																</tr>
																<tr height="23">
																	<td class="stab">BASE</td>
																</tr>
																<tr height="23">
																	<td class="stab">USAGE</td>
																</tr>
															</table>
														</td>
														<td>
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col align="center" width="100">
																<col align="center" width="150">
																<tr height="23">
																	<td class="pClothBs">Adv/Arrears</td>
																	<td class="pClothBs">Cycle</td>
																</tr>
																<tr height="23">
																	<td><ezf:select name="defBaseTpCd_V3" ezfName="defBaseTpCd_V3" ezfBlank="1" ezfCodeName="defBaseTpCd_V1" ezfDispName="defBaseTpNm_V2" /></td>
																	<td><ezf:select name="defBaseCycleCd_V3" ezfName="defBaseCycleCd_V3" ezfBlank="1" ezfCodeName="defBaseCycleCd_V1" ezfDispName="defBaseCycleNm_V2" /></td>
																</tr>
																<tr height="23">
																	<td><ezf:select name="defUsgTpCd_V3" ezfName="defUsgTpCd_V3" ezfBlank="1" ezfCodeName="defUsgTpCd_V1" ezfDispName="defUsgTpNm_V2" /></td>
																	<td><ezf:select name="defUsgCycleCd_V3" ezfName="defUsgCycleCd_V3" ezfBlank="1" ezfCodeName="defUsgCycleCd_V1" ezfDispName="defUsgCycleNm_V2" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="stab" colspan="2">
															<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col align="center" width="115">
																<col align="center" width="250">
																<tr height="23">
																	<td>Invoicing Option</td>
																	<td><ezf:select name="dsInvTgtrTpCd_V1" ezfName="dsInvTgtrTpCd_V1" ezfBlank="1" ezfCodeName="dsInvTgtrTpCd_V2" ezfDispName="dsInvTgtrTpDescTxt_V1" otherAttr=" style=\"width:200;\""/></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'BankAcct'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_ON";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table>
									<col width="250">
									<col width="260">
									<col width="210">
									<tr>
										<td></td>
										<td></td>
										<td align="right" width="580">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="I" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_I1" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_I1" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_I1" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_I1" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_I1" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
											<ezf:skip>
											<table border="0" cellpadding="0" width="100%">
												<tr>
													<td align="right">
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
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<div id="LeftTable_I_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="300">	<!-- Account Name -->
														<col align="center" width="200">	<!-- Account Number -->
														<col align="center" width="200">	<!-- Routing Number -->
														<col align="center" width="200">	<!-- Currency -->
														<col align="center" width="80">		<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
														<tr>
															<td class="pClothBs">Account Name</td>
															<td class="pClothBs">Account Number</td>
															<td class="pClothBs">Routing Number</td>
															<td class="pClothBs">Currency</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_I" style="overflow-y:scroll; height:250; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="I" style="table-layout: fixed">
														<col width="300">	<!-- Account Name -->
														<col width="200">	<!-- Account Number -->
														<col width="200">	<!-- Routing Number -->
														<col width="200">	<!-- Currency -->
														<col width="80">	<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
													<ezf:row ezfHyo="I">
														<tr id="id_leftI_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsBankAcctNm_I1" ezfName="dsBankAcctNm_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBankAcctNum_I1" ezfName="dsBankAcctNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="bankRteNum_I1" ezfName="bankRteNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="ccyNm_I1" ezfName="ccyNm_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="effFromDt_I1" ezfName="effFromDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="effThruDt_I1" ezfName="effThruDt_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"effThruDt_I1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_I1#{EZF_ROW_NUMBER}', 4);"></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_I1" ezfName="xxRecHistCratTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_I1" ezfName="xxRecHistCratByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_I1" ezfName="xxRecHistUpdTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_I1" ezfName="xxRecHistUpdByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_I1" ezfName="xxRecHistTblNm_I1" ezfHyo="I" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_leftI_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsBankAcctNm_I1" ezfname="dsBankAcctNm_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4</label></td>
															<td><label ezfout name="dsBankAcctNum_I1" ezfname="dsBankAcctNum_I1" ezfhyo="I">WWWWWWWWW1WWWW</label></td>
															<td><label ezfout name="bankRteNum_I1" ezfname="bankRteNum_I1" ezfhyo="I">WWWWWWWWW1</label></td>
															<td><label ezfout name="ccyNm_I1" ezfname="ccyNm_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><label ezfout name="effFromDt_I1" ezfname="effFromDt_I1" ezfhyo="I">2014/01/22</label></td>
															<td><input id="effThruDt_I1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_I1" ezfname="effThruDt_I1" ezfhyo="I"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_I1#{EZF_ROW_NUMBER}', 4);"></td>
														</tr>
														<tr id="id_leftI_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsBankAcctNm_I1" ezfname="dsBankAcctNm_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4</label></td>
															<td><label ezfout name="dsBankAcctNum_I1" ezfname="dsBankAcctNum_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><label ezfout name="bankRteNum_I1" ezfname="bankRteNum_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><label ezfout name="ccyNm_I1" ezfname="ccyNm_I1" ezfhyo="I">WWWWWWWWW1WWWWWWWWW2</label></td>
															<td><label ezfout name="effFromDt_I1" ezfname="effFromDt_I1" ezfhyo="I">2014/01/22</label></td>
															<td><input id="effThruDt_I1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_I1" ezfname="effThruDt_I1" ezfhyo="I"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_I1#{EZF_ROW_NUMBER}', 4);"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'MsgNote'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_ON";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table>
									<col>
									<col>
									<tr>
									<td align="left"><ezf:inputButton name="AddMsgNote" value="Add" htmlClass="pBtn6" />
										<ezf:inputButton name="DeleteMsgNote" value="Delete" htmlClass="pBtn6" /></td>
										<td align="right" width="580">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="J" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_J1" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_J1" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_J1" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_J1" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_J1" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
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
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<div id="LeftTable_J_Top" style="overflow-x:none; overflow-y:none; width:940; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="20">		<!-- Check Box -->
														<col align="center" width="120">	<!-- Line Of Business -->
														<col align="center" width="120">	<!-- Business Area -->
														<col align="center" width="60">		<!-- Type -->
														<col align="center" width="200">	<!-- Message Body -->
														<col align="center" width="60">		<!-- Print On Invoice -->
														<col align="center" width="160">	<!-- Effective Level -->
														<col align="center" width="100">	<!-- End Date -->
														<col align="center" width="100">	<!-- Attachment -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Line Of<br />Business</td>
															<td class="pClothBs">Business Area</td>
															<td class="pClothBs">Type</td>
															<td class="pClothBs">Message Body</td>
															<td class="pClothBs">Print On<br />Invoice</td>
															<td class="pClothBs">Effective Level</td>
															<td class="pClothBs">End Date</td>
															<td class="pClothBs">Attachment</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_J" style="overflow-y:scroll; height:230; overflow-x:hidden; width:959; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="J" style="table-layout: fixed">
														<col width="20">	<!-- Check Box -->
														<col width="120">	<!-- Line Of Business -->
														<col width="120">	<!-- Business Area -->
														<col width="60">	<!-- Type -->
														<col width="200">	<!-- Message Body -->
														<col align="center" width="60">		<!-- Print On Invoice -->
														<col width="160">	<!-- Effective Level -->
														<col align="center" width="100">	<!-- End Date -->
														<col align="center" width="100">	<!-- Attachment -->
													<ezf:row ezfHyo="J">
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_J1" ezfName="xxChkBox_J1" value="Y" ezfHyo="J" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_J1#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:select name="lineBizTpCd_J3" ezfName="lineBizTpCd_J3" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_J1" ezfDispName="lineBizTpNm_J2" ezfCodeDispHyo="J" otherAttr=" style=\"width: 118px\""/></td>
															<td><ezf:select name="dsBizAreaCd_J3" ezfName="dsBizAreaCd_J3" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsBizAreaCd_J1" ezfDispName="dsBizAreaNm_J2" ezfCodeDispHyo="J" /></td>
															<td><ezf:select name="dsCustMsgTpCd_J3" ezfName="dsCustMsgTpCd_J3" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCustMsgTpCd_J1" ezfDispName="dsCustMsgTpNm_J2" ezfCodeDispHyo="J" /></td>
															<td><ezf:textArea name="dsCustMsgTxt_J1" ezfName="dsCustMsgTxt_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" cols=\"25\" rows=\"1\" maxlength=\"4000\""/></td>
															<td><ezf:inputCheckBox name="dsPrintOnInvFlg_J1" ezfName="dsPrintOnInvFlg_J1" value="Y" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:select name="custEffLvlCd_J3" ezfName="custEffLvlCd_J3" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_J1" ezfDispName="custEffLvlNm_J2" ezfCodeDispHyo="J" /></td>
															<td><ezf:inputText name="effThruDt_J1" ezfName="effThruDt_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" id=\"effThruDt_J1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_J1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputButton name="OpenWin_Attachments" value="Attachments" ezfHyo="J" ezfArrayNo="0" htmlClass="pBtn7" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_J1" ezfName="xxRecHistCratTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_J1" ezfName="xxRecHistCratByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_J1" ezfName="xxRecHistUpdTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_J1" ezfName="xxRecHistUpdByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_J1" ezfName="xxRecHistTblNm_J1" ezfHyo="J" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_J1" value="Y" ezfname="xxChkBox_J1" ezfhyo="J" id="xxChkBox_J1#{EZF_ROW_NUMBER}"></td>
															<td><select name="lineBizTpCd_J3" ezfname="lineBizTpCd_J3" ezflist="dsBizLineCd_J1,dsBizLineNm_J2,99, ,blank" ezfhyo="J" style="width: 118px"><option>ALL</option><option>ESS</option><option>EMSD</option></select></td>
															<td><select name="dsBizAreaCd_J3" ezfname="dsBizAreaCd_J3" ezflist="dsBizAreaCd_J1,dsBizAreaNm_J2,99, ,blank" ezfhyo="J"><option>COLLECTIONS</option><option>CONTRACTS</option><option>CUSTOMER</option></select></td>
															<td><select name="dsCustMsgTpCd_J3" ezfname="dsCustMsgTpCd_J3" ezflist="dsCustMsgTpCd_J1,dsCustMsgTpNm_J2,99, ,blank" ezfhyo="J"><option>MSG</option><option>NOTE</option></select></td>
															<td><input type="text" size="26" maxlength="80" name="dsCustMsgTxt_J1" ezfname="dsCustMsgTxt_J1" ezfhyo="J"></td>
															<td><input type="checkbox" name="dsPrintOnInvFlg_J1" ezfname="dsPrintOnInvFlg_J1" ezfhyo="J" value="Y"></td>
															<td><select name="custEffLvlCd_J3" ezfname="custEffLvlCd_J3" ezflist="custEffLvlCd_J1,custEffLvlNm_J2,99, ,blank" ezfhyo="J"><option>Account Only</option><option>Account & Children</option></select></td>
															<td><input id="effThruDt_J1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_J1" ezfname="effThruDt_J1" ezfhyo="J"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_J1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input type="button" class="pBtn7" value="Attachments" name="OpenWin_Attachments" onclick="sendServer(this)" ezfhyo="J"></td>
														</tr>
														<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_J1" value="Y" ezfname="xxChkBox_J1" ezfhyo="J" id="xxChkBox_J1#{EZF_ROW_NUMBER}"></td>
															<td><select name="lineBizTpCd_J3" ezfname="lineBizTpCd_J3" ezflist="dsBizLineCd_J1,dsBizLineNm_J2,99, ,blank" ezfhyo="J" style="width: 118px"><option>ALL</option><option>ESS</option><option>EMSD</option></select></td>
															<td><select name="dsBizAreaCd_J3" ezfname="dsBizAreaCd_J3" ezflist="dsBizAreaCd_J1,dsBizAreaNm_J2,99, ,blank" ezfhyo="J"><option>COLLECTIONS</option><option>CONTRACTS</option><option>CUSTOMER</option></select></td>
															<td><select name="dsCustMsgTpCd_J3" ezfname="dsCustMsgTpCd_J3" ezflist="dsCustMsgTpCd_J1,dsCustMsgTpNm_J2,99, ,blank" ezfhyo="J"><option>MSG</option><option>NOTE</option></select></td>
															<td><input type="text" size="26" maxlength="80" name="dsCustMsgTxt_J1" ezfname="dsCustMsgTxt_J1" ezfhyo="J"></td>
															<td><input type="checkbox" name="dsPrintOnInvFlg_J1" ezfname="dsPrintOnInvFlg_J1" ezfhyo="J" value="Y"></td>
															<td><select name="custEffLvlCd_J3" ezfname="custEffLvlCd_J3" ezflist="custEffLvlCd_J1,custEffLvlNm_J2,99, ,blank" ezfhyo="J"><option>Account Only</option><option>Account & Children</option></select></td>
															<td><input id="effThruDt_J1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_J1" ezfname="effThruDt_J1" ezfhyo="J"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_J1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input type="button" class="pBtn7" value="Attachments" name="OpenWin_Attachments" onclick="sendServer(this)" ezfhyo="J"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Shipping'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_ON";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table>
									<col>
									<col>
									<tr>
									<td align="left"><ezf:inputButton name="AddShipping" value="Add" htmlClass="pBtn6" />
										<ezf:inputButton name="DeleteShipping" value="Delete" htmlClass="pBtn6" /></td>
										<td align="right" width="580">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="M" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum_M2" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum_M2" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum_M2" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum_M2" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum_M2" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
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
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<div id="LeftTable_M_Top" style="float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="20">		<!-- Check Box -->
														<col align="center" width="50">		<!-- Default -->
														<col align="center" width="80">		<!-- Line Of Business -->
														<col align="center" width="110">	<!-- Business Area -->
														<col align="center" width="120">	<!-- Freight Term -->
														<col align="center" width="100">	<!-- Service Level -->
														<col align="center" width="180">	<!-- Carrier -->
														<col align="center" width="110">	<!-- Account Number -->
														<col align="center" width="100">	<!-- Start Date -->
														<col align="center" width="100">	<!-- End Date -->
														<col align="center" width="110">	<!-- Effective Level -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Default</td>
															<td class="pClothBs">Line Of<br />Business</td>
															<td class="pClothBs">Business Area</td>
															<td class="pClothBs">Freight Term</td>
															<td class="pClothBs">Service Level</td>
															<td class="pClothBs">Carrier</td>
															<td class="pClothBs">Account<br />Number</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
															<td class="pClothBs">Effective Level</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_M" style="overflow-y:scroll; height:230; width:1080; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="M" style="table-layout: fixed">
														<col width="20">				<!-- Check Box -->
														<col align="center" width="50">	<!-- Default -->
														<col width="80">				<!-- Line Of Business -->
														<col width="110">				<!-- Business Area -->
														<col width="120">				<!-- Freight Term -->
														<col width="100">				<!-- Service Level -->
														<col width="180">				<!-- Carrier -->
														<col width="110">				<!-- Account Number -->
														<col width="100">				<!-- Start Date -->
														<col width="100">				<!-- End Date -->
														<col width="110">				<!-- Effective Level -->
													<ezf:row ezfHyo="M">
														<tr id="id_MeftA_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="Y" ezfHyo="M" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_M1#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputCheckBox name="xxChkBox_MD" ezfName="xxChkBox_MD" value="Y" ezfHyo="M" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_MD#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:select name="lineBizTpCd_M3" ezfName="lineBizTpCd_M3" ezfHyo="M" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_M1" ezfDispName="lineBizTpNm_M2" ezfCodeDispHyo="M" otherEvent1=" onchange=\"sendServer('OnChange_lineBizTp', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 78px\""/></td>
															<td><ezf:select name="dsBizAreaCd_M3" ezfName="dsBizAreaCd_M3" ezfHyo="M" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsBizAreaCd_M1" ezfDispName="dsBizAreaNm_M2" ezfCodeDispHyo="M" otherAttr=" style=\"width: 108px\""/></td>
															<td><ezf:select name="frtCondCd_M3" ezfName="frtCondCd_M3" ezfHyo="M" ezfArrayNo="0" ezfBlank="1" ezfCodeName="frtCondCd_M1" ezfDispName="frtCondNm_M2" ezfCodeDispHyo="M" otherEvent1=" onchange=\"sendServer('OnChange_frtCond', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 118px\""/></td>
															<td><ezf:select name="shpgSvcLvlCd_M3" ezfName="shpgSvcLvlCd_M3" ezfHyo="M" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_M1" ezfDispName="shpgSvcLvlNm_M2" ezfCodeDispHyo="M" otherAttr=" style=\"width: 98px\""/></td>
															<td>
																<span><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrInfo" ezfHyo="M" >Carrier</ezf:anchor></span>
																<ezf:inputText name="carrNm_M3" ezfName="carrNm_M3" ezfHyo="M" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
															</td>
															<td><ezf:inputText name="dsCarrAcctNum_M1" ezfName="dsCarrAcctNum_M1" ezfHyo="M" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															<td><ezf:inputText name="effFromDt_M1" ezfName="effFromDt_M1" ezfHyo="M" ezfArrayNo="0" otherAttr=" id=\"effFromDt_M1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_M1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="effThruDt_M1" ezfName="effThruDt_M1" ezfHyo="M" ezfArrayNo="0" otherAttr=" id=\"effThruDt_M1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_M1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:select name="custEffLvlCd_M3" ezfName="custEffLvlCd_M3" ezfHyo="M" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custEffLvlCd_M1" ezfDispName="custEffLvlNm_M2" ezfCodeDispHyo="M" otherAttr=" style=\"width: 108px\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_M1" ezfName="xxRecHistCratTs_M1" ezfHyo="M" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_M1" ezfName="xxRecHistCratByNm_M1" ezfHyo="M" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_M1" ezfName="xxRecHistUpdTs_M1" ezfHyo="M" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_M1" ezfName="xxRecHistUpdByNm_M1" ezfHyo="M" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_M1" ezfName="xxRecHistTblNm_M1" ezfHyo="M" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_R1" value="Y" ezfname="xxChkBox_R1" ezfhyo="R" id="xxChkBox_R1#{EZF_ROW_NUMBER}"></td>
															<td><input type="checkbox" name="xxChkBox_MD" value="Y" ezfname="xxChkBox_MD" ezfhyo="M" id="xxChkBox_MD#{EZF_ROW_NUMBER}"></td>
															<td><select name="lineBizTpCd_R3" ezfname="lineBizTpCd_R3" ezfhyo="R" style="width: 78px" ezflist="lineBizTpCd_R1,lineBizTpNm_R2,99,R,blank"><option>ALL</option><option>ESS</option><option>EMSD</option></select></td>
															<td><select name="dsBizAreaCd_R3" ezfname="dsBizAreaCd_R3" ezfhyo="R" style="width: 108px"ezflist="dsBizAreaCd_R1,dsBizAreaNm_R2,99,R,blank"><option>COLLECTIONS</option><option>CONTRACTS</option><option>CUSTOMER</option></select></td>
															<td><select name="frtCondCd_R3" ezfname="frtCondCd_R3" ezfhyo="R" style="width: 118px"ezflist="frtCondCd_R1,frtCondNm_R2,99,R,blank"><option>MSG</option><option>NOTE</option></select></td>
															<td><select name="shpgSvcLvlCd_R3" ezfname="shpgSvcLvlCd_R3" ezfhyo="R"style="width: 98px" ezflist="shpgSvcLvlCd_R1,shpgSvcLvlNm_R2,99,R,blank"><option>MSG</option><option>NOTE</option></select></td>
															<td>
																<span><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrInfo" ezfHyo="M" >Carrier</ezf:anchor></span>
																<input type="text" size="16" maxlength="60" name="carrNm_M3" ezfname="carrNm_M3" ezfhyo="M">
															</td>
															<td><input type="text" size="10" maxlength="10" name="dsCarrAcctNum_R1" ezfname="dsCarrAcctNum_R1" ezfhyo="R"></td>
															<td><input id="effFromDt_R1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_R1" ezfname="effFromDt_R1" ezfhyo="R"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_M1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input id="effThruDt_R1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_R1" ezfname="effThruDt_R1" ezfhyo="R"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_R1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><select name="custEffLvlCd_R3" ezfname="custEffLvlCd_R3" ezfhyo="R"style="width: 108px" ezflist="custEffLvlCd_R1,custEffLvlNm_R2,99,R,blank"><option>Account Only</option><option>Account & Children</option></select></td>
														</tr>
														<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_R1" value="Y" ezfname="xxChkBox_R1" ezfhyo="R" id="xxChkBox_R1#{EZF_ROW_NUMBER}"></td>
															<td><input type="checkbox" name="xxChkBox_MD" value="Y" ezfname="xxChkBox_MD" ezfhyo="M" id="xxChkBox_MD#{EZF_ROW_NUMBER}"></td>
															<td><select name="lineBizTpCd_R3" ezfname="lineBizTpCd_R3" ezfhyo="R" style="width: 78px" ezflist="lineBizTpCd_R1,lineBizTpNm_R2,99,R,blank"><option>ALL</option><option>ESS</option><option>EMSD</option></select></td>
															<td><select name="dsBizAreaCd_R3" ezfname="dsBizAreaCd_R3" ezfhyo="R" style="width: 108px"ezflist="dsBizAreaCd_R1,dsBizAreaNm_R2,99,R,blank"><option>COLLECTIONS</option><option>CONTRACTS</option><option>CUSTOMER</option></select></td>
															<td><select name="frtCondCd_R3" ezfname="frtCondCd_R3" ezfhyo="R" style="width: 118px"ezflist="frtCondCd_R1,frtCondNm_R2,99,R,blank"><option>MSG</option><option>NOTE</option></select></td>
															<td><select name="shpgSvcLvlCd_R3" ezfname="shpgSvcLvlCd_R3" ezfhyo="R"style="width: 98px" ezflist="shpgSvcLvlCd_R1,shpgSvcLvlNm_R2,99,R,blank"><option>MSG</option><option>NOTE</option></select></td>
															<td><select name="vndCd_R3" ezfname="vndCd_R3" ezflist="vndCd_R1,locNm_R2,99,R,blank" ezfhyo="R" style="width: 175px"><option>BST - SAN DIEGO</option><option>BST - SAN FRAN</option><option>BST - WASHINGTON</option></select></td>
															<td><input type="text" size="10" maxlength="10" name="dsCarrAcctNum_R1" ezfname="dsCarrAcctNum_R1" ezfhyo="R"></td>
															<td><input id="effFromDt_R1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effFromDt_R1" ezfname="effFromDt_R1" ezfhyo="R"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_M1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input id="effThruDt_R1#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" name="effThruDt_R1" ezfname="effThruDt_R1" ezfhyo="R"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_R1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><select name="custEffLvlCd_R3" ezfname="custEffLvlCd_R3" ezfhyo="R"style="width: 108px" ezflist="custEffLvlCd_R1,custEffLvlNm_R2,99,R,blank"><option>Account Only</option><option>Account & Children</option></select></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>

							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Taxing'}">
							<script type="text/javascript">
<%if(AddresslFlg.equals("Y")){%>
								document.getElementById( "Addresses" ).className = "pTab_OFF";
<%}%>
<%if(HierarchyFlg.equals("Y")){%>
								document.getElementById( "Hierarchy" ).className = "pTab_OFF";
<%}%>
<%if(RelnshipsFlg.equals("Y")){%>
								document.getElementById( "Relnships" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
								document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(MarketingFlg.equals("Y")){%>
								document.getElementById( "Marketing" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
								document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(CrCltFlg.equals("Y")){%>
								document.getElementById( "CrClt" ).className = "pTab_OFF";
<%}%>
<%if(InvBllgFlg.equals("Y")){%>
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
<%}%>
<%if(BankAcctFlg.equals("Y")){%>
								document.getElementById( "BankAcct" ).className = "pTab_OFF";
<%}%>
<%if(MsgNoteFlg.equals("Y")){%>
								document.getElementById( "MsgNote" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
								document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
								document.getElementById( "Taxing" ).className = "pTab_ON";
<%}%>
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" style="table-layout: fixed">
									<br/>
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" height="250">
												<col width="100" valign="top">
												<col width="220" valign="top">
												<col width="500" valign="top">
												<tr>
													<td class="stab">Vertex Group Exemption</td>
													<td><ezf:select name="dsTaxGrpExemCd_U3" ezfName="dsTaxGrpExemCd_U3" ezfBlank="1" ezfCodeName="dsTaxGrpExemCd_U1" ezfDispName="dsTaxGrpExemNm_U2" /></td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>

							</c:choose>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Addresses'}">
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, true);
</script>
</c:when>
</c:choose>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Contacts'}">
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxD", "DHEAD", "D", -1, true, true);
</script>
</c:when>
</c:choose>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Marketing'}">
<script type="text/javascript" defer>
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
		item.select();
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>
</c:when>
</c:choose>
<%@ page import="business.servlet.NMAL6700.NMAL6700Bean" %>
<jsp:include page="../treeView/S21TreeView.jsp">
	<jsp:param name="treeView" value='<%= ((NMAL6700Bean)databean).getTreeView() != null ? ((NMAL6700Bean)databean).getTreeView().isDisableAllFields() : false %>' />
	<jsp:param name="TreeViewPropertyNameList" value="treeView" />
</jsp:include>
<script type="text/javascript" defer>
    var inputTextSizeList = [[1,26],[2,24],[3,9]];
    setTreeviewStyle(inputTextSizeList);
</script> 
<style type="text/css">
.pTab_ON {
    margin-right:1px;
}
.pTab_OFF {
    margin-right:1px;
}
</style>


<%-- **** Task specific area ends here **** --%>
