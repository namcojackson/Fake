<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190415083122 --%>
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
			<input type="hidden" name="pageID" value="NSAL0990Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Order">

	<%@ page import="business.servlet.NSAL0990.NSAL0990BMsg" %>
	<% NSAL0990BMsg bMsg = (NSAL0990BMsg)databean.getEZDBMsg(); %>

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Supply Order" class="pTab_ON"><a href="#">Sply Order</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<%-- ######################################## Search Criteria ######################################## --%>
				<div class="pTab_BODY">
				<div id="top" style="border-style:solid; border-width:0px; height:550px; overflow-y:scroll; width:1116px; overflowx:none; padding-left:10;">
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" height="40">
									<col width="10">
									<col width="80">
									<col width="10">
									<col width="20">
									<col width="100">
									<col width="10">
									<col width="20">
									<col width="100">
									<col width="10">
									<col width="20">
									<col width="80">
									<col width="10">
									<col width="20">
									<col width="75">
									<col width="10">
									<col width="10">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Serial Number</td>
										<td><ezf:inputText name="serNum_HD" ezfName="serNum_HD" value="NF128789012345678" otherAttr=" size=\"18\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Account Number</td>
										<td><ezf:inputText name="ownrAcctNum" ezfName="ownrAcctNum" value="1088302" otherAttr=" size=\"18\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Contract Type</td>
										<td><ezf:inputText name="dsContrCatgDescTxt" ezfName="dsContrCatgDescTxt" value="Non Fleet" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Contract</td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="12345678" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Order#</td>
										<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
										<% if(ZYPCommonFunc.hasValue(bMsg.cpoOrdNum.getValue())) { %>
										<td class="stab"><ezf:anchor name="orderEntry_LK" ezfName="orderEntry_LK" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" ezfanchortext=\"\""><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" /></ezf:anchor></td>
										<% } else {%>
										<td>&nbsp;</td>
										<% }%>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Model Name</td>
										<td><ezf:inputText name="xxScrItem34Txt" ezfName="xxScrItem34Txt" value="ADVC7065" otherAttr=" size=\"18\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Sales Person</td>
										<td><ezf:inputText name="coaBrNm" ezfName="coaBrNm" value="602 HOUSTON" otherAttr=" size=\"18\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Staples Inclusive</td>
										<td><ezf:inputText name="svcTermCondDataDispTxt_02" ezfName="svcTermCondDataDispTxt_02" value="N" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Yield Type</td>
										<td><ezf:inputText name="svcTermCondDataDispTxt_03" ezfName="svcTermCondDataDispTxt_03" value="N" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Toner Type</td>
										<td><ezf:inputText name="svcTermCondDataDispTxt_01" ezfName="svcTermCondDataDispTxt_01" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table width="1090px" border="0" cellpadding="5" cellspacing="0" height="20">
									<col width="910">
									<col width="100">
									<tr>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_EditingList" value="Editing List" htmlClass="pBtn10" /></td>
									</tr>
								</table>
							</td>
					   </tr>
					</table>
