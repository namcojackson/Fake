<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221222080759 --%>
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

<%@ page import="business.servlet.NFDL0020.NFDL0020BMsg" %>
<%@ page import="business.servlet.NFDL0020.NFDL0020_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NFDL0020BMsg bMsg = (NFDL0020BMsg)databean.getEZDBMsg(); %>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NFDL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Collection Header">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Col Search</a></li>
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
				<table border="0" bordercolor="green">
					<tr valign ="bottom">
						<td style="padding: 0px 0px 0px 0px;" height="10">
							<table width="99%" border="0" bordercolor="orange" cellpadding="0" cellspacing="0" align="center" >
								<col width="80">
								<col width="350">
								<col width="80">
								<col width="40">
								<col width="470">
								<col width="" align="right">
								<col>
								<tr>
									<td class="stab">Account</td>
									<td valign ="bottom">
										<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_AcctSoldTo" otherAttr=" ezfanchortext=\"\"">
											<ezf:label name="dsAcctNum_H" ezfName="dsAcctNum_H" /></ezf:anchor>
										<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"35\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_OpenWin_BillSrch" >Bill To</ezf:anchor></td>
									<td valign ="bottom"><ezf:inputText name="billToCustCd_H" ezfName="billToCustCd_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/>
										<ezf:inputButton name="Click_SetBillToLocNm" value=">>" htmlClass="pBtn1" />
										<ezf:inputText name="locNm_H" ezfName="locNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"35\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/>
										<ezf:inputButton name="Click_AcctBillTo" value="Details" htmlClass="pBtn3" /></td>
									<td valign ="bottom"><ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn6" /></td>
									<td></td>
								</tr>
							</table>
							<hr></hr>
						</td>
					</tr>
					<tr>
						<td style="padding: 0px 0px 3px 1px;">
							<table border="1" cellpadding="3" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<col width="80">
											<col width="180">
											<col width="5">
											<col width="240">
											<col width="5">
											<col width="90">
											<col width="140">
											<col width="355">
											<tr>
												<td class="stab" style="hight:37px;">Addl Name</td>
												<td><ezf:inputText name="addlLocNm_H" ezfName="addlLocNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"25\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
												<td></td>
												<td rowspan="8">
 													<table border="1" cellpadding="2" cellspacing="0">
														<tr>
															<td>
 																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="80">
																	<col width="150">
																	<tr>
																		<td class="stab" colspan="2"><b>Contact Information</b></td>
																	</tr>
																	<tr>
																		<td align="right" colspan="2">
																			<ezf:inputButton name="Click_ContactInfoEdit" value="Edit" htmlClass="pBtn3" />
																			<ezf:inputButton name="Click_ContactInfoUpdate" value="Update" htmlClass="pBtn3" />
																			<ezf:inputButton name="Click_ContactInfoCancel" value="Cancel" htmlClass="pBtn3" />
																		</td>
																	</tr>
																	<tr>
																		<td class="stab">Contact Type</td>
																		<td><ezf:select name="ctacTpCd_H" ezfName="ctacTpCd_H" ezfBlank="1" ezfCodeName="ctacTpCd_LC" ezfDispName="ctacTpDescTxt_LD" otherAttr=" style=\"width:147px;\""/></td>
																	</tr>
																	<tr>
																		<td class="stab">First Name</td>
																		<td><ezf:inputText name="ctacPsnFirstNm_H" ezfName="ctacPsnFirstNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"1502\""/></td>
																	</tr>
																	<tr>
																		<td class="stab">Last Name</td>
																		<td><ezf:inputText name="ctacPsnLastNm_H" ezfName="ctacPsnLastNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"1502\""/></td>
																	</tr>
																	<tr>
																		<td class="stab">Phone#</td>
																		<td><ezf:inputText name="dsCtacPntValTxt_H1" ezfName="dsCtacPntValTxt_H1" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"1502\""/></td>
																	</tr>
																	<tr>
																		<td class="stab">Email</td>
																		<td><ezf:inputText name="dsCtacPntValTxt_H2" ezfName="dsCtacPntValTxt_H2" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"20\" maxlength=\"320\" tabindex=\"1502\""/></td>
																	</tr>
        														</table>
															</td>
														</tr>
													</table>
                                                </td>
												<td></td>
												<td class="stab">Collection Status</td>
												<td><ezf:inputText name="xxScrItem130Txt_H" ezfName="xxScrItem130Txt_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
												<td rowspan="8">
 													<table border="1" cellpadding="3" cellspacing="0">
														<tr>
															<td>
			 													<table border="0" cellpadding="1" cellspacing="0">
																	<col width="50">
																	<col width="100">
																	<col width="10">
																	<col width="130">
																	<col width="100">
																	<tr>
																		<td class="stab" colspan="2"><b>Aging Details</b></td>
																		<td>&nbsp;</td>
																		<td class="stab" colspan="2"><b>Open Credits</b></td>
																	</tr>
																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L1" ezfName="xxTotAmt_L1" ezfEmulateBtn="1" ezfGuard="Click_AgingCurrent" otherAttr=" ezfanchortext=\"\"">Current</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H1" ezfName="xxTotAmt_H1" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td class="stab">Unapplied Cash</td>
																		<td><ezf:inputText name="xxTotAmt_H8" ezfName="xxTotAmt_H8" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>

																	</tr>
																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L2" ezfName="xxTotAmt_L2" ezfEmulateBtn="1" ezfGuard="Click_Aging30" otherAttr=" ezfanchortext=\"\"">1-30</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H2" ezfName="xxTotAmt_H2" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td class="stab"></td>
																		<td></td>

																	</tr>
 																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L3" ezfName="xxTotAmt_L3" ezfEmulateBtn="1" ezfGuard="Click_Aging60" otherAttr=" ezfanchortext=\"\"">31-60</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H3" ezfName="xxTotAmt_H3" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td class="stab">Credits</td>
																		<td><ezf:inputText name="xxTotAmt_H9" ezfName="xxTotAmt_H9" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>

																	</tr>
																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L4" ezfName="xxTotAmt_L4" ezfEmulateBtn="1" ezfGuard="Click_Aging90" otherAttr=" ezfanchortext=\"\"">61-90</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H4" ezfName="xxTotAmt_H4" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td class="stab">Dispute Amt</td>
																		<td><ezf:inputText name="dealCltDsptAmt_H" ezfName="dealCltDsptAmt_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>

																	</tr>
																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L5" ezfName="xxTotAmt_L5" ezfEmulateBtn="1" ezfGuard="Click_Aging180" otherAttr=" ezfanchortext=\"\"">91-180</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H5" ezfName="xxTotAmt_H5" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td class="stab">Promise Amt</td>
																		<td><ezf:inputText name="dealCltPrmsAmt_H" ezfName="dealCltPrmsAmt_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>

																	</tr>

																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L6" ezfName="xxTotAmt_L6" ezfEmulateBtn="1" ezfGuard="Click_Aging360" otherAttr=" ezfanchortext=\"\"">181-365</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H6" ezfName="xxTotAmt_H6" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																	</tr>
																	<tr>
																		<td class="stab"><ezf:anchor name="xxTotAmt_L7" ezfName="xxTotAmt_L7" ezfEmulateBtn="1" ezfGuard="Click_Aging361" otherAttr=" ezfanchortext=\"\"">366+</ezf:anchor></td>
																		<td><ezf:inputText name="xxTotAmt_H7" ezfName="xxTotAmt_H7" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																		<td><ezf:inputButton name="Click_NoteAttach" value="Attachment" htmlClass="pBtn7" /></td></td>
																	</tr>
			        											</table>
															</td>
														</tr>
													</table>
                                                </td>
      										</tr>
											<tr>
												<td class="stab">Address Line 1</td>
												<td><ezf:inputText name="firstLineAddr_H" ezfName="firstLineAddr_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"25\" maxlength=\"35\" tabindex=\"1505\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">Amount Overdue</td>
												<td><ezf:inputText name="dealRmngBalGrsAmt_H1" ezfName="dealRmngBalGrsAmt_H1" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">Address Line 2</td>
												<td><ezf:inputText name="scdLineAddr_H" ezfName="scdLineAddr_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"25\" maxlength=\"35\" tabindex=\"1505\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">Net Balance</td>
												<td><ezf:inputText name="dealRmngBalGrsAmt_H2" ezfName="dealRmngBalGrsAmt_H2" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">Address Line 3</td>
												<td><ezf:inputText name="thirdLineAddr_H" ezfName="thirdLineAddr_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"25\" maxlength=\"35\" tabindex=\"1506\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">DSO</td>
												<td><ezf:inputText name="invTotDealNetAmt_H" ezfName="invTotDealNetAmt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">Address Line 4</td>
												<td><ezf:inputText name="frthLineAddr_H" ezfName="frthLineAddr_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"25\" maxlength=\"35\" tabindex=\"1507\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">Last Payment Date</td>
												<td><ezf:inputText name="rcptDt_H" ezfName="rcptDt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">Country/Zip</td>
												<td><ezf:inputText name="ctryCd_H" ezfName="ctryCd_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"14\" maxlength=\"20\" tabindex=\"1508\" ezftoupper=\"\""/><ezf:inputText name="postCd_H" ezfName="postCd_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1508\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">Payment Amount</td>
												<td><ezf:inputText name="rcptAmt_H" ezfName="rcptAmt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">City/County</td>
												<td><ezf:inputText name="ctyAddr_H" ezfName="ctyAddr_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1508\" ezftoupper=\"\""/><ezf:inputText name="cntyNm_H" ezfName="cntyNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"12\" maxlength=\"13\" tabindex=\"1508\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td class="stab">Credit Rating</td>
												<td><ezf:inputText name="custCrRtgDescTxt_H" ezfName="custCrRtgDescTxt_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											</tr>
											<tr>
												<td class="stab">State/Province</td>
												<td><ezf:inputText name="stCd_H" ezfName="stCd_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"2\" maxlength=\"20\" tabindex=\"1508\" ezftoupper=\"\""/><ezf:inputText name="provNm_H" ezfName="provNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"22\" maxlength=\"10\" tabindex=\"1508\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="padding: 0px 0px 2px 0px;">
							<table width="99%" border="0" bordercolor="pink" cellpadding="0" cellspacing="0" align="center">
								<col width="90">
								<col width="">
								<col>
								<tr valign="middle">
									<td class="stab">Note</td>
									<td><ezf:inputText name="cltHdrNoteTxt_H" ezfName="cltHdrNoteTxt_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"145\" maxlength=\"2000\" tabindex=\"1502\" ezftoupper=\"\""/>
								</tr>
							</table>
						</td>
					</tr>
				</table>
					
				<!-- ######################################## DETAIL ######################################## -->
				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<ul>
						<li class="pTab_OFF" id="Transaction" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabTransaction" otherAttr=" tabindex=\"102\"">Transaction</ezf:anchor></li>
						<li class="pTab_OFF" id="Note" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabNote" otherAttr=" tabindex=\"105\"">Note</ezf:anchor></li>
						<li class="pTab_OFF" id="Task" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabTask" otherAttr=" tabindex=\"105\"">Task</ezf:anchor></li>
						<li class="pTab_OFF" id="Contract" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabContract" otherAttr=" tabindex=\"105\"">Contract</ezf:anchor></li>
						<li class="pTab_OFF" id="Strategy" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabStrategy" otherAttr=" tabindex=\"104\"">Strategy</ezf:anchor></li>
						<li class="pTab_OFF" id="AdjHistory" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabAdjHistory" otherAttr=" tabindex=\"105\"">AdjHistory</ezf:anchor></li>
						<li class="pTab_OFF" id="Statement" ><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TabStatement" otherAttr=" tabindex=\"105\"">Statement</ezf:anchor></li>
					</ul>
				</div>
				
				<c:choose>
				<%--######################################## TAB "Transaction" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Transaction'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_ON";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>

				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table width="1123">
						<tr>
							<td>
								<table border="0" bordercolor="blue" cellpadding="1" cellspacing="0">
									<col width="30">
									<col width="100">
									<col width="20">
									<col width="70">
									<col width="270">
									<col width="30">
									<col width="100">
									<col width="30">
									<col width="100">
									<col width="5">
									<col width="120">
									<col width="120">
									<col width="120">
									<col width="62">
									<tr>
										<td class="stab">Class</td>
										<td>
											<ezf:select name="arTrxTpCd_AH" ezfName="arTrxTpCd_AH" ezfBlank="1" ezfCodeName="arTrxTpCd_LC" ezfDispName="arTrxTpDescTxt_LD" otherAttr=" style=\"width:100px;\""/>
										</td>
										<td></td>

										<td class="stab">Due Date</td>
										<td>
											<ezf:inputText name="invDueDt_A1" ezfName="invDueDt_A1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('invDueDt_A1', 4);" >
											-
											<ezf:inputText name="invDueDt_A2" ezfName="invDueDt_A2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('invDueDt_A2', 4);" >
										</td>

										<td class="stab">Contract</td>
										<td><ezf:inputText name="dsContrNum_AH" ezfName="dsContrNum_AH" value="XXXX1XXXX2XXXX3XXXX412" otherAttr=" size=\"15\" maxlength=\"22\" tabindex=\"1502\" ezftoupper=\"\""/></td>
										<td class="stab">Serial</td>
										<td><ezf:inputText name="serNum_AH" ezfName="serNum_AH" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\""/></td>

										<td></td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" otherAttr=" tabindex=\"3302\""/>Include Current</td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" otherAttr=" tabindex=\"3302\""/>Include Closed</td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_A3" ezfName="xxChkBox_A3" value="Y" otherAttr=" tabindex=\"3302\""/>Include Disputes</td>
										<td><ezf:inputButton name="Click_TransactionSearch" value="Search" htmlClass="pBtn5" /></td>
									</tr>
								</table>
								
								<!-- Pageing Start-->
								<table width="100%">
									<tr align="right">
										<td align="left" width="180">
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td align="left" class="stab" width="90">Transaction#(*)</td>
										<td align="left" width="128">
											<ezf:inputText name="arCustRefNum_AH" ezfName="arCustRefNum_AH" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX7" otherAttr=" size=\"15\" maxlength=\"35\" ezftoupper=\"\""/>
										</td>
										<td align="left" class="stab" width="70">Bill Number(*)</td>
										<td align="left" width="128">
											<ezf:inputText name="grpInvNum_AH" ezfName="grpInvNum_AH" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX7" otherAttr=" size=\"15\" maxlength=\"35\" ezftoupper=\"\""/>
										</td>
										<td align="left" class="stab" width="72">PO Number(*)</td>
										<td align="left" width="128">
											<ezf:inputText name="custIssPoNum_AH" ezfName="custIssPoNum_AH" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX7" otherAttr=" size=\"15\" maxlength=\"35\""/>
										</td>
										<td>
											<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10"> 
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
													<td class="stab">to</td>
													<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
													<td class="stab">of</td>
													<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
												</tr>
											</table>
											</ezf:skip>
											<%-- Pagenation --%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_AH" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_AH" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_AH" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<!-- Pageing End-->
							</td>
						</tr>
					</table>

					<!-- ##Transaction Tab spread ##-->
					<!--<table border="1" bordercolor ="orange" >-->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="99%" align="center" >
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
										<div id="leftTbl" style="float:left; display:block; height:151px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div> <!-- left table -->
									
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="2100px" style="margin-right:20px">
												<col width="30">                <!--Check Box-->
												<col width="130">               <!--Trxaction#-->
												<col width="100">               <!--Trx Type-->
												<col width="100">               <!--ConBill#-->
												<col width="100">               <!--Trx/Bill Date-->
												<col width="70">                <!--Account#-->
												<col width="70">                <!--Bill To-->
												<col width="150">               <!--Contact Name-->
												<col width="110">               <!--Contact Number-->
												<col width="170">               <!--Email Address-->
												<col width="70">                <!--Ship To-->
												<col width="150">               <!--Ship Name-->
												<col width="100" align="right"> <!--Line Amt-->
												<col width="100" align="right"> <!--Freight Amt-->
												<col width="100" align="right"> <!--Tax Amt-->
												<col width="100" align="right"> <!--Additional Amt-->
												<col width="100" align="right"> <!--Inv Amt-->
												<col width="100" align="right"> <!--Bal Amt-->
												<col width="100">               <!--Due Date-->
												<col width="100" align="right"> <!--Days Past Due-->
												<col width="50">                <!--CCY-->
												<col width="100">               <!--CI Number-->
												<col width="100">               <!--CI Status-->
												<col width="100" align="center"><!--Dispute Date-->
												<col width="100" align="right"> <!--Dispute Amt-->
												<col width="100" align="right"> <!--Promise Amt-->
												<col width="100" align="right"> <!--Broken Promise Amt-->
												<col width="100">               <!--Contract Number-->
												<col width="100" align="right"> <!--Late Fees-->
												<col width="100">               <!--Bill Period From-->
												<col width="100">               <!--Bill Period To-->
												<col width="100">               <!--PO Number-->
												<col width="120" align="center"><!--Lease PO Number-->
												<col width="130" align="center"><!--Comments-->
												<col width="70"  align="right"> <!--Installment-->
												<col width="100" align="center"><!--Class-->
												<col width="150" align="center"><!--Type-->
												<col width="150" align="center"><!--Line Type-->
												<col width="100" align="center"><!--Order#-->
												<col width="100" align="center"><!--GL Date-->
												<col width="130" align="center"><!--Payment Term-->
												<col width="140" align="center"><!--Salesrep-->
												<col width="50"  align="center"><!--Branch-->
												<col width="100" align="center"><!--Original Invoice-->
												<col width="100">               <!--Reference#-->
                          						<tr height="33">
													<td class="pClothBs colFix" align="center" id="AH0">&nbsp;</td>
													<td class="pClothBs" align="center" id="AH1"><a href="#" class="pSortCol" onclick="columnSort('A', 'arCustRefNum_A')">Transaction#</a><img id="sortIMG.arCustRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH2"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxTpDescTxt_A')">Trx Type</a><img id="sortIMG.arTrxTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH3"><a href="#" class="pSortCol" onclick="columnSort('A', 'grpInvNum_A')">ConBill#</a><img id="sortIMG.grpInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH4"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxDt_A')">Trx/Bill Date</a><img id="sortIMG.arTrxDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH5"><a href="#" class="pSortCol" onclick="columnSort('A', 'sellToCustCd_A')">Account#</a><img id="sortIMG.sellToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH6"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A')">Bill To</a><img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH7"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxFullNm_A')">Contact Name</a><img id="sortIMG.xxFullNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH8"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsCtacPntValTxt_A')">Contact Number</a><img id="sortIMG.dsCtacPntValTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH9"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsCtacPntValTxt_A1')">Email Address</a><img id="sortIMG.dsCtacPntValTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH10"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToCustCd_A')">Ship To</a><img id="sortIMG.shipToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH11"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToLocNm_A')">Ship Name</a><img id="sortIMG.shipToLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH12"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealNetSlsAmt_A')">Line Amt</a><img id="sortIMG.dealNetSlsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH13"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealFrtAmt_A')">Freight Amt</a><img id="sortIMG.dealFrtAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH14"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealTaxAmt_A')">Tax Amt</a><img id="sortIMG.dealTaxAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH15"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgPrcDealAmt_A')">Additional Amt</a><img id="sortIMG.addlChrgPrcDealAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH16"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealOrigGrsAmt_A')">Inv Amt</a><img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH17"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A')">Bal Amt</a><img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH18"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A')">Due Date</a><img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH19"><a href="#" class="pSortCol" onclick="columnSort('A', 'daysPastDueAot_A')">Days Past Due</a><img id="sortIMG.daysPastDueAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH20"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCcyCd_A')">CCY</a><img id="sortIMG.dealCcyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH21"><a href="#" class="pSortCol" onclick="columnSort('A', 'custCareTktNum_A')">CI Number</a><img id="sortIMG.custCareTktNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH22"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem130Txt_A')">CI Status</a><img id="sortIMG.xxScrItem130Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH23"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltDsptDt_A')">Dispute Date</a><img id="sortIMG.cltDsptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH24"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCltDsptAmt_A')">Dispute Amt</a><img id="sortIMG.dealCltDsptAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH25"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCltPrmsAmt_A1')">Promise Amt</a><img id="sortIMG.dealCltPrmsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH26"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCltPrmsAmt_A')">Broken Promise<br>Amt</a><img id="sortIMG.dealCltPrmsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH27"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract Number</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH28"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLateFeeAmt_A')">Late Fees</a><img id="sortIMG.arLateFeeAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH29"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxBillFromDt_A')">Bill Period From</a><img id="sortIMG.arTrxBillFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH30"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxBillThruDt_A')">Bill Period To</a><img id="sortIMG.arTrxBillThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH31"><a href="#" class="pSortCol" onclick="columnSort('A', 'custIssPoNum_A')">PO Number</a><img id="sortIMG.custIssPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH32"><a href="#" class="pSortCol" onclick="columnSort('A', 'leaseCmpyPoNum_A')">Lease PO Number</a><img id="sortIMG.leaseCmpyPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH33"><a href="#" class="pSortCol" onclick="columnSort('A', 'invFirstCmntTxt_A')">Comments</a><img id="sortIMG.invFirstCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH34"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxInvdCnt_A')">Installment</a><img id="sortIMG.xxInvdCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH35"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpNm_A')">Class</a><img id="sortIMG.invTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH36"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTpNm_A')">Type</a><img id="sortIMG.dsInvTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH37"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvChrgTpRptTxt_A')">Line Type</a><img id="sortIMG.svcInvChrgTpRptTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH38"><a href="#" class="pSortCol" onclick="columnSort('A', 'cpoOrdNum_A')">Order#</a><img id="sortIMG.cpoOrdNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH39"><a href="#" class="pSortCol" onclick="columnSort('A', 'glDt_A')">GL Date</a><img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH40"><a href="#" class="pSortCol" onclick="columnSort('A', 'pmtTermCashDiscDescTxt_A')">Payment Term</a><img id="sortIMG.pmtTermCashDiscDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH41"><a href="#" class="pSortCol" onclick="columnSort('A', 'tocNm_A')">Salesrep</a><img id="sortIMG.tocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH42"><a href="#" class="pSortCol" onclick="columnSort('A', 'coaBrCd_A')">Branch</a><img id="sortIMG.coaBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH43"><a href="#" class="pSortCol" onclick="columnSort('A', 'origInvNum_A')">Original Invoice</a><img id="sortIMG.origInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="AH44"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxNum_A')">Reference#</a><img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
												
										<div id="rightTbl" style="width:1118px; height:168px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2100px">
												<col width="30">                <!--Check Box-->
												<col width="130">               <!--Trxaction#-->
												<col width="100">               <!--Trx Type-->
												<col width="100">               <!--ConBill#-->
												<col width="100">               <!--Trx/Bill Date-->
												<col width="70">                <!--Account#-->
												<col width="70">                <!--Bill To-->
												<col width="150">               <!--Contact Name-->
												<col width="110">               <!--Contact Number-->
												<col width="170">               <!--Email Address-->
												<col width="70">                <!--Ship To-->
												<col width="150">               <!--Ship Name-->
												<col width="100" align="right"> <!--Line Amt-->
												<col width="100" align="right"> <!--Freight Amt-->
												<col width="100" align="right"> <!--Tax Amt-->
												<col width="100" align="right"> <!--Additional Amt-->
												<col width="100" align="right"> <!--Inv Amt-->
												<col width="100" align="right"> <!--Bal Amt-->
												<col width="100">               <!--Due Date-->
												<col width="100" align="right"> <!--Days Past Due-->
												<col width="50">                <!--CCY-->
												<col width="100">               <!--CI Number-->
												<col width="100">               <!--CI Status-->
												<col width="100" align="center"><!--Dispute Date-->
												<col width="100" align="right"> <!--Dispute Amt-->
												<col width="100" align="right"> <!--Promise Amt-->
												<col width="100" align="right"> <!--Broken Promise Amt-->
												<col width="100">               <!--Contract Number-->
												<col width="100" align="right"> <!--Late Fees-->
												<col width="100">               <!--Bill Period From-->
												<col width="100">               <!--Bill Period To-->
												<col width="100">               <!--PO Number-->
												<col width="120" align="center"><!--Lease PO Number-->
												<col width="130" align="center"><!--Comments-->
												<col width="70"  align="right"> <!--Installment-->
												<col width="100" align="center"><!--Class-->
												<col width="150" align="center"><!--Type-->
												<col width="150" align="center"><!--Line Type-->
												<col width="100" align="center"><!--Order#-->
												<col width="100" align="center"><!--GL Date-->
												<col width="130" align="center"><!--Payment Term-->
												<col width="140" align="center"><!--Salesrep-->
												<col width="50"  align="center"><!--Branch-->
												<col width="100" align="center"> <!--Original Invoice-->
												<col width="100">               <!--Reference#-->
												<tbody>
												<% int aIdx = 0; %>
												<ezf:row ezfHyo="A">
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(aIdx).eipRptRqstPk_A)) { %>
													<td><ezf:anchor name="xxLinkProt_A" ezfName="xxLinkProt_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPLateFee" otherAttr=" ezfanchortext=\"\""><ezf:label name="arCustRefNum_A" ezfName="arCustRefNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<% } else { %>
													<td><ezf:anchor name="xxLinkProt_A" ezfName="xxLinkProt_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_TransactionDetail" otherAttr=" ezfanchortext=\"\""><ezf:label name="arCustRefNum_A" ezfName="arCustRefNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<% } %>
													<td><ezf:label name="arTrxTpDescTxt_A" ezfName="arTrxTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="grpInvNum_A" ezfName="grpInvNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="arTrxDt_A" ezfName="arTrxDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="sellToCustCd_A" ezfName="sellToCustCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="billToCustCd_A" ezfName="billToCustCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="xxFullNm_A" ezfName="xxFullNm_A" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dsCtacPntValTxt_A" ezfName="dsCtacPntValTxt_A" value="999-999-9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dsCtacPntValTxt_A1" ezfName="dsCtacPntValTxt_A1" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="shipToCustCd_A" ezfName="shipToCustCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:inputText name="shipToLocNm_A" ezfName="shipToLocNm_A" value="AMAZON.COM_XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shipToLocNm_A#{EZF_ROW_NUMBER}\" size=\"18\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
													<td><ezf:label name="dealNetSlsAmt_A" ezfName="dealNetSlsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealFrtAmt_A" ezfName="dealFrtAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealTaxAmt_A" ezfName="dealTaxAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="addlChrgPrcDealAmt_A" ezfName="addlChrgPrcDealAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invDueDt_A" ezfName="invDueDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="daysPastDueAot_A" ezfName="daysPastDueAot_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealCcyCd_A" ezfName="dealCcyCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="custCareTktNum_A" ezfName="custCareTktNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="xxScrItem130Txt_A" ezfName="xxScrItem130Txt_A" value="TICKET STATUS 001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="cltDsptDt_A" ezfName="cltDsptDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealCltDsptAmt_A" ezfName="dealCltDsptAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealCltPrmsAmt_A1" ezfName="dealCltPrmsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealCltPrmsAmt_A" ezfName="dealCltPrmsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="arLateFeeAmt_A" ezfName="arLateFeeAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="arTrxBillFromDt_A" ezfName="arTrxBillFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="arTrxBillThruDt_A" ezfName="arTrxBillThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="custIssPoNum_A" ezfName="custIssPoNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="leaseCmpyPoNum_A" ezfName="leaseCmpyPoNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="invFirstCmntTxt_A" ezfName="invFirstCmntTxt_A" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"18\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="xxInvdCnt_A" ezfName="xxInvdCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="invTpNm_A" ezfName="invTpNm_A" value="0--------1-----" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"15\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dsInvTpNm_A" ezfName="dsInvTpNm_A" value="0--------1---------2-----" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"25\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="svcInvChrgTpRptTxt_A" ezfName="svcInvChrgTpRptTxt_A" value="0--------1---------2-----" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"47\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="glDt_A" ezfName="glDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="pmtTermCashDiscDescTxt_A" ezfName="pmtTermCashDiscDescTxt_A" value="0--------1---------2---------3---------4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"40\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="tocNm_A" ezfName="tocNm_A" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="coaBrCd_A" ezfName="coaBrCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="origInvNum_A" ezfName="origInvNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="arTrxNum_A" ezfName="arTrxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
												<% aIdx++; %>
                   								</ezf:row>
                   								<ezf:skip>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" value="Y" ezfhyo="A" /></td>
													<td><a href="#"  ezfhyo="A" ezfname="xxLinkProt_A" name="xxLinkProt_A" onclick="sendServer('Click_TransactionDetail')" ezfanchortext><label ezfout name="arCustRefNum_A" ezfname="arCustRefNum_A" ezfhyo="A">1234567</label></a></td>
													<td><label ezfout name="arTrxTpDescTxt_A"    ezfname="arTrxTpDescTxt_A"    ezfhyo="A">INVOICE</label></td>
													<td><label ezfout name="grpInvNum_A"         ezfname="grpInvNum_A"         ezfhyo="A">12345678</label></td>
													<td><label ezfout name="arTrxDt_A"           ezfname="arTrxDt_A"           ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="sellToCustCd_A"      ezfname="sellToCustCd_A"      ezfhyo="A">23177</label></td>
													<td><label ezfout name="billToCustCd_A"      ezfname="billToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><input type="text" class="pPro" size="20" name="xxFullNm_A" ezfhyo="A"         ezfname="xxFullNm_A"         readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><input type="text" class="pPro" size="14" name="dsCtacPntValTxt_A" ezfhyo="A"  ezfname="dsCtacPntValTxt_A"  readOnly style="border:none; background-color:transparent;" value="999-999-9999"></td>
													<td><input type="text" class="pPro" size="24" name="dsCtacPntValTxt_A1" ezfhyo="A" ezfname="dsCtacPntValTxt_A1" readOnly style="border:none; background-color:transparent;" value="0--------1---------2---------3---------4---------5---------6"></td>
													<td><label ezfout name="shipToCustCd_A"      ezfname="shipToCustCd_A"      ezfhyo="A">19631</label></td>
													<td><label ezfout name="shipToLocNm_A"       ezfname="shipToLocNm_A"       ezfhyo="A">19631</label></td>
													<td><label ezfout name="dealNetSlsAmt_A"     ezfname="dealNetSlsAmt_A"     ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealFrtAmt_A"        ezfname="dealFrtAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealTaxAmt_A"        ezfname="dealTaxAmt_A"        ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="addlChrgPrcDealAmt_A" ezfname="addlChrgPrcDealAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealOrigGrsAmt_A"    ezfname="dealOrigGrsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealRmngBalGrsAmt_A" ezfname="dealRmngBalGrsAmt_A" ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="invDueDt_A"          ezfname="invDueDt_A"          ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="daysPastDueAot_A"    ezfname="daysPastDueAot_A"    ezfhyo="A">30</label></td>
													<td><label ezfout name="dealCcyCd_A"         ezfname="dealCcyCd_A"         ezfhyo="A">USD</label></td>
													<td><label ezfout name="custCareTktNum_A"    ezfname="custCareTktNum_A"    ezfhyo="A">1234567890</label></td>
													<td><input type="text" size="13" maxlength="13" value="TICKET STATUS 001" name="xxScrItem130Txt_A" ezfname="xxScrItem130Txt_A" ezfhyo="A" style="border:none; background-color:transparent;"></td>
													<td><label ezfout name="dealCltDsptAmt_A"    ezfname="dealCltDsptAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dealCltPrmsAmt_A"    ezfname="dealCltPrmsAmt_A"    ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="dsContrNum_A"        ezfname="dsContrNum_A"        ezfhyo="A">1234567890</label></td>
													<td><label ezfout name="arLateFeeAmt_A"      ezfname="arLateFeeAmt_A"      ezfhyo="A">999,999.00</label></td>
													<td><label ezfout name="arTrxBillFromDt_A"   ezfname="arTrxBillFromDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxBillThruDt_A"   ezfname="arTrxBillThruDt_A"   ezfhyo="A">01/08/2015</label></td>
													<td><label ezfout name="arTrxNum_A"      ezfname="arTrxNum_A"      ezfhyo="A">12345678</label></td>
												</tr>
												</ezf:skip>
                   								</tbody>
											</table>
										</div>
									</div> <!-- right table -->
								</div>
							</td>
						</tr>
					</table>

					<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  true);
					</script>

					<table height="30" border="0" cellpadding="1" cellspacing="0" width="99%" align="center">
						<tr>
							<td align="left">
								<ezf:inputButton name="Click_TransactionPayment" value="Payment" htmlClass="pBtn10" />
								<ezf:inputButton name="Click_TransactionActivity" value="Activity" htmlClass="pBtn10" />
								<ezf:inputButton name="Click_TransactionPromiseDispute" value="Promise/Dispute" htmlClass="pBtn10" />
							</td>
							<td class="stab" align="center">
								Bal Amt Total
								<ezf:inputText name="dealRmngBalGrsAmt_AH" ezfName="dealRmngBalGrsAmt_AH" otherAttr=" size=\"15\""/>
								<ezf:inputButton name="Click_TransactionCalc" value="Calc" htmlClass="pBtn2" />
							</td>
							<td align="right">
								<ezf:inputButton name="Click_TransactionPrintInvoice" value="Print Invoice" htmlClass="pBtn10" />
								<ezf:inputButton name="Click_TransactionCustomerCare" value="Customer Care" htmlClass="pBtn10" />
								<ezf:inputButton name="Click_TransactionPostApplies" value="Post Applies" htmlClass="pBtn10" />
								<ezf:inputButton name="Click_TransactionWriteOff" value="Write Off" htmlClass="pBtn10" />
 								&nbsp;
							</td>
						</tr>
					</table>
				</c:when>
				<%--######################################## TAB "Note" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Note'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_ON";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>

				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table bordercolor="blue" border ="0" width="1123">
						<tr height="270">
							<td style="padding: 0px 0px 0px 1px;">
								<table bordercolor="red" border ="0" width="618">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="40">
												<col width="100">
												<col width="10">
												<col width="100">
												<col width="10">
												<col width="90">
												<col width="215">
												<col width="35">
												<col width="95" align ="left">
												<tr>
													<td class="stab">Date</td>
													<td>
														<ezf:inputText name="cratDt_FF" ezfName="cratDt_FF" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_FF', 4);" >
													</td>
													<td>-</td>
													<td>
														<ezf:inputText name="cratDt_FT" ezfName="cratDt_FT" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_FT', 4);" >
													</td>
													<td></td>
													<td class="stab">Note(*)</td>
													<td>
														<ezf:inputText name="xxMlBodyTxt_FS" ezfName="xxMlBodyTxt_FS" value="XXXXXXXX" otherAttr=" size=\"25\" maxlength=\"512\""/>
													</td>
													<td class="stab">Att<ezf:inputCheckBox name="xxChkBox_FH" ezfName="xxChkBox_FH" value="Y" /></td>
													<td><ezf:inputButton name="Click_NoteSearch" value="Search" htmlClass="pBtn8" /></td>
												</tr>
											</table>

											<!-- Pageing Start-->
											<table width="100%" border=0 bordercolor="green">
												<col width="60">
												<col width="120">
												<col>
												<tr>
													<td class="stab">Note Type</td>
													<td class="stab">
														<ezf:select name="cltNoteTpCd_FS" ezfName="cltNoteTpCd_FS" ezfBlank="1" ezfCodeName="cltNoteTpCd_LC" ezfDispName="cltNoteTpDescTxt_LD" otherAttr=" style=\"width:180px;\""/>
													</td>
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
															<col>
															<col width="1">
															<col>
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
																<td class="stab">to</td>
																<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
																<td class="stab">of</td>
																<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
															</tr>
														</table>
														</ezf:skip>
														<%-- Pagenation --%>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="F" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_FH" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_FH" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_FH" />
														</jsp:include>
													</td>
												</tr>
											</table>
											<!-- Pageing End-->
										</td>
									</tr>
								</table>
								
								<!-- ##Note Tab spread ##-->
								<div id="RightTBL_F_Top" 
									style="overflow-y:hidden; height:; overflow-x:hidden; width:627; table-layout:fixed" 
									onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL_F' ) );">
									<table width="610" border="1" cellpadding="1" cellspacing="0">
										<col width="75">
										<col width="140">
										<col width="105">
										<col width="170">
										<col width="95">
										<tr>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'cltNoteDtlPk_FD' )">#<img id="sortIMG.cltNoteDtlPk_FD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxSortKeyTxt_FD' )">Date<img id="sortIMG.xxSortKeyTxt_FD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'cltNoteTpDescTxt_FD' )">Note Type<img id="sortIMG.cltNoteTpDescTxt_FD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxMlBodyTxt_FD' )">Note<img id="sortIMG.xxMlBodyTxt_FD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxPsnNm_FD' )">Created By<img id="sortIMG.xxPsnNm_FD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>
								<div id="RightTBL_F"
									style="overflow-y:scroll; height:180; overflow-x:scroll; width:627"
									onScroll="synchroScroll( this.id, new Array( 'RightTBL_F_Top' ) );">
									<table width="610" border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;" id="F">
										<col width="75">
										<col width="140">
										<col width="105">
										<col width="170">
										<col width="95">
										<ezf:row ezfHyo="F">
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:anchor name="Click_NoteDetail" ezfName="Click_NoteDetail" ezfHyo="F" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_NoteDetail" ><ezf:label name="cltNoteDtlPk_FD" ezfName="cltNoteDtlPk_FD" ezfHyo="F" ezfArrayNo="0" /></ezf:anchor></td>
											<td><ezf:inputText name="cratTsDplyTxt_FD" ezfName="cratTsDplyTxt_FD" value="02/13/2015 12:00:00" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltNoteTpDescTxt_FD" ezfName="cltNoteTpDescTxt_FD" value="AAA" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="xxMlBodyTxt_FD" ezfName="xxMlBodyTxt_FD" value="AAA" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="xxPsnNm_FD" ezfName="xxPsnNm_FD" value="Yosuke Suga" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" tabindex=\"1519\" ezftoupper=\"\""/></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="100" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="F" ezfname="Click_NoteDetail" name="Click_NoteDetail" onclick="sendServer('Click_NoteDetail')"><label ezfout name="cltNoteDtlPk_FD" ezfname="cltNoteDtlPk_FD" ezfhyo="F">23177</label></a></td>
											<td><input type="text" size="19" maxlength="19" value="02/13/2015 12:00:00" name="cratTsDplyTxt_FD" ezfname="cratTsDplyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="14" maxlength="100" value="AAA" name="cltNoteTpDescTxt_FD" ezfname="cltNoteTpDescTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="23" maxlength="2000" value="AAA" name="xxMlBodyTxt_FD" ezfname="xxMlBodyTxt_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
											<td><input type="text" size="15" maxlength="60" value="Yosuke Suga" name="xxPsnNm_FD" ezfname="xxPsnNm_FD" tabindex="1519" ezftoupper ezfhyo="F"></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
							<td>
								<table width="420">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="50">
												<col width="170">
												<col width="50">
												<col width="150">
												<tr>
													<td class="stab">Note #</td>
													<td><ezf:inputText name="cltNoteDtlPk_FH" ezfName="cltNoteDtlPk_FH" otherAttr=" size=\"23\""/></td>
													<td class="stab">Date</td>
													<td><ezf:inputText name="cratTsDplyTxt_FH" ezfName="cratTsDplyTxt_FH" otherAttr=" size=\"19\" maxlength=\"19\""/></td>
												</tr>
												<tr>
													<td class="stab">Note Type</td>
													<td colspan="3"><ezf:select name="cltNoteTpCd_FH" ezfName="cltNoteTpCd_FH" ezfBlank="1" ezfCodeName="cltNoteTpCd_LC" ezfDispName="cltNoteTpDescTxt_LD" otherAttr=" style=\"width:180px;\""/></td>
												</tr>
												<tr>
													<td colspan="4"><ezf:textArea name="xxMlBodyTxt_FH" ezfName="xxMlBodyTxt_FH" otherAttr=" rows=\"16\" cols=\"60\" maxlength=\"65536\""/></td>
												</tr>
												<tr>
													<td  colspan="4" align ="right">
													    <ezf:inputText name="xxYesNoCd_FH" ezfName="xxYesNoCd_FH" value="0" otherAttr=" style=\"visibility:hidden;\" ezftoupper=\"\""/>
													    <ezf:inputButton name="Click_NoteAttachment" value="Attachment" htmlClass="pBtn7" />
													    <ezf:inputButton name="Click_NoteNew" value="New" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<script type="text/javascript" defer>
					window.attachEvent('onload', function() {
						document.getElementsByName("xxMlBodyTxt_FH")[0].value = document.getElementsByName("xxMlBodyTxt_FH")[0].value.replace(/\u00a0/g, ' ');
					});
				</script>
				</c:when>
				<%--######################################## TAB "Task" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Task'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_ON";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>
				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table border="0" bordercolor="blue" width="1123">
						<tr height="270">
							<td style="padding: 0px 0px 0px 1px;">
								<table bordercolor="red" border ="0" width="618">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="40">
												<col width="100">
												<col width="10">
												<col width="100">
												<col width="25">
												<col width="70">
												<col width="205">
												<col width="50">
												<col width="95" align ="left">
												<tr>
													<td class="stab">Date</td>
													<td>
														<ezf:inputText name="cltTaskRwsdDt_GF" ezfName="cltTaskRwsdDt_GF" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltTaskRwsdDt_GF', 4);" >
													</td>
													<td>-</td>
													<td>
														<ezf:inputText name="cltTaskRwsdDt_GT" ezfName="cltTaskRwsdDt_GT" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltTaskRwsdDt_GT', 4);" >
													</td>
													<td></td>
													<td class="stab">Name(*)</td>
													<td>
														<ezf:inputText name="cltTaskSubjTxt_GS" ezfName="cltTaskSubjTxt_GS" value="XXXXXXXX" otherAttr=" size=\"25\" maxlength=\"100\" ezftoupper=\"\""/>
													</td>
													<td class="stab">&nbsp;</td>
													<td><ezf:inputButton name="Click_TaskSearch" value="Search" htmlClass="pBtn8" /></td>
												</tr>
											</table>

										    <!-- Pageing Start-->
											<table width="100%">
												<col width="40">
												<col width="120">
												<tr>
													<td class="stab">Type</td>
													<td>
														<ezf:select name="cltTaskTpCd_GS" ezfName="cltTaskTpCd_GS" ezfBlank="1" ezfCodeName="cltTaskTpCd_LC" ezfDispName="cltTaskTpDescTxt_LD" otherAttr=" style=\"width:140px;\""/>
													</td>
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
															<col>
															<col width="1">
															<col>
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
																<td class="stab">to</td>
																<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
																<td class="stab">of</td>
																<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
															</tr>
														</table>
														</ezf:skip>
														<%-- Pagenation --%>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="G" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_GH" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_GH" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_GH" />
														</jsp:include>
													</td>
												</tr>
											</table>
											<!-- Pageing End-->
										</td>
									</tr>
								</table>
								
								<!-- ##Task Tab spread ##-->
								<div id="RightTBLG_Top" 
									style="overflow-y:hidden; height:; overflow-x:hidden; width:627; table-layout:fixed" 
									onScroll="synchroScrollLeft( this.id, new Array( 'RightTBLG' ) );">
									<table width="610" border="1" cellpadding="1" cellspacing="0">
										<col width="50">
										<col width="80">
										<col width="85">
										<col width="80">
										<col width="80">
										<col width="80">
										<col width="120">
										<tr>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskPk_GD' )">Task#<img id="sortIMG.cltTaskPk_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskRwsdDt_GD' )">Date<img id="sortIMG.cltTaskRwsdDt_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskSubjTxt_GD' )">Name<img id="sortIMG.cltTaskSubjTxt_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskTpDescTxt_GD' )">Type<img id="sortIMG.cltTaskTpDescTxt_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskStsDescTxt_GD' )">Status<img id="sortIMG.cltTaskStsDescTxt_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltTaskPrtyDescTxt_GD' )">Priority<img id="sortIMG.cltTaskPrtyDescTxt_GD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cltPsnNm_G3' )">Owned By<img id="sortIMG.cltPsnNm_G3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>

								<div id="RightTBLG"
									style="overflow-y:scroll; height:180; overflow-x:scroll; width:627; table-layout:fixed"
									onScroll="synchroScrollLeft( this.id, new Array( 'RightTBLG_Top' ) );">
									<table width="610" border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;" id="G">
										<col width="50">
										<col width="80">
										<col width="85">
										<col width="80">
										<col width="80">
										<col width="80">
										<col width="120">
										<col>
										<ezf:row ezfHyo="G">
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:anchor name="Click_TaskDetail" ezfName="Click_TaskDetail" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_TaskDetail" ><ezf:label name="cltTaskPk_GD" ezfName="cltTaskPk_GD" ezfHyo="G" ezfArrayNo="0" /></ezf:anchor></td>
											<td><ezf:inputText name="cltTaskRwsdDt_GD" ezfName="cltTaskRwsdDt_GD" value="06/17/2016" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltTaskSubjTxt_GD" ezfName="cltTaskSubjTxt_GD" value="1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"100\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltTaskTpDescTxt_GD" ezfName="cltTaskTpDescTxt_GD" value="Test" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"4000\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltTaskStsDescTxt_GD" ezfName="cltTaskStsDescTxt_GD" value="Callback" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltTaskPrtyDescTxt_GD" ezfName="cltTaskPrtyDescTxt_GD" value="Open" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" tabindex=\"1519\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="cltPsnNm_G3" ezfName="cltPsnNm_G3" value="Yosuke Suga" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\" tabindex=\"1519\" ezftoupper=\"\""/></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="G" ezfname="Click_TaskDetail" name="Click_TaskDetail" onclick="sendServer('Click_TaskDetail')"><label ezfout name="cltTaskPk_GD" ezfname="cltTaskPk_GD" ezfhyo="G">23177</label></a></td>
											<td><input type="text" size="10" maxlength="10" value="06/17/2016" name="cltTaskRwsdDt_GD" ezfname="cltTaskRwsdDt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="11" maxlength="100" value="1" name="cltTaskSubjTxt_GD" ezfname="cltTaskSubjTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="4000" value="Test" name="cltTaskTpDescTxt_GD" ezfname="cltTaskTpDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Callback" name="cltTaskStsDescTxt_GD" ezfname="cltTaskStsDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Open" name="cltTaskPrtyDescTxt_GD" ezfname="cltTaskPrtyDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="16" maxlength="60" value="Yosuke Suga" name="cltPsnNm_G3" ezfname="cltPsnNm_G3" tabindex="1519" ezftoupper ezfhyo="G"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="G" ezfname="Click_TaskDetail" name="Click_TaskDetail" onclick="sendServer('Click_TaskDetail')"><label ezfout name="cltTaskPk_GD" ezfname="cltTaskPk_GD" ezfhyo="G">23177</label></a></td>
											<td><input type="text" size="10" maxlength="10" value="06/17/2016" name="cltTaskRwsdDt_GD" ezfname="cltTaskRwsdDt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="11" maxlength="100" value="1" name="cltTaskSubjTxt_GD" ezfname="cltTaskSubjTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="4000" value="Test" name="cltTaskTpDescTxt_GD" ezfname="cltTaskTpDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Callback" name="cltTaskStsDescTxt_GD" ezfname="cltTaskStsDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Open" name="cltTaskPrtyDescTxt_GD" ezfname="cltTaskPrtyDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="16" maxlength="60" value="Yosuke Suga" name="cltPsnNm_G3" ezfname="cltPsnNm_G3" tabindex="1519" ezftoupper ezfhyo="G"></td>
										</tr>
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><a href="#"  ezfhyo="G" ezfname="Click_TaskDetail" name="Click_TaskDetail" onclick="sendServer('Click_TaskDetail')"><label ezfout name="cltTaskPk_GD" ezfname="cltTaskPk_GD" ezfhyo="G">23177</label></a></td>
											<td><input type="text" size="10" maxlength="10" value="06/17/2016" name="cltTaskRwsdDt_GD" ezfname="cltTaskRwsdDt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="11" maxlength="100" value="1" name="cltTaskSubjTxt_GD" ezfname="cltTaskSubjTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="4000" value="Test" name="cltTaskTpDescTxt_GD" ezfname="cltTaskTpDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Callback" name="cltTaskStsDescTxt_GD" ezfname="cltTaskStsDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="10" maxlength="50" value="Open" name="cltTaskPrtyDescTxt_GD" ezfname="cltTaskPrtyDescTxt_GD" tabindex="1519" ezftoupper ezfhyo="G"></td>
											<td><input type="text" size="16" maxlength="60" value="Yosuke Suga" name="cltPsnNm_G3" ezfname="cltPsnNm_G3" tabindex="1519" ezftoupper ezfhyo="G"></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
							<td>
								<table width="420">
									<tr>
										<td>
											<table border="0" bordercolor="green" cellpadding="1" cellspacing="0">
												<col width="70">
												<col width="150">
												<col width="70">
												<col width="150">
												<tr>
													<td class="stab">Type</td>
													<td>
														<ezf:select name="cltTaskTpCd_GH" ezfName="cltTaskTpCd_GH" ezfBlank="1" ezfCodeName="cltTaskTpCd_LC" ezfDispName="cltTaskTpDescTxt_LD" otherAttr=" style=\"width:140px;\""/>
													</td>
													<td class="stab">Status</td>
													<td>
														<ezf:select name="cltTaskStsCd_GH" ezfName="cltTaskStsCd_GH" ezfBlank="1" ezfCodeName="cltTaskStsCd_LC" ezfDispName="cltTaskStsDescTxt_LD" otherAttr=" style=\"width:140px;\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Name</td>
													<td colspan="3"><ezf:inputText name="cltTaskSubjTxt_GH" ezfName="cltTaskSubjTxt_GH" value="Yosuke Sug" otherAttr=" size=\"51\" maxlength=\"100\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_TaskOwner" >Owner</ezf:anchor></td>
													<td colspan="1"><ezf:inputText name="cltTaskOwnrId_GH" ezfName="cltTaskOwnrId_GH" value="Yosuke Sug" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
            														<ezf:inputButton name="Click_TaskSetOwnerName" value=">>" htmlClass="pBtn1" /></td>
													<td colspan="2"><ezf:inputText name="cltPsnNm_G1" ezfName="cltPsnNm_G1" value="Yosuke Sug" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Created By</td>
													<td colspan="1"><ezf:inputText name="cratUsrId_GH" ezfName="cratUsrId_GH" value="Yosuke Sug" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td colspan="2"><ezf:inputText name="xxPsnNm_G2" ezfName="xxPsnNm_G2" value="Yosuke Sug" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Updated By</td>
													<td colspan="1"><ezf:inputText name="updUsrId_GH" ezfName="updUsrId_GH" value="Yosuke Sug" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td colspan="2"><ezf:inputText name="xxPsnNm_G5" ezfName="xxPsnNm_G5" value="Yosuke Sug" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Priority</td>
													<td>
														<ezf:select name="cltTaskPrtyCd_GH" ezfName="cltTaskPrtyCd_GH" ezfBlank="1" ezfCodeName="cltTaskPrtyCd_LC" ezfDispName="cltTaskPrtyDescTxt_LD" otherAttr=" style=\"width:140px;\""/></td>
													<td class="stab">Update Date</td>
													<td align="left"><ezf:inputText name="cltTaskUpdDt_GH" ezfName="cltTaskUpdDt_GH" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Start Date</td>
													<td><ezf:inputText name="cltTaskRwsdDt_GH" ezfName="cltTaskRwsdDt_GH" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltTaskRwsdDt_GH', 4);" ></td>
													<td class="stab">End Date</td>
													<td><ezf:inputText name="cltTaskRwedDt_GH" ezfName="cltTaskRwedDt_GH" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltTaskRwedDt_GH', 4);" ></td>
												</tr>
												<tr>
													<td class="stab">Ctac Name</td>
													<td><ezf:inputText name="cltTaskCtacNm_GH" ezfName="cltTaskCtacNm_GH" value="XXXX1XXXX2XXX" otherAttr=" size=\"18\" maxlength=\"28\" ezftoupper=\"\""/></td>
													<td class="stab">Ctac Num</td>
													<td align="left"><ezf:inputText name="cltTaskCtacPhoNum_GH" ezfName="cltTaskCtacPhoNum_GH" value="XXXX1XXXX2XXX" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td colspan="4"><ezf:textArea name="cltTaskDescTxt_GH" ezfName="cltTaskDescTxt_GH" otherAttr=" rows=\"7\" cols=\"60\""/></td>
												</tr>
												<tr>
													<td colspan="4" align="right">
													    <ezf:inputText name="xxYesNoCd_GH" ezfName="xxYesNoCd_GH" value="0" otherAttr=" style=\"visibility:hidden;\" ezftoupper=\"\""/>
													    <ezf:inputButton name="Click_TaskNew" value="New" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<script type="text/javascript" defer>
					window.attachEvent('onload', function() {
						document.getElementsByName("cltTaskDescTxt_GH")[0].value = document.getElementsByName("cltTaskDescTxt_GH")[0].value.replace(/\u00a0/g, ' ');
					});
				</script>
				</c:when>
				<%--######################################## TAB "Contract" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Contract'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_ON";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>

				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table border ="0" bordercolor="blue" width="1123">
						<tr>
							<td>
								<table border="0" bordercolor="blue" cellpadding="1" cellspacing="0">
									<col width="35">
									<col width="120">
									<col width="60">
									<col>
									<tr>
										<td class="stab" >Class</td>
										<td><ezf:select name="dsContrTpCd_BH" ezfName="dsContrTpCd_BH" ezfBlank="1" ezfCodeName="dsContrTpCd_LC" ezfDispName="dsContrTpDescTxt_LD" otherAttr=" style=\"width:200px;\""/></td>
										<td><ezf:inputButton name="Click_ContractSearch" value="Search" htmlClass="pBtn8" /></td>
										<td></td>
									</tr>
								</table>
								
								<!-- Pageing Start-->
								<table width="100%">
									<tr align="right">
										<td align="left" width="300">
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td>
											<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10"> 
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
													<td class="stab">to</td>
													<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
													<td class="stab">of</td>
													<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
												</tr>
											</table>
											</ezf:skip>
											<%-- Pagenation --%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="B" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_BH" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_BH" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_BH" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<!-- Pageing End-->
							</td>
						</tr>
					</table>

					<!-- ##Contract Tab spread ##-->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="99%" align="center" >
						<tr>
							<td>
								<div id="parentBoxB">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
										<div id="leftTbl" style="float:left; display:block; height:183px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div> <!-- left table -->
									
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="BHEAD" width="1143px" style="margin-right:20px">
												<col width="110">
												<col width="100">
												<col width="100">
												<col width="40">
												<col width="90">
												<col width="90">
												<col width="90">
												<col width="90">
												<col width="90">
												<col width="110">
												<col width="140">
												<col width="70">
												<col width="110">
												<col width="140">
												<col width="140">
												<tr height="25">
													<td class="pClothBs" align="center" id="BH0" ><a href="#" class="pSortCol" onclick="columnSort('B', 'dsContrNum_BD')">Contract Number</a><img id="sortIMG.dsContrNum_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH1" ><a href="#" class="pSortCol" onclick=""                                >Modifier</a></td>
													<td class="pClothBs" align="center" id="BH2" ><a href="#" class="pSortCol" onclick="columnSort('B', 'svcContrRefCmntTxt_BD')">Description</a><img id="sortIMG.svcContrRefCmntTxt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH3" ><a href="#" class="pSortCol" onclick="columnSort('B', 'ccyCd_BD')">CCY</a><img id="sortIMG.ccyCd_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH4" ><a href="#" class="pSortCol" onclick="columnSort('B', 'contrVrsnEffFromDt_BD')">Start Date</a><img id="sortIMG.contrVrsnEffFromDt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH5" ><a href="#" class="pSortCol" onclick="columnSort('B', 'contrVrsnEffThruDt_BD')">End Date</a><img id="sortIMG.contrVrsnEffThruDt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH6" ><a href="#" class="pSortCol" onclick="columnSort('B', 'contrCloDt_BD')">Terminated</a><img id="sortIMG.contrCloDt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH7" ><a href="#" class="pSortCol" onclick="columnSort('B', 'rnwEffFromDt_BD')">Renewed</a><img id="sortIMG.rnwEffFromDt_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH8" ><a href="#" class="pSortCol" onclick="columnSort('B', 'xxDt10Dt_B1')">Cancelled</a><img id="sortIMG.xxDt10Dt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH9" ><a href="#" class="pSortCol" onclick="columnSort('B', 'xxDt10Dt_B2')">Hold Creation Date</a><img id="sortIMG.xxDt10Dt_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH10"><a href="#" class="pSortCol" onclick="columnSort('B', 'stsMemoTxt_B1')">Hold Reason</a><img id="sortIMG.stsMemoTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH11"><a href="#" class="pSortCol" onclick="columnSort('B', 'contrHldFlg_BD')">Hold Flag</a><img id="sortIMG.contrHldFlg_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH12"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxDt10Dt_B3')">Hold Release Date</a><img id="sortIMG.xxDt10Dt_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH13"><a href="#" class="pSortCol" onclick="columnSort('B', 'stsMemoUpdFullPsnNm_BD')">Release By</a><img id="sortIMG.stsMemoUpdFullPsnNm_BD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="BH14"><a href="#" class="pSortCol" onclick="columnSort('B', 'stsMemoTxt_B2')">Hold Release</a><img id="sortIMG.stsMemoTxt_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1118px; height:200px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B" width="1143px">
												<col width="110">	
												<col width="100">
												<col width="100">
												<col width="40">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="110" align="center">
												<col width="140">
												<col width="70" align="center">
												<col width="110" align="center">
												<col width="140">
												<col width="140">
												<ezf:row ezfHyo="B">
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><ezf:anchor name="Click_ContractDetail" ezfName="Click_ContractDetail" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_ContractDetail" otherAttr=" ezfanchortext=\"\""><ezf:label name="dsContrNum_BD" ezfName="dsContrNum_BD" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="svcContrRefCmntTxt_BD" ezfName="svcContrRefCmntTxt_BD" value="12345678901234567890123456789" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"14\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="ccyCd_BD" ezfName="ccyCd_BD" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="contrVrsnEffFromDt_BD" ezfName="contrVrsnEffFromDt_BD" value="2015/02/13" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="contrVrsnEffThruDt_BD" ezfName="contrVrsnEffThruDt_BD" value="2015/02/13" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="contrCloDt_BD" ezfName="contrCloDt_BD" value="2015/02/13" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="rnwEffFromDt_BD" ezfName="rnwEffFromDt_BD" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="xxDt10Dt_B1" ezfName="xxDt10Dt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="xxDt10Dt_B2" ezfName="xxDt10Dt_B2" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="stsMemoTxt_B1" ezfName="stsMemoTxt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:inputCheckBox name="contrHldFlg_BD" ezfName="contrHldFlg_BD" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
			    									<td><ezf:inputText name="xxDt10Dt_B3" ezfName="xxDt10Dt_B3" value="2015/02/13" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="stsMemoUpdFullPsnNm_BD" ezfName="stsMemoUpdFullPsnNm_BD" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="stsMemoTxt_B2" ezfName="stsMemoTxt_B2" ezfHyo="B" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><a href="#"  ezfhyo="B" ezfname="Click_ContractDetail" name="Click_ContractDetail" onclick="sendServer('Click_ContractDetail')" ezfanchortext><label ezfout name="dsContrNum_BD" ezfname="dsContrNum_BD" ezfhyo="B">1234567</label></a></td>
													<td>&nbsp;</td>
													<td><input type="text" size="14" maxlength="14" value="12345678901234567890123456789" name="svcContrRefCmntTxt_BD" ezfname="svcContrRefCmntTxt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
									     			<td><label ezfout name="ccyCd_BD" ezfname="ccyCd_BD" ezfhyo="B">USD</label></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffFromDt_BD" ezfname="contrVrsnEffFromDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrVrsnEffThruDt_BD" ezfname="contrVrsnEffThruDt_BD" ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="contrCloDt_BD"         ezfname="contrCloDt_BD"         ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="rnwEffFromDt_BD" ezfname="rnwEffFromDt_BD" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B1" ezfname="xxDt10Dt_B1" ezfhyo="B">23177</label></td>
														<td><label ezfout name="xxDt10Dt_B2" ezfname="xxDt10Dt_B2" ezfhyo="B">23177</label></td>
														<td><label ezfout name="stsMemoTxt_B1" ezfname="stsMemoTxt_B1" ezfhyo="B">15</label></td>
														<td><input type="checkbox" name="contrHldFlg_BD" ezfname="contrHldFlg_BD" value="Y" ezfhyo="B"/></td>
				    									<td><input type="text" size="10" maxlength="10" value="2015/02/13" name="xxDt10Dt_B3"     ezfname="xxDt10Dt_B3"     ezfhyo="B" style="border:none; background-color:transparent;"></td>
														<td><label ezfout name="stsMemoUpdFullPsnNm_BD" ezfname="stsMemoUpdFullPsnNm_BD" ezfhyo="B">15</label></td>
														<td><label ezfout name="stsMemoTxt_B2" ezfname="stsMemoTxt_B2" ezfhyo="B">15</label></td>
												</tr>
												</ezf:skip>
											</table>
										</div>
									</div> <!-- right table -->
								</div>
							</td>
						</tr>
					</table>
					<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxB", "BHEAD", "B", 0, true);
					</script>
				</c:when>
				<%--######################################## TAB "Strategy" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Strategy'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_ON";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>
					
					<%-- ###TAB - BODY --%>
					<div class="pTab_BODY_In">
						<table border ="0" bordercolor="blue" width="1123" cellpadding="1">
							<tr>
								<td>
									<!-- ##Strategy Tab spread ##-->
									<div id="strgyTBLC_H" 
									    style="overflow-y:none; overflow-x:hidden; height:; width:850; table-layout:fixed" 
									    onScroll="synchroScrollLeft(this.id, new Array('strgyTBLC_D'));">
										<table width="850" border="1" cellpadding="0" cellspacing="0">
											<col width="25" align="center">
											<col width="100" align="center">
											<col width="200" align="center">
											<col width="100" align="center">
											<col width="355" align="center">
											<tr>
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'billToCustCd_CD' )">Bill To<img id="sortIMG.billToCustCd_CD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'cltStrgyNm_CD' )">Strategy Name<img id="sortIMG.cltStrgyNm_CD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'cltStrgyStsCd_CD' )">Status<img id="sortIMG.cltStrgyStsCd_CD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'entBillToCustLocAddr_CD' )">Address<img id="sortIMG.entBillToCustLocAddr_CD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											</tr>
										</table>
									</div>
									<div id="strgyTBLC_D" 
									    style="overflow-y:scroll; height:78; overflow-x:hidden; width:867;" 
									    onScroll="onScroll=synchroScrollLeft(this.id, new Array('strgyTBLC_H'));">
										<table width="850" border="1" cellpadding="0" cellspacing="0" style=";word-break:break-all;" id="C">
											<col width="25" align="center">
											<col width="100" align="left">
											<col width="200" align="left">
											<col width="100" align="center">
											<col width="355" align="left">
											<tbody>
												<ezf:row ezfHyo="C">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td>
														<ezf:inputRadio name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" otherAttr="onclick=\"sendServer('OnChange_Strategy');\""/>
													</td>
													<td><ezf:label name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C" ezfArrayNo="0" /></td>
													<td><ezf:label name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C" ezfArrayNo="0" /></td>
													<td><ezf:select name="cltStrgyStsCd_CD" ezfName="cltStrgyStsCd_CD" ezfHyo="C" ezfArrayNo="0" ezfCodeName="cltStrgyStsCd_LC" ezfDispName="cltStrgyStsDescTxt_LD" otherEvent1=" onchange=\"sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px;\""/></td>
													<td><ezf:inputText name="entBillToCustLocAddr_CD" ezfName="entBillToCustLocAddr_CD" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}\" size=\"50\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="radio" name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" id="xxRadioBtn_CD{EZF_ROW_NUMBER}" /></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="radio" name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" id="xxRadioBtn_CD{EZF_ROW_NUMBER}" /></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="radio" name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" id="xxRadioBtn_CD{EZF_ROW_NUMBER}" /></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="radio" name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" id="xxRadioBtn_CD{EZF_ROW_NUMBER}" /></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputRadio name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" otherAttr="onClick=\"sendServer('OnChange_Strategy');\""/></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="radio" name="xxRadioBtn_CD" ezfName="xxRadioBtn_CD" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="C" ezfGetLineNoOffset="0" id="xxRadioBtn_CD{EZF_ROW_NUMBER}" /></td>
													<td><label ezfout name="billToCustCd_CD" ezfName="billToCustCd_CD" ezfHyo="C">abc</label></td>
													<td><label ezfout name="cltStrgyNm_CD" ezfName="cltStrgyNm_CD" ezfHyo="C">doremi</label></td>
													<td><select style="width:90px;" name="cltStrgyStsCd_CD" ezfname="cltStrgyStsCd_CD" ezfhyo="C" ezflist="cltStrgyStsCd_LC,cltStrgyStsDescTxt_LD,99, ," onChange="sendServer('OnChange_StrategyStatus', '{EZF_ROW_NUMBER}')">
														<option>Open</option></select></td>
													<td><input type="text" id="entBillToCustLocAddr_CD#{EZF_ROW_NUMBER}" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="C" ezfArrayNo="0" size="50" style="border:none; background-color:transparent;" tabindex="-1" readonly name="entBillToCustLocAddr_CD" ezfname="entBillToCustLocAddr_CD" /></td>
												</tr>
												</ezf:skip>
											</tbody>
										</table>
									</div>
								</td>
								<td>
									<div id="strgy_A" style="overflow-y:none; overflow-x:hidden; height:78; width:440; align:left;">
										<table width="440" border="0" cellpadding="0" cellspacing="0"">
											<col width="200">
											<col >
											<tr> 
												<td class="stab">Display Closed Strategies<ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" onClick="sendServer('OnChange_ChkBoxDisplayStrategies')" /></td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</div>
									<div id="strgy_B" style="overflow-y:none;overflow-x:none; width:440; align:right;height:;">
									<table border="0" cellpadding="0" cellspacing="0">
										<col width="122">
										<col >
										<tr><td>&nbsp;</td>
											<td><ezf:inputButton name="Click_AddWorkItem" value="Add Work Item" htmlClass="pBtn9" /></td>
										</tr>
									</table>
									</div>
								</td>
							</tr>
							</table>
							<table border ="0" bordercolor="blue" width="1123" cellpadding="1">	
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<div id="wrkItmTBLC_H" style="overflow-y:none; overflow-x:hidden; height:; width:1093px; table-layout:fixed" 
												 onScroll="synchroScrollLeft(this.id, new Array('wrkItmTBLC_D'));">
													<table border="1" cellpadding="0" cellspacing="0" style="width:1280;">
														<col width="400" align="center">
														<col width="100" align="center">
														<col width="115" align="center">
														<col width="140" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<tr>
															<td class="pClothBs">Work Item Name</td>
															<td class="pClothBs">Status</td>
															<td class="pClothBs">Type</td>
															<td class="pClothBs">Assigned To</td>
															<td class="pClothBs">Request Start<br>Date</td>
															<td class="pClothBs">Request Comp<br>Date</td>
															<td class="pClothBs">Actual Start<br>Date</td>
															<td class="pClothBs">Actual Comp<br>Date</td>
														</tr>
													</table>
												</div>
												<div id="wrkItmTBLC_D" style="overflow-y:scroll; height:144; overflow-x:scroll; width:1110px;" 
												 onScroll="onScroll=synchroScrollLeft(this.id, new Array('wrkItmTBLC_H'));">
													<table border="1" cellpadding="0" cellspacing="0" style="width:1280; px;word-break:break-all;" id="C">
														<col width="400" align="left">
														<col width="100" align="center">
														<col width="115" align="left">
														<col width="140" align="left">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<tbody>
															<ezf:row ezfHyo="D">
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td>
																	<ezf:anchor name="cltWrkItemNm_LK" ezfName="cltWrkItemNm_LK" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPDunningLeter', '{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\"">
																		<ezf:label name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D" ezfArrayNo="0" />
																	</ezf:anchor>
																</td>
																<td><ezf:select name="cltWrkItemStsCd_DD" ezfName="cltWrkItemStsCd_DD" ezfHyo="D" ezfArrayNo="0" ezfCodeName="cltWrkItemStsCd_LC" ezfDispName="cltWrkItemStsDescTxt_LD" otherAttr=" style=\"width:98px;\""/></td>
																<td><ezf:inputText name="cltWrkTpNm_DD" ezfName="cltWrkTpNm_DD" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\" style=\"border:none; background-color:transparent;\""/></td>
																<td><ezf:inputText name="cltPsnNm_DD" ezfName="cltPsnNm_DD" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\" style=\"border:none; background-color:transparent;\""/></td>
																<td><ezf:inputText name="cltWrkItemRwsdDt_DD" ezfName="cltWrkItemRwsdDt_DD" value="01/06/2018" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"1502\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputText name="cltWrkItemRwedDt_DD" ezfName="cltWrkItemRwedDt_DD" value="04/06/2018" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"1502\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputText name="cltWrkItemWsrdDt_DD" ezfName="cltWrkItemWsrdDt_DD" value="01/06/2018" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"1502\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputText name="cltWrkItemWerdDt_DD" ezfName="cltWrkItemWerdDt_DD" value="04/06/2018" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"1502\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
															</tr>
															</ezf:row>
															<ezf:skip>
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><label ezfout name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D">Canon Call customer - Escalation for the Final Notice</label></div></td>
																<td><select style="width:98px;" name="cltWrkItemStsCd_DD" ezfname="cltWrkItemStsCd_DD" ezflist="cltWrkItemStsCd_LC,cltWrkItemStsDescTxt_LD,99, ," ezfhyo="D">
																	<option>Open</option></select></td>
																<td><input type="text" size="15" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltWrkTpNm_DD"       ezfname="cltWrkTpNm_DD"       tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input type="text" size="19" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltPsnNm_DD"         ezfname="cltPsnNm_DD"         tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input id="cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemRwsdDt_DD" ezfname="cltWrkItemRwsdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemRwedDt_DD" ezfname="cltWrkItemRwedDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemWsrdDt_DD" ezfname="cltWrkItemWsrdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemWerdDt_DD" ezfname="cltWrkItemWerdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
															</tr>
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><label ezfout name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D">Canon Call customer - Escalation for the Final Notice</label></div></td>
																<td><select style="width:100px;" name="cltWrkItemStsCd_DD" ezfname="cltWrkItemStsCd_DD" ezflist="cltWrkItemStsCd_LC,cltWrkItemStsDescTxt_LD,99, ," ezfhyo="D">
																	<option>Open</option></select></td>
																<td><input type="text" size="15" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltWrkTpNm_DD"       ezfname="cltWrkTpNm_DD"       tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input type="text" size="19" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltPsnNm_DD"         ezfname="cltPsnNm_DD"         tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input id="cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemRwsdDt_DD" ezfname="cltWrkItemRwsdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemRwedDt_DD" ezfname="cltWrkItemRwedDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemWsrdDt_DD" ezfname="cltWrkItemWsrdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemWerdDt_DD" ezfname="cltWrkItemWerdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
															</tr>
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><label ezfout name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D">Canon Call customer - Escalation for the Final Notice</label></div></td>
																<td><select style="width:100px;" name="cltWrkItemStsCd_DD" ezfname="cltWrkItemStsCd_DD" ezflist="cltWrkItemStsCd_LC,cltWrkItemStsDescTxt_LD,99, ," ezfhyo="D">
																	<option>Open</option></select></td>
																<td><input type="text" size="15" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltWrkTpNm_DD"       ezfname="cltWrkTpNm_DD"       tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input type="text" size="19" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltPsnNm_DD"         ezfname="cltPsnNm_DD"         tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input id="cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemRwsdDt_DD" ezfname="cltWrkItemRwsdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemRwedDt_DD" ezfname="cltWrkItemRwedDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemWsrdDt_DD" ezfname="cltWrkItemWsrdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemWerdDt_DD" ezfname="cltWrkItemWerdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
															</tr>
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><label ezfout name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D">Canon Call customer - Escalation for the Final Notice</label></div></td>
																<td><select style="width:100px;" name="cltWrkItemStsCd_DD" ezfname="cltWrkItemStsCd_DD" ezflist="cltWrkItemStsCd_LC,cltWrkItemStsDescTxt_LD,99, ," ezfhyo="D">
																	<option>Open</option></select></td>
																<td><input type="text" size="15" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltWrkTpNm_DD"       ezfname="cltWrkTpNm_DD"       tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input type="text" size="19" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltPsnNm_DD"         ezfname="cltPsnNm_DD"         tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input id="cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemRwsdDt_DD" ezfname="cltWrkItemRwsdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemRwedDt_DD" ezfname="cltWrkItemRwedDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemWsrdDt_DD" ezfname="cltWrkItemWsrdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemWerdDt_DD" ezfname="cltWrkItemWerdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
															</tr>
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td><label ezfout name="cltWrkItemNm_DD" ezfName="cltWrkItemNm_DD" ezfHyo="D">Canon Call customer - Escalation for the Final Notice</label></div></td>
																<td><select style="width:100px;" name="cltWrkItemStsCd_DD" ezfname="cltWrkItemStsCd_DD" ezflist="cltWrkItemStsCd_LC,cltWrkItemStsDescTxt_LD,99, ," ezfhyo="D">
																	<option>Open</option></select></td>
																<td><input type="text" size="15" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltWrkTpNm_DD"       ezfname="cltWrkTpNm_DD"       tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input type="text" size="19" maxlength="30" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" name="cltPsnNm_DD"         ezfname="cltPsnNm_DD"         tabindex="1502" ezftoupper ezfhyo="D" style="border:none; background-color:transparent;"></td>
																<td><input id="cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemRwsdDt_DD" ezfname="cltWrkItemRwsdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwsdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemRwedDt_DD" ezfname="cltWrkItemRwedDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemRwedDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="01/06/2018" name="cltWrkItemWsrdDt_DD" ezfname="cltWrkItemWsrdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWsrdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
																<td><input id="cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" value="04/06/2018" name="cltWrkItemWerdDt_DD" ezfname="cltWrkItemWerdDt_DD" tabindex="1502" ezftoupper ezfhyo="D"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cltWrkItemWerdDt_DD#{EZF_ROW_NUMBER}', 4);"></td>
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
					</div>
				</c:when>
				<%--######################################## TAB "AdjHistory" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'AdjHistory'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_ON";
						document.getElementById( "Statement" ).className = "pTab_OFF";
					</script>

				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table width="1110">
						<tr>
							<td>
								<table border="0" bordercolor="blue" cellpadding="1" cellspacing="0">
									<col width="90">
									<col width="230">
									<col width="10">
									<col width="80">
									<col width="90">
									<col width="405">
									<col width="100">
									<col width="30">
									<col width="62">
									<tr>
										<td class="stab">Adjustment Date</td>
										<td>
											<ezf:inputText name="glDt_H1" ezfName="glDt_H1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_H1', 4);" >
											-
											<ezf:inputText name="glDt_H2" ezfName="glDt_H2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_H2', 4);" >
										</td>
										<td></td>
										<td class="stab">Transaction#</td>
										<td><ezf:inputText name="arTrxNum_H1" ezfName="arTrxNum_H1" value="XXXX1XXXX2XX3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Display All</td>
										<td></td>
										<td><ezf:inputButton name="Click_AdjHistorySearch" value="Search" htmlClass="pBtn5" /></td>
									</tr>
								</table>
								
								<!-- Pageing Start-->
								<table width="100%">
									<tr align="right">
										<td align="left" width="300">
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td>
											<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10"> 
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
													<td class="stab">to</td>
													<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
													<td class="stab">of</td>
													<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
												</tr>
											</table>
											</ezf:skip>
											<%-- Pagenation --%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="H" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_H" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_H" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_H" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<!-- Pageing End-->
							</td>
						</tr>
					</table>

					<!-- ##Transaction Tab spread ##-->
					<!--<table border="1" bordercolor ="orange" >-->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="99%" align="center" >
						<tr>
							<td>
								<div id="parentBoxH">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
										<div id="leftTbl" style="float:left; display:block; height:151px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div> <!-- left table -->
									
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="HHEAD" width="1101px" style="margin-right:20px">
												<col width="90">                <!--Adjustoment#-->
												<col width="110">               <!--Adjustoment Date-->
												<col width="125">               <!--Adjustment Amount-->
												<col width="100">               <!--Transaction#-->
												<col width="140">               <!--Activity Name-->
												<col width="140">               <!--Comments-->
												<col width="213">               <!--Note-->
												<col width="100">               <!--Status-->
												<col width="80">                <!--Due Date-->
												
												<tr height="33">
													<td class="pClothBs colFix" align="center" id="HH0"><a href="#" class="pSortCol" onclick="columnSort('H', 'arAdjNum_H')">Adjustoment#</a><img id="sortIMG.arAdjNum_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH1"><a href="#" class="pSortCol" onclick="columnSort('H', 'glDt_H')">Adjustoment Date</a><img id="sortIMG.glDt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH2"><a href="#" class="pSortCol" onclick="columnSort('H', 'dealApplyAmt_H')">Adjustment Amount</a><img id="sortIMG.dealApplyAmt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH3"><a href="#" class="pSortCol" onclick="columnSort('H', 'arTrxNum_H')">Transaction#</a><img id="sortIMG.arTrxNum_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH4"><a href="#" class="pSortCol" onclick="columnSort('H', 'arAdjTpDescTxt_H')">Activity Name</a><img id="sortIMG.arAdjTpDescTxt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH5"><a href="#" class="pSortCol" onclick="columnSort('H', 'adjCmntTxt_H')">Comments</a><img id="sortIMG.adjCmntTxt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH6"><a href="#" class="pSortCol" onclick="columnSort('H', 'arWrtOffNoteTxt_H')">Note</a><img id="sortIMG.arWrtOffNoteTxt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH7"><a href="#" class="pSortCol" onclick="columnSort('H', 'arCashApplyStsDescTxt_H')">Status</a><img id="sortIMG.arCashApplyStsDescTxt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="HH8"><a href="#" class="pSortCol" onclick="columnSort('H', 'invDueDt_H')">Due Date</a><img id="sortIMG.invDueDt_H" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>

										<div id="rightTbl" style="width:1118px; height:200px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="H" width="1101px">
												<col width="90"  align ="center">   <!--Adjustoment#-->
												<col width="110" align ="center">   <!--Adjustoment Date-->
												<col width="125" align ="right">    <!--Adjustment Amount-->
												<col width="100" align ="center">   <!--Transaction#-->
												<col width="140" align ="left">     <!--Activity Name-->
												<col width="140" align ="left">     <!--Comments-->
												<col width="213" align ="left">     <!--Note-->
												<col width="100" align ="left">     <!--Status-->
												<col width="80"  align ="center">   <!--Due Date-->
												<tbody>
												<ezf:row ezfHyo="H">
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:label name="arAdjNum_H" ezfName="arAdjNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
													<td><ezf:label name="glDt_H" ezfName="glDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="dealApplyAmt_H" ezfName="dealApplyAmt_H" value="999999999999999" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
													<td><ezf:label name="arTrxNum_H" ezfName="arTrxNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="arAdjTpDescTxt_H" ezfName="arAdjTpDescTxt_H" value="---------1---------2---------3---------4---------5" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="adjCmntTxt_H" ezfName="adjCmntTxt_H" value="---------1---------2---------3---------4---------5---------6-----" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arWrtOffNoteTxt_H" ezfName="arWrtOffNoteTxt_H" value="---------1---------2---------3---------4------1000" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arCashApplyStsDescTxt_H" ezfName="arCashApplyStsDescTxt_H" value="---------1---------2---------3---------4---------5" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="invDueDt_H" ezfName="invDueDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td><label ezfout name="arAdjNum_H"                 ezfname="arAdjNum_H"                ezfhyo="H">XXXXXXXX</label></td>
													<td><label ezfout name="glDt_H"                     ezfname="glDt_H"                    ezfhyo="H">01/08/2015</label></td>
													<td><input type="text"  class="pPro" size="15"    name="dealApplyAmt_H"                 ezfname="dealApplyAmt_H"          ezfhyo="H" readOnly style="border:none; background-color:transparent;text-align:right;" value="999999999999999"></td>
													<td><label ezfout name="arTrxNum_H"               ezfname="arTrxNum_H"                  ezfhyo="H">---------1---</label></td>
													<td><input type="text"  class="pPro" size="19"    name="arAdjTpDescTxt_H"               ezfname="arAdjTpDescTxt_H"        ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><input type="text"  class="pPro" size="19"    name="adjCmntTxt_H"                   ezfname="adjCmntTxt_H"            ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5---------6-----"></td>
													<td><input type="text"  class="pPro" size="30"    name="arWrtOffNoteTxt_H"              ezfname="arWrtOffNoteTxt_H"       ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4------1000"></td>
													<td><input type="text"  class="pPro" size="15"    name="arCashApplyStsDescTxt_H"        ezfname="arCashApplyStsDescTxt_H" ezfhyo="H" readOnly style="border:none; background-color:transparent;"                  value="---------1---------2---------3---------4---------5"></td>
													<td><label ezfout name="invDueDt_H"               ezfname="invDueDt_H"                  ezfhyo="H">01/08/2015</label></td>
												</tr>
												</ezf:skip>
                   								</tbody>
											</table>
										</div>
									</div> <!-- right table -->
								</div>
							</td>
						</tr>
					</table>

					<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxH", "HHEAD", "H",-1, true);
					</script>
				</c:when>

				<%--######################################## TAB "Statement" ########################################--%>
				<c:when test="${pageScope._ezddatabean.xxDplyTab_H == 'Statement'}">
					<script type="text/javascript">
						document.getElementById( "Transaction" ).className = "pTab_OFF";
						document.getElementById( "Note" ).className = "pTab_OFF";
						document.getElementById( "Task" ).className = "pTab_OFF";
						document.getElementById( "Contract" ).className = "pTab_OFF";
						document.getElementById( "Strategy" ).className = "pTab_OFF";
						document.getElementById( "AdjHistory" ).className = "pTab_OFF";
						document.getElementById( "Statement" ).className = "pTab_ON";
					</script>

				<%-- ###TAB - BODY --%>	
				<div class="pTab_BODY_In">
					<table width="1110">
						<tr>
							<td>
								<!-- Pageing Start-->
								<table width="100%">
									<tr align="right">
										<td align="left" width="300">
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td>
											<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10"> 
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut"><label ezfout name="xxPageShowFromNum_23" ezfname="xxPageShowFromNum_23">1</label></td>
													<td class="stab">to</td>
													<td class="pOut"><label ezfout name="xxPageShowToNum_23" ezfname="xxPageShowToNum_23">10</label></td>
													<td class="stab">of</td>
													<td class="pOut"><label ezfout name="xxPageShowOfNum_23" ezfname="xxPageShowOfNum_23">200</label></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
												</tr>
											</table>
											</ezf:skip>
											<%-- Pagenation --%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="J" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_J" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_J" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_J" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<!-- Pageing End-->
							</td>
						</tr>
					</table>

					<!-- ##Statement Tab spread ##-->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="99%" align="center" >
						<tr>
							<td>
								<div id="parentBoxJ">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
										<div id="leftTbl" style="float:left; display:block; height:177px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div> <!-- left table -->
									
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="JHEAD" width="1143px" style="margin-right:20px">
												<col width="90">               <!--Statement#-->
												<col width="90">               <!--Statement Date-->
												<col width="80">               <!--Bill To-->
												<col width="150">              <!--Bill To Name-->
												<col width="325">              <!--Address-->
												<col width="90">               <!--Current-->
												<col width="90">               <!--Over due 1-30 Days-->
												<col width="90">               <!--Overdue 31-60 Days-->
												<col width="90">               <!--Overdue 61-90 Days-->
												<col width="90">               <!--Overdue 90 Days-->
												<col width="90">               <!--Total Balance-->
												<tr height="43">
													<td class="pClothBs colFix" align="center" id="JH0"><a href="#" class="pSortCol" onclick="columnSort('J', 'stmtSqPk_J')">Statement#</a><img id="sortIMG.stmtSqPk_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH1"><a href="#" class="pSortCol" onclick="columnSort('J', 'stmtPrintDt_J')">Statement Date</a><img id="sortIMG.stmtPrintDt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH2"><a href="#" class="pSortCol" onclick="columnSort('J', 'billToCustCd_J')">Bill To</a><img id="sortIMG.billToCustCd_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH3"><a href="#" class="pSortCol" onclick="columnSort('J', 'scdCustNm_J')">Bill To Name</a><img id="sortIMG.scdCustNm_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH4"><a href="#" class="pSortCol" onclick="columnSort('J', 'billToCustLocAddr_J')">Address</a><img id="sortIMG.billToCustLocAddr_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH5"><a href="#" class="pSortCol" onclick="columnSort('J', 'ageCurAmt_J')">Current</a><img id="sortIMG.ageCurAmt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH6"><a href="#" class="pSortCol" onclick="columnSort('J', 'age0130Amt_J')">Over due 1-30 Days</a><img id="sortIMG.age0130Amt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH7"><a href="#" class="pSortCol" onclick="columnSort('J', 'age3160Amt_J')">Overdue 31-60 Days</a><img id="sortIMG.age3160Amt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH8"><a href="#" class="pSortCol" onclick="columnSort('J', 'age6190Amt_J')">Overdue 61-90 Days</a><img id="sortIMG.age6190Amt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH9"><a href="#" class="pSortCol" onclick="columnSort('J', 'ageOverAmt_J')">Overdue 90 Days</a><img id="sortIMG.ageOverAmt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td class="pClothBs" align="center" id="JH10"><a href="#" class="pSortCol" onclick="columnSort('J', 'balTotAmt_J')">Total Balance</a><img id="sortIMG.balTotAmt_J" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>

										<div id="rightTbl" style="width:1118px; height:194px; display:block; overflow-y:scroll; overflow-x:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="J" width="1143px">
												<col width="90" align="center">              <!--Statement#-->
												<col width="90" align="center">              <!--Statement Date-->
												<col width="80">                             <!--Bill To-->
												<col width="150">                            <!--Bill To Name-->
												<col width="325">                            <!--Address-->
												<col width="90" align="right">               <!--Current-->
												<col width="90" align="right">               <!--Over due 1-30 Days-->
												<col width="90" align="right">               <!--Overdue 31-60 Days-->
												<col width="90" align="right">               <!--Overdue 61-90 Days-->
												<col width="90" align="right">               <!--Overdue 90 Days-->
												<col width="90" align="right">               <!--Total Balance-->
												<tbody>
												<ezf:row ezfHyo="J">
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td>
														<ezf:anchor name="stmtSqPk_LK" ezfName="stmtSqPk_LK" ezfHyo="J" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPStatement" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="stmtSqPk_J" ezfName="stmtSqPk_J" ezfHyo="J" ezfArrayNo="0" /></ezf:anchor>
													</td>
													<td><ezf:label name="stmtPrintDt_J" ezfName="stmtPrintDt_J" ezfHyo="J" ezfArrayNo="0" />
													<td>
														<ezf:anchor name="" ezfName="" ezfHyo="F" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_AcctBillTo" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="billToCustCd_J" ezfName="billToCustCd_J" ezfHyo="J" ezfArrayNo="0" /></ezf:anchor>
													</td>
													<td><ezf:inputText name="scdCustNm_J" ezfName="scdCustNm_J" value="---------1---------2---------3---------4---------5" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="billToCustLocAddr_J" ezfName="billToCustLocAddr_J" value="---------1---------2---------3---------4---------5" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"45\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="ageCurAmt_J" ezfName="ageCurAmt_J" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:label name="age0130Amt_J" ezfName="age0130Amt_J" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:label name="age3160Amt_J" ezfName="age3160Amt_J" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:label name="age6190Amt_J" ezfName="age6190Amt_J" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:label name="ageOverAmt_J" ezfName="ageOverAmt_J" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:label name="balTotAmt_J" ezfName="balTotAmt_J" ezfHyo="J" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
                   								</tbody>
											</table>
										</div>
									</div> <!-- right table -->
								</div>
							</td>
						</tr>
					</table>

					<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxJ", "JHEAD", "J",-1, true);
					</script>
				</c:when>
				</c:choose>
				</div>
			</td>
		</tr>
	</table>
</center>

	<!-- **** Scroll **** -->
	<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="0" otherAttr=" id=\"xxListNum_LY\""/>
	<script type="text/javascript">
	setScroll();

	/**
	 * Save scroll position.
	 */
	function synchroScroll(id, ary) {
		if (id == 'RightTBL_F') {
			document.getElementById('xxListNum_LY').value = document.getElementById(id).scrollTop;
		}
	synchroScrollLeft(id, ary);
	}

	/**
	 * Set before event scroll position.
	 */
	function setScroll() {
		var yVal = document.getElementById('xxListNum_LY').value;
		var rightTblF = document.getElementById('RightTBL_F');
		if (yVal < rightTblF.scrollHeight) {
			rightTblF.scrollTop = yVal;
			}
	}
	</script>
	<!-- **** Scroll **** -->



<%-- **** Task specific area ends here **** --%>
