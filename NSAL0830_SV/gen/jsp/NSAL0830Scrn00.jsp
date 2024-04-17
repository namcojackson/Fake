<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128163318 --%>
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
			<input type="hidden" name="pageID" value="NSAL0830Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tiers for Interface">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Tiers</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%" align="center">
									<col width="20">
									<col width="80">
									<col width="150">
									<col width="40">
									<col width="80">
									<col width="150">
									<col width="40">
									<col width="80">
									<col width="150">
									<col align="right">
									<tr height="21">
										<td></td>
										<td class="stab">Source Type</td>
										<td><ezf:select name="contrIntfcSrcTpCd_SS" ezfName="contrIntfcSrcTpCd_SS" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_SC" ezfDispName="contrIntfcSrcTpDescTxt_SC" /></td>
										<td></td>
										<td class="stab">Interface Bat#</td>
										<td><ezf:inputText name="dsContrIntfcBatNum_S" ezfName="dsContrIntfcBatNum_S" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Source Ref#</td>
										<td><ezf:inputText name="dsContrSrcRefNum_S" ezfName="dsContrSrcRefNum_S" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr height="21">
										<td></td>
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum_S" ezfName="dsContrNum_S" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Errors Only</td>
										<td><ezf:inputCheckBox name="xxErrFlg_S" ezfName="xxErrFlg_S" value="Y" /></td>
										<td></td>
										<td></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="OpenForCorrection" value="Open For Correction" htmlClass="pBtn12" />&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>
					<br>
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width=98%;margin-left:20;">
						<col width="180">
						<col width="20">
						<col width="110">
						<col width="370">
						<col width="">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
							<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
							<!-- Pagination & Navigation--START-->
							<td>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="LEFT_NONE" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" ></td>
										<td class="stab">/</td>
										<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="3" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
										<td class="stab">page</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" ></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; " width="98%" align="center" >
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
									<tr>
										<td>

												<div id="LeftTable_A_Top" style="width:1070px; display:block; overflow-x:hidden; overflow-y:none; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="30" align="center">		<!-- CheckBox -->
														<col width="100" align="center">	<!-- Interface Bat# -->
														<col width=" 80" align="center">		<!-- Source Ref# -->
														<col width="135" align="center">	<!-- Source Type -->
														<col width="75" align="center">		<!-- Contract# -->
														<col width="115" align="center">	<!-- Action -->
														<col width="125" align="center">	<!-- Serial# -->
														<col width="120" align="center">	<!-- IB ID -->
														<col width="125" align="center">	<!-- Mdse Code -->
														<col width="305" align="center">	<!-- Billing Counter -->
														<col width="30" align="center">		<!-- Pricing -->
														<col width="80" align="center">		<!-- Allowance -->
														<col width="80" align="center">		<!-- Price -->
														<col width="50" align="center">		<!-- Proc Flag -->
														<col width="120" align="center">	<!-- Proc Message -->
														<col width="90" align="center">		<!-- Proc Status -->
														<tr>
															<td rowspan="2" id="AH0"  class="pClothBs colFix">&nbsp;</td>
															<td rowspan="2" id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcBatNum_A')">Interface Bat#</a><img id="sortIMG.dsContrIntfcBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrSrcRefNum_A')">Source Ref#</a><img id="sortIMG.dsContrSrcRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcSrcTpCd_A')">Source Type</a><img id="sortIMG.contrIntfcSrcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcActDescTxt_A')">Action</a><img id="sortIMG.dsContrIntfcActDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_A')">Billing Counter</a><img id="sortIMG.mtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td colspan="3" id="AH10" class="pClothBs">Pricing</td>
															<td rowspan="2" id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcStsCd_A')">Proc Flag</a><img id="sortIMG.dsContrIntfcStsCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intfcErrMsgTxt_A')">Proc Message</a><img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
															<td rowspan="2" id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrProcStsDescTxt_A')">Proc Status</a><img id="sortIMG.dsContrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														</tr>
														<tr>
															<td class="pClothBs">&nbsp;</td>
															<td id="AH12" class="pClothBs">Allowance</td>
															<td id="AH13" class="pClothBs">Price</td>
														</tr>
													</table>
												</div>
												<div id="LeftTable_A" style="width:1087px; display:block; overflow-x:scroll; overflow-y:scroll; height:390;  float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'LeftTable_A_Top' ) );">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
														<col width=" 30" align="center">	<!-- CheckBox -->
														<col width="100" align="center">	<!-- Interface Bat# -->
														<col width=" 80" align="center">	<!-- Source Ref# -->
														<col width="135" align="center">	<!-- Source Type -->
														<col width=" 75" align="center">	<!-- Contract# -->
														<col width="115" align="center">	<!-- Action -->
														<col width="125" align="center">	<!-- Serial# -->
														<col width="120" align="center">	<!-- IB ID -->
														<col width="125" align="center">	<!-- Mdse Code -->
														<col width="305" align="center">	<!-- Billing Counter -->
														<col width="190" align="center">	<!-- Pricing -->
														<col width=" 50" align="center">	<!-- Proc Flag -->
														<col width="120" align="center">	<!-- Proc Message -->
														<col width=" 90" align="center">	<!-- Proc Status -->

