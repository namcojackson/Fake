<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190308154104 --%>
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
			<input type="hidden" name="pageID" value="NFAL0200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="COA Segments Maintenance">
			
<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
			<%-- ###TAB - HEAD --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
				<table border="0" cellpadding="0" cellspacing="0" width="1100" style="margin-left:5px;">
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
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:290px;\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="SaveSearch" ezfName="SaveSearch" value="SaveSearch" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="DeleteSearch" ezfName="DeleteSearch" value="DeleteSearch" htmlClass="pBtn7" /></td>
							<td>&nbsp;</td>
						</tr>
				</table>

				<hr size="1" noshade>
				<table  border="0">
					<col width="106">
					<col width="100">
					<col width="112">
					<col width="106">
					<col width="106">
					<col width="106">
					<col width="96">
					<col width="136">
					<col width="120">
					<col width="">
					<tr>
						<td class="stab">Company Code</td>
						<td><ezf:inputText name="coaCmpyCd" ezfName="coaCmpyCd" value="ADB" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaBrCd_LK" ezfName="coaBrCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaBr" otherAttr=" id=\"coaBrCd_LK\" ezfanchortext=\"\"">Branch  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaBrCd" ezfName="coaBrCd" value="500" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaCcCd_LK" ezfName="coaCcCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaCc" otherAttr=" id=\"coaCcCd_LK\" ezfanchortext=\"\"">Cost Center  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaCcCd" ezfName="coaCcCd" value="000000" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaAcctCd_LK" ezfName="coaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaAcct" otherAttr=" id=\"coaAcctCd_LK\" ezfanchortext=\"\"">Natural Account  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaAcctCd" ezfName="coaAcctCd" value="000000" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaProdCd_LK" ezfName="coaProdCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaProd" otherAttr=" id=\"coaProdCd_LK\" ezfanchortext=\"\"">Product Code  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaProdCd" ezfName="coaProdCd" value="AO" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
						
					</tr>
				</table>

				<table border="0">
					<col width="106">
					<col width="100">
					<col width="112">
					<col width="106">
					<col width="106">
					<col width="106">
					<col width="96">
					<col width="136">
					<col width="">
					<col width="">
					<tr>
						<td class="stab"><ezf:anchor name="coaChCd_LK" ezfName="coaChCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaCh" otherAttr=" id=\"coaChCd_LK\" ezfanchortext=\"\"">Sales Channel (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaChCd" ezfName="coaChCd" value="000" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaAfflCd_LK" ezfName="coaAfflCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaAffl" otherAttr=" id=\"coaAfflCd_LK\" ezfanchortext=\"\"">Intercompany Code (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaAfflCd" ezfName="coaAfflCd" value="ADB" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaProjCd_LK" ezfName="coaProjCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaProj" otherAttr=" id=\"coaProjCd_LK\" ezfanchortext=\"\"">Merchandise Type  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaProjCd" ezfName="coaProjCd" value="10" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
						<td class="stab"><ezf:anchor name="coaExtnCd_LK" ezfName="coaExtnCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaExtn" otherAttr=" id=\"coaExtnCd_LK\" ezfanchortext=\"\"">Business Unit  (*)</ezf:anchor></td>
						<td><ezf:inputText name="coaExtnCd" ezfName="coaExtnCd" value="111" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
						
					</tr>
				</table>
				
				<table border="0">
					<col width="760">
					<col width="20">
					<col width="190">
					<col width="120">
					<col width="120">
					<col width="120">
					<col width="120">
					<tr>
						<td></td>
						<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
						<td>Show Combinations</td>
						<td><ezf:inputButton name="ClearBtn" value="Clear" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
						<td><ezf:inputButton name="SearchBtn" value="Search" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
						<td><ezf:inputButton name="DownloadCombBtn" value="Comb Download" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
						<td><ezf:inputButton name="DownloadCoaBtn" value="COA Download" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
					</tr>
				</table>
				
				<hr>
				
				<div id="RightTBL_TopA" style="overflow-x:hidden; overflow-y:hidden; width:1100;float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL_A' ));" >
				<table border="1" cellpadding="0" cellspacing="0" style="width:1364px">
					<col width="40"  align="center">
					<col width="320" align="center">
					<col width="400" align="center">
					<col width="90" align="center">
					<col width="80" align="center">
					<col width="80" align="center">
					<col width="80" align="center">
					<col width="80" align="center">
					<col width="80" align="center">
					
					<col width="17" align="center">
					<tr>
						<td class="pClothBs">&nbsp;</td>
						<td class="pClothBs">COA Code Combination</td>
						<td class="pClothBs">Account Description</td>
						<td class="pClothBs">Type</td>
						<td class="pClothBs">From Date</td>
						<td class="pClothBs">To Date</td>
						<td class="pClothBs">Enabled</td>
						<td class="pClothBs">Posting</td>
						<td class="pClothBs">Budgeting</td>
						
						<td class="pClothBs"></td>
					</tr>
				</table>
				</div>
				
				<div id="RightTBL_A" style="overflow-x:scroll; overflow-y:scroll; height:160; width:1117; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL_TopA' ));" >
					<table border="1" cellpadding="0" cellspacing="0" id="A" style="width:1344px;word-break:break-all;">
						<col width="40" align="center">
						<col width="320">
						<col width="400">
						<col width="90">
						<col width="80">
						<col width="80">
						<col width="80">
						<col width="80">
						<col width="80">
						<tbody>
							<ezf:row ezfHyo="A">
								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
									<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr="onClick=\"sendServer('OnChange_RadioBtn');\""/></td>
									<td><ezf:inputText name="xxComnScrCondValTxt_A" ezfName="xxComnScrCondValTxt_A" value="ADB.000.123456.12345678.AA.000.ADB.00.000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"44\" maxlength=\"44\""/></td>
									<td><ezf:inputText name="xxRecNmTxt_A" ezfName="xxRecNmTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"55\" maxlength=\"100\""/></td>
									<td><ezf:inputText name="xxRptTpTxt_A" ezfName="xxRptTpTxt_A" value="Asset" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									<td><ezf:label name="coaStartActvDt_A" ezfName="coaStartActvDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="coaEndActvDt_A" ezfName="coaEndActvDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center"><ezf:inputCheckBox name="coaGlCmbnEnblFlg_A" ezfName="coaGlCmbnEnblFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center"><ezf:inputCheckBox name="coaGlCmbnPstgAllwFlg_A" ezfName="coaGlCmbnPstgAllwFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center"><ezf:inputCheckBox name="coaGlCmbnBdgAllwFlg_A" ezfName="coaGlCmbnBdgAllwFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
							</ezf:row>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr height="7">
			<td></td>
		</tr>	
		<tr>
			<td>
				
				
<%-- ######################################## DETAIL ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<ul>
							<li class="pTab_ON" id="BR" ><ezf:anchor name="" ezfName="xxTabProt_C" ezfEmulateBtn="1" ezfGuard="TAB_Br" >Branch</ezf:anchor></li>
							<li class="pTab_OFF" id="CC" ><ezf:anchor name="" ezfName="xxTabProt_D" ezfEmulateBtn="1" ezfGuard="TAB_Cc" >Cost Center</ezf:anchor></li>
							<li class="pTab_OFF" id="ACCT" ><ezf:anchor name="" ezfName="xxTabProt_E" ezfEmulateBtn="1" ezfGuard="TAB_Acct" >Natural Account</ezf:anchor></li>
							<li class="pTab_OFF" id="PROD" ><ezf:anchor name="" ezfName="xxTabProt_G" ezfEmulateBtn="1" ezfGuard="TAB_Prod" >Product Code</ezf:anchor></li>
							<li class="pTab_OFF" id="CH" ><ezf:anchor name="" ezfName="xxTabProt_B" ezfEmulateBtn="1" ezfGuard="TAB_Ch" >Sales Channel</ezf:anchor></li>
							<li class="pTab_OFF" id="AFFL" ><ezf:anchor name="" ezfName="xxTabProt_H" ezfEmulateBtn="1" ezfGuard="TAB_Affl" >Intercompany</ezf:anchor></li>
							<li class="pTab_OFF" id="PROJ" ><ezf:anchor name="" ezfName="xxTabProt_F" ezfEmulateBtn="1" ezfGuard="TAB_Proj" >MDSE Type</ezf:anchor></li>
							<li class="pTab_OFF" id="EXTN" ><ezf:anchor name="" ezfName="xxTabProt_I" ezfEmulateBtn="1" ezfGuard="TAB_Extn" >Business Unit</ezf:anchor></li>
						</ul>
				</div>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'CH'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_ON";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="80" width="100%">
							<col valign="top">
						
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="570" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
														</tr>
													</table>
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="B">
														<col width="80">
														<col width="570">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
															<tbody>
																<ezf:row ezfHyo="B">
																	<tr>
																		<td><ezf:label name="coaChCd_B" ezfName="coaChCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaChDescTxt_B" ezfName="coaChDescTxt_B" value="TEST" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"78\""/></td>
																		<td><ezf:label name="coaStartActvDt_B" ezfName="coaStartActvDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_B" ezfName="coaEndActvDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_B" ezfName="coaEnblFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_B" ezfName="coaPstgAllwFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_B" ezfName="coaBdgAllwFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'BR'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_ON";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
										
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table  border="0" cellpadding="1" cellspacing="0">
										<col width="">
											<tr>
												<td>
													<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1100;float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
													<table border="1" cellpadding="0" cellspacing="0" style="width:2155px">
													<col width="80" align="center">
													<col width="330" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="200" align="center">
													<col width="200" align="center">
													<col width="200" align="center">
													<col width="200" align="center">
													<col width="100" align="center">
													<col width="200" align="center">
													<col width="100" align="center">
													<col width="17" align="center">
													
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
															<td class="pClothBs">Region</td>
															<td class="pClothBs">Zone</td>
															<td class="pClothBs">Print Branch</td>
															<td class="pClothBs">Branch Type</td>
															<td class="pClothBs">Contract Branch</td>
															<td class="pClothBs">Printer Code</td>
															<td class="pClothBs">GEO Code</td>
															
															<td class="pClothBs"></td>
															
														</tr>
													</table>
													</div>
													
													<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:169; width:1117; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
														<table border="1" cellpadding="0" cellspacing="0" id="C" style="width:2135px;word-break:break-all;">
															<col width="80">
															<col width="330">
															<col width="80">
															<col width="80">
															<col width="80" align="center">
															<col width="80" align="center">
															<col width="80" align="center">
															<col width="80" align="center">
															<col width="200" align="center">
															<col width="200" align="center">
															<col width="200" align="center">
															<col width="200" align="center">
															<col width="100" align="center">
															<col width="200" align="center">
															<col width="100" align="center">
															<tbody>
																<ezf:row ezfHyo="C">
																	<tr>
																		<td><ezf:label name="coaBrCd_C" ezfName="coaBrCd_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaBrDescTxt_C" ezfName="coaBrDescTxt_C" value="TEST" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"42\""/></td>
																		<td><ezf:label name="coaStartActvDt_C" ezfName="coaStartActvDt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_C" ezfName="coaEndActvDt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_C" ezfName="coaEnblFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_C" ezfName="coaPstgAllwFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_C" ezfName="coaBdgAllwFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:select name="coaBrRgCd_C" ezfName="coaBrRgCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaBrRgCd_LC" ezfDispName="coaBrRgDescTxt_LN" otherAttr=" style=\"width=90%;\" id=\"coaBrRgCd_C#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:select name="coaBrZnCd_C" ezfName="coaBrZnCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaBrZnCd_LC" ezfDispName="coaBrZnDescTxt_LN" otherAttr=" style=\"width=90%;\" id=\"coaBrZnCd_C#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:select name="coaBrPrintCd_C" ezfName="coaBrPrintCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaBrPrintCd_LC" ezfDispName="coaBrPrintDescTxt_LN" otherAttr=" style=\"width=90%;\" id=\"coaBrPrintCd_C#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:select name="coaBrTpCd_C" ezfName="coaBrTpCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaBrTpCd_LC" ezfDispName="coaBrTpDescTxt_LN" otherAttr=" style=\"width=90%;\" id=\"coaBrTpCd_C#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="contrCoaBrCd_C" ezfName="contrCoaBrCd_C" value="999" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" id=\"contrCoaBrCd_C#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_Branch" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Branch#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:select name="coaBrPrtrCd_C" ezfName="coaBrPrtrCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaBrPrtrCd_LC" ezfDispName="coaBrPrtrDescTxt_LN" otherAttr=" style=\"width=90%;\" id=\"coaBrPrtrCd_C#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="coaBrGeoTxt_C" ezfName="coaBrGeoTxt_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"coaBrGeoTxt_C#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'CC'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_ON";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="470" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="100" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
															<td class="pClothBs">Business Entity</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="D">
														<col width="80">
														<col width="470">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="100" align="center">
															<tbody>
																<ezf:row ezfHyo="D">
																	<tr>
																		<td><ezf:label name="coaCcCd_D" ezfName="coaCcCd_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaCcDescTxt_D" ezfName="coaCcDescTxt_D" value="TEST" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"65\""/></td>
																		<td><ezf:label name="coaStartActvDt_D" ezfName="coaStartActvDt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_D" ezfName="coaEndActvDt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_D" ezfName="coaEnblFlg_D" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_D" ezfName="coaPstgAllwFlg_D" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_D" ezfName="coaBdgAllwFlg_D" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:select name="coaLineBizTpCd_D" ezfName="coaLineBizTpCd_D" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaLineBizTpCd_C" ezfDispName="coaLineBizTpDescTxt_D" otherAttr=" style=\"width:100px;\""/>
																		</td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'ACCT'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_ON";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="300" align="center">
													<col width="120" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">Type</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
															<td class="pClothBs">Accrual</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="E">
														<col width="80">
														<col width="300" align="center">
													    <col width="120">
													    <col width="80">
														<col width="80">
														<col width="80" align="center">
														<col width="80" align="center">
														<col width="80" align="center">
														<col width="80" align="center">
														<col width="80" align="center">
															<tbody>
																<ezf:row ezfHyo="E">
																	<tr>
																		<td><ezf:label name="coaAcctCd_E" ezfName="coaAcctCd_E" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaAcctDescTxt_E" ezfName="coaAcctDescTxt_E" value="TEST" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"38\""/></td>
																		<td><ezf:label name="trialBalTpNm_E" ezfName="trialBalTpNm_E" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaStartActvDt_E" ezfName="coaStartActvDt_E" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_E" ezfName="coaEndActvDt_E" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_E" ezfName="xxChkBox_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_E" ezfName="coaEnblFlg_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_E" ezfName="coaPstgAllwFlg_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_E" ezfName="coaBdgAllwFlg_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaAcrlFlg_E" ezfName="coaAcrlFlg_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'PROJ'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_ON";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="570" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="F">
														<col width="80">
														<col width="570">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
															<tbody>
																<ezf:row ezfHyo="F">
																	<tr>
																		<td><ezf:label name="coaProjCd_F" ezfName="coaProjCd_F" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaProjDescTxt_F" ezfName="coaProjDescTxt_F" value="TEST" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"78\""/></td>
																		<td><ezf:label name="coaStartActvDt_F" ezfName="coaStartActvDt_F" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_F" ezfName="coaEndActvDt_F" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_F" ezfName="xxChkBox_F" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_F" ezfName="coaEnblFlg_F" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_F" ezfName="coaPstgAllwFlg_F" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_F" ezfName="coaBdgAllwFlg_F" value="Y" ezfHyo="F" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'PROD'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_ON";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="570" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="G">
														<col width="80">
														<col width="570">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
															<tbody>
																<ezf:row ezfHyo="G">
																	<tr>
																		<td><ezf:label name="coaProdCd_G" ezfName="coaProdCd_G" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaProdDescTxt_G" ezfName="coaProdDescTxt_G" value="TEST" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"78\""/></td>
																		<td><ezf:label name="coaStartActvDt_G" ezfName="coaStartActvDt_G" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_G" ezfName="coaEndActvDt_G" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_G" ezfName="xxChkBox_G" value="Y" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_G" ezfName="coaEnblFlg_G" value="Y" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_G" ezfName="coaPstgAllwFlg_G" value="Y" ezfHyo="G" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_G" ezfName="coaBdgAllwFlg_G" value="Y" ezfHyo="G" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'AFFL'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_ON";
							document.getElementById( "EXTN" ).className = "pTab_OFF";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
													<col width="80" align="center">
													<col width="570" align="center">
													<col width="80" align="center">
													<col width="80" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
													<col width="60" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="H">
														<col width="80">
														<col width="570">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
															<tbody>
																<ezf:row ezfHyo="H">
																	<tr>
																		<td><ezf:label name="coaAfflCd_H" ezfName="coaAfflCd_H" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaAfflDescTxt_H" ezfName="coaAfflDescTxt_H" value="TEST" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"78\""/></td>
																		<td><ezf:label name="coaStartActvDt_H" ezfName="coaStartActvDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_H" ezfName="coaEndActvDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_H" ezfName="coaEnblFlg_H" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_H" ezfName="coaPstgAllwFlg_H" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_H" ezfName="coaBdgAllwFlg_H" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
					
					<c:when test="${pageScope._ezddatabean.xxDplyTab == 'EXTN'}">
						<script type="text/javascript">
							document.getElementById( "CH" ).className = "pTab_OFF";
							document.getElementById( "BR" ).className = "pTab_OFF";
							document.getElementById( "CC" ).className = "pTab_OFF";
							document.getElementById( "ACCT" ).className = "pTab_OFF";
							document.getElementById( "PROJ" ).className = "pTab_OFF";
							document.getElementById( "PROD" ).className = "pTab_OFF";
							document.getElementById( "AFFL" ).className = "pTab_OFF";
							document.getElementById( "EXTN" ).className = "pTab_ON";
						</script>
							
						<%-- ###TAB - BODY --%>
						<div class="pTab_BODY_In">
							<table height="180" width="100%">
							<col valign="top">
								<tr>
									<td>
										<table>
										<col width="">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0">
														<col width="80" align="center">
														<col width="570" align="center">
														<col width="80" align="center">
														<col width="80" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<tr>
															<td class="pClothBs">Value</td>
															<td class="pClothBs">Description</td>
															<td class="pClothBs">From Date</td>
															<td class="pClothBs">To Date</td>
															<td class="pClothBs">Parent</td>
															<td class="pClothBs">Enabled</td>
															<td class="pClothBs">Posting</td>
															<td class="pClothBs">Budgeting</td>
														</tr>
													</table>
													
													<div style="overflow:auto; height:169;">
														<table border="1" cellpadding="1" cellspacing="0" id="I">
														<col width="80">
														<col width="570">
														<col width="80">
														<col width="80">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
														<col width="60" align="center">
															<tbody>
																<ezf:row ezfHyo="I">
																	<tr>
																		<td><ezf:label name="coaExtnCd_I" ezfName="coaExtnCd_I" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="coaExtnDescTxt_I" ezfName="coaExtnDescTxt_I" value="TEST" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"78\""/></td>
																		<td><ezf:label name="coaStartActvDt_I" ezfName="coaStartActvDt_I" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:label name="coaEndActvDt_I" ezfName="coaEndActvDt_I" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_I" ezfName="xxChkBox_I" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaEnblFlg_I" ezfName="coaEnblFlg_I" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaPstgAllwFlg_I" ezfName="coaPstgAllwFlg_I" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td align="center"><ezf:inputCheckBox name="coaBdgAllwFlg_I" ezfName="coaBdgAllwFlg_I" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><ezf:inputButton name="OpenWin_Hrch" value="View Hierarcy" htmlClass="pBtn3" otherAttr=" style=\"width:100px;\""/></td>
								</tr>
							</table>
						</div>
					</c:when>
				</c:choose>	
			</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
