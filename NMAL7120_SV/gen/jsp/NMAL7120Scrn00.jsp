<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170913144150 --%>
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
			<input type="hidden" name="pageID" value="NMAL7120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSMP Search & Update">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">
							<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<!-- include S21BusinessProcessTAB --> 
													<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="0" cellspacing="0" height="120">
									<tr>
										<td valign="top">&nbsp;
                                        </td>
										<td valign="top" width="500">&nbsp;
											<fieldset>
											<table cellpadding="0" border="0"  width="500">
												<tr>
													<td colspan="3" class="stab"><b>Search Filter</b></td>
												</tr>
												<tr>
													<td class="stab">Account Name(*):</td>
													<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"360\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="Acct_Link" >Account Number(*):</ezf:anchor></td>
													<td><ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">CSMP#(*):</td>
													<td><ezf:inputText name="csmpNum_H1" ezfName="csmpNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"15\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">CSA#(*):</td>
													<td><ezf:inputText name="dlrRefNum_H1" ezfName="dlrRefNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="Prc_Link" >CSMP Level(*):</ezf:anchor></td>
													<td><ezf:inputText name="prcCatgNm_H1" ezfName="prcCatgNm_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"75\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="Contr_Link" >Contract#(*):</ezf:anchor></td>
													<td><ezf:inputText name="prcContrNum_H1" ezfName="prcContrNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Effective Date From:</td>
													<td><ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
													&nbsp;&nbsp;<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" otherAttr=" id=\"xxChkBox_H1\""/>No CSMP#</td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Expiration Date:</td>
													<td><ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" ></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Status:</td>
													<td><ezf:select name="csmpContrActvFlg_H1" ezfName="csmpContrActvFlg_H1" ezfBlank="1" ezfCodeName="csmpContrActvFlg_L1" ezfDispName="xxWfProcStsNm_L1" otherAttr=" style=\"width:150\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Dealer Code(*):</td>
													<td><ezf:inputText name="rtlDlrCd_H1" ezfName="rtlDlrCd_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Renewed CSMP#(*):</td>
													<td><ezf:inputText name="rnwCsmpNum_H1" ezfName="rnwCsmpNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="Clear_Search" value="Clear" htmlClass="pBtn6" /></td>
												</tr>
											</table>
											</fieldset>
										</td>
										<td>&nbsp;
										</td>
										<td valign="top" width="430">&nbsp;
											<fieldset>
											<table cellpadding="0" border="0" width="430">
												<tr>
													<td colspan="3" class="stab"><b>Mass Update</b></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">CSMP#:</td>
													<td><ezf:inputText name="csmpNum_MU" ezfName="csmpNum_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"15\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">CSA#:</td>
													<td><ezf:inputText name="dlrRefNum_MU" ezfName="dlrRefNum_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<!-- S21_NA#20206(Sol#269)
												<tr>
													<td class="stab"><a href="#" onclick="sendServer('Contr_Mass_Link')" ezfname="xxLinkProt_04">Contract#:</a></td>
													<td><input type="text" name="prcContrNum_MU" value="XX"  size="30" maxlength="50" ezftoupper="" ezfname="prcContrNum_MU"></td>
													<td></td>
												</tr>-->
												<tr>
													<td class="stab">Note:</td>
													<td><ezf:inputText name="csmpNumCmntTxt_MU" ezfName="csmpNumCmntTxt_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"90\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Effective Date From:</td>
													<td><ezf:inputText name="effFromDt_MU" ezfName="effFromDt_MU" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_MU', 4);" ></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Expiration Date:</td>
													<td><ezf:inputText name="effThruDt_MU" ezfName="effThruDt_MU" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_MU', 4);" ></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Status:</td>
													<td><ezf:select name="csmpContrActvFlg_MU" ezfName="csmpContrActvFlg_MU" ezfBlank="1" ezfCodeName="csmpContrActvFlg_L2" ezfDispName="xxWfProcStsNm_L2" otherAttr=" style=\"width:150\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Rejection Date:</td>
													<td><ezf:inputText name="cusaRejDt_MU" ezfName="cusaRejDt_MU" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('cusaRejDt_MU', 4);" ></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Equipment Markup:</td>
													<td><ezf:inputText name="uplftEquipRatio_MU" ezfName="uplftEquipRatio_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Accessory Markup:</td>
													<td><ezf:inputText name="uplftAsryRatio_MU" ezfName="uplftAsryRatio_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Renewed CSMP#:</td>
													<td><ezf:inputText name="rnwCsmpNum_MU" ezfName="rnwCsmpNum_MU" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td></td>
													<td></td>
													<td><ezf:inputButton name="Mass_Update_CSMP" value="Update" htmlClass="pBtn6" /></td>
												</tr>

											</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<hr>
								<ezf:inputHidden name="xxMsgTxt" ezfName="xxMsgTxt" otherAttr=" ezftoupper=\"\""/>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col align="center" width="25">
									<col width="150">
									<col width="925">
									<tr>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('Click_SelAll_UnSelAll_Checkbox')" /></td>
										<td class="stab">Select All/Unselect All</td>
									
										<td align="right">
											<table>
												<tr>
													<ezf:skip>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum_10" value="1" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum_10"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowToNum_10" ezfName="xxPageShowToNum_10" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
													</ezf:skip>
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
														<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_10" />
														<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_10" />
														<jsp:param name="ViewMode"       value="FULL" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" >
									<tr>
										<td valign="Top" width="1132">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:615; height:48; float:left;">
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="615" height="48">
													<col align="center" width="25">
													<col align="center" width="100">
													<col align="center" width="290">
													<col align="center" width="100">
													<col align="center" width="100">
													<tr>
														<td class="pClothBs">&nbsp;&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">Account Number<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csmpNum_A1' )">CSMP Number<img id="sortIMG.csmpNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dlrRefNum_A1' )">CSA Number<img id="sortIMG.dlrRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:472; height:48; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1785" height="48">
													<col align="center" width="200">
													<col align="center" width="200">
													<col align="center" width="140">
													<col align="center" width="150">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="200">
													<col align="center" width="50">
													<col align="center" width="53">
													<tr>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcCatgNm_A1' )">CSMP Level<img id="sortIMG.prcCatgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcContrNum_A1' )">Contract Number<img id="sortIMG.prcContrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'origCoaBrCd_A1' )">Originating GL Branch Code<img id="sortIMG.origCoaBrCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlDlrCd_A1' )">Origin Dealer Code<img id="sortIMG.rtlDlrCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A1' )">Effective Date<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A1' )">Expiration Date<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cusaRejDt_A1' )">Rejection Date<img id="sortIMG.cusaRejDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'erlTrmnDt_A1' )">Early Term Date<img id="sortIMG.erlTrmnDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rnwCsmpNum_A1' )">Renewed CSMP#<img id="sortIMG.rnwCsmpNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'uplftEquipRatio_A1' )">Markup Equipment<img id="sortIMG.uplftEquipRatio_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'uplftAsryRatio_A1' )">Markup Accessory<img id="sortIMG.uplftAsryRatio_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csmpNumCmntTxt_A1' )">Notes<img id="sortIMG.csmpNumCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custMbrId_A1' )">Customer  Membership ID<img id="sortIMG.custMbrId_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csmpContrActvFlg_A1' )">Active<img id="sortIMG.csmpContrActvFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxModeNm13Txt_A1' )">Expired<img id="sortIMG.xxModeNm13Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:140; width:615; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="615" id="A1">
													<col align="center" width="25">
													<col align="center" width="100">
													<col align="center" width="290">
													<col align="center" width="100">
													<col align="center" width="100">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"csmpTabIdx_01#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"8\""/><ezf:inputButton name="LineCustNum" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"360\" size=\"42\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="csmpNum_A1" ezfName="csmpNum_A1" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"15\" size=\"13\""/></td>
														<td><ezf:inputText name="dlrRefNum_A1" ezfName="dlrRefNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"20\" size=\"13\" id=\"csmpTabIdx_02#{EZF_ROW_NUMBER}\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" maxlength="20" size="8"/><input type="button" class="pBtn1" value="..." name="LineCustNum" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="dsAcctNm_A1" ezfName="dsAcctNm_A1" maxlength="360" size="42" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="csmpNum_A1" ezfName="csmpNum_A1" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" ezftoupper="" size="13"/></td>
														<td><input type="text" name="dlrRefNum_A1" ezfName="dlrRefNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" maxlength="20" size="13" id="csmpTabIdx_02#{EZF_ROW_NUMBER}"/></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:157; width:492; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1785" id="A2">
													<col align="center" width="200">
													<col align="center" width="200">
													<col align="center" width="140">
													<col align="center" width="150">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="98">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="200">
													<col align="center" width="50">
													<col align="center" width="53">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputText name="prcCatgNm_A1" ezfName="prcCatgNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWW75" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"75\" size=\"22\" id=\"csmpTabIdx_03#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="LinePrcCatgCd" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="prcContrNum_A1" ezfName="prcContrNum_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"50\" size=\"25\""/><!--<input type="button" class="pBtn1" value="..." name="LinePrcContrNum" onclick="sendServer(this)" ezfhyo="A">-->	</td>
														<td><ezf:inputText name="origCoaBrCd_A1" ezfName="origCoaBrCd_A1" value="WW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"3\" size=\"13\""/><ezf:inputButton name="LineCoaBrCd" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="rtlDlrCd_A1" ezfName="rtlDlrCd_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"20\""/></td>
														<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><ezf:inputText name="cusaRejDt_A1" ezfName="cusaRejDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cusaRejDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><ezf:inputText name="erlTrmnDt_A1" ezfName="erlTrmnDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('erlTrmnDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><ezf:inputText name="rnwCsmpNum_A1" ezfName="rnwCsmpNum_A1" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"15\" size=\"20\""/></td>
														<td><ezf:inputText name="uplftEquipRatio_A1" ezfName="uplftEquipRatio_A1" value="9,999.99999" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"11\" size=\"20\""/></td>
														<td><ezf:inputText name="uplftAsryRatio_A1" ezfName="uplftAsryRatio_A1" value="9,999.99999" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"11\" size=\"20\""/></td>
														<td><ezf:inputText name="csmpNumCmntTxt_A1" ezfName="csmpNumCmntTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"90\" size=\"20\""/></td>
														<td><ezf:inputText name="custMbrId_A1" ezfName="custMbrId_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" maxlength=\"250\" size=\"27\""/></td>
														<td><ezf:inputCheckBox name="csmpContrActvFlg_A1" ezfName="csmpContrActvFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"csmpTabIdx_04#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:label name="xxModeNm13Txt_A1" ezfName="xxModeNm13Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    </tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><input type="text" name="prcCatgNm_A1" ezfName="prcCatgNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWW75" ezfHyo="A" ezfArrayNo="0" maxlength="75" size="22" id="csmpTabIdx_03#{EZF_ROW_NUMBER}"/><input type="button" class="pBtn1" value="..." name="LinePrcCatgCd" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="prcContrNum_A1" ezfName="prcContrNum_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" maxlength="50" size="22"/><input type="button" class="pBtn1" value="..." name="LinePrcContrNum" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="origCoaBrCd_A1" ezfName="origCoaBrCd_A1" value="WW3" ezfHyo="A" ezfArrayNo="0" maxlength="3" size="13"/><input type="button" class="pBtn1" value="..." name="LineCoaBrCd" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="rtlDlrCd_A1" ezfName="rtlDlrCd_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" maxlength="20" size="20"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A" ezftoupper><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A" ezftoupper><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="cusaRejDt_A1" ezfname="cusaRejDt_A1" ezfhyo="A" ezftoupper><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cusaRejDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="erlTrmnDt_A1" ezfname="erlTrmnDt_A1" ezfhyo="A" ezftoupper><img src="img/calendar.gif" class="pCalendar" onclick="calendar('erlTrmnDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="rnwCsmpNum_A1" ezfName="rnwCsmpNum_A1" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" maxlength="15" size="20"/></td>
														<td><input type="text" name="uplftEquipRatio_A1" ezfName="uplftEquipRatio_A1" value="9,999.99999" ezfHyo="A" ezfArrayNo="0" maxlength="11" size="20"/></td>
														<td><input type="text" name="uplftAsryRatio_A1" ezfName="uplftAsryRatio_A1" value="9,999.99999" ezfHyo="A" ezfArrayNo="0" maxlength="11" size="20"/></td>
														<td><input type="text" name="csmpNumCmntTxt_A1" ezfName="csmpNumCmntTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" maxlength="90" size="20"/></td>
														<td><input type="text" name="custMbrId_A1" ezfName="custMbrId_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" maxlength="250" size="27"/></td>
														<td><input type="checkbox" name="csmpContrActvFlg_A1" ezfname="csmpContrActvFlg_A1" value="Y" ezfhyo="A" id="csmpTabIdx_04#{EZF_ROW_NUMBER}"></td>
														<td><label name="xxModeNm13Txt_A1" ezfname="xxModeNm13Txt_A1" ezfout ezfhyo="A" ezfArrayNo="0">&nbsp;</label></td>                                                    </tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right" height="28">
                            <ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="Del" value="Del" htmlClass="pBtn6" />&nbsp;&nbsp;&nbsp;
                        </td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
