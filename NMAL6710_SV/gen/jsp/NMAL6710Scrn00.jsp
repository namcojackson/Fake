<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200721140622 --%>
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
			<input type="hidden" name="pageID" value="NMAL6710Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Account Search">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
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
								<table width="1080" border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td valign="top">
                                            <br>
								<div style="border-style: solid ; border-width: 1px; width:100%;">
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
											<ezf:select name="xxAcctSrchModeCd_D1" ezfName="xxAcctSrchModeCd_D1" ezfCodeName="xxAcctSrchModeCd_01" ezfDispName="xxModeNm23Txt_01" otherEvent1=" onchange=\"sendServer('OnChange_SearchMode')\"" />
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
							<td valign="left" width="1080">
							<table border="0" align="left">
								<td valign="top" width="1080">
								<div style="border-style: solid ; border-width: 1px; width:100%;">
								<table border="0" align="center">
								<col width="120">
								<col width="130">
								<col width="30">
								<col width="30">
								<col width="10">
								<col width="140">
								<col width="170">
								<col width="130">
								<col width="120">
									<tr>
										<td class="stab">Account Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsAcctNm_01" ezfName="dsAcctNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\""/></td>
										<td></td>
										<td  class="stab">DUNS#(*):</td>
										<td><ezf:inputText name="dsAcctDunsNum_01" ezfName="dsAcctDunsNum_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">External Reference#(*):</td>
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
										</td>									</tr>
									
									<tr>
										<td class="stab">Postal Code(*):</td>
										<td colspan="3"><ezf:inputText name="postCd_01" ezfName="postCd_01" value="XX" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Contact Last Name(*):</td>
										<td><ezf:inputText name="ctacPsnLastNm_01" ezfName="ctacPsnLastNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="dsAcctNum_L1" ezfName="dsAcctNum_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctNum" otherAttr=" ezfanchortext=\"\"">Account#(*):</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="dsAcctNum_01" ezfName="dsAcctNum_01" value="XXXXXXX8" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Contact First Name(*):</td>
										<td><ezf:inputText name="ctacPsnFirstNm_01" ezfName="ctacPsnFirstNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
										<td class="stab">Display Related Accounts:</td>
										<td>
											<ezf:select name="xxAcctSrchDplyRelnCd_D4" ezfName="xxAcctSrchDplyRelnCd_D4" ezfBlank="1" ezfCodeName="xxAcctSrchDplyRelnCd_04" ezfDispName="xxModeNm23Txt_04" />
										</td>
									</tr>
									<tr>
										<td class="stab">Location#(*):</td>
										<td colspan="3"><ezf:inputText name="locNum_01" ezfName="locNum_01" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Contact Phone#(*):</td>
										<td><ezf:inputText name="ctacPsnTelNum_01" ezfName="ctacPsnTelNum_01" value="XX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">Related Account Name(*):</td>
										<td><ezf:inputText name="dsAcctNm_RT" ezfName="dsAcctNm_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab">DBA Name(*):</td>
										<td colspan="3"><ezf:inputText name="dbaNm_01" ezfName="dbaNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Cross Reference Category:</td>
										<td>
											<ezf:select name="dsXrefAcctTpCd_DP" ezfName="dsXrefAcctTpCd_DP" ezfBlank="1" ezfCodeName="dsXrefAcctTpCd_01" ezfDispName="dsXrefAcctTpNm_01" otherAttr=" style=\"width:145px\""/>
										</td>
										<td class="stab">Related Account#(*):</td>
										<td><ezf:inputText name="dsAcctNum_RT" ezfName="dsAcctNum_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab">Account Legal Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsAcctLegalNm_01" ezfName="dsAcctLegalNm_01" value="XX" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Cross Reference#(*):</td>
										<td><ezf:inputText name="dsXrefAcctCd_01" ezfName="dsXrefAcctCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td class="stab">Related Address:</td>
										<td><ezf:inputText name="xxAllLineAddr_RT" ezfName="xxAllLineAddr_RT" value="XX" otherAttr=" size=\"30\" maxlength=\"62\" ezftoupper=\"\""/></td>
									
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="dsAcctGrp_L1" ezfName="dsAcctGrp_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctGrp" otherAttr=" ezfanchortext=\"\"">Account Group Name(*):</ezf:anchor></td>
										<td colspan="3">
											<ezf:inputText name="dsAcctGrpCd_DP" ezfName="dsAcctGrpCd_DP" value="XX" otherAttr=" size=\"10\" maxlength=\"28\" ezftoupper=\"\""/>
											<ezf:inputText name="dsAcctGrpDescTxt_DP" ezfName="dsAcctGrpDescTxt_DP" value="XX" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td class="stab">Bill To Code(*):</td>
										<td><ezf:inputText name="billToCustCd_01" ezfName="billToCustCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td colspan="2" class="stab">
											<ezf:inputCheckBox name="xxChkBox_02" ezfName="xxChkBox_02" value="Y" />Display Inactive Location
										</td>
									</tr>	
									<tr>
										<td class="stab">Account Classification:</td>
										<td colspan="3">
											<ezf:select name="dsAcctClsCd_DP" ezfName="dsAcctClsCd_DP" ezfBlank="1" ezfCodeName="dsAcctClsCd_01" ezfDispName="dsAcctClsNm_01" otherAttr=" style=\"width:210px\""/>
										</td>
										<td></td>
										<td class="stab">Ship To Code(*):</td>
										<td><ezf:inputText name="shipToCustCd_01" ezfName="shipToCustCd_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td colspan="2" class="stab">
											<ezf:inputCheckBox name="xxChkBox_03" ezfName="xxChkBox_03" value="Y" />Display Inactive Bill to / Ship to
										</td>
									</tr>
									<tr>
										<td class="stab">Location Name(*):</td>
										<td colspan="3"><ezf:inputText name="dsLocNm_01" ezfName="dsLocNm_01" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									
								</table>
								</div>
							</td>
							</table>
							</td>
							
							
						</tr>
						<tr>
										<table width="97%">
											<tr>
                                                <td align="right">
                                                    <ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" id=\"btn11\""/>
                                                    <ezf:inputButton name="OpneWin_AcctInfoRef" value="Account Info" htmlClass="pBtn7" otherAttr=" id=\"btn12\""/>
                                                    <ezf:inputButton name="OpenWin_LocInfo" value="Location Info" htmlClass="pBtn7" otherAttr=" id=\"btn13\""/>
                                                    <ezf:inputButton name="OpneWin_AcctInfo" value="Add Account" htmlClass="pBtn7" otherAttr=" id=\"btn14\""/>
                                                    <ezf:inputButton name="OpenWin_Note" value="Note" htmlClass="pBtn7" otherAttr=" id=\"btn15\""/>
                                                    <ezf:inputButton name="OpenWin_CtacSrch" value="Contacts" htmlClass="pBtn7" otherAttr=" id=\"btn16\""/>
                                                    <ezf:inputButton name="MoveWin_UploadDuns" value="Duns Upload" htmlClass="pBtn7" otherAttr=" id=\"btn17\""/>
                                                </td>
											</tr>
										</table>
									</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
						<c:choose>
