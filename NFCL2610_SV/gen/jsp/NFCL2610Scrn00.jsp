<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220726094346 --%>
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
			<input type="hidden" name="pageID" value="NFCL2610Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AR Refund  by Check Entry">


<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Group Level Report" class="pTab_ON"><a href="#">Work Item</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
				<table border="0" cellpadding="1" cellspacing="0" style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<col width="90" align="left">
					<col width="50" align="left">
					<col width="40" align="center">
					<col width="55" align="left">
					<col width="230" align="left">
					<col width="100"  align="right">
					<col width="40"  align="right">
					<col width="120"  align="left">
					<col width="40"  align="right">
					<col width="80"  align="left">
					<col width="100"  align="right">
					<tr>
						<td class="stab">Refund Number</td>
						<td><ezf:inputText name="arRfRqstPk" ezfName="arRfRqstPk" value="1234567" otherAttr=" size=\"13\" maxlength=\"28\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Refund Type</td>
						<td>
							<div>
								<ezf:select name="arRfTpCd_H" ezfName="arRfTpCd_H" ezfCodeName="arRfTpCd_C" ezfDispName="arRfTpDescTxt_D" otherEvent1=" onchange=\"sendServer('OnChange_RefundType')\"" otherAttr=" style=\"width:180px\""/>
							</div>
						</td>
						<td class="stab">Refund Trx Tp</td>
						<td><ezf:inputCheckBox name="xxChkBox_1" ezfName="xxChkBox_1" value="Y" /></td>
						<td class="stab">Receipt/On Account</td>
						<td><ezf:inputCheckBox name="xxChkBox_2" ezfName="xxChkBox_2" value="Y" /></td>
						<td class="stab">Credit Memo</td>
						<td></td>
					</tr>
					<tr>
						<%@ page import="business.servlet.NFCL2610.NFCL2610BMsg" %>
						<% NFCL2610BMsg bMsg = (NFCL2610BMsg)databean.getEZDBMsg(); %>
						<% if (bMsg.billToCustAcctCd.isInputProtected() == false) {%>
						<td class="stab"><ezf:anchor name="billToCustCd_1" ezfName="billToCustCd_1" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" >Customer</ezf:anchor></td>
						<% } else { %>
						<td class="stab">Customer</td>
						<% } %>
						<td><ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="1234567" otherAttr=" size=\"13\" maxlength=\"7\" ezftoupper=\"\""/></td>
						<td><ezf:inputButton name="GetCustomerNm" value=">>" htmlClass="pBtn0" /></td>
						<td colspan="2"><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW" otherAttr=" size=\"60\" maxlength=\"240\" ezftoupper=\"\""/></td>
						<td class="stab">Related Customer</td>
						<td><ezf:inputCheckBox name="xxChkBox_5" ezfName="xxChkBox_5" value="Y" /></td>
					</tr>
					<tr>
						<% if (bMsg.billToCustLocCd.isInputProtected() == false) {%>
						<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
						<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_BillToLoc')" ezfname="billToCustCd_2" name="billToCustCd_2">Bill To Loc</a></td> -->
						<td class="stab"><ezf:anchor name="billToCustCd_2" ezfName="billToCustCd_2" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToLoc" >Bill To Code</ezf:anchor></td>
						<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						<% } else { %>
						<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
						<!-- <td class="stab">Bill To Loc</td> -->
						<td class="stab">Bill To Code</td>
						<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						<% } %>
						<td><ezf:inputText name="billToCustLocCd" ezfName="billToCustLocCd" value="123456" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
						<td><ezf:inputButton name="GetBillToLoc" value=">>" htmlClass="pBtn0" /></td>
						<td colspan="2"><ezf:inputText name="billToCustNm" ezfName="billToCustNm" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1wwwww" otherAttr=" size=\"60\" maxlength=\"240\" ezftoupper=\"\""/></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><ezf:inputButton name="ReEntry" value="Re-Entry" htmlClass="pBtn6" /></td>
					</tr>
					<tr>
						<td class="stab">Address</td>
						<td colspan="4"><ezf:inputText name="xxAllLineAddr" ezfName="xxAllLineAddr" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"70\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td class="stab">Transaction#</td>
						<td colspan="4"><ezf:inputText name="xxQueryFltrTxt" ezfName="xxQueryFltrTxt" otherAttr=" size=\"35\" maxlength=\"200\" ezftoupper=\"\""/></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="0">
					<col width="100" align="left">
					<col width="400" align="left">
					<col width="100" align="left">
					<col width="200" align="left">
					<col width="120" align="right">
					<col width="130" align="left">
					<tr>
						<td class="stab">Refund Comment</td>
						<td><ezf:inputText name="arRcptRfCmntTxt" ezfName="arRcptRfCmntTxt" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"50\" maxlength=\"2000\""/></td>
						<td></td>
						<td></td>
						<td class="stab">Refund Status</td>
						<td><ezf:inputText name="arRfStsDescTxt" ezfName="arRfStsDescTxt" value="AP Inovice Created" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
					</tr>
					<tr>
						<td class="stab">Check Notes</td>
						<td><ezf:inputText name="arRfChkCmntTxt" ezfName="arRfChkCmntTxt" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWW" otherAttr=" size=\"50\" maxlength=\"35\""/></td>
						<td><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" /></td>
						<td></td>
						<td class="stab">Total Refund Amount</td>
						<td><ezf:inputText name="xxTotRfAmt" ezfName="xxTotRfAmt" value="-123,456,789,123.12" otherAttr=" style=\"text-align:right\" size=\"15\" maxlength=\"23\""/></td>
					</tr>
				</table>
				<hr>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr align="left">
						<col width="80" align="left">
						<col width="80" align="left">
						<col width="400" align="right">
						<col width="540" align="left">
						<td><ezf:inputButton name="CheckAll" value="CheckAll" htmlClass="pBtn6" otherAttr=" id=\"CheckAll\""/></td>
						<td><ezf:inputButton name="UncheckAll" value="UncheckAll" htmlClass="pBtn6" otherAttr=" id=\"UncheckAll\""/></td>
						<td></td>
						<td>
							  <ezf:skip>
								  <TABLE cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 27px;">
									  <COLGROUP>
										  <COL align="center" width="54">
										  <COL align="right" width="40">
										  <COL align="center" width="16">
										  <COL align="right" width="40">
										  <COL align="center" width="16">
										  <COL align="center" width="20">
										  <COL width="40">
										  <COL width="10">
										  <COL>
										  <COL width="1">
										  <COL>
									  </COLGROUP>
									  <TBODY>
									  <TR>
										  <TD class="stab"><LABEL>Showing</LABEL></TD>
										  <TD class="pOut">1</TD>
										  <TD class="stab"><LABEL>/</LABEL></TD>
										  <TD class="pOut">40</TD>
										  <TD class="stab"><LABEL>Page</LABEL></TD>
										  <TD>&nbsp;</TD>
										  <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Jump" name="PagePrev"></TD>
										  <TD>&nbsp;</TD>
										  <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></TD>
										  <TD></TD>
										  <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></TD>
									  </TR>
									  </TBODY>
								  </TABLE>
							  </ezf:skip>
							  <TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
								  <TBODY>
								  <tr align="right">
									  <td style="padding-right: 24px;">
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
				<br>

				<table border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td valign="top">
							<div id="Top" style="overflow-x:hidden; overflow-y:hidden; width:1100;" onscroll="synchroScrollLeft(this.id, new Array('Detail'));">
							<table border="1" cellpadding="0" cellspacing="0" width="1300" style="table-layout: fixed;">
								<col width="20" align="center">
								<col width="60" align="center">
								<col width="95" align="center">
								<col width="95" align="center">
								<col width="44"  align="center">
								<col width="150"  align="center">
								<col width="80"  align="center">
								<col width="65"  align="center">
								<col width="65" align="center">
								<col width="200" align="center">
								<col width="113" align="center">
								<col width="113" align="center">
								<col width="200" align="center">
								<tr height="24" style="font-size:8pt;">
									<td class="pClothBs"></td>
