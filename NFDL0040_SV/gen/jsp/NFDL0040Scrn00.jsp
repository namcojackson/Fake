<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230522110641 --%>
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
            <input type="hidden" name="pageID" value="NFDL0040Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Promise/Dispute Search">
            
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## Search Criteria - [START] ######################################## --%>
<%--
                <div class="pTab_HEAD">
                    <ul>
                        <li class="pTab_ON"><a href="./NPAL1140Scrn00.html">ACK Reprc</a></li>
                    </ul>
                </div>
--%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <div class="pTab_BODY" style="WIDTH: 1133px; word-break:kepp-all">
                    <table width="1121" height = "50" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="5">
                        <col width="60">
                        <col width="210">
                        <col width="80">
                        <col width="210">
                        <col width="60">
                        <col width="200">
                        <col width="90">
                        <col width="220">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Account#</td>
                            <td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="123456789A123456789B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab">Account Name</td>
                            <td>
                            	<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="123456789A123456789B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/>
                            </td>
                            <td class="stab">Invoice#</td>
                            <td>
                                <ezf:inputText name="xxTrxNumSrchTxt" ezfName="xxTrxNumSrchTxt" value="123456789A123456789B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/>
                            </td>
                            <td class="stab">Current Balance</td>
                            <td>
                                <ezf:inputText name="dealRmngBalGrsAmt_H" ezfName="dealRmngBalGrsAmt_H" value="123456789A123456789B" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\" style=\"text-align:right\""/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
<%-- ######################################## Search Result - [START] ######################################## --%>

                    <%-- ###TAB - HEAD --%>
					<div class="pTab_HEAD">
						<ul>
							<li class="pTab_ON" id="Promise" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_TabPromise" otherAttr=" tabindex=\"101\"">Promise</ezf:anchor></li>
							<li class="pTab_OFF" id="Dispute" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_TabDispute" otherAttr=" tabindex=\"101\"">Dispute</ezf:anchor></li>
						</ul>
					</div>

                    <%-- ###TAB - Header --%>
                    <c:choose>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Promise'}">
                        <script type="text/javascript">
                          document.getElementById("Promise").className = "pTab_ON";
                          document.getElementById("Dispute").className = "pTab_OFF";
                        </script>
                        <div class="pTab_BODY">
                         		<table border="0" cellpadding="1" cellspacing="0" width="1003">
									<tr>
										<td>
											<table width="100%">

												<tr>
													<td class="stab">Promise Amount</td>
													<td><ezf:inputText name="dealCltPrmsAmt_AH" ezfName="dealCltPrmsAmt_AH" value="999,999,999" otherAttr=" size=\"19\" style=\"text-align:right\""/></td>
													<td class="stab">Promise Due Date</td>
													<td>
														<ezf:inputText name="cltPrmsDt_AH" ezfName="cltPrmsDt_AH" value="10/10/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltPrmsDt_AH', 4);" >
													</td>
													<td class="stab"><ezf:inputButton name="OnClick_PromiseAdd" ezfName="OnClick_PromiseAdd" value="Add" htmlClass="pBtn6" /></td>
													<td class="stab"><ezf:inputCheckBox name="xxChkBox_AH" ezfName="xxChkBox_AH" value="Y" />Include Close/Cancel</input></td>
													<td class="stab"><ezf:inputButton name="OnClick_PromiseApply" ezfName="OnClick_PromiseApply" value="Search" htmlClass="pBtn6" /></td>
													
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
														</ezf:skip>

														<%-- Pagenation --%>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
														</jsp:include>
														</td>
													</tr>
											</table>
										</td>

									</tr>
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0">
												<tr>
													<td>
														<div id="RightTBL2_Top" style="overflow-x:hidden; overflow-y:hidden; width:1060;float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL2' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1380px;">
 																<col width="80" align="center">
 																<col width="100" align="center">
																<col width="100" align="center">
 																<col width="150" align="center">
																<col width="150" align="center">
																<col width="150" align="center">
																<col width="100" align="center">
																<col width="80" align="center">
																<col width="50" align="center">
																<col width="100" align="center">
																<col width="290" align="center">

																<tr height="30">
																    <td class="pClothBs">Date</td>
																	<td class="pClothBs">Invoice#</td>
																	<td class="pClothBs">Promise#</td>
																	<td class="pClothBs">Original Invoice Balance</td>
																	<td class="pClothBs">Actual Invoice Balance</td>
																	<td class="pClothBs">Promise Amount</td>
																	<td class="pClothBs">Status</td>
																	<td class="pClothBs">Promise Due Date</td>
																	<td class="pClothBs">Broken</td>
																	<td class="pClothBs">Created By</td>
																	<td class="pClothBs">Comment</td>
																</tr>
															</table>
														</div>
