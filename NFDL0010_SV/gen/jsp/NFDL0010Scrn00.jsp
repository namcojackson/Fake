<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20211004184729 --%>
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
			<input type="hidden" name="pageID" value="NFDL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Collection Summary">

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>

<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="ISF"                  class="pTab_OFF"><a href="./ALAL1040Scrn00.html">ISF</a></li>
										<li title="Diff"                 class="pTab_ON" ><a href="./ALAL1070Scrn00.html">Diff</a></li>
										<li title="Invoice"              class="pTab_OFF"><a href="#">Invoice</a></li>
										<li title="WH Change"            class="pTab_OFF"><a href="#">WH Change</a></li>
										<li title="Work Order Receive"   class="pTab_OFF"><a href="#">W Ord Receive</a></li>
										<li title="Invoice Release"      class="pTab_OFF"><a href="#">Inv Release</a></li>
										<li title="Custom"               class="pTab_OFF"><a href="#">Custom</a></li>
										<li title="Assign Broker"        class="pTab_OFF"><a href="#">Broker</a></li>
										<li title="WH Schedule"          class="pTab_OFF"><a href="#">WH Schedule</a></li>
										<li title="Receiving Work Sheet" class="pTab_OFF"><a href="#">RWS</a></li>
										<li title="Seal"                 class="pTab_OFF"><a href="#">Seal</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0" onClick="location.href='#'"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
 After Convert to JSP, this area suppose to be deleted [TO] --%>

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">
					<table height="528">
						<tr valign="top">
							<td>
								<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed; margin-left:10px">
									<col width="100">
									<col width="200">
									<col width="30">
									<col width="100">
									<col width="200">
									<col width="30">
									<col width="100">
									<col width="200">
									<col width="100">
									<tr>

										<td class="stab">Display</td>
										<td><ezf:select name="cltDispTpCd_H" ezfName="cltDispTpCd_H" ezfBlank="1" ezfCodeName="cltDispTpCd_LC" ezfDispName="cltDispTpDescTxt_LD" otherEvent1=" onchange=\"sendServer('OnChange_Display')\"" otherAttr=" style=\"width:180px;\""/></td>
										<td></td>
										<td class="stab">Search Mode</td>
										<td><ezf:select name="xxModeCd_H" ezfName="xxModeCd_H" ezfCodeName="xxModeCd_LC" ezfDispName="xxModeNm23Txt_LD" otherAttr=" style=\"width:180px;\""/></td>
										<td></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>

										<td class="stab"><ezf:anchor name="xxQueryFltrTxt_L1" ezfName="xxQueryFltrTxt_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchCollectorPopup" otherAttr=" ezfanchortext=\"\"">Collector</ezf:anchor></td>
										<td>
											<ezf:inputText name="xxQueryFltrTxt_H1" ezfName="xxQueryFltrTxt_H1" value="Q06015,Q06016,Q06017" otherAttr=" size=\"25\" maxlength=\"200\""/>
										</td>
										<td></td>
										<td class="stab">Collector Name(*)</td>
										<td>
											<ezf:inputText name="cltPsnNm_H1" ezfName="cltPsnNm_H1" value="Marvin" otherAttr=" size=\"25\" maxlength=\"25\""/>
										</td>
										<td></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>

									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchAccountPopup" >Account#</ezf:anchor></td>
										<td>
											<ezf:inputText name="xxQueryFltrTxt_H2" ezfName="xxQueryFltrTxt_H2" value="XXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"25\" maxlength=\"200\""/>
										</td>
										<td></td>
										<td class="stab">Account Name(*)</td>
										<td>
											<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="XXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									
									<tr>
										<td><ezf:inputButton name="OpenWin_SearchAccountByTrxPopup" value="Search By Trx" htmlClass="pBtn8" /></td>
										<td>
                                            <ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" />
                                        </td>
										<td></td>
										<td class="stab">Portfolio Name(*)</td>
										<td>
											<ezf:inputText name="cltPtfoNm_H" ezfName="cltPtfoNm_H" value="XXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									
									<tr>
										<td class="stab" colspan="2"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" />Include Current</input></td>
  									    <td></td>
										<td class="stab"></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>


								<hr>

