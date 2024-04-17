<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170224013405 --%>
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
			<input type="hidden" name="pageID" value="NWCL0130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Special Billing Regeneration Request">

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
								<table border="0" cellpadding="0" cellspacing="0" height="90">
									<tr>
										<td valign="top">&nbsp;
                                        </td>
										<td valign="top" width="500">&nbsp;
											<fieldset>
											<table cellpadding="0" border="0"  width="500">
												<col width="100">
												<col width="400">
												<tr>
													<td class="stab">Action:</a></td>
													<td>
														<ezf:select name="conslRgnrActTpCd_H1" ezfName="conslRgnrActTpCd_H1" ezfBlank="0" ezfCodeName="conslRgnrActTpCd_PL" ezfDispName="conslRgnrActTpDescTxt_PL" otherAttr=" style=\"width:120px;\""/>
                                                    </td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="Bill_Num_Link" >Bill#:</ezf:anchor></td>
													<td><ezf:inputText name="conslBillNum_H1" ezfName="conslBillNum_H1" value="XXXXXXXXXX" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="Inv_Num_Link" >Invoice#:</ezf:anchor></td>
													<td><ezf:inputText name="invNum_H1" ezfName="invNum_H1" value="XXXXXXXXXX" otherAttr=" size=\"15\" maxlength=\"13\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td colspan=2><ezf:inputButton name="AddToList" value="Add to List" htmlClass="pBtn6" /></td>
												</tr>
											</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<hr>
								<table border="0" cellpadding="0" cellspacing="0" height="24" style="table-layout:fixed;">
									<col align="center" width="25">
									<col width="150">
									<col width="380">
									<col width="520">
									<tr>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('Click_SelAll_UnSelAll_Checkbox')" /></td>
										<td class="stab">Select All/Unselect All</td>
										<td class="stab"></td>
										<td align="right">
											<table>
												<tr>
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
										<td valign="Top" width="1100">
											<div id="RightTBL" style="overflow-x:none; overflow-y:none; width:1095; height:24; float:left;">
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1095" height="48">
													<col align="center" width="25">
													<col align="center" width="80">
													<col align="center" width="100">
													<col align="center" width="230">
													<col align="center" width="80">
													<col align="center" width="80">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<tr>
														<td class="pClothBs">&nbsp;&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A1' )">Bill To<img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToDsAcctNum_A1' )">Bill To Account#<img id="sortIMG.billToDsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToDsAcctNm_A1' )">Customer Name<img id="sortIMG.billToDsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'conslBillNum_A1' )">Bill#<img id="sortIMG.conslBillNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invNum_A1' )">Invoice#<img id="sortIMG.invNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custCareTktNum_A1' )">Ticket#<img id="sortIMG.custCareTktNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxUrnNum_A1' )">URN#<img id="sortIMG.xxUrnNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDt_A1' )">Invoice Date<img id="sortIMG.invDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invTotDealNetAmt_A1' )">Amount<img id="sortIMG.invTotDealNetAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'conslRgnrActTpNm_A1' )">Action<img id="sortIMG.conslRgnrActTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:scroll; height:330; width:1115; float:left;" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1115" id="A1">
													<col align="left" width="25">
													<col align="left" width="80">
													<col align="left" width="100">
													<col align="left" width="230">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="right" width="100">
													<col align="left" width="100">
													<col align="center" width="20">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"csmpTabIdx_01#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="billToCustCd_A1" ezfName="billToCustCd_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"27\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="conslBillNum_A1" ezfName="conslBillNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="invNum_A1" ezfName="invNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="custCareTktNum_A1" ezfName="custCareTktNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxUrnNum_A1" ezfName="xxUrnNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="invDt_A1" ezfName="invDt_A1" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"15\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A" id="csmpTabIdx_01#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" name="billToCustCd_A1" ezfName="billToCustCd_A1" maxlength="15" size="11" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNum_A1" ezfName="billToDsAcctNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="billToDsAcctNm_A1" ezfName="billToDsAcctNm_A1" maxlength="15" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslBillNum_A1" ezfName="conslBillNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invNum_A1" ezfName="invNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="custCareTktNum_A1" ezfName="custCareTktNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxUrnNum_A1" ezfName="xxUrnNum_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invDt_A1" ezfName="invDt_A1" maxlength="15" size="13" value="03/04/2016" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="conslRgnrActTpNm_A1" ezfName="conslRgnrActTpNm_A1" maxlength="15" size="13" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right" height="28">
                            <ezf:inputButton name="Del" value="Del" htmlClass="pBtn6" />&nbsp;&nbsp;&nbsp;
                        </td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
