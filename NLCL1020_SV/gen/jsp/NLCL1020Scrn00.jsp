<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230413143528 --%>
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
			<input type="hidden" name="pageID" value="NLCL1020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Field Transfer Order Entry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<%--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "FLD Trans" class="pTab_ON" ><a href="#">FLD Trans</a></li>
								</td>

								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>

							</tr>
						</table>
					</ul>
				</div>
				--%>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					
					<table height="528" width="100%">
						<col valign="top">
						<tr>
							<td>
<%-- ## HEADER 2(Loc From/To) ## --%>
								<table border="0">
								
								
								
									<!-- Loc From -->
									<col width="100">
									<col width="">
									<col width="">
									<col width="">
									<col width="5">
									<!-- Loc To -->
									<col width="">
									<col width="">
									<col width="">
									<col width="">
									<col width="5">
									<!-- Traansaction Number -->
									<col width="">
									<col width="">
									<col width="5">
									<!-- Search button -->
									<col width="">
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkAncr_FR" ezfName="xxLinkAncr_FR" ezfEmulateBtn="1" ezfGuard="OpenWin_NPAL1010_LocFrom" >From Tech WH/SWH</ezf:anchor></td>
										<td><ezf:inputText name="fromRtlWhCd" ezfName="fromRtlWhCd" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="fromRtlSwhCd" ezfName="fromRtlSwhCd" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="locNm_FR" ezfName="locNm_FR" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/></td>
										<td></td>
										<td class="stab"><ezf:anchor name="xxLinkAncr_TO" ezfName="xxLinkAncr_TO" ezfEmulateBtn="1" ezfGuard="OpenWin_NPAL1010_LocTo" >To Tech WH/SWH</ezf:anchor></td>
										<td><ezf:inputText name="toRtlWhCd" ezfName="toRtlWhCd" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="toRtlSwhCd" ezfName="toRtlSwhCd" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="locNm_TO" ezfName="locNm_TO" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/></td>
										<td></td>
										<td class="stab">Transaction #</td>
										<td><ezf:inputText name="invtyOrdNum" ezfName="invtyOrdNum" value="WWWWWWWW" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
										<td></td>
										<td><ezf:inputButton name="Search_Trx" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>

<%-- ## HEADER 3(Loc Sts) ## --%>
								<table border="0">
									<col width="100">
									<col width="287">
									<col width="590">
									<tr>
										<td class="stab">Location Status</td>
										<td>
											<%-- need modify --%>
											<ezf:select name="locStsCd_P1" ezfName="locStsCd_P1" ezfBlank="1" ezfCodeName="locStsCd_H1" ezfDispName="xxLocStsTxt_H1" otherAttr=" style=\"width:211px;\""/>
										</td>
										<td></td>
									</tr>
								</table>
<%-- ## HEADER 5 (Comment) ## --%>
								<table border="0">
									<col width="100">
									<col width="770">
									<tr>
										<td class="stab">Comment</td>
										<td>
											<ezf:inputText name="firstInvtyOrdCmntTxt" ezfName="firstInvtyOrdCmntTxt" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW64" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"></td>
										<td>
											<ezf:inputText name="scdInvtyOrdCmntTxt" ezfName="scdInvtyOrdCmntTxt" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW64" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"></td>
										<td>
											<ezf:inputText name="thirdInvtyOrdCmntTxt" ezfName="thirdInvtyOrdCmntTxt" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW64" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
									</tr>
								</table>

								<hr>
<%-- ######################################## DETAIL ######################################## --%>
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table cellpadding="1" cellspacing="0">
												<col width="11">
												<col width="65">
												<col width="102">
												<col width="30">
												<col width="168">
												<col width="5">
												<col width="16">
												<col width="112">
												<col width="11">
												<col width="30">
												<col width="5">
												<col width="50">
												<col width="104">
												<col width="5">
												<col width="24">
												<col width="104">
												<col width="31">
												<col width="160">
												<tr>
													<td></td>
													<td class="stab"><ezf:anchor name="xxMdseCdAncr" ezfName="xxMdseCdAncr" ezfEmulateBtn="1" ezfGuard="OpenWin_NMAL6800" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="xxMdseCdAncr" ezfName="xxMdseCdAncr" />
													</ezf:anchor></td>
													<td><ezf:inputText name="mdseCd_HD" ezfName="mdseCd_HD" value="2172B002AA" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="Display_MDSEName" value=">>" htmlClass="pBtn0" /></td>
													<td><ezf:inputText name="mdseDescShortTxt_HD" ezfName="mdseDescShortTxt_HD" value="INK JET PRINTER" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"250\""/></td>
													<td></td>
													<td class="stab">SS</td>
													<td>
														<%-- after generation, have to need edit --%>
														<ezf:select name="stkStsCd_PH" ezfName="stkStsCd_PH" ezfBlank="1" ezfCodeName="stkStsCd_HH" ezfDispName="xxStkStsTxt_HH" otherEvent1=" onchange=\"sendServer('Onchange_StkSts')\"" otherAttr=" style=\"width:123px;\""/>
													</td>
													<td></td>
													<td><ezf:inputButton name="Display_AvalQty" value=">>" htmlClass="pBtn0" /></td>
													<td></td>
													<td class="stab">Current<br>Available</td>
													<td><ezf:inputText name="invtyAvalQty_HO" ezfName="invtyAvalQty_HO" value="1,234,567,890" otherAttr=" tabindex=\"-1\" size=\"13\" maxlength=\"13\""/></td><%-- need modify --%>
													<td></td>
													<td class="stab">Qty</td>
													<td><ezf:inputText name="invtyAvalQty_HI" ezfName="invtyAvalQty_HI" value="1,234,567,890" otherAttr=" size=\"13\" maxlength=\"13\""/></td><%-- need modify --%>
													<td></td>