<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="business.servlet.NSAL0830.NSAL0830BMsg" %>
<%@ page import="business.servlet.NSAL0830.NSAL0830_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NSAL0830BMsg bMsg = (NSAL0830BMsg)databean.getEZDBMsg(); %>
<% BigDecimal preContrIntfcPk = null; %>
<% BigDecimal curContrIntfcPk = null; %>
<% BigDecimal nxtContrIntfcPk = null; %>
<% int hdrArrayNo = 0; %>
<% 	for (int i = 0; i < bMsg.A.getValidCount(); i++ ) { %>
<%		curContrIntfcPk = bMsg.A.no(i).dsContrIntfcPk_A.getValue(); %>
<%		if (i == bMsg.A.getValidCount() - 1) { %>
<%			nxtContrIntfcPk = null; %>
<%		} else { %>
<%			nxtContrIntfcPk = bMsg.A.no(i+1).dsContrIntfcPk_A.getValue(); %>
<%		} %>

<%		if (!ZYPCommonFunc.hasValue(curContrIntfcPk) || !curContrIntfcPk.equals(preContrIntfcPk)) { %>
<%			// write parent table row %>
<%			hdrArrayNo = i; %>
															<tr>
																<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
																<td align="left"><ezf:label name="dsContrIntfcBatNum_A" ezfName="dsContrIntfcBatNum_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
																<td align="center"><ezf:inputText name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" value="12345678" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
																<td align="center"><ezf:select name="contrIntfcSrcTpCd_A" ezfName="contrIntfcSrcTpCd_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_AC" ezfDispName="contrIntfcSrcTpDescTxt_AC" otherAttr=" style=\"width:130;\" style=\"width:130;\""/></td>
																<td align="center"><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
																<td align="left"><ezf:label name="dsContrIntfcActDescTxt_A" ezfName="dsContrIntfcActDescTxt_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
																<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="W2345678" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Machine" value="Go" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" htmlClass="pBtn0" /></td>
																<td align="center"><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="W2345" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" size=\"15\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																<td align="center"><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Merchandise" value="Go" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" htmlClass="pBtn0" /></td>
																<td align="left"><ezf:label name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
																<td>
<%		} else { %>
<%			// write parent hidden row %>
																<ezf:inputHidden name="xxChkBox_A" ezfName="xxChkBox_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="contrIntfcSrcTpCd_A" ezfName="contrIntfcSrcTpCd_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
																<ezf:inputHidden name="dsContrProcStsDescTxt_A" ezfName="dsContrProcStsDescTxt_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
<%		} %>
<%		if (!ZYPCommonFunc.hasValue(curContrIntfcPk) || !curContrIntfcPk.equals(preContrIntfcPk)) { %>
<%			// write child table header %>
																	<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																		<col width="30">
																		<col width="80">
																		<col width="80">
<%		} %>

<%			// write child table row %>
																		<tr>
																			<td><ezf:inputRadio name="xxRadioBtn_H" ezfName="xxRadioBtn_H" value="<%= String.valueOf(i) %>" /></td>
																			<td><ezf:inputText name="xsMtrCopyQty_A" ezfName="xsMtrCopyQty_A" value="1000" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"8\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" value="0.01" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"8\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																			<td class="pAuditInfo">
																				<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"0\""/>
																				<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"0\""/>
																				<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"0\""/>
																				<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"0\""/>
																				<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"0\""/>
																			</td>
																		</tr>

<%		if (!ZYPCommonFunc.hasValue(curContrIntfcPk) || !curContrIntfcPk.equals(nxtContrIntfcPk)) { %>
<%			// write child table footer %>
																	</table>
																	<table style="table-layout:fixed;">
																		<tr>
																			<td><ezf:inputButton name="AddPricingRow" value="Add Row" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn6" /></td>
																			<td><ezf:inputButton name="DeletePricingRow" value="Delete Row" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn6" /> </td>
																		</tr>
																	</table>
<%			// write parent table row (rest) %>
																</td>
																<td align="center"><ezf:label name="dsContrIntfcStsCd_A" ezfName="dsContrIntfcStsCd_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
																<td align="left" class="stab"><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
																<td align="left"><ezf:label name="dsContrProcStsDescTxt_A" ezfName="dsContrProcStsDescTxt_A" ezfHyo="A" ezfArrayNo="<%= hdrArrayNo %>" /></td>
															</tr>
<%		} %>
<%		preContrIntfcPk = curContrIntfcPk; %>
<%	} %>
<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
														<ezf:skip>
														</ezf:skip>
													</table>
												</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table  border="0" style="table-layout:fixed;width=96%;">
						<col align="left" width="300">
						<col align="right">
						<tr>
							<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" /><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn8" />&nbsp;<ezf:inputButton name="AddRow" value="Add Row" htmlClass="pBtn8" /></td>
							<td><ezf:inputButton name="ValidateData" value="Validate Data" htmlClass="pBtn8" /></td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
