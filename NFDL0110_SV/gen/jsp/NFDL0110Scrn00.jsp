<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230220184116 --%>
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
            <input type="hidden" name="pageID" value="NFDL0110Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Customer Care">
            
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## Search Criteria - [START] ######################################## --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <%@ page import="business.servlet.NFDL0110.*" %>
                <%@ include file="../common/common.jsp" %>
                <% NFDL0110BMsg scrnMsg = (NFDL0110BMsg)databean.getEZDBMsg(); %>

                <div class="pTab_BODY" style="WIDTH: 1133px; word-break:kepp-all">
                    <table width="1121" height = "50" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="30">
                        <col width="120">
                        <col width="60">
                        <col width="210">
                        <col width="40">
                        <col width="60">
                        <col width="60">
                        <col width="210">
                        <col width="40">
                        <col width="80">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Customer Account#</td>
                            <td><ezf:inputText name="billToCustAcctCd_H" ezfName="billToCustAcctCd_H" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="123456789A123456789B" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab"></td>
                            <td class="stab">Bill to</td>
                            <td>
                                <ezf:inputText name="billToCustCd_H" ezfName="billToCustCd_H" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
                            </td>
                            <td><ezf:inputText name="locNm_H" ezfName="locNm_H" value="123456789A123456789B" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab"></td>
                            <td>
                                <% if ("Y".equals(scrnMsg.xxBtnFlg.getValue())) { %>
                                    <a href="<%=getCustomAppURL("EXTNE193T010")%>" target="CustomerCare" onclick="f_open_window_max(this.href, 'CustomerCare')">
                                        <ezf:inputButton name="OnClick_Create" ezfName="OnClick_Create" value="Create" htmlClass="pBtn6" />
                                    </a>
                                <% } else { %>
                                    <ezf:inputButton name="OnClick_Create" ezfName="OnClick_Create" value="Create" htmlClass="pBtn6" />
                                <% } %>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
