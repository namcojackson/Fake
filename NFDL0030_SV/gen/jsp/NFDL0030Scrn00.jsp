<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230525084455 --%>
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
			<input type="hidden" name="pageID" value="NFDL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit Card Payment">
<%@ page import="business.servlet.NFDL0030.NFDL0030BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NFDL0030BMsg  bMsg = (NFDL0030BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Price Category" class="pTab_OFF" ><a href="./AMALxxxxScrn00.html">Sell Rate</a></li>
									</div>
								</td>
								<td></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<div class="pTab_BODY">
<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" width="95%">
						<tr height="20"><td></td></tr>
						<tr>
							<td>
								<fieldset style="padding:5 20 5 20;">
									<table border="0">
										<col width="100">
										<col width="80">
										<col width="30">
										<col width="100">
										<col width="150">
										<c:choose>
										<c:when test="${pageScope._ezddatabean.xxModeCd == '01'}">
										<tr height="20">
											<td class="stab"><b>Payment</b></td>
										</tr>
										<tr height="20">
											<td class="stab">Payment Amount</td>
											<td><ezf:inputText name="dealNetRcptAmt_H" ezfName="dealNetRcptAmt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"20\" maxlength=\"13\" tabindex=\"2\" ezftoupper=\"\""/></td>
										</tr>
                                        <tr height="20">
                                            <td class="stab">Payment Type</td>
                                            <td><ezf:select name="dsPmtMethCd_H" ezfName="dsPmtMethCd_H" ezfBlank="1" ezfCodeName="dsPmtMethCd_FC" ezfDispName="dsPmtMethDescTxt_FD" otherEvent1=" onchange=\"sendServer('OnChange_DS_PMT_METH')\"" otherAttr=" style=\"width:110\""></ezf:select></td>
                                        </tr>
										</c:when>
										<c:when test="${pageScope._ezddatabean.xxModeCd == '02'}">
										<tr height="20">
											<td class="stab"><b>On Account</b></td>
										</tr>
										<tr height="20">
											<td class="stab">Trans Date</td>
											<td><ezf:inputText name="acctDt_H" ezfName="acctDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"2\""/></td>
											<td>&nbsp;</td>
											<td class="stab">Payment Amount</td>
											<td><ezf:inputText name="dealNetRcptAmt_H" ezfName="dealNetRcptAmt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"20\" maxlength=\"13\" tabindex=\"2\" ezftoupper=\"\""/></td>
										</tr>
                                        <tr height="20">
                                            <td class="stab">Payment Type</td>
                                            <td align="left" class="stab" colspan="4"><ezf:select name="dsPmtMethCd_H" ezfName="dsPmtMethCd_H" ezfBlank="1" ezfCodeName="dsPmtMethCd_FC" ezfDispName="dsPmtMethDescTxt_FD" otherEvent1=" onchange=\"sendServer('OnChange_DS_PMT_METH')\"" otherAttr=" style=\"width:110\""></ezf:select></td>
                                        </tr>
										</c:when>
										</c:choose>
									</table>
									<br>
									<table border="0">
										<col width="150">
										<col width="200">
										<col width="50">
										<col width="150">
										<col width="200">
										
										<% if (!ZYPCommonFunc.hasValue(bMsg.dsPmtMethCd_H)) { %>
										<tr height="20">
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<% } else if (DS_PMT_METH.E_CHECK.equals(bMsg.dsPmtMethCd_H.getValue())) { %>
										<tr height="20">
				                            <td class="stab"><ezf:anchor name="xxLinkProt_BR" ezfName="xxLinkProt_BR" ezfEmulateBtn="1" ezfGuard="OpenWin_BankAccountSearch" >Bank Routing #</ezf:anchor></td>
				                            <td><ezf:inputText name="bankRteNum_H" ezfName="bankRteNum_H" value="70711756" otherAttr=" size=\"20\""/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr height="20">
                                            <td class="stab"><ezf:anchor name="xxLinkProt_AC" ezfName="xxLinkProt_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_BankAccountSearch" >Bank Account #</ezf:anchor></td>
                                            <td><ezf:inputText name="dsBankAcctNum_H" ezfName="dsBankAcctNum_H" value="****1234" otherAttr=" size=\"20\""/></td>
                                            <td><ezf:inputButton name="OpenWin_BankAccountDetail" value="Add" htmlClass="pBtn1" /></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr height="20">
											<td class="stab">Payer Name</td>
											<td><ezf:inputText name="sellToCustLocNm_H" ezfName="sellToCustLocNm_H" value="xxx.Inc." otherAttr=" size=\"17\""/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<% } else{ %>
										<tr height="20">
											<td><ezf:inputButton name="OpenWin_CreditCardEntry" value="Credit Card Entry" htmlClass="pBtn12" otherAttr=" tabindex=\"3\""/></td>
										</tr>
										<tr height="20">
											<td class="stab">Card Type</td>
											<td><ezf:inputText name="crCardTpNm_H" ezfName="crCardTpNm_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"25\" maxlength=\"25\" tabindex=\"2\" ezftoupper=\"\""/></td>
											<td>&nbsp;</td>
											<td class="stab">Card Holder Name</td>
											<td><ezf:inputText name="crCardHldNm_H" ezfName="crCardHldNm_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"2\" ezftoupper=\"\""/></td>
										</tr>
										<tr height="20">
											<td class="stab">Card Number</td>
											<td><ezf:inputText name="crCardCustRefNum_H2" ezfName="crCardCustRefNum_H2" value="XXXX1XXXX2XXX" otherAttr=" size=\"25\" maxlength=\"40\" tabindex=\"2\" ezftoupper=\"\""/></td>
											<td>&nbsp;</td>
											<td class="stab">Bill to Address</td>
											<td rowspan="3"><ezf:textArea name="xxAllLineAddr_H" ezfName="xxAllLineAddr_H" otherAttr=" cols=\"30\" rows=\"5\" ezftoupper=\"\""/></td>
										</tr>
										<tr height="20">
											<td class="stab">Expiration Year Month</td>
											<td><ezf:inputText name="crCardExprYrMth_H" ezfName="crCardExprYrMth_H" value="07/2016" otherAttr=" size=\"7\" maxlength=\"7\" tabindex=\"2\" ezftoupper=\"\""/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr height="20">
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<%  } %>
									</table>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxModeCd == '01'}">
								<fieldset style="padding:5 20 5 20;">
									<table border="0" cellpadding="1" cellspacing="0">
  									<col align="left" width="200">
  									<col align="right" width="597">
										<tr style="height:30;">
										<td>
											<ezf:inputButton name="AddLine" value="Add" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" tabindex=\"2\""/>
											<ezf:inputButton name="DeleteLine" value="Delete" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" tabindex=\"2\""/>
										</td>
										<td>
										    <% if (DS_PMT_METH.E_CHECK.equals(bMsg.dsPmtMethCd_H.getValue()) || DS_PMT_METH.CREDIT_CARD.equals(bMsg.dsPmtMethCd_H.getValue())) { %>
                                            <ezf:inputButton name="AddOnAcctLine" value="OnAccount" htmlClass="pBtn6" />
                                            <%  } %>
											<ezf:inputButton name="Calc" value="Calc" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" tabindex=\"2\""/>
										</td>
										</tr>
									</table>
									<div id="topTBL" style="overflow-x:hidden; width:810; overflow-y:none; height:25;">
										<table border="1" cellpadding="1" cellspacing="0">
											<col align="center" width="40">
											<col align="center" width="170">
											<col align="center" width="50">
											<col align="center" width="170">
											<col align="center" width="120">
											<col align="center" width="50">
											<col align="center" width="170">
											<tr style="height:24;">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Invoice#</td>
												<td class="pClothBs">>></td>
												<td class="pClothBs">Invoice Balance</td>
												<td class="pClothBs">Trans Date</td>
												<td class="pClothBs">Pay Full</td>
												<td class="pClothBs">Payment Amount</td>
											</tr>
										</table>
									</div>
									<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:818; height:222;">
										<table border="1" cellpadding="1" cellspacing="0" id="A">
											<col align="center" width="40">
											<col align="left"   width="170">
											<col align="center" width="50">
											<col align="left"   width="170">
											<col align="center" width="120">
											<col align="center" width="50">
											<col align="center" width="170">
											<ezf:row ezfHyo="A">
											<tr height="24" id="id_row{EZF_ROW_NUMBER}">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="arTrxNum_A1" ezfName="arTrxNum_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"13\" tabindex=\"2\" ezftoupper=\"\""/></td>
												<td><ezf:inputButton name="SearchInvoice" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchInvoice#{EZF_ROW_NUMBER}\" tabindex=\"2\""/></td>
												<td><ezf:inputText name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"13\" tabindex=\"2\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="acctDt_A1" ezfName="acctDt_A1" value="10/10/09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"2\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('acctDt_A1', 4, '{EZF_ROW_NUMBER}');"  tabindex="2"></td>
												<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnCheck_PayFull')" /></td>
												<td><ezf:inputText name="dealNetRcptAmt_A1" ezfName="dealNetRcptAmt_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"13\" tabindex=\"2\" ezftoupper=\"\""/></td>
											</tr>
											</ezf:row>
										</table>
									</div>
								</fieldset>
							</c:when>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td>
								<fieldset style="padding:5 20 5 20;">
									<div>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="80">
											<col width="830">
											<tr>
												<td class="stab">Comment</td>
												<td>
													<ezf:textArea name="dtlNoteTxt_H" ezfName="dtlNoteTxt_H" otherAttr=" rows=\"2\" cols=\"100\" maxlength=\"4000\""/>
												</td>
											</tr>
										</table>
									</div>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );

		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>
<%-- **** Task specific area ends here **** --%>
