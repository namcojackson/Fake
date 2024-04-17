<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190308180915 --%>
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
			<input type="hidden" name="pageID" value="NFCL3120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Bank Account Search">
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Man Jrnl Inq</a></li>
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

				<div class="pTab_BODY" style="padding-top:5px;">
					<table width="1100" border="0" cellpadding="1" cellspacing="0" align="center">
						<col width="110">
						<col width="340">
						<col width="110">
						<col width="310">
						<col width="100">
						<col width="">
						<tr>
							<td class="stab">Saved Search Option</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width: 320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							</td>
							<td>
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr>
					<table width="100%" border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td align="center">
								<table width="1100" cellSpacing="0" cellPadding="0" border="0">
									<col width="120" align="left">
									<col width="030" align="left">
									<col width="145" align="left">
									<col width="005" align="right">
									<col width="340" align="left">
									<col width="010">
									<col width="050" align="center">
									<col width="020">
									<col width="050" align="center">
									<col width="020">
									<col width="310" align="right">
									<tr>
										<td class="stab">Customer Unidentified</td>
										<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('Click_Customer_Unidentified')" otherAttr=" tabindex=\"1\""/></td>
										<td class="stab"><ezf:anchor name="dsAcctNum_L1" ezfName="dsAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1\"">Customer Account Name(*)</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="NJ DEPT OF LAW & PUBLIC SAFETY" otherAttr=" size=\"50\" maxlength=\"360\" tabindex=\"1\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="dsBankAcctNum_L1" ezfName="dsBankAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount" otherAttr=" tabindex=\"1\"">Bank Name(*)</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="dsBankAcctNm_H1" ezfName="dsBankAcctNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"50\" maxlength=\"80\" tabindex=\"1\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="dsBankBrNum_L1" ezfName="dsBankBrNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount" otherAttr=" tabindex=\"1\"">Branch Name(*)</ezf:anchor></td>
										<td>&nbsp;</td>
										<td><ezf:inputText name="dsBankBrNm_H1" ezfName="dsBankBrNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"50\" maxlength=\"80\" tabindex=\"1\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">External</td>
									<%--<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr=" onclick=\"sendServer( 'OnChangeRadio' );\""/></input></td>--%>
										<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr=" onclick=\"sendServer( 'OnChangeRadio' );\""/></input></td>
										<td class="stab">Internal</td>
									<%--<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr=" onclick=\"sendServer( 'OnChangeRadio' );\""/></input></td>--%>
										<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr=" onclick=\"sendServer( 'OnChangeRadio' );\""/></input></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"1\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
					<table border="0" cellpadding="1" cellspacing="0" width="1110" style="margin-left:5px;">
						<tr>
							<td>
								<table width="100%"  border="0">
									<col width="200" align="left">
									<col width="250">
									<col width="660">
									<tr>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td align="left">
											<ezf:inputButton name="New" value="New" htmlClass="pBtn6" otherAttr=" tabindex=\"1\""/>
										</td>
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"           value="A" />
												<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"          value="FULL" />
											</jsp:include>
											<ezf:skip>
												<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 27px;">
													<colgroup>
														<col align="center" width="54">
														<col align="right" width="40">
														<col align="center" width="16">
														<col align="right" width="40">
														<col align="center" width="16">
														<col align="center" width="20">
														<col width="40">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<col width="20">
													</colgroup>
													<tbody>
													<tr>
														<td class="stab"><label>Showing</label></td>
														<td class="pOut">1</td>
														<td class="stab"><label>/</label></td>
														<td class="pOut">15</td>
														<td class="stab"><label>Page</label></td>
														<td>&nbsp;</td>
														<td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Jump" name="PagePrev"></td>
														<td>&nbsp;</td>
														<td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
														<td></td>
														<td><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
														<td></td>
													</tr>
													</tbody>
												</table>
											</ezf:skip>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:380px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1110px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1100px" style="margin-right:20px">
												<col width="115" align="center">
												<col width="195" align="center">
												<col width="100" align="center">
												<col width="195" align="center">
												<col width="110" align="center">
												<col width="195" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<tr height="24">
													<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsBankAcctNum_A1')">Bank Account#</a><img id="sortIMG.dsBankAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsBankAcctNm_A1')" >Bank Name</a>            <img id="sortIMG.dsBankAcctNm_A1"  border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A1')"    >Cust Account#</a>        <img id="sortIMG.dsAcctNum_A1"     border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A1')"     >Customer Account Name</a><img id="sortIMG.dsAcctNm_A1"      border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bankRteNum_A1')"   >Routing Number</a>       <img id="sortIMG.bankRteNum_A1"    border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsBankBrNm_A1')"   >Branch Name</a>          <img id="sortIMG.dsBankBrNm_A1"    border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A1')"    >Eff From Date</a>        <img id="sortIMG.effFromDt_A1"     border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A1')"    >Eff Thru Date</a>        <img id="sortIMG.effThruDt_A1"     border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id='rightTbl' style="width:1127px; height:380px; display:block; overflow-x:hidden; overflow-y:scroll; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1127px" >
												<col width="115" align="left">
												<col width="195" align="center">
												<col width="100" align="center">
												<col width="195" align="center">
												<col width="110" align="center">
												<col width="195" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<ezf:row ezfHyo="A">
													<tr  height="24" id="id_row{EZF_ROW_NUMBER}">
														<td>
															<ezf:anchor name="Click_Link_NFCL3170" ezfName="Click_Link_NFCL3170" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_Link_NFCL3170" otherAttr=" ezfanchortext=\"\" tabindex=\"1\"">
																<ezf:label name="dsBankAcctNum_A1" ezfName="dsBankAcctNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsBankAcctNum_A1\""/></td>
															</ezf:anchor>
														</td>
														<td><ezf:inputText name="dsBankAcctNm_A1" ezfName="dsBankAcctNm_A1" value="11111111111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"80\" id=\"dsBankAcctNm_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="11111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"28\" id=\"dsAcctNum_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="11111111111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"60\" id=\"dsAcctNm_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="bankRteNum_A1" ezfName="bankRteNum_A1" value="1111111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bankRteNum_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="dsBankBrNm_A1" ezfName="dsBankBrNm_A1" value="11111111111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"80\" id=\"dsAcctNm_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A1\""/></td>
														<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A1\""/></td>
													</tr>
												</ezf:row>
											</table>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
<script type="text/javascript" defer>
   S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0, true);
</script>

<%-- **** Task specific area ends here **** --%>