<!--
                                                        <div id="Right2TBL" style="overflow-x:scroll; overflow-y:scroll; height:210; width:1127; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL2_Top' ));" >
-->
														<div id="Right2TBL" style="overflow-x:scroll; overflow-y:scroll; height:420; width:1077; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL2_Top' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1380px;word-break:break-all;" id="B">
 																<col width="80" align="center">
 																<col width="100" align="left">
																<col width="100" align="left">
 																<col width="150" align="right">
																<col width="150" align="right">
																<col width="150" align="right">
																<col width="100" align="center">
																<col width="80" align="center">
																<col width="50" align="center">
																<col width="100" align="left">
																<col width="290" align="center">
																<tbody>
																	<ezf:row ezfHyo="A">
																	<tr id="id_row{EZF_ROW_NUMBER}"  height="20">
																		<td><ezf:label name="cltPrmsCratDt_A" ezfName="cltPrmsCratDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="arTrxNum_A" ezfName="arTrxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="cltPrmsDtlPk_A" ezfName="cltPrmsDtlPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dealRmngBalGrsAmt_A2" ezfName="dealRmngBalGrsAmt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
                    													<td><ezf:inputText name="dealCltPrmsAmt_A" ezfName="dealCltPrmsAmt_A" value="999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"text-align:right\""/></td>
																		<td><ezf:label name="cltPrmsStsDescTxt_A" ezfName="cltPrmsStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																        <td><ezf:label name="cltPrmsDt_A" ezfName="cltPrmsDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																        <td><ezf:label name="cltPrmsBrknFlg_A" ezfName="cltPrmsBrknFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!--																        <td><label ezfout name="fullPsnNm_A" ezfname="fullPsnNm_A" ezfhyo="A">Marvin</label ezfout></td>-->
                    													<td><ezf:inputText name="fullPsnNm_A" ezfName="fullPsnNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																		<td><ezf:inputText name="cltPrmsNoteTxt_A" ezfName="cltPrmsNoteTxt_A" value="Comment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\""/></td>
																	</tr>
																	</ezf:row>
																</tbody>
															</table>
                                					</div>
					                              </td>
                    					        </tr>
                    					    </table>
	                    			    </tr>
	                    		     </td>
           					    </table>

                    </c:when>
                    <%-- ###TAB - Detail --%>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Dispute'}">
                        <script type="text/javascript">
                          document.getElementById("Promise").className = "pTab_OFF";
                          document.getElementById("Dispute").className = "pTab_ON";
                        </script>
                        <div class="pTab_BODY">
                         		<table border="0" cellpadding="1" cellspacing="0" width="1003">
									<tr>
										<td>
											<table width="100%">
											    <col width="100" align="left">
											    <col width="200" align="left">
											    <col width="40" align="left">
											    <col width="100" align="left">
											    <col width="10" align="left">
											    <col width="100" align="left">
												<col valign="top">
												<tr>
													<td class="stab">Dispute Amount</td>
													<td><ezf:inputText name="dealCltDsptAmt_BH" ezfName="dealCltDsptAmt_BH" value="BOA_CHICAGO_200150309" otherAttr=" size=\"20\""/></td>
													<td class="stab">Reason</td>
													<td><ezf:select name="cltDsptRsnCd_BH" ezfName="cltDsptRsnCd_BH" ezfBlank="1" ezfCodeName="cltDsptRsnCd_LC" ezfDispName="cltDsptRsnDescTxt_LD" otherAttr=" style=\"width:220\""/></td>
                                                    <td>&nbsp;</td>
													<td class="stab"><ezf:inputButton name="OnClick_DisputeAdd" ezfName="OnClick_DisputeAdd" value="Add" htmlClass="pBtn6" /></td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
														</ezf:skip>

														<%-- Pagenation --%>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="B" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
														</jsp:include>
														</td>
													</tr>
											</table>
										</td>

									</tr>
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0">
												<tr>
													<td>
														<div id="DISPUTE_TBL_H" style="overflow-x:hidden; overflow-y:hidden; width:1070; table-layout:fixed"
															onscroll="synchroScrollLeft( this.id, new Array( 'DISPUTE_TBL_D' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1360;">
 																<col width="80"  align="center">
 																<col width="80"  align="center">
																<col width="80"  align="center">
 																<col width="145" align="center">
																<col width="145" align="center">
																<col width="170" align="center">
																<col width="90"  align="center">
																<col width="130" align="center">
																<col width="120" align="center">
																<col width="290" align="center">
																<tr height="30">
																    <td class="pClothBs">Date</td>
																	<td class="pClothBs">Invoice#</td>
																	<td class="pClothBs">Dispute#</td>
																	<td class="pClothBs">Actual Invoice Balance</td>
																	<td class="pClothBs">Dispute Amount</td>
																	<td class="pClothBs">Reason</td>
																	<td class="pClothBs">CI Ticket</td>
																	<td class="pClothBs">Created By</td>
																	<td class="pClothBs">Status</td>
																	<td class="pClothBs">Comment</td>
																</tr>
															</table>
														</div>
														<div id="DISPUTE_TBL_D" style="overflow-x:scroll; overflow-y:scroll; height:420; width:1087;"
															onScroll="synchroScrollLeft(this.id, new Array('DISPUTE_TBL_H'));">
															<table border="1" cellpadding="0" cellspacing="0" style="width:1360; word-break:break-all;" id="B">
																<col width="80"  align="center">
																<col width="80"  align="left">
																<col width="80"  align="left">
																<col width="145" align="right">
																<col width="145" align="right">
																<col width="170" align="center">
																<col width="90"  align="center">
																<col width="130" align="center">
																<col width="120" align="center">
																<col width="290" align="center">
																<tbody>
																	<%@ page import="business.servlet.NFDL0040.*" %>
																	<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
																	<% NFDL0040BMsg scrnMsg = (NFDL0040BMsg)databean.getEZDBMsg(); %>
																	<%@ include file="../common/common.jsp" %>
																	<% int i = 0; %>
																	<ezf:row ezfHyo="B">
																	<tr id="id_row{EZF_ROW_NUMBER}" height="20">
																		<td><ezf:label name="cltDsptDt_B" ezfName="cltDsptDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="arTrxNum_B" ezfName="arTrxNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="cltDsptTrxPk_B" ezfName="cltDsptTrxPk_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="dealRmngBalGrsAmt_B" ezfName="dealRmngBalGrsAmt_B" value="999,999,999.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"text-align:right\" style=\"border:none; background-color:transparent;\""/></td>
																		<td><ezf:inputText name="dealCltDsptAmt_B" ezfName="dealCltDsptAmt_B" value="999,999,999.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"text-align:right\""/></td>
																		<td><ezf:select name="cltDsptRsnCd_B" ezfName="cltDsptRsnCd_B" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="cltDsptRsnCd_LC" ezfDispName="cltDsptRsnDescTxt_LD" otherAttr=" style=\"width:160\""/></td>
																		<td>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxBtnFlg == 'Y'}">
																			<a href="<%=getCustomAppURL("EXTNE193T010")%>" target="CustomerCare" onclick="f_open_window_max(this.href, 'CustomerCare')">
</c:when>
</c:choose>
																				<ezf:inputButton name="OnClick_Create" ezfName="OnClick_Create" value="Create" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"OnClick_Create#{EZF_ROW_NUMBER}\""/>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxBtnFlg == 'Y'}">
																			</a>
</c:when>
</c:choose>
																		</td>
																		<td><ezf:inputText name="fullPsnNm_B" ezfName="fullPsnNm_B" value="Yamada,Taro" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/></td>
																		<td>
																			<ezf:select name="cltDsptStsCd_B" ezfName="cltDsptStsCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="cltDsptStsCd_LC" ezfDispName="cltDsptStsDescTxt_LD" otherAttr=" style=\"width:110\""/>
																		</td>
																		<td><ezf:inputText name="cltDsptNoteTxt_B" ezfName="cltDsptNoteTxt_B" value="Comment" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\""/></td>
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
								</table>
							</tbody>
                          </table>
                        </center>
                        </div>
                    </c:when>
                    </c:choose>
<%-- ######################################## Search Result - [END] ######################################## --%>
                </div>
            </td>
        </tr>
    </table>
</center>

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