<%-- ######################################## Order Header Details ######################################## --%>
					<table>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Order Header Details</font>&nbsp;</legend>
								<table width="1090px" border="0px" cellpadding="0px" cellspacing="0px" height="180px">
									<col width="10">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="7">
									<col width="10">
									<col width="5">
									<col width="10">
									<col width="10">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="billToLocNum_LK" ezfName="billToLocNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" ezfanchortext=\"\"">Bill To</ezf:anchor></td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputText name="billToLocNum" ezfName="billToLocNum" value="1088302" otherEvent1="OnBlur_BillToLocation" otherAttr=" size=\"7\" ezffocusout=\"OnBlur_BillToLocation\""/>
											<ezf:inputHidden name="billToLocNum_SI" ezfName="billToLocNum_SI" />
										</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search_BillTo" value=">>" htmlClass="pBtn0" /></td>
										<td>&nbsp;</td>
										<td colspan="7"><ezf:inputText name="xxLocAddrNm_A1" ezfName="xxLocAddrNm_A1" value="KATTEN MUCHIN ROSENMAN LLP" otherAttr=" size=\"40\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="curLocNum_LK" ezfName="curLocNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" ezfanchortext=\"\"">Ship To</ezf:anchor></td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputText name="curLocNum" ezfName="curLocNum" value="1088302" otherEvent1="OnBlur_ShipToLocation" otherAttr=" size=\"7\" ezffocusout=\"OnBlur_ShipToLocation\""/>
											<ezf:inputHidden name="curLocNum_SI" ezfName="curLocNum_SI" />
										</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search_ShipTo" value=">>" htmlClass="pBtn0" /></td>
										<td>&nbsp;</td>
										<td colspan="7"><ezf:inputText name="xxLocAddrNm_A4" ezfName="xxLocAddrNm_A4" value="KATTEN MUCHIN ROSENMAN LLP" otherAttr=" size=\"40\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td colspan="3">
											<ezf:inputButton name="OpenWin_SpecialInstruction" value="Special Instruction" htmlClass="pBtn10" />
											<ezf:inputHidden name="funcMstrIdDescTxt" ezfName="funcMstrIdDescTxt" />
											<ezf:inputHidden name="dsTrxRuleTpCd" ezfName="dsTrxRuleTpCd" />
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Address</td>
										<td>&nbsp;</td>
										<td colspan="11"><ezf:inputText name="xxLocAddrNm_A2" ezfName="xxLocAddrNm_A2" value="525 W MONROE ST STE 1900" otherAttr=" size=\"54\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab">Address</td>
										<td>&nbsp;</td>
										<td colspan="11"><ezf:inputText name="xxLocAddrNm_A5" ezfName="xxLocAddrNm_A5" value="1301 MCKINNEY ST FL 30" otherAttr=" size=\"54\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="11"><ezf:inputText name="xxLocAddrNm_A3" ezfName="xxLocAddrNm_A3" otherAttr=" size=\"54\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="11"><ezf:inputText name="xxLocAddrNm_A6" ezfName="xxLocAddrNm_A6" otherAttr=" size=\"54\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="5"><ezf:inputText name="ctyAddr_A1" ezfName="ctyAddr_A1" value="CHICAGO" otherAttr=" size=\"21\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="stCd_A1" ezfName="stCd_A1" value="IL" otherAttr=" size=\"3\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="postCd_A1" ezfName="postCd_A1" value="60661-3718" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="ctryCd_A1" ezfName="ctryCd_A1" value="USA" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="5"><ezf:inputText name="ctyAddr_A2" ezfName="ctyAddr_A2" value="HOUSTON" otherAttr=" size=\"21\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="stCd_A2" ezfName="stCd_A2" value="TX" otherAttr=" size=\"3\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="postCd_A2" ezfName="postCd_A2" value="77010-3031" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="ctryCd_A2" ezfName="ctryCd_A2" value="USA" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">PO#</td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Contact</td>
										<td class="stab"><ezf:anchor name="ctacPsnFirstNm_LK" ezfName="ctacPsnFirstNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ContactSearch" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
										<td colspan="7"><ezf:inputText name="ctacPsnFirstNm" ezfName="ctacPsnFirstNm" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
										<td class="stab">L</td>
										<td colspan="3"><ezf:inputText name="ctacPsnLastNm" ezfName="ctacPsnLastNm" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab">Email</td>
										<td>&nbsp;</td>
										<td colspan="11"><ezf:inputText name="ctacPsnEmlAddr" ezfName="ctacPsnEmlAddr" otherAttr=" size=\"54\" maxlength=\"320\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="3" class="stab">Order Notes</td>
										<td>&nbsp;</td>
										<td colspan="28"><ezf:textArea name="xxDsMultMsgDplyTxt" ezfName="xxDsMultMsgDplyTxt" otherAttr=" rows=\"4\" cols=\"130\""/></td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</fieldset>
							</td>
						</tr>
					</table>
