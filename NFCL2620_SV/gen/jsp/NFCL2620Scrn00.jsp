<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220802152342 --%>
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
			<input type="hidden" name="pageID" value="NFCL2620Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AR Refund Inquiry">

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
				<div class="pTab_BODY">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:20px;">
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
							<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:290px;\" tabindex=\"1\""/>
						</td>
						<td class="stab">Search Option Name</td>
						<td>
							<ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/>
						</td>
						<td>
							<ezf:inputButton name="Save_Search" ezfName="Save_Search" value="Save Search" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/>
							 </td>
						<td>
							<ezf:inputButton name="Delete_Search" ezfName="Delete_Search" value="Delete Search" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<hr>
				<table border="0" cellpadding="1" cellspacing="0" height="150" style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<col width="120" align="left">
					<col width="250" align="left">
					<col width="100" align="left">
					<col width="200" align="left">
					<col width="370" align="right">
					<tr>
						<td class="stab">Refund Number</td>
						<td><ezf:inputText name="arRfRqstPk_H" ezfName="arRfRqstPk_H" value="1234567" otherAttr=" size=\"13\" maxlength=\"28\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab">Refund Type</td>
						<td>
							<div>
								<ezf:select name="arRfTpCd_H" ezfName="arRfTpCd_H" ezfBlank="1" ezfCodeName="arRfTpCd_C" ezfDispName="arRfTpDescTxt_D" otherAttr=" style=\"width:180px\""/>
							</div>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" >Customer #</ezf:anchor></td>
						<td colspan="4">
							<ezf:inputText name="billToCustAcctCd_H" ezfName="billToCustAcctCd_H" value="1234567" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
							<ezf:inputButton name="GetCustomerNm" value=">>" htmlClass="pBtn0" />
							<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW" otherAttr=" size=\"60\" maxlength=\"240\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Refund Status</td>
						<td>
							<div>
								<ezf:select name="arRfStsCd_H" ezfName="arRfStsCd_H" ezfBlank="1" ezfCodeName="arRfStsCd_C" ezfDispName="arRfStsDescTxt_D" otherAttr=" style=\"width:220px\""/>
							</div>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab">Refund Creation Date</td>
						<td colspan="3">
							<table border="0" cellpadding="1">
								<tr>
									<td>
										<ezf:inputText name="arRfCratDt_F" ezfName="arRfCratDt_F" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" <img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('arRfCratDt_F', 4);" id="arRfCratDt_F">
									</td>
									<td align="center" class="stab">-</td>
									<td>
										<ezf:inputText name="arRfCratDt_T" ezfName="arRfCratDt_T" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" <img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('arRfCratDt_T', 4);" id="arRfCratDt_T">
									</td>
							</table>
						</td>
					</tr>
					<tr>
						<td class="stab">Check#</td>
						<td colspan="3">
							<table border="0" cellpadding="1">
								<col width="190" align="left">
								<col width="90"  align="left">
								<col width="270" align="left">
								<tr>
									<td><ezf:inputText name="apPmtChkNum_H" ezfName="apPmtChkNum_H" value="1234567" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
									<td class="stab">Refund Amount</td>
									<td><ezf:inputText name="dealRfAmt_H" ezfName="dealRfAmt_H" value="-123,456,789.00" otherAttr=" size=\"23\" maxlength=\"26\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
								</tr>
							</table>
						</td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
			  <TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
				  <TBODY>
				  <tr>
				  	  <td style="padding-left: 24px; width:500;">
				 	 	  <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
				  	  </td>
					  <td align="right" style="padding-right: 24px;">
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
									  <COL width="20">
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
									  <TD></TD>
								  </TR>
								  </TBODY>
							  </TABLE>
						  </ezf:skip>
					  </td>
				  </tr>
				  </TBODY>
				<div id="parentBoxA">
					<table>
						<tr>
							<td width="10"></td>
							<td>
								<div style="float:left; display:block"> <!-- left table -->
									<div id="leftTblHead" style="display:block;"></div>
									<div id="leftTbl" style="float:left; display:block; height:266px; overflow:hidden; margin:0px; padding:0px;padding-bottom: 20px"></div>
								</div>  <!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id="rightTblHead" style="width:1085px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<table border="1" cellpadding="1" cellspacing="0"  id="AHEAD" width="1950px" style="table-layout:fixed; margin-right:20px;">
											<col width="125" align="center">	<!-- Refound Type   -->
											<col width="125" align="center">	<!-- Customer #     -->
											<col width="300" align="center">	<!-- Customer Name  -->
											<col width="80"  align="center">	<!-- Refund Number  -->
											<col width="180" align="center">	<!-- Status         -->
											<col width="100" align="center">	<!-- Transaction #  -->
											<col width="100" align="center">	<!-- Reference #    -->
											<col width="180" align="center">	<!-- Refund Amount  -->
											<col width="220" align="center">	<!-- Requester      -->
											<col width="220" align="center">	<!-- Approver       -->
											<col width="120" align="center">	<!-- AP Invoice #   -->
											<col width="180" align="center">	<!-- Invoice Status -->
											<col width="120" align="center">	<!-- Check#         -->
											<tr>
												<td id="AH0" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arRfTpDescTxt' )">
														Refund Type<img id="sortIMG.arRfTpDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH1" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd' )">
														Customer #<img id="sortIMG.billToCustAcctCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH2" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm' )">
														Customer Name<img id="sortIMG.dsAcctNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH3" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arRfRqstPk' )">
														Refund<br>Number<img id="sortIMG.arRfRqstPk" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH4" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arRfStsDescTxt' )">
														Refund Status<img id="sortIMG.arRfStsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