<%-- ######################################## Search Result - [START] ######################################## --%>
								<hr>



            		<table border="0" cellpadding="1" cellspacing="0" width="1003">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%" border="1">
												<col width="5">
												<col width="">
												<col width="800" align="right">
												<tr>
                          <td>&nbsp;</td>
												  <td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										      </td>
													<td  align="right">
														<table>
															<tr>
																<td>
																	<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
																	<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_A" />
																	<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_A" />
																	<jsp:param name="ViewMode"       value="FULL" />

																	</jsp:include>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									</table>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="5">
						<col width="">
						<tr>
								<td>&nbsp;</td>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:430px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1950px" style="margin-right:20px">
													<col width="80"   align="center">   <!--Bill-To-Loc-->
													<col width="100"  align="center">   <!--Contract/Order-->
													<col width="100"  align="center">   <!--Invoice No-->
													<col width="100"  align="center">   <!--Line Type-->
													<col width="100"  align="center">   <!--Bill From-->
													<col width="100"  align="center">   <!--Bill To-->
													<col width="100"  align="center">   <!--Orig Amt-->
													<col width="100"  align="center">   <!--Remain Amt-->
													<col width="100"  align="center">   <!--Due Date-->
													<col width="100"  align="center">   <!--Days Past Due-->
													<col width="100"  align="center">   <!--CI Number-->
													<col width="100"  align="center">   <!--Create Date-->
													<col width="80"   align="center">   <!--Status-->
													<tr height="32">
														<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
														<!-- <td class="pClothBs " id="AH0"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A')">Bill-To-Loc</a><img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td> -->
														<td class="pClothBs " id="AH0"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A')">Bill-To-Code</a><img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
														<td class="pClothBs " id="AH1"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDplyTrxNumTxt_A')">Contract/Order</a><img id="sortIMG.xxDplyTrxNumTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH2"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxNum_A')">Invoice No</a><img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH3"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem130Txt_A1')">Line Type</a><img id="sortIMG.xxScrItem130Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH4"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgPerFromDt_A')">Bill From</a><img id="sortIMG.bllgPerFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH5"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgPerToDt_A')">Bill To</a><img id="sortIMG.bllgPerToDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH6"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealOrigGrsAmt_A')">Orig Amt</a><img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH7"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A')">Remain Amt</a><img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH8"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A')">Due Date</a><img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH9"><a href="#" class="pSortCol" onclick="columnSort('A', 'pmtLateDaysAot_A')">Days Past Due</a><img id="sortIMG.pmtLateDaysAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH10"><a href="#" class="pSortCol" onclick="columnSort('A', 'custCareTktNum_A')">CI Number</a><img id="sortIMG.custCareTktNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH11"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCratDt_A')">Create Date</a><img id="sortIMG.xxCratDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs " id="AH12"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem130Txt_A2')">Status</a><img id="sortIMG.xxScrItem130Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
   											<div id="rightTbl" style="width:1118px; height:430px; display:block; overflow:scroll; margin:0px; padding:0px;" >
      											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1950px">
															<col width="80"   align="left">      <!--Bill-To-Loc-->
															<col width="100"  align="left">      <!--Contract/Order-->
															<col width="100"  align="left">      <!--Invoice No-->
															<col width="100"  align="left">      <!--Line Type-->
															<col width="100"  align="center">    <!--Bill From-->
															<col width="100"  align="center">    <!--Bill To-->
															<col width="100"  align="right">     <!--Orig Amt-->
															<col width="100"  align="right">     <!--Remain Amt-->
															<col width="100"  align="center">    <!--Due Date-->
															<col width="100"  align="right">     <!--Days Past Due-->
															<col width="100"  align="left">      <!--CI Number-->
															<col width="100"  align="center">    <!--Create Date-->
															<col width="80"   align="left">      <!--Status-->
															<% int i = 0; %>
															<ezf:row ezfHyo="A">
															<tr height="25px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="2000003" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"billToCustCd_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxDplyTrxNumTxt_A" ezfName="xxDplyTrxNumTxt_A" value="0000018" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"xxDplyTrxNumTxt_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="arTrxNum_A" ezfName="arTrxNum_A" value="6000123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"arTrxNum_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxScrItem130Txt_A1" ezfName="xxScrItem130Txt_A1" value="Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"xxScrItem130Txt_A1#{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="bllgPerFromDt_A" ezfName="bllgPerFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"bllgPerFromDt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="bllgPerToDt_A" ezfName="bllgPerToDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"bllgPerToDt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" value="1100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"dealOrigGrsAmt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"text-align:right; border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" value="1100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"dealRmngBalGrsAmt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"text-align:right; border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="invDueDt_A" ezfName="invDueDt_A" value="01/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"invDueDt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="pmtLateDaysAot_A" ezfName="pmtLateDaysAot_A" value="30" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"pmtLateDaysAot_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"text-align:right;border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td>
																	<div id="custCareTktNum_A#{EZF_ROW_NUMBER}">
																	<a href="<%=getCustomAppURL(scrnMsg.xxComnScrCondValTxt_P0.getValue())%>?<%=scrnMsg.xxComnScrCondValTxt_P1.getValue()%>=<%=scrnMsg.A.no(i++).custCareTktNum_A.getValue()%>" target="_blank" border="0">
																			<ezf:label name="custCareTktNum_A" ezfName="custCareTktNum_A" ezfHyo="A" ezfArrayNo="0" />
																	</a>
																	</div>
																</td>
																<td><ezf:inputText name="xxCratDt_A" ezfName="xxCratDt_A" value="03/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"xxCratDt_A#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxScrItem130Txt_A2" ezfName="xxScrItem130Txt_A2" value="Assigned" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"xxScrItem130Txt_A2#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
															</tr>
															<ezf:skip>
															<tr height="25px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td><input type="text" class="pPro" size="10" id="billToCustCd_A#{EZF_ROW_NUMBER}" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none; background-color:transparent;" tabindex="-1" readonly name="billToCustCd_A" ezfname="billToCustCd_A" /></td>
																<td><input type="text" class="pPro" size="13" id="xxDplyTrxNumTxt_A#{EZF_ROW_NUMBER}" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none; background-color:transparent;" tabindex="-1" readonly name="xxDplyTrxNumTxt_A" ezfname="xxDplyTrxNumTxt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="arTrxNum_A#{EZF_ROW_NUMBER}" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none; background-color:transparent;" tabindex="-1" readonly name="arTrxNum_A" ezfname="arTrxNum_A" /></td>
																<td><input type="text" class="pPro" size="13" id="xxScrItem130Txt_A1#{EZF_ROW_NUMBER}" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none; background-color:transparent;" tabindex="-1" readonly name="xxScrItem130Txt_A1" ezfname="xxScrItem130Txt_A1" /></td>
																<td><input type="text" class="pPro" size="13" id="bllgPerFromDt_A#{EZF_ROW_NUMBER}" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none; background-color:transparent;" tabindex="-1" readonly name="bllgPerFromDt_A" ezfname="bllgPerFromDt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="bllgPerToDt_A#{EZF_ROW_NUMBER}" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none; background-color:transparent;" tabindex="-1" readonly name="bllgPerToDt_A" ezfname="bllgPerToDt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="dealOrigGrsAmt_A#{EZF_ROW_NUMBER}" value="1,100.00" ezfHyo="A" ezfArrayNo="0" size="19" style="text-align:right; border:none; background-color:transparent;" tabindex="-1" readonly name="dealOrigGrsAmt_A" ezfname="dealOrigGrsAmt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="dealRmngBalGrsAmt_A#{EZF_ROW_NUMBER}" value="1,100.00" ezfHyo="A" ezfArrayNo="0" size="19" style="text-align:right; border:none; background-color:transparent;" tabindex="-1" readonly name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="invDueDt_A#{EZF_ROW_NUMBER}" value="01/31/2016" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none; background-color:transparent;" tabindex="-1" readonly name="invDueDt_A" ezfname="invDueDt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="pmtLateDaysAot_A#{EZF_ROW_NUMBER}" value="30" ezfHyo="A" ezfArrayNo="0" size="19" style="text-align:right;border:none; background-color:transparent;" tabindex="-1" readonly name="pmtLateDaysAot_A" ezfname="pmtLateDaysAot_A" /></td>
																<td>
																	<div id="custCareTktNum_A#{EZF_ROW_NUMBER}">
																	<a href="<%=getCustomAppURL(scrnMsg.xxComnScrCondValTxt_P0.getValue())%>?<%=scrnMsg.xxComnScrCondValTxt_P1.getValue()%>=<%=scrnMsg.A.no(i++).custCareTktNum_A.getValue()%>" target="_blank" border="0">
																			<ezf:label name="custCareTktNum_A" ezfName="custCareTktNum_A" ezfHyo="A" value="0--------1---------2---------3" ezfArrayNo="0" />
																	</a>
																</div>
																</td>
																<td><input type="text" class="pPro" size="13" id="xxCratDt_A#{EZF_ROW_NUMBER}" value="03/01/2015" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none; background-color:transparent;" tabindex="-1" readonly name="xxCratDt_A" ezfname="xxCratDt_A" /></td>
																<td><input type="text" class="pPro" size="13" id="xxScrItem130Txt_A2#{EZF_ROW_NUMBER}" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none; background-color:transparent;" tabindex="-1" readonly name="xxScrItem130Txt_A2" ezfname="xxScrItem130Txt_A2" /></td>
															</tr>
															</ezf:skip>
															</ezf:row>
												</table>
											</div>
										</div>
									</tr>
								</table>
							</td>
						</tr>
					</table>
</center>

<script type="text/javascript" defer>
S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  true, false);
</script>

<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>

<%-- **** Task specific area ends here **** --%>
