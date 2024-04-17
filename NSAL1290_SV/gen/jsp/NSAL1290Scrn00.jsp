<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161207163306 --%>
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
			<input type="hidden" name="pageID" value="NSAL1290Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Billing Counter Map">

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
										<li title="Counter Group Setup" class="pTab_ON"><a href="#">BillCounterMap</a></li>
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
												<col width="100">
												<col width=" 10">
												<col width="250">
												<col width=" 10">
												<col width="100">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_RegularCounter" >Regular Counter</ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="mtrLbNm_H" ezfName="mtrLbNm_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"32\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="100%">
						<hr width="99%">
						<tr>
							<td>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="5">
									<col width="80">
									<col width="80">
									<col width="180">
									<tr>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="AddLine" value="Add line" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="DeleteLine" value="Delete line" htmlClass="pBtn6" /></td>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\""/></td>
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
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1749px" style="margin-right:20px" >
												<col width=" 30" align="center">	<!-- CheckBox -->
												<col width="290" align="center">	<!-- Billing Counter -->
												<col width="400" align="center">	<!-- Description -->
												<col width=" 90" align="center">	<!-- Level -->
												<col width=" 50" align="center">	<!-- Active -->
												<col width="120" align="center">	<!-- Start Date  -->
												<col width="120" align="center">	<!-- End Date -->
												<tr height="40px">
													<td id="AH1" class="pClothBs colFix">&nbsp;</td>
													<td id="AH2" class="pClothBs">Billing Counter</td>
													<td id="AH3" class="pClothBs">Description</td>
													<td id="AH4" class="pClothBs">Level</td>
													<td id="AH5" class="pClothBs">Active</td>
													<td id="AH6" class="pClothBs">Start Date</td>
													<td id="AH7" class="pClothBs">End Date</td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1122px; height:444px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1749px" >
												<col width=" 30">	<!-- CheckBox -->
												<col width="290">	<!-- Billing Counter -->
												<col width="400">	<!-- Description-->
												<col width=" 90">	<!-- Level -->
												<col width=" 50">	<!-- Active -->
												<col width="120">	<!-- Start Date  -->
												<col width="120">	<!-- End Date -->
												<ezf:row ezfHyo="A">
												<tr height="25px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center">
														<table border="0" cellpadding="1" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="mtrLbNm_BC" ezfName="mtrLbNm_BC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"30\""/></td>
																<td><ezf:inputButton name="OpenWin_BillingCounter" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td align="left"><ezf:label name="mtrLbDescTxt_BC" ezfName="mtrLbDescTxt_BC" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:select name="bllgMtrMapLvlNum_A" ezfName="bllgMtrMapLvlNum_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_01" ezfDispName="xxDplyByCtrlItemCdNm_01" otherAttr=" style=\"width:75\""/></td>
													<td align="center"><ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" ezftoupper=\"\" id=\"effFromDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" ezftoupper=\"\" id=\"effThruDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
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