<%-- ######################################## Shipping Information Details ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Shipping Information Details</font>&nbsp;</legend>
								<table width="1090" border="0" cellpadding="0" cellspacing="0" height="70">
									<col width="10"  align="left">
									<col width="120" align="left">
									<col width="5"   align="left">
									<col width="250" align="left">
									<col width="10"  align="left">
									<col width="100" align="left">
									<col width="5"   align="left">
									<col width="200" align="left">
									<col width="390">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="frtCondDescTxt_LK" ezfName="frtCondDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FreightTerms" otherAttr=" ezfanchortext=\"\"">Freight Terms</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="frtCondDescTxt" ezfName="frtCondDescTxt" otherEvent1="OnBlur_DeriveFromFreightTerms" otherAttr=" size=\"30\" ezfnoupperfocusout=\"OnBlur_DeriveFromFreightTerms\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Service Level</td>
										<td>&nbsp;</td>
										<td><ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_ShpgSvcLvlCd')\"" otherAttr=" style=\"width: 145px\" tabindex=\"1\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="carrSvcLvlDescTxt_LK" ezfName="carrSvcLvlDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierServiceLevel" otherAttr=" ezfanchortext=\"\"">Carrier Service Level</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" otherEvent1="OnBlur_DeriveFromCarrierServiceLevel" otherAttr=" size=\"30\" ezfnoupperfocusout=\"OnBlur_DeriveFromCarrierServiceLevel\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Carrier Acct Num</td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Attention</td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="scrLbNm" ezfName="scrLbNm" otherAttr=" size=\"30\" maxlength=\"90\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Shipping Instructions</td>
										<td>&nbsp;</td>
										<td colspan="6"><ezf:textArea name="shpgInstnCmntTxt" ezfName="shpgInstnCmntTxt" otherAttr=" rows=\"4\" cols=\"120\""/></td>
									</tr>
								</table>
							</fieldset>
							</td>
						</tr>
					</table>
