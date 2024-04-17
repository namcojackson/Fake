<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181212172236 --%>
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
			<input type="hidden" name="pageID" value="NSAL1200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Counter Group Setup">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" size=\"0\" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" size=\"0\" id=\"xxRecHistTblNm\""/>

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Counter Group Setup" class="pTab_ON"><a href="#">CounterGrp</a></li>
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
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="100%" align="left">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="104">
												<col width=" 10">
												<col width="248">
												<col width=" 10">
												<col width="144">
												<col width="  6">
												<col width="  4">
												<col width="216">
												<col width=" 10">
												<col width="204">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_NWAL1130" >Counter Group</ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="mtrGrpNm_H" ezfName="mtrGrpNm_H" otherAttr=" size=\"30\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Counter Group Type</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><ezf:select name="mtrGrpTpCd_H" ezfName="mtrGrpTpCd_H" ezfBlank="1" ezfCodeName="mtrGrpTpCd_L1" ezfDispName="mtrGrpTpDescTxt_L1" /></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="OpenWin_NSAL0480" value="Associate Model" htmlClass="pBtn12" /></td>
												</tr>
												<tr>
													<td class="stab">Description</td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="mtrGrpDescTxt_H" ezfName="mtrGrpDescTxt_H" otherAttr=" size=\"30\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Check Counter Level</td>
													<td>&nbsp;</td>
													<td colspan="2"><ezf:inputCheckBox name="prcVldFlg_H" ezfName="prcVldFlg_H" value="Y" /></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="AddCounterGroup" value="Add Counter group" htmlClass="pBtn12" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="5">
									<col width="80">
									<col width="80">
									<col width="180">
									<col width="225">
									<col width="550" align="right">
									<tr>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="AddLine" value="Add line" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="DeleteLine" value="Delete line" htmlClass="pBtn6" /></td>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\""/></td>
										<td>&nbsp;</td>
										<td align="right" class="stab">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="A" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
											<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="left">	
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																<tr>
																	<td>Results 950 - 1000 of 1000</td>
																</tr>
															</table>
														</td>
														<td align="right">
															<table border="0" cellpadding="0" cellspacing="0">
																<col width="54"  align="center">
																<col width="40"  align="center">
																<col width="16"  align="center">
																<col width="40"  align="center">
																<col width="26"  align="center">
																<col width="10">
																<col>
																<col width="20">
																<col>
																<col width="1">
																<col>
																<tr>
																	<td class="stab">Showing</td>
																	<td>20</td>
																	<td class="stab">/</td>
																	<td>20</td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ezf:skip>
										</td>
									</tr>
								</table>
							</td>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
						</tr>
<!-- ######################################## DETAIL #################################### -->
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:427px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1494px" style="margin-right:20px" >
												<col width=" 34" align="center">	<!-- CheckBox -->
												<col width="220" align="center">	<!-- Counter -->
												<col width=" 75" align="center">	<!-- Default As Billable -->
												<col width=" 80" align="center">	<!-- Multiplier -->
												<col width="220" align="center">	<!-- Billing Counter LVL1 -->
												<col width="220" align="center">	<!-- Billing Counter LVL2 -->
												<col width="220" align="center">	<!-- Billing Counter LVL3 -->
												<col width=" 55" align="center">	<!-- Counter Digit -->
												<col width=" 80" align="center">	<!-- Tech Read Mandatory -->
												<col width=" 50" align="center">	<!-- Active -->
												<col width="100" align="center">	<!-- Start Date  -->
												<col width="100" align="center">	<!-- End Date -->
												<tr height="40px">
													<td id="AH0"  class="pClothBs colFix">&nbsp;</td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlMtrLbCd_A')">Counter<img id="sortIMG.mdlMtrLbCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrEntryMndFlg_A')">Default As<BR>Billable<img id="sortIMG.mtrEntryMndFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrMultRate_A')">Multiplier<img id="sortIMG.mtrMultRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrLbCd_L1')">Billing Counter LVL1<img id="sortIMG.bllgMtrLbCd_L1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrLbCd_L2')">Billing Counter LVL2<img id="sortIMG.bllgMtrLbCd_L2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrLbCd_L3')">Billing Counter LVL3<img id="sortIMG.bllgMtrLbCd_L3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cntrDigitNum_A')">Counter<BR>Digit<img id="sortIMG.cntrDigitNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techReadMndFlg_A')">Tech Read<BR>Mandatory<img id="sortIMG.techReadMndFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'actvFlg_A')">Active<img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A')">Start Date<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A')">End Date<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1122px; height:444px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1494px" >
												<col width=" 34">	<!-- CheckBox -->
												<col width="220">	<!-- Counter -->
												<col width=" 75">	<!-- Default As Billable -->
												<col width=" 80">	<!-- Multiplier -->
												<col width="220">	<!-- Billing Counter LVL1 -->
												<col width="220">	<!-- Billing Counter LVL2 -->
												<col width="220">	<!-- Billing Counter LVL3 -->
												<col width=" 55">	<!-- Counter Digit -->
												<col width=" 80">	<!-- Tech Read Mandatory -->
												<col width=" 50">	<!-- Active -->
												<col width="100">	<!-- Start Date  -->
												<col width="100">	<!-- End Date -->
												<ezf:row ezfHyo="A">
												<tr height="25px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<table border="0" cellpadding="1" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="mtrLbDescTxt_CN" ezfName="mtrLbDescTxt_CN" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																<td><ezf:inputButton name="OpenWin_Counter" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td align="center"><ezf:inputCheckBox name="mtrEntryMndFlg_A" ezfName="mtrEntryMndFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="mtrMultRate_A" ezfName="mtrMultRate_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"8\""/></td>
													<td>
														<table border="0" cellpadding="1" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="mtrLbDescTxt_L1" ezfName="mtrLbDescTxt_L1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																<td><ezf:inputButton name="OpenWin_LVL1Counter" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td>
														<table border="0" cellpadding="1" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="mtrLbDescTxt_L2" ezfName="mtrLbDescTxt_L2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																<td><ezf:inputButton name="OpenWin_LVL2Counter" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td>
														<table border="0" cellpadding="1" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="mtrLbDescTxt_L3" ezfName="mtrLbDescTxt_L3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																<td><ezf:inputButton name="OpenWin_LVL3Counter" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td align="center"><ezf:inputText name="cntrDigitNum_A" ezfName="cntrDigitNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"2\" maxlength=\"2\""/></td>
													<td align="center"><ezf:inputCheckBox name="techReadMndFlg_A" ezfName="techReadMndFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"effFromDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"effThruDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													</td>
												</tr>
												</ezf:row>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
		S21TableUI.initialize("parentBoxA", "AHEAD", "A");
	</script>

<%-- **** Task specific area ends here **** --%>