<!-- START 2018/09/14 T.Ogura [QC#25091,MOD] -->
<!-- 								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxNum_A' )">Number<img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td> -->
<!-- START 2020/09/16 R.Kurahashi [QC#57703,ADD] -->
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arDsWfStsNm_A' )">Status<img id="sortIMG.arDsWfStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- END 2020/09/16 R.Kurahashi [QC#57703,ADD] -->
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A' )">Transaction#<img id="sortIMG.arCustRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxNum_A' )">Reference#<img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
<!-- END   2018/09/14 T.Ogura [QC#25091,MOD] -->
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxTpCd_A' )">Class<img id="sortIMG.arTrxTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsInvTpDescTxt_A' )">Invoice Type<img id="sortIMG.dsInvTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A' )">Trx Date<img id="sortIMG.arTrxDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_A' )">Customer<img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A' )">Bill To<img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxAllLineAddr_A' )">Bill To Address<img id="sortIMG.xxAllLineAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A' )">Original Amt<img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A' )">Balance Due<img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arRcptRfRsnCd_A' )">Refund Reason<img id="sortIMG.arRcptRfRsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>
							</div>
							<div id="Detail" style="overflow-x:scroll; overflow-y:scroll; height:146; width:1117;" onscroll="synchroScrollLeft(this.id, new Array('Top'));">
								<table id="A1" border="1" cellpadding="1" cellspacing="0" width="1300" style="table-layout:fixed;" >
									<col width="20" align="center">
									<col width="60" align="center">
									<col width="95" align="center">
									<col width="95" align="center">
									<col width="44"  align="center">
									<col width="150" align="center">
									<col width="80"  align="center">
									<col width="65"  align="center">
									<col width="65"  align="center">
									<col width="200" align="center">
									<col width="113" align="center">
									<col width="113" align="center">
									<col width="200" align="center">
									<ezf:row ezfHyo="A">
										<tr id="id_b_row{EZF_ROW_NUMBER}" height="24">
											<td align="center"><ezf:inputCheckBox name="xxChkBox_3" ezfName="xxChkBox_3" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_BalDueTotal', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_3#{EZF_ROW_NUMBER}\""/></td>