<!-- START 2018/09/14 T.Ogura [QC#25091,ADD] -->
												<td id="AH5" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum' )">
														Transaction #<img id="sortIMG.arCustRefNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
<!-- END   2018/09/14 T.Ogura [QC#25091,ADD] -->
												<td id="AH6" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxNum' )">
<!-- START 2018/09/14 T.Ogura [QC#25091,MOD] -->
<!--													CM/Receipt #<img id="sortIMG.arTrxNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"> -->
														Reference #<img id="sortIMG.arTrxNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
<!-- END   2018/09/14 T.Ogura [QC#25091,MOD] -->
													</a>
												</td>
												<td id="AH7" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRfAmt' )">
														Refund Amount<img id="sortIMG.dealRfAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH8" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'cratUsrNm' )">
														Requester<img id="sortIMG.cratUsrNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH9" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxWfAsgNm' )">
														Approver<img id="sortIMG.xxWfAsgNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH10" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'apVndInvNum' )">
														AP Invoice #<img id="sortIMG.apVndInvNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH11" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'acctInvStsDescTxt' )">
														Invoice Status<img id="sortIMG.acctInvStsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH12" class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'apPmtChkNum' )">
														Check#<img id="sortIMG.apPmtChkNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
											</tr>
										</table>
									</div>
									<div id="rightTbl" style="width:1100px; height:283px; display:block; overflow:scroll; margin:0px; padding:0px;">
										<table border="1" cellpadding="0" cellspacing="0" id="A" width="1950px" style="table-layout:fixed;">
											<col width="125" align="center">
											<col width="125" align="center">
											<col width="300" align="center">
											<col width="80"  align="center">
											<col width="180" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="180" align="center">
											<col width="220" align="center">
											<col width="220" align="center">
											<col width="120" align="center">
											<col width="180" align="center">
											<col width="120" align="center">
<!-- START 2018/09/14 T.Ogura [QC#25091,ADD] -->
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="business.servlet.NFCL2620.NFCL2620BMsg" %>
<%@ page import="business.servlet.NFCL2620.NFCL2620_ABMsg" %>
											<% NFCL2620BMsg bMsg = (NFCL2620BMsg)databean.getEZDBMsg(); %>
											<% int i = 0; %>
<!-- END   2018/09/14 T.Ogura [QC#25091,ADD] -->
											<ezf:row ezfHyo="A">
<!-- START 2018/09/14 T.Ogura [QC#25091,ADD] -->
											<% NFCL2620_ABMsg abMsg = bMsg.A.no(i++); %>
											<% boolean invNumFlg = ZYPCommonFunc.hasValue(abMsg.apVndInvNum); %>
<!-- END   2018/09/14 T.Ogura [QC#25091,ADD] -->
											<tr id="id_b_row{EZF_ROW_NUMBER}">
												<td align="left"><ezf:inputText name="arRfTpDescTxt" ezfName="arRfTpDescTxt" value="Customer Refund" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"arRfTpDescTxt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="left"><ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"billToCustAcctCd#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="left"><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" id=\"dsAcctNm#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="right"><ezf:anchor name="arRfRqstPk" ezfName="arRfRqstPk" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ArRefundByCheckEntry" otherAttr=" id=\"arRfRqstPk#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="arRfRqstPk" ezfName="arRfRqstPk" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden; width:80;\""/></ezf:anchor></td>
												<td align="left"><ezf:inputText name="arRfStsDescTxt" ezfName="arRfStsDescTxt" value="Error in Refund Creation" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" id=\"arRfStsDescTxt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
<!-- START 2018/09/14 T.Ogura [QC#25091,ADD] -->
												<td align="left"><ezf:inputText name="arCustRefNum" ezfName="arCustRefNum" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"arCustRefNum#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
<!-- END   2018/09/14 T.Ogura [QC#25091,ADD] -->
												<td align="left"><ezf:inputText name="arTrxNum" ezfName="arTrxNum" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" id=\"arTrxNum#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="right"><ezf:inputText name="dealRfAmt" ezfName="dealRfAmt" value="12,345,678,9012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" id=\"dealRfAmt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="left"><ezf:inputText name="cratUsrNm" ezfName="cratUsrNm" value="123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"100\" id=\"cratUsrNm#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="left"><ezf:inputText name="xxWfAsgNm" ezfName="xxWfAsgNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"100\" id=\"xxWfAsgNm#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
<!-- START 2018/09/14 T.Ogura [QC#25091,MOD] -->
<!--											<td align="left"><input type="text" class="pPro" size="15" ezfname="apVndInvNum"    ezfhyo="A" name="apVndInvNum"   id="apVndInvNum#{EZF_ROW_NUMBER}"   style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWWW" ezfout></td> -->
												<% if(invNumFlg) { %>
													<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ApInv" ><ezf:label name="apVndInvNum" ezfName="apVndInvNum" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
												<% } else { %>
													<td align="left">&nbsp;</td>
												<% } %>
<!-- END   2018/09/14 T.Ogura [QC#25091,MOD] -->
												<td align="left"><ezf:inputText name="acctInvStsDescTxt" ezfName="acctInvStsDescTxt" value="Linked To Cost Calculation" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" id=\"acctInvStsDescTxt#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
												<td align="left"><ezf:inputText name="apPmtChkNum" ezfName="apPmtChkNum" value="WWWWWWWWW1WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"apPmtChkNum#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
										<tr>
											<td align="left"><input type="text" class="pPro" size="15"  ezfname="arRfTpDescTxt" name="arRfTpDescTxt" id="arRfTpDescTxt#{EZF_ROW_NUMBER}" style="border:none; background-color:transparent;" value="CFS Refund" ezfhyo="A" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="15"  ezfname="billToCustAcctCd" name="billToCustAcctCd" id="billToCustAcctCd#{EZF_ROW_NUMBER}" style="border:none; background-color:transparent;" ezfhyo="A" value="WWWWWWW" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="40" ezfname="dsAcctNm"       ezfhyo="A" name="dsAcctNm"      id="dsAcctNm#{EZF_ROW_NUMBER}"      style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfout></td>
											<td align="right"><a href="#" onclick="sendServer('OpenWin_ArRefundByCheckEntry')" id="arRfRqstPk#{EZF_ROW_NUMBER}" name="arRfRqstPk" ezfname="arRfRqstPk" ezfhyo="A" ezfanchortext><label ezfout style="white-space:nowrap; overflow:hidden; width:80;" name="arRfRqstPk" ezfname="arRfRqstPk" ezfhyo="A">1234567</label></a></td>
											<td align="left"><input type="text" class="pPro" size="24" ezfname="arRfStsDescTxt"      ezfhyo="A" name="arRfStsDescTxt"     id="arRfStsDescTxt#{EZF_ROW_NUMBER}"     style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWW" ezfout></td>
<!-- START 2018/09/14 T.Ogura [QC#25091,ADD] -->
											<td align="left"><input type="text" class="pPro" size="13" ezfname="arCustRefNum"   ezfhyo="A" name="arCustRefNum"  id="arCustRefNum#{EZF_ROW_NUMBER}"  style="border:none; background-color:transparent;" value="1234567890123" ezfout></td>
<!-- END   2018/09/14 T.Ogura [QC#25091,ADD] -->
											<td align="left"><input type="text" class="pPro" size="13" ezfname="arTrxNum"       ezfhyo="A" name="arTrxNum"      id="arTrxNum#{EZF_ROW_NUMBER}"      style="border:none; background-color:transparent;" value="1234567890123" ezfout></td>
											<td align="right"><input type="text" class="pPro" size="23" ezfname="dealRfAmt"     ezfhyo="A" name="dealRfAmt"     id="dealRfAmt#{EZF_ROW_NUMBER}"     style="border:none; background-color:transparent;" value="12,345,678,9012,345.00" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="30" ezfname="cratUsrNm" maxlength="100"  ezfhyo="A" name="cratUsrNm" id="cratUsrNm#{EZF_ROW_NUMBER}" style="border:none; background-color:transparent;" value="123456789012345678" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="30" ezfname="xxWfAsgNm" maxlength="100"  ezfhyo="A" name="xxWfAsgNm" id="xxWfAsgNm#{EZF_ROW_NUMBER}" style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfout></td>
										<td align="left"><input type="text" class="pPro" size="15" ezfname="apVndInvNum"    ezfhyo="A" name="apVndInvNum"   id="apVndInvNum#{EZF_ROW_NUMBER}"   style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWWW" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="25" ezfname="acctInvStsDescTxt"   ezfhyo="A" name="acctInvStsDescTxt"  id="acctInvStsDescTxt#{EZF_ROW_NUMBER}"  style="border:none; background-color:transparent;" value="Linked To Cost Calculation" ezfout></td>
											<td align="left"><input type="text" class="pPro" size="15" ezfname="apPmtChkNum"    ezfhyo="A" name="apPmtChkNum"   id="apPmtChkNum#{EZF_ROW_NUMBER}"   style="border:none; background-color:transparent;" value="WWWWWWWWW1WWWWW" ezfout></td>
										</tr>
										</ezf:skip>
									</div>
								</div><!-- right table-->
							</td>
						</tr>
					</table>
				</div><!-- parent box-->
			  </TABLE>
			  <br>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0, false);
</script>


<%-- **** Task specific area ends here **** --%>