<!-- START 2019/03/26 T.Ogura [QC#30124,MOD] -->
													<!-- <td><input type="button" class="pBtn6" value="Add (F11)" onclick="sendServer(this)" name="Add_Dtaill_Line"></td> -->
													<td>
														<ezf:inputButton name="Add_Dtaill_Line" value="Add (F11)" htmlClass="pBtn6" />
														<ezf:inputButton name="Delete_Dtaill_Line" value="Delete" htmlClass="pBtn6" />
													</td>
<!-- END   2019/03/26 T.Ogura [QC#30124,MOD] -->
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<table cellpadding="1" cellspacing="0">
												<col width="">
												<tr>
													<td valign="top">
														<div style="overflow-y:none; overflow-x:hidden; width:1005;">
															<table border="1" cellpadding="1" cellspacing="0" width="1003">
																<col width="50"  align="center">
																<col width="48" align="center">
																<col width="110" align="center">
																<col width="336" align="center">
																<col width="187" align="center">
																<col width="112" align="center">
																<col width="112" align="center">
																<tr height="28">
																	<td class="pClothBs">Delete</td>
																	<td class="pClothBs">Line</td>
																	<td class="pClothBs">Item Number</td>
																	<td class="pClothBs">Item Name</td>
																	<td class="pClothBs">Stock Status</td>
																	<td class="pClothBs">Qty</td>
																	<td class="pClothBs">Current Available</td>
																</tr>
															</table>
														</div>
<!--														<div style="overflow-y:auto; height:226;">-->
														<div style="overflow-y:auto; height:338;">
															<table border="1" cellpadding="1" cellspacing="0" width="1003" id="A">
																<col width="50" align="center">
																<col width="48" align="right">
																<col width="110">
																<col width="336" align="center">
																<col width="187" align="center">
																<col width="112" align="center">
																<col width="112" align="right">
															<tbody>
															<ezf:row ezfHyo="A">
																<tr height="28">
																	<td>
																		<ezf:inputCheckBox name="xxChkBox_AD" ezfName="xxChkBox_AD" value="Y" ezfHyo="A" ezfArrayNo="0" />
																	</td>
																	<td><ezf:label name="invtyOrdLineNum_A1" ezfName="invtyOrdLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="SD1000 DIGITAL ELPH (SLN)" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"41\" maxlength=\"250\""/></td>
																	<td>
																		<%-- need modify --%>
																		<ezf:select name="stkStsCd_AP" ezfName="stkStsCd_AP" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_HH" ezfDispName="xxStkStsTxt_HH" otherAttr=" style=\"width:187px;\""/>
																	</td>
																	<td><ezf:inputText name="invtyAvalQty_AI" ezfName="invtyAvalQty_AI" value="5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td><%-- need modify --%>
																	<td><ezf:label name="invtyAvalQty_AO" ezfName="invtyAvalQty_AO" ezfHyo="A" ezfArrayNo="0" /></td>
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
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>2</label></td>
																	<td><label ezfout>1862B001AA</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value="SD1000 DIGITAL ELPH (SLN)"></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected>2:Rank-B</option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value="1"></td>
																	<td><label ezfout>25</label></td>
																</tr>
																<tr height="28">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>3</label></td>
																	<td><label ezfout>1862B001AA</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value="SD1000 DIGITAL ELPH (SLN)"></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected>3:Waiting for Inspection</option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value="2"></td>
																	<td><label ezfout>25</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>4</label></td>
																	<td><label ezfout>WWWWWWWWWW12</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWW30"></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected>WWWWWWWWWWWWWWWWWW20</option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value="1,234,567,890"></td>
																	<td><label ezfout>1,234,567,890</label></td>
																</tr>
																<tr height="28">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>5</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>6</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>7</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>8</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>9</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>10</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>11</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<input type="checkbox" class="" value="Y">
																	</td>
																	<td><label ezfout>12</label></td>
																	<td><label ezfout>&nbsp;</label></td>
																	<td><input type="text" readonly class="pPro" size="41" maxlength="30" value=""></td>
																	<td>
																		<select class="pHsu" style="width:187px;">
																			<option></option>
																			<option selected></option>
																		</select>
																	</td>
																	<td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
																	<td><label ezfout>&nbsp;</label></td>
																</tr>
															</ezf:skip>
															</tbody>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
<%-- ######################################## FOOTER ######################################## --%>
						 	</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
<script type="text/javascript">
             setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