<!-- START 2018/09/14 T.Ogura [QC#25091,MOD] -->
<!-- 										<td align="left"><input type="text" class="pPro"   size="12"  ezfname="arTrxNum_A" ezfhyo="A" name="arTrxNum_A" id="arTrxNum_A#{EZF_ROW_NUMBER}" style="border:none; background-color:transparent;" value="1234567890123" ezfout></td> -->
<!-- START 2020/09/16 R.Kurahashi [QC#57703,ADD] -->
											<td align="left"><ezf:inputText name="arDsWfStsNm_A" ezfName="arDsWfStsNm_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" id=\"arDsWfStsNm_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
<!-- END 2020/09/16 R.Kurahashi [QC#57703,ADD] -->
											<td align="left"><ezf:inputText name="arCustRefNum_A" ezfName="arCustRefNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"arCustRefNum_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="arTrxNum_A" ezfName="arTrxNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"arTrxNum_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
<!-- END   2018/09/14 T.Ogura [QC#25091,MOD] -->
											<td align="left" ><ezf:inputText name="arTrxTpCd_A" ezfName="arTrxTpCd_A" value="WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" id=\"arTrxTpCd_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="dsInvTpDescTxt_A" ezfName="dsInvTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"dsInvTpDescTxt_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="center"><ezf:inputText name="arTrxDt_A" ezfName="arTrxDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"arTrxDt_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" value="1234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"billToCustAcctCd_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="1234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"billToCustCd_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="xxAllLineAddr_A" ezfName="xxAllLineAddr_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\" id=\"xxAllLineAddr_A#{EZF_ROW_NUMBER}\""/></td>
											<td align="right"><ezf:inputText name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"dealOrigGrsAmt_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="right"><ezf:inputText name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"dealRmngBalGrsAmt_A#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="center">
												<ezf:select name="arRcptRfRsnCd_A" ezfName="arRcptRfRsnCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="arRcptRfRsnCd_C" ezfDispName="arRcptRfRsnDescTxt_D" otherAttr=" style=\"width:180\""/>
											</td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<hr>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<col width="80" align="right">
						<col width="100" align="right">
						<td><ezf:inputButton name="SupplierEntry" value="Supplier Entry" htmlClass="pBtn8" otherAttr=" id=\"SupplierEntry\""/></td>
						<td><ezf:inputButton name="NewSupplier" value="New Supplier" htmlClass="pBtn8" otherAttr=" id=\"NewSupplier\""/></td>
					</tr>
				</table>
				<br>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<table border="1" cellpadding="0" cellspacing="0" width="1100" height="24" style="table-layout: fixed;">
								<col width="20"  align="center">
								<col width="70"  align="center">
								<col width="150" align="center">
								<col width="90" align="center">
								<col width="50"  align="center">
								<col width="100" align="center">
								<col width="140" align="center">
								<col width="140" align="center">
								<col width="130" align="center">
								<col width="40"  align="center">
								<col width="80" align="center">
								<col width="90" align="center">
								
								<tr height="24" style="font-size:8pt;">
									<td class="pClothBs"></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prntVndCd' )">Supplier#<img id="sortIMG.prntVndCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prntVndNm' )">Supplier Name<img id="sortIMG.prntVndNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxScrItem61Txt' )">Supplier Type<img id="sortIMG.xxScrItem61Txt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxChkBox_4' )">Active<img id="sortIMG.xxChkBox_4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxLocNm_P' )">Site Name<img id="sortIMG.xxLocNm_P" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'firstLineAddr' )">Address1<img id="sortIMG.firstLineAddr" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'scdLineAddr' )">Address2<img id="sortIMG.scdLineAddr" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlCtrCtyAddr' )">City<img id="sortIMG.rtlCtrCtyAddr" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'stCd' )">State<img id="sortIMG.stCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'postCd' )">Postal Code<img id="sortIMG.postCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxToDt' )">Creation Date<img id="sortIMG.xxToDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>

							<div style="overflow-y:scroll; height:98; width:1117;">
								<table id="B1" border="1" cellpadding="0" cellspacing="0" height="24" style="table-layout: fixed;">
									<col width="20"  align="center">
									<col width="70"  align="left">
									<col width="150" align="left">
									<col width="90" align="left">
									<col width="50"  align="center">
									<col width="100" align="right">
									<col width="140" align="left">
									<col width="140" align="left">
									<col width="130" align="left">
									<col width="40" align="left">
									<col width="80" align="left">
									<col width="90" align="left">

									<ezf:row ezfHyo="B">
										<tr height="24">
											<td align="center"><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" /></td>
											<td align="left"><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"prntVndCd#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="WWWWWWWWW1WWWWWWWWW1WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" id=\"prntVndNm#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td align="left"><ezf:inputText name="xxScrItem61Txt" ezfName="xxScrItem61Txt" value="WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"xxScrItem61Txt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="center"><ezf:inputCheckBox name="xxChkBox_4" ezfName="xxChkBox_4" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_4#{EZF_ROW_NUMBER}\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="xxLocNm_P" ezfName="xxLocNm_P" value="WWWWWWWWW1WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"xxLocNm_P#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="firstLineAddr" ezfName="firstLineAddr" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" id=\"firstLineAddr#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="scdLineAddr" ezfName="scdLineAddr" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" id=\"scdLineAddr#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="rtlCtrCtyAddr" ezfName="rtlCtrCtyAddr" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"rtlCtrCtyAddr#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="stCd" ezfName="stCd" value="WW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"2\" id=\"stCd#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="left"><ezf:inputText name="postCd" ezfName="postCd" value="12345123451234" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"postCd#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											<td class="fontMin" align="center"><ezf:inputText name="xxToDt" ezfName="xxToDt" value="MM/DD/YYYY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"xxToDt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