<%-- #################################################### treeView ################################################### --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='Hierarchy'}">
								<table width="100%">
									<col valign="top">
									<tr>
										<td>
											<table border="0">
												<tr>
													<td valign="Top" width="1097" >
														<div id="RightTBL_Top" style="overflow-y:hidden; height:24; overflow-x:hidden; width:1063" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
															<table border="1" cellpadding="0" cellspacing="0"  style="table-layout: fixed">
																
																<col width="231">
																<col width="21">
																<col width="157">
																<col width="150">
																<col width="75">
																<col width="40">
																<col width="60">
																<col width="70">
																<col width="101">
																<col width="50">
																<col width="50">
																<col width="60">
															<%
																if ("Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_03())) {
															%>
																<col width="60">
																<col width="65">
																<col width="75">
																<col width="75">
															<%
																}
															%>
																
																<tr height="24">
																	<td align="center" class="pClothBs">Acct#</td>
																	<td align="center" class="pClothBs">&nbsp;</td>
																	<td align="center" class="pClothBs">Acct Name</td>
																	<td align="center" class="pClothBs">Address</td>
																	<td align="center" class="pClothBs">Loc#</td>
																	<td align="center" class="pClothBs">Prim</td>
																	<td align="center" class="pClothBs">Type</td>
																	<td align="center" class="pClothBs">Relation</td>
																	<td align="center" class="pClothBs">Relationship(s)</td>
																	<td align="center" class="pClothBs">Bill To</td>
																	<td align="center" class="pClothBs">Ship To</td>
																	<td align="center" class="pClothBs">Acct Sts</td>
															<%
																if ("Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_03())) {
															%>
																	<td align="center" class="pClothBs">Loc Sts</td>
																	<td align="center" class="pClothBs">Bill To Sts</td>
																	<td align="center" class="pClothBs">Ship To Sts</td>
																	<td align="center" class="pClothBs">Acct Loc Sts</td>
															<%
																}
															%>
																</tr>
															</table>
														</div> 
															<div id="RightTBL"  style="overflow-x:scroll; overflow-y:scroll; height:198; width:1080; background-color:#FFFFFF"  onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
															<%@ page import="business.servlet.NMAL6710.NMAL6710Bean" %>
															<% if (((NMAL6710Bean)databean).getTreeView() != null) { %>
																<%
																	if ("Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_02()) || "Y".equals(((business.servlet.NMAL6710.NMAL6710Bean)databean).getXxChkBox_03())) {
																%>
																<uji:treeView
																	bean 				= "${__screenName__}"
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
																	
																	dataCellType			= "data;radio;data;data;data;data;data;data;data;data;data;data;data;data;data;data;data;"
																	columnWidth				= "231;21;157;150;75;40;60;70;101;50;50;0;60;60;65;75;75;"
																	dataAlignmentHorizontal	= "center;left;left;left;left;center;left;left;left;left;left;left;left;left;left;left;left;"
																	dataEditable			= ";;true;true;;;;;true;;;;;;;;;;;;"
																/>
																<%
																	} else {
																%>
																<uji:treeView
																	bean 				= "${__screenName__}"
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
																	
																	dataCellType			= "data;radio;data;data;data;data;data;data;data;data;data;data;data;"
																	columnWidth				= "231;21;157;150;75;40;60;70;101;50;50;0;60;"
																	dataAlignmentHorizontal	= "center;left;left;left;left;center;left;left;left;left;left;left;left;"
																	dataEditable			= ";;true;true;;;;;true;;;;;;;;"
																/>

																<%
																	}
																%>
															<% } %>
															</div>
													</td>
													</tr>
											</table>
											<%-- include S21TreeView.jsp --%>
															<%@ page import="business.servlet.NMAL6710.NMAL6710Bean" %>
															<jsp:include page="../treeView/S21TreeView.jsp">
																<jsp:param name="treeView" value='<%= ((NMAL6710Bean)databean).getTreeView() != null ? ((NMAL6710Bean)databean).getTreeView().isDisableAllFields() : false %>' />
																<jsp:param name="TreeViewPropertyNameList" value="treeView" />
															</jsp:include>
															<script type="text/javascript" defer>
															    var inputTextSizeList = [[2,21],[3,20],[8,13]];
															    setTreeviewStyle(inputTextSizeList);
															</script> 
										</td>
									</tr>
								</table>
							</c:when>