<%-- ######################################## DETAIL ######################################## --%>

								<%-- ###TAB - HEAD --%>


					<table width="1121">
						<col valign="top">
						<col width="600">
						<tr>
						<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td>
							  <TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
								  <TBODY>
								  <tr align="right">
									  <td>
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"         value="A" />
												<jsp:param name="ShowingFrom"     value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"       value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"       value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"        value="FULL" />
											</jsp:include>
									  </td>
								  </tr>
								  </TBODY>
							  </TABLE>
						    </td>
						</tr>
					</table>
						<table>
							<tr>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:350px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="2520px" style="margin-right:20px">
													<col width="30" align="center">	
													<col width="80"  align="center">
													<col width="125"  align="center">
													<col width="125"  align="center">
													<col width="100"  align="center">
													<col width="125"  align="center">
													<col width="125"  align="center">
													<col width="100"  align="center">
													<col width="140"  align="center">
													<col width="140"  align="center">
													<col width="140"  align="center">
													<col width="140"  align="center">
													<col width="80"  align="center">
													<col width="80"  align="center">
													<col width="50"  align="center">
													<col width="150"  align="center">
													<col width="150"  align="center">
													<col width="290"  align="center">
													<col width="140"  align="center">
													<col width="240"  align="center">
													<col width="140"  align="center">
													<col width="140"  align="center">
													<col width="140"  align="center">
													<col width="80"  align="center">
													<col width="140"  align="center">
													<tr height="32">
														<td class="pClothBs colFix" id="A3H0"></td>
														<td class="pClothBs " id="A3H1"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">Account#<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H3"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pmtTermDescTxt_A1' )">Payment Terms<img id="sortIMG.pmtTermDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H4"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsoAmt_A1' )">DSO<img id="sortIMG.dsoAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H5"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltPsnNm_A1' )">Collector Name<img id="sortIMG.cltPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H6"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltPtfoNm_A1' )">Portfolio Name<img id="sortIMG.cltPtfoNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H7"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsoAmt_A2' )">Collector DSO<img id="sortIMG.dsoAmt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H8"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'totBalAmt_A1' )">Over Due Amt<img id="sortIMG.totBalAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H9"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'netAmt_A1' )">Net Amt<img id="sortIMG.netAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H10"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealCltDsptAmt_A1' )">Disputes Amt<img id="sortIMG.dealCltDsptAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H11"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealCltPrmsAmt_A1' )">Promise Amt<img id="sortIMG.dealCltPrmsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H12"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltPrmsCratDt_A1' )">Promise Date<img id="sortIMG.cltPrmsCratDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H13"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltPrmsDt_A1' )">Promise Due Date<img id="sortIMG.cltPrmsDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H14"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltPrmsBrknFlg_A1' )">Broken Promise<img id="sortIMG.cltPrmsBrknFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H15"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_A1' )">Contact Name<img id="sortIMG.xxFullNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H16"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_AT' )">Contact Number<img id="sortIMG.dsCtacPntValTxt_AT" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H17"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_AE' )">Email Address<img id="sortIMG.dsCtacPntValTxt_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H18"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cratTsDplyTxt_A1' )">Last Contact Date<img id="sortIMG.cratTsDplyTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H19"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltHdrNoteTxt_A1' )">Notes<img id="sortIMG.cltHdrNoteTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H20"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem30Txt_A1' )">Status<img id="sortIMG.xxScrItem30Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H21"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltStrgyNm_A1' )">Strategy Name<img id="sortIMG.cltStrgyNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H22"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cltWrkItemNm_A1' )">Current Work Item Name<img id="sortIMG.cltWrkItemNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H23"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cptDt_A1' )">Last Payment Date<img id="sortIMG.cptDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs " id="A3H24"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRcptAmt_A1' )">Last Payment Amt<img id="sortIMG.dealRcptAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
   												<div id="rightTbl" style="width:1118px; height:350px; display:block; overflow:scroll; margin:0px; padding:0px;" >
      											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2520px">
															<col width="30" align="center">
															<col width="80"  align="left">
															<col width="125"  align="left">
															<col width="125"  align="left">
															<col width="100"  align="right">
															<col width="125"  align="left">
															<col width="125"  align="left">
															<col width="100"  align="right">
															<col width="140"  align="right">
															<col width="140"  align="right">
															<col width="140"  align="right">
															<col width="140"  align="right">
															<col width="80"  align="center">
															<col width="80"  align="center">
															<col width="50"  align="center">
															<col width="150"  align="left">
															<col width="150"  align="left">
															<col width="290"  align="left">
															<col width="140"  align="center">
															<col width="240"  align="left">
															<col width="140"  align="left">
															<col width="140"  align="left">
															<col width="140"  align="left">
															<col width="80"  align="center">
															<col width="140"  align="right">
               								<ezf:row ezfHyo="A">
															<tr height="25px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																<td class=""><ezf:anchor name="OpenWin_CollectionHeader" ezfName="OpenWin_CollectionHeader" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CollectionHeader" otherAttr=" id=\"arBatRcptNm_B#{EZF_ROW_NUMBER}\" size=\"10\" ezfanchortext=\"\""><ezf:label name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																<td class=""><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A1#{EZF_ROW_NUMBER}\" size=\"17\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="pmtTermDescTxt_A1" ezfName="pmtTermDescTxt_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"pmtTermDescTxt_A1#{EZF_ROW_NUMBER}\" size=\"17\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dsoAmt_A1" ezfName="dsoAmt_A1" value="999.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsoAmt_A1#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltPsnNm_A1" ezfName="cltPsnNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltPsnNm_A1#{EZF_ROW_NUMBER}\" size=\"17\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltPtfoNm_A1" ezfName="cltPtfoNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltPtfoNm_A1#{EZF_ROW_NUMBER}\" size=\"17\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dsoAmt_A2" ezfName="dsoAmt_A2" value="999.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsoAmt_A2#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="totBalAmt_A1" ezfName="totBalAmt_A1" value="1100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"totBalAmt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="netAmt_A1" ezfName="netAmt_A1" value="85.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"netAmt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dealCltDsptAmt_A1" ezfName="dealCltDsptAmt_A1" value="800.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealCltDsptAmt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dealCltPrmsAmt_A1" ezfName="dealCltPrmsAmt_A1" value="800.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealCltPrmsAmt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltPrmsCratDt_A1" ezfName="cltPrmsCratDt_A1" value="02/05/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltPrmsCratDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltPrmsDt_A1" ezfName="cltPrmsDt_A1" value="02/05/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltPrmsDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltPrmsBrknFlg_A1" ezfName="cltPrmsBrknFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltPrmsBrknFlg_A1#{EZF_ROW_NUMBER}\" size=\"1\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="xxFullNm_A1" ezfName="xxFullNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxFullNm_A1#{EZF_ROW_NUMBER}\" size=\"20\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dsCtacPntValTxt_AT" ezfName="dsCtacPntValTxt_AT" value="999-999-9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsCtacPntValTxt_AT#{EZF_ROW_NUMBER}\" size=\"20\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dsCtacPntValTxt_AE" ezfName="dsCtacPntValTxt_AE" value="XXXXXXXXXXX_CONSULTANT@CUSA.CANON.COM" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsCtacPntValTxt_AE#{EZF_ROW_NUMBER}\" size=\"40\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cratTsDplyTxt_A1" ezfName="cratTsDplyTxt_A1" value="02/05/2015 00:00:00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cratTsDplyTxt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltHdrNoteTxt_A1" ezfName="cltHdrNoteTxt_A1" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltHdrNoteTxt_A1#{EZF_ROW_NUMBER}\" size=\"33\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="xxScrItem30Txt_A1" ezfName="xxScrItem30Txt_A1" value="Current" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem30Txt_A1#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltStrgyNm_A1" ezfName="cltStrgyNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltStrgyNm_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="cltWrkItemNm_A1" ezfName="cltWrkItemNm_A1" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltWrkItemNm_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="rcptDt_A1" ezfName="rcptDt_A1" value="02/05/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cptDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td class=""><ezf:inputText name="dealRcptAmt_A1" ezfName="dealRcptAmt_A1" value="800.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealRcptAmt_A1#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;text-align:right;\" tabindex=\"-1\""/></td>
															</tr>
                   				</ezf:row>
												</table>
											</div>


										</div>
									</div>
									
								</td>
							</tr>
					</table>
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
		<td align="right">
			<ezf:inputButton name="OpenWin_Payment" value="Payment" htmlClass="cBtn" />
			&nbsp;&nbsp;&nbsp;
		</td>
</tr>
</table>

</center>

					<script type="text/javascript" defer>
					  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  true, false);
					</script>
<style TYPE="text/css">
<!--
	.textLabel{border:none; background-color:transparent;}
-->
</style>

<%-- **** Task specific area ends here **** --%>