<%-- ######################################## Payment Details ######################################## --%>
					<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyCtrlFlg == 'Y'}">
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Payment Details</font>&nbsp;</legend>
								<table width="1090" border="0" cellpadding="0" cellspacing="0" height="20">
									<col width="10">
									<col width="105">
									<col width="5">
									<col width="10">
									<col width="15">
									<col width="100">
									<col width="5">
									<col width="10">
									<col width="20">
									<col width="10">
									<col width="164">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Invoice Comments</td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="invCmntTxt" ezfName="invCmntTxt" otherAttr=" size=\"60\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Payment Method</td>
										<td>&nbsp;</td>
										<td>
											<ezf:select name="dsPmtMethCd" ezfName="dsPmtMethCd" ezfBlank="1" ezfCodeName="dsPmtMethCd_CD" ezfDispName="dsPmtMethDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_PaymentMethod')\"" otherAttr=" style=\"width: 145px\" tabindex=\"1\""/>
										</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="MoveWin_CreditCard" value="Credit Card" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<br>
							</fieldset>
							</td>
						</tr>
					</table>
					</c:when>
					</c:choose>

				<c:choose>
				<%-- ################################################################################################### --%>
				<%-- ######################################## Supply Order Mode ######################################## --%>
				<%-- ################################################################################################### --%>
				<c:when test="${pageScope._ezddatabean.xxScrDply == 'SplyOrdMode'}">
				<script type="text/javascript">
					document.getElementById( "splyOrdMode" ).style.display = "block";
					document.getElementById( "splyOrdEditMode" ).style.display = "none";
				</script>
				<div id="splyOrdMode">
				<%-- ######################################## Toner Allotments ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Toner Allotments</font>&nbsp;</legend>
								<br>
								<div style="overflow-y:none; height:20; overflow-x:none; width=1090;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="374" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<tr class="pEvenNumberBGcolor">
										<td class="pClothBs" height="20">Contract#</td>
										<td class="pClothBs" height="20">BW</td>
										<td class="pClothBs" height="20">CLR</td>
										<td class="pClothBs" height="20">Total</td>
										<td class="pClothBs" height="20">&nbsp;</td>
									</tr>
								</table>
								</div>
								<div style="overflow-x:none;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="374" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<tr height="20">
										<% if("Y".equals(bMsg.xxDplyCtrlFlg_A.getValue())) { %>
											<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" /></td>
											<td><ezf:label name="bwPrrtQty_A" ezfName="bwPrrtQty_A" /></td>
											<td><ezf:label name="colorPrrtQty_A" ezfName="colorPrrtQty_A" /></td>
											<td><ezf:label name="totQty_A" ezfName="totQty_A" /></td>
											<td class="stab"><ezf:anchor name="xxImageTxt_LK" ezfName="xxImageTxt_LK" ezfEmulateBtn="1" ezfGuard="MoveWin_SupplyWatch" otherAttr=" ezfanchortext=\"\"">Go to Watchlist</ezf:anchor></td>
										<% } else { %>
											<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" /></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										<% } %>
									</tr>
								</table>
								</div>
								<br>
							</fieldset>
							</td>
						</tr>
					</table>
				<%-- ######################################## Order History ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Order History</font>&nbsp;</legend>
								<br>
								<div style="overflow-y:none; height:20; overflow-x:none; width=1090;">
								<table>
									<col width="923" align="left">
									<col width="167" align="left">
									<tr>
										<td>
											<div id="topTBL" style="overflow-y:none; height:20; overflow-x:none;">
												<table border="1" cellpadding="1" cellspacing="0">
													<col width="120" align="center">
													<col width="130" align="center">
													<col width="130" align="center">
													<col width="270" align="center">
													<col width="80" align="center">
													<col width="140" align="center">
													<tr class="pEvenNumberBGcolor">
														<td class="pClothBs" height="20">Order Date</td>
														<td class="pClothBs" height="20">Order Number</td>
														<td class="pClothBs" height="20">Item Cd</td>
														<td class="pClothBs" height="20">Item Name</td>
														<td class="pClothBs" height="20">Ordered Qty</td>
														<td class="pClothBs" height="20">Status</td>
													</tr>
												</table>
											</div>
											<div id="bottomTBL" style="overflow-y:scroll; height:80; overflow-x:none;">
											<table border="1" cellpadding="1" cellspacing="0" id="B">
													<col width="120" align="center">
													<col width="130" align="center">
													<col width="130" align="center">
													<col width="270" align="center">
													<col width="80" align="center">
													<col width="140" align="center">
												<ezf:row ezfHyo="B">
												<tr height="20">
													<td><ezf:label name="xxOrdDt_B" ezfName="xxOrdDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="cpoOrdNum_B" ezfName="cpoOrdNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="GPR-43 TONER BLACK" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"37\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													<td><ezf:label name="ordQty_B" ezfName="ordQty_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="ordLineStsNm_B" ezfName="ordLineStsNm_B" value="Closed" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="20">
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >12/16/2015</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >31633537</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >7420A077AA</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >REFURBISHED WASTE CONTAINER FOR IR</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >4</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >Closed</label></td>
												</tr>
												</ezf:skip>
												<ezf:skip>
												<tr height="20">
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >12/16/2015</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >31633537</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >7420A077BB</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >REFURBISHED WASTE CONTAINER FOR IR</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >2</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >Closed</label></td>
												</tr>
												</ezf:skip>
												<ezf:skip>
												<tr height="20">
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >12/16/2015</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >31633537</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >7420A077CC</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >REFURBISHED WASTE CONTAINER FOR IR</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >1</label></td>
													<td><label ezfout name="xxOrdDt_B_y"    ezfName="xxOrdDt_B"      ezfHyo="B" >Closed</label></td>
												</tr>
												</ezf:skip>
											</table>
											</div>
										</td>
										<td valign="top">
											<ezf:select name="xxCondCd" ezfName="xxCondCd" ezfBlank="1" ezfCodeName="xxCondCd_CD" ezfDispName="xxCondNm_NM" otherEvent1=" onchange=\"sendServer('OnBlur_ChangePeriod')\"" otherAttr=" style=\"width:145px\""/>
										</td>
									</tr>
								</table>
								</div>
								<br>
							</fieldset>
							</td>
						</tr>
					</table>
				<%-- ######################################## Line Details ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Line Details</font>&nbsp;</legend>
								<br>
								<label>&nbsp;Service Program : </label>
								<ezf:label name="mdseDescShortTxt_SP" ezfName="mdseDescShortTxt_SP" />
								<br>
								<br>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="10" align="center">
									<col width="50" align="left">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="40" align="center">
									<col width="120" align="center">
									<col width="150" align="left">
									<col width="50" align="center">
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" ezfanchortext=\"\"">Item Cd</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"14\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search_MdseNm" value=">>" htmlClass="pBtn0" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" otherAttr=" size=\"43\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Add_Mdse" value="Add" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><label>Filter By Model : </label></td>
										<td><ezf:select name="t_MdlId_S" ezfName="t_MdlId_S" ezfBlank="1" ezfCodeName="t_MdlId_CD" ezfDispName="xxScrItem34Txt_NM" otherAttr=" style=\"width: 200px\""/></td>
										<td><ezf:inputButton name="FilterByModel" value="Go" htmlClass="pBtn2" /></td>
									</tr>
								</table>
								<br>
								<div style="overflow-y:none; height:20; overflow-x:none; width=1090;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="25" align="center">
									<col width="120" align="center">
									<col width="60" align="center">
									<col width="100" align="center">
									<col width="300" align="center">
									<col width="50" align="center">
									<col width="100" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<tr class="pEvenNumberBGcolor">
										<td class="pClothBs" height="20">&nbsp;</td>
										<td class="pClothBs" height="20">Serial#</td>
										<td class="pClothBs" height="20">Color</td>
										<td class="pClothBs" height="20">Item Cd</td>
										<td class="pClothBs" height="20">Item Name</td>
										<td class="pClothBs" height="20">UOM(EA)</td>
										<td class="pClothBs" height="20">OEM Code</td>
										<td class="pClothBs" height="20">Unit Price</td>
										<td class="pClothBs" height="20">Ordered Qty</td>
										<td class="pClothBs" height="20">Price</td>
										</tr>
								</table>
								</div>
								<div style="overflow-y:scroll; height:242; overflow-x:none; width=1084;">
								<table border="1" cellpadding="1" cellspacing="0" id="C">
									<col width="25" align="center">
									<col width="120" align="center">
									<col width="60" align="center">
									<col width="100" align="center">
									<col width="300" align="left">
									<col width="50" align="center">
									<col width="100" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<% int idx = 0; %>
									<% String preImgSplyColorTpNm = ""; %>
									<% String preSerNum = ""; %>
									<ezf:row ezfHyo="C">
									<tr height="20">
										<td><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<td><ezf:label name="serNum_C" ezfName="serNum_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<% if(preSerNum.equals(bMsg.C.no(idx).serNum_C.getValue()) && preImgSplyColorTpNm.equals(bMsg.C.no(idx).imgSplyColorTpNm_C.getValue())) { %>
											<td>&nbsp;</td>
										<% } else { %>
											<td><ezf:label name="imgSplyColorTpNm_C" ezfName="imgSplyColorTpNm_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<% } %>
										<td><ezf:label name="mdseCd_C" ezfName="mdseCd_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="mdseDescShortTxt_C" ezfName="mdseDescShortTxt_C" value="GPR-33 TONER BLACK01234567890123456789012345678901" ezfHyo="C" ezfArrayNo="${idx}" otherAttr=" size=\"42\" tabindex=\"-1\" style=\"border:none; background-color:transparent\""/></td>
										<td><ezf:label name="cpoMinOrdQty_C" ezfName="cpoMinOrdQty_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="imgSplyOemCd_C" ezfName="imgSplyOemCd_C" value="AAAAAAAAAABBBBBBBBBBCCCCCCCCCC" ezfHyo="C" ezfArrayNo="${idx}" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none; background-color:transparent\""/></td>
										<td><ezf:label name="entDealNetUnitPrcAmt_C" ezfName="entDealNetUnitPrcAmt_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="ordCustUomQty_C" ezfName="ordCustUomQty_C" value="0" ezfHyo="C" ezfArrayNo="${idx}" otherAttr=" style=\"text-align:right\" size=\"12\" ezftoupper=\"\""/></td>
										<td><ezf:label name="entCpoDtlDealSlsAmt_C" ezfName="entCpoDtlDealSlsAmt_C" ezfHyo="C" ezfArrayNo="${idx}" /></td>
									</tr>
									<% preImgSplyColorTpNm = bMsg.C.no(idx).imgSplyColorTpNm_C.getValue(); %>
									<% preSerNum = bMsg.C.no(idx).serNum_C.getValue(); %>
									<% idx++; %>
									</ezf:row>
								</table>
								</div>
								<div style="overflow-x:none;">
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="27" align="center">
									<col width="122" align="center">
									<col width="62" align="center">
									<col width="102" align="center">
									<col width="302" align="left">
									<col width="52" align="center">
									<col width="102" align="center">
									<col width="94" align="center">
									<col width="90" align="left">
									<col width="94" align="center">
									<tr height="20">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>Total</td>
										<td>
											<table border="1" width="100%">
												<col align="center">
												<tr>
													<td><ezf:label name="xxTotAmt_C" ezfName="xxTotAmt_C" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<br>
								<br>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="530" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<tr height="20">
										<td>&nbsp;</td>
										<td><ezf:inputButton name="DeleteLine" value="Delete" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_InventoryStockOverview" value="Inventory" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="MoveWin_MemoEntry" value="Notes" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_SupplyOrderSer" value="Add More" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Calcu" value="Calculation" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Disp_SupplyOrderEdit" value="Payment and Review" htmlClass="pBtn10" /></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								</div>
								<br>
							</fieldset>
							</td>
						</tr>
					</table>
				<%-- ######################################## Supply Stats ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Supply Stats</font>&nbsp;</legend>
								<br>
								<br>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="50" align="center">
									<col width="50" align="left">
									<col width="10" align="center">
									<col width="60" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<tr>
										<td>&nbsp;</td>
										<td>Bill To</td>
										<td>&nbsp;</td>
										<td><ezf:label name="billToLocNum_D" ezfName="billToLocNum_D" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="xxLocAddrNm_D" ezfName="xxLocAddrNm_D" value="KATTEN MUCHIN ROSENMAN LLP" otherAttr=" size=\"43\""/></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<br>
								<div style="overflow-y:none; height:20; overflow-x:none; width=1090;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="200" align="center">
									<tr class="pEvenNumberBGcolor">
										<td class="pClothBs" height="20">Supply Profit</td>
										<td class="pClothBs" height="20"><ezf:label name="dwhTrgtYr_01" ezfName="dwhTrgtYr_01" /></td>
										<td class="pClothBs" height="20"><ezf:label name="dwhTrgtYr_02" ezfName="dwhTrgtYr_02" /></td>
										<td class="pClothBs" height="20"><ezf:label name="dwhTrgtYr_03" ezfName="dwhTrgtYr_03" /></td>
										<td class="pClothBs" height="20">Last Three Years</td>
									</tr>
								</table>
								</div>
								<div style="overflow-x:none;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="150" align="right">
									<col width="150" align="right">
									<col width="150" align="right">
									<col width="150" align="right">
									<col width="200" align="right">
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Total Revenue</label></td>
										<td><ezf:label name="totRevAmt_01" ezfName="totRevAmt_01" /></td>
										<td><ezf:label name="totRevAmt_02" ezfName="totRevAmt_02" /></td>
										<td><ezf:label name="totRevAmt_03" ezfName="totRevAmt_03" /></td>
										<td><ezf:label name="totRevAmt_04" ezfName="totRevAmt_04" /></td>
									</tr>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Total Costs</label></td>
										<td><ezf:label name="totCostAmt_01" ezfName="totCostAmt_01" /></td>
										<td><ezf:label name="totCostAmt_02" ezfName="totCostAmt_02" /></td>
										<td><ezf:label name="totCostAmt_03" ezfName="totCostAmt_03" /></td>
										<td><ezf:label name="totCostAmt_04" ezfName="totCostAmt_04" /></td>
									</tr>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Profit Dollars</label></td>
										<td><ezf:label name="totPrftAmt_01" ezfName="totPrftAmt_01" /></td>
										<td><ezf:label name="totPrftAmt_02" ezfName="totPrftAmt_02" /></td>
										<td><ezf:label name="totPrftAmt_03" ezfName="totPrftAmt_03" /></td>
										<td><ezf:label name="totPrftAmt_04" ezfName="totPrftAmt_04" /></td>
									</tr>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Profit%</label></td>
										<td><ezf:label name="totPrftPct_01" ezfName="totPrftPct_01" /></td>
										<td><ezf:label name="totPrftPct_02" ezfName="totPrftPct_02" /></td>
										<td><ezf:label name="totPrftPct_03" ezfName="totPrftPct_03" /></td>
										<td><ezf:label name="totPrftPct_04" ezfName="totPrftPct_04" /></td>
									</tr>
									<ezf:skip>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Total Costs</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >69.87</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >15342.45</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >33903.21</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >49315.53</label></td>
									</tr>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Profit Dollars</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >3134.82</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >51865.49</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >36696.18</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >91696.49</label></td>
									</tr>
									<tr height="20">
										<td><label name="rtrnSubStageTpNm_S1" >Profit%</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >4486.65</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >338.05</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >108.24</label></td>
										<td><label name="rtrnSubStageTpNm_S1" >185.94</label></td>
									</tr>
									</ezf:skip>
								</table>
								</div>
								<br>
							</fieldset>
							</td>
						</tr>
					</table>
				</div>
				</c:when>

				<%-- ######################################################################################################## --%>
				<%-- ######################################## Supply Order Edit Mode ######################################## --%>
				<%-- ######################################################################################################## --%>
				<c:when test="${pageScope._ezddatabean.xxScrDply == 'SplyOrdEditMode'}">
				<script type="text/javascript">
					document.getElementById( "splyOrdMode" ).style.display = "none";
					document.getElementById( "splyOrdEditMode" ).style.display = "block";
				</script>
				<div id="splyOrdEditMode">
				<%-- ######################################## Line Details ######################################## --%>
					<table>
						<br>
						<col valign="top">
						<tr>
							<td>
							<fieldset>
								<legend>&nbsp;<font size="2">Line Details</font>&nbsp;</legend>
								<br>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="600" align="center">
									<col width="120" align="center">
									<col width="150" align="left">
									<col width="50" align="center">
									<tr>
										<td>&nbsp;</td>
										<td><label>Filter By Model : </label></td>
										<td><ezf:select name="t_MdlId_S" ezfName="t_MdlId_S" ezfBlank="1" ezfCodeName="t_MdlId_CD" ezfDispName="xxScrItem34Txt_NM" otherAttr=" style=\"width: 200px\""/></td>
										<td><ezf:inputButton name="FilterByModel" value="Go" htmlClass="pBtn2" /></td>
									</tr>
								</table>
								<br>
								<div style="overflow-y:none; height:20; overflow-x:none; width=1090;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="25" align="center">
									<col width="120" align="center">
									<col width="150" align="center">
									<col width="60" align="center">
									<col width="100" align="center">
									<col width="200" align="center">
									<col width="50" align="center">
									<col width="70" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<tr class="pEvenNumberBGcolor">
										<td class="pClothBs" height="20">&nbsp;</td>
										<td class="pClothBs" height="20">Serial#</td>
										<td class="pClothBs" height="20">Model Name</td>
										<td class="pClothBs" height="20">Color</td>
										<td class="pClothBs" height="20">Item Cd</td>
										<td class="pClothBs" height="20">Item Name</td>
										<td class="pClothBs" height="20">UOM(EA)</td>
										<td class="pClothBs" height="20">OEM Code</td>
										<td class="pClothBs" height="20">Unit Price</td>
										<td class="pClothBs" height="20">Ordered Qty</td>
										<td class="pClothBs" height="20">Price</td>
									</tr>
								</table>
								</div>
								<div style="overflow-x:none;">
								<table border="1" cellpadding="1" cellspacing="0" id="E">
									<col width="25" align="center">
									<col width="120" align="center">
									<col width="150" align="left">
									<col width="60" align="center">
									<col width="100" align="center">
									<col width="200" align="left">
									<col width="50" align="center">
									<col width="70" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<% int idx = 0; %>
									<% String preImgSplyColorTpNm = ""; %>
									<% String preMdlNm = ""; %>
									<% String preSerNum = ""; %>
									<ezf:row ezfHyo="E">
									<tr height="20">
										<td><ezf:inputCheckBox name="xxChkBox_E" ezfName="xxChkBox_E" value="Y" ezfHyo="E" ezfArrayNo="${idx}" /></td>
										<% if(preSerNum.equals(bMsg.E.no(idx).serNum_E.getValue())) { %>
											<td>&nbsp;</td>
										<% } else { %>
											<td align="center">
												<% if("FLT".equals(bMsg.E.no(idx).dsContrCatgCd_E.getValue())) { %>
													<ezf:label name="serNum_E" ezfName="serNum_E" ezfHyo="E" ezfArrayNo="${idx}" />
												<% } else { %>
													<ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfHyo="E" ezfArrayNo="${idx}" ezfEmulateBtn="1" ezfGuard="Disp_SupplyOrder" otherAttr=" tabindex=\"-1\"">
														<ezf:label name="serNum_E" ezfName="serNum_E" ezfHyo="E" ezfArrayNo="${idx}" />
													</ezf:anchor>
												<% } %>
											</td>
										<% } %>
										<% if(preSerNum.equals(bMsg.E.no(idx).serNum_E.getValue()) && preMdlNm.equals(bMsg.E.no(idx).t_MdlNm_E.getValue())) { %>
											<td>&nbsp;</td>
										<% } else { %>
											<% if("FLT".equals(bMsg.E.no(idx).dsContrCatgCd_E.getValue())) { %>
												<td>&nbsp;</td>
											<% } else { %>
												<td><ezf:inputText name="t_MdlNm_E" ezfName="t_MdlNm_E" value="01234567890123456789012345678901234567890123456789" ezfHyo="E" ezfArrayNo="${idx}" otherAttr=" size=\"20\" tabindex=\"-1\" style=\"border:none; background-color:transparent\""/></td>
											<% } %>
										<% } %>
										<% if(preSerNum.equals(bMsg.E.no(idx).serNum_E.getValue()) && preMdlNm.equals(bMsg.E.no(idx).t_MdlNm_E.getValue()) && preImgSplyColorTpNm.equals(bMsg.E.no(idx).imgSplyColorTpNm_E.getValue())) { %>
											<td>&nbsp;</td>
										<% } else { %>
											<td><ezf:label name="imgSplyColorTpNm_E" ezfName="imgSplyColorTpNm_E" ezfHyo="E" ezfArrayNo="${idx}" /></td>
										<% } %>
										<td><ezf:label name="mdseCd_E" ezfName="mdseCd_E" ezfHyo="E" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="mdseDescShortTxt_E" ezfName="mdseDescShortTxt_E" value="01234567890123456789012345678901234567890123456789" ezfHyo="E" ezfArrayNo="${idx}" otherAttr=" size=\"28\" tabindex=\"-1\" style=\"border:none; background-color:transparent\""/></td>
										<td><ezf:label name="cpoMinOrdQty_E" ezfName="cpoMinOrdQty_E" ezfHyo="E" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="imgSplyOemCd_E" ezfName="imgSplyOemCd_E" value="AAAAAAAAAABBBBBBBBBBCCCCCCCCCC" ezfHyo="E" ezfArrayNo="${idx}" otherAttr=" size=\"8\" tabindex=\"-1\" style=\"border:none; background-color:transparent\""/></td>
										<td><ezf:label name="entDealNetUnitPrcAmt_E" ezfName="entDealNetUnitPrcAmt_E" ezfHyo="E" ezfArrayNo="${idx}" /></td>
										<td><ezf:inputText name="ordCustUomQty_E" ezfName="ordCustUomQty_E" value="0" ezfHyo="E" ezfArrayNo="${idx}" otherAttr=" style=\"text-align:right\" size=\"12\" ezftoupper=\"\""/></td>
										<td><ezf:label name="entCpoDtlDealSlsAmt_E" ezfName="entCpoDtlDealSlsAmt_E" ezfHyo="E" ezfArrayNo="${idx}" /></td>
									</tr>
									<% preImgSplyColorTpNm = bMsg.E.no(idx).imgSplyColorTpNm_E.getValue(); %>
									<% preMdlNm = bMsg.E.no(idx).t_MdlNm_E.getValue(); %>
									<% preSerNum = bMsg.E.no(idx).serNum_E.getValue(); %>
									<% idx++; %>
									</ezf:row>
								</table>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="27" align="center">
									<col width="122" align="center">
									<col width="152" align="left">
									<col width="62" align="center">
									<col width="102" align="center">
									<col width="202" align="left">
									<col width="52" align="center">
									<col width="82" align="center">
									<col width="94" align="center">
									<col width="90" align="left">
									<col width="94" align="center">
									<tr height="20">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>Tax</td>
										<td>
											<table border="1" width="100%">
												<col align="center">
												<tr>
													<td><ezf:label name="xxTotTaxAmt_E" ezfName="xxTotTaxAmt_E" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr height="20">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>Total</td>
										<td>
											<table border="1" width="100%">
												<col align="center">
												<tr>
													<td><ezf:label name="xxTotAmt_E" ezfName="xxTotAmt_E" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<br>
								<br>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="675" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<col width="10" align="center">
									<tr height="20">
										<td>&nbsp;</td>
										<td><ezf:inputButton name="DeleteQuote" value="Delete Quote" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_InventoryStockOverview" value="Inventory" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="MoveWin_MemoEntry" value="Notes" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_SupplyOrderSer" value="Add More" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Calcu" value="Calculation" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
									</tr>
								</table>
								</div>
								<br>
							</fieldset>
							</td>
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