<%-- #################################################### treeView ################################################### --%>
<%-- #################################################### Preferred View Area ################################################### --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='QuickLookup' || pageScope._ezddatabean.xxDplyTab=='HierarchyQuickLookup'}">
								<table width="100%">
									<col valign="top">
									<tr>
										<td>
								<table cellpadding="0" cellspacing="0">
									<col width="003">
									<col width="200">
									<col width="900">
									<tr>
										<td></td>
										<td>
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td align="right">
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
										<td valign="Top" width="1107">
	<div style="float:left; display:block"> <!-- left table -->
          <div id='leftTblHead' style="display:block;">
          </div>
          <div id='leftTbl' style="float:left; display:block; height:133px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
          </div>
    </div>  <!-- left table -->
    <div style="float:left"> <!-- right table -->
    	
		<div id='rightTblHead' style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1090" >
													<col align="center" width="21">
													<col align="center" width="73">
													<col align="center" width="70">
													<col align="center" width="140">
													<col align="center" width="268">
													<col align="center" width="60">
													<col align="center" width="40">
													<col align="center" width="60">
													<col align="center" width="100">
													<col align="center" width="83">
													<col align="center" width="73">
													<col align="center" width="50">
													<col align="center" width="50">
													<col align="center" width="60">
													<col align="center" width="60">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="75">
												<!--
												-->
													<tr height="24">
														<td id="AH0" class="pClothBs">&nbsp;</td>
														<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNum_C1' )">Acct#<img id="sortIMG.dsAcctNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctRelnTpNm_C1' )">Relation<img id="sortIMG.dsAcctRelnTpNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNm_C1' )">Acct Name<img id="sortIMG.dsAcctNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxAllLineAddr_C1' )">Address<img id="sortIMG.xxAllLineAddr_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'locNum_C1' )">Loc#<img id="sortIMG.locNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxYesNoCd_C1' )">Prim<img id="sortIMG.xxYesNoCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctTpNm_C1' )">Type<img id="sortIMG.dsAcctTpNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxCtlNm_C1' )">Relationship(s)<img id="sortIMG.xxCtlNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'relnDsAcctNum_C1' )">Parent Acct#<img id="sortIMG.relnDsAcctNum_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'dsAcctNum_C2' )">Orig Acct#<img id="sortIMG.dsAcctNum_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'billToCustCd_C1' )">Bill To<img id="sortIMG.billToCustCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'shipToCustCd_C1' )">Ship To<img id="sortIMG.shipToCustCd_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C1' )">Acct Sts<img id="sortIMG.xxRgtnStsTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C2' )">Loc Sts<img id="sortIMG.xxRgtnStsTxt_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C3' )">Bill To Sts<img id="sortIMG.xxRgtnStsTxt_C3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C4' )">Ship To Sts<img id="sortIMG.xxRgtnStsTxt_C4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxRgtnStsTxt_C5' )">Acct Loc Sts<img id="sortIMG.xxRgtnStsTxt_C5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<!--
												-->
													</tr>
												</table>

											</div><!-- rightTblHead -->
											<div  id="rightTbl" style="width:1107px; height:150px; display:block; overflow:scroll; margin:0px; padding:0px;">
												<table style="table-layout:fixed;"  border="1" cellpadding="0" cellspacing="0" id="C" width="1080">
													<col align="left" width="21">
													<col align="left" width="73">
													<col align="left" width="70">
													<col align="left" width="140">
													<col align="left" width="268">
													<col align="left" width="60">
													<col align="left" width="40">
													<col align="left" width="60">
													<col align="left" width="100">
													<col align="left" width="83">
													<col align="left" width="73">
													<col align="left" width="50">
													<col align="left" width="50">
													<col align="left" width="60">
													<col align="left" width="60">
													<col align="left" width="65">
													<col align="left" width="75">
													<col align="left" width="75">
												<!--
												-->
                                                    <ezf:row ezfHyo="C">
													<tr height="24">
														<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" otherAttr=" size=\"3\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:anchor name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToAcct" otherAttr=" ezfanchortext=\"\" id=\"dsAcctNum_C1#{EZF_ROW_NUMBER}\"">
															<ezf:label name="dsAcctNum_C1" ezfName="dsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:inputText name="dsAcctRelnTpNm_C1" ezfName="dsAcctRelnTpNm_C1" value="WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctNm_C1" ezfName="dsAcctNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxAllLineAddr_C1" ezfName="xxAllLineAddr_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"37\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:anchor name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToLoc" otherAttr=" ezfanchortext=\"\" id=\"locNum_C1#{EZF_ROW_NUMBER}\"">
															<ezf:label name="locNum_C1" ezfName="locNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:inputText name="xxYesNoCd_C1" ezfName="xxYesNoCd_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsAcctTpNm_C1" ezfName="dsAcctTpNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxCtlNm_C1" ezfName="xxCtlNm_C1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:anchor name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToAcctReln" otherAttr=" ezfanchortext=\"\" id=\"relnDsAcctNum_C1#{EZF_ROW_NUMBER}\"">
															<ezf:label name="relnDsAcctNum_C1" ezfName="relnDsAcctNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:anchor name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToAcctOrg" otherAttr=" ezfanchortext=\"\" id=\"dsAcctNum_C2#{EZF_ROW_NUMBER}\"">
															<ezf:label name="dsAcctNum_C2" ezfName="dsAcctNum_C2" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:label name="billToCustCd_C1" ezfName="billToCustCd_C1" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="shipToCustCd_C1" ezfName="shipToCustCd_C1" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="xxRgtnStsTxt_C1" ezfName="xxRgtnStsTxt_C1" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="xxRgtnStsTxt_C2" ezfName="xxRgtnStsTxt_C2" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="xxRgtnStsTxt_C3" ezfName="xxRgtnStsTxt_C3" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="xxRgtnStsTxt_C4" ezfName="xxRgtnStsTxt_C4" ezfHyo="C" ezfArrayNo="0" /></a></td>
														<td><ezf:label name="xxRgtnStsTxt_C5" ezfName="xxRgtnStsTxt_C5" ezfHyo="C" ezfArrayNo="0" /></a></td>
													</tr>
													</ezf:row>
												<!--
												-->
												</table>
											</div><!-- rightTbl -->
										</td>
									</tr>
								</table>
							</div><!-- parentBoxA -->
										</td>
									</tr>
								</table>
							</c:when>
<%-- #################################################### Preferred View Area ################################################### --%>
						</c:choose>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "C", 0, true);
</script>

<jsp:include page="../wfcomp/S21WfWorkListSetPageInvoker.jsp">
	<jsp:param name="processCodes" value='<%=databean.getWfWorklistProcessCodes()%>' />
	<jsp:param name="taskCodes" value='<%=databean.getWfWorklistTaskCodes()%>' />
	<jsp:param name="displayColumn" value="Task Name~wf:taskName~center~" />
	<jsp:param name="displayColumn" value="Maintenance Unit~maintenanceUnitCode~center~" />
	<jsp:param name="displayColumn" value="Requested By~wf:requestedBy~center~" />
	<jsp:param name="displayColumn" value="Due Date~wf:processDueDate~center~MM/dd/yyyy HH:mm" />
	<jsp:param name="displayColumn" value="Task Started~wf:taskStartDate~center~MM/dd/yyyy HH:mm" />
	<jsp:param name="displayColumn" value="Process Created~wf:processStartDate~center~MM/dd/yyyy HH:mm" />
	<jsp:param name="displayColumn" value="Document ID~wf:documentId~center~" />
	<jsp:param name="toDoListDisplayMode" value="expanded" />
	<jsp:param name="toDoListHeight" value="645px" />
	<jsp:param name="canDoListDisplayMode" value="hidden" />
	<jsp:param name="windowWidth" value="1000px" />
	<jsp:param name="windowHeight" value="680px" />
	<jsp:param name="transitionNameOnSelect" value="SelectWorkItem" />
</jsp:include>

<jsp:include page="../wfcomp/S21WfTaskHistoryPageInvoker.jsp">
	<jsp:param name="displayMode" value="static" />
	<jsp:param name="windowWidth" value="1000px" />
	<jsp:param name="windowHeight" value="600px" />
	<jsp:param name="procInstId" value='<%=databean.getWfProcInstId()%>' />
</jsp:include>

<%-- **** Task specific area ends here **** --%>
