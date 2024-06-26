<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190213174149 --%>
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
			<input type="hidden" name="pageID" value="NSAL0640Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update Contract Branch/Representative">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Update Contract Branch/Representative" class="pTab_ON"><a href="#">Upd Br</a></li>
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
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<col width="600">
									<col width="100">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectBranch" >New Branch</ezf:anchor></td>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width="300">
												<tr>
													<td><ezf:inputText name="xxDplyByCdNmCnctTxt_H" ezfName="xxDplyByCdNmCnctTxt_H" value="WWWWWWWWWWWWWWW" otherEvent1="ChangeBranch" otherAttr=" size=\"30\" ezffocusout=\"ChangeBranch\""/></td>
												</tr>
											</table>
										</td>
										<td></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Rep" >New Rep</ezf:anchor></td>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width="300">
												<tr>
													<td><ezf:inputText name="xxPsnNm_H" ezfName="xxPsnNm_H" value="WWWWWWWWWWWWWWW" otherEvent1="ChangeRep" otherAttr=" size=\"30\" ezfnoupperfocusout=\"ChangeRep\""/></td>
												</tr>
											</table>
										</td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H" ezfName="svcMemoRsnCd_H" ezfBlank="1" ezfCodeName="svcMemoRsnCd_L" ezfDispName="svcMemoRsnNm_L" /></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td><ezf:textArea name="svcCmntTxt_H" ezfName="svcCmntTxt_H" otherAttr=" cols=\"80\" rows=\"4\""/></td>
										<td><ezf:inputButton name="ApplyToAll" value="APPLY TO ALL" htmlClass="pBtn8" otherAttr=" valign=\"bottom\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="100%">
						<tr>
							<td>
								<table  border="0" cellpadding="0" cellspacing="0" width="1090">
									<col width="" align="right" valign="top">
									<tr>
										<td>
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">0</td>
														<td class="stab">to</td>
														<td class="pOut">0</td>
														<td class="stab">of</td>
														<td class="pOut">0</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
													</tr>
												</table>
											</ezf:skip>
											<table width="500">
												<tr align="right">
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 95" align="center"><!-- Contract# -->
									<col width="120" align="center"><!-- Customer Name -->
									<col width=" 80" align="center"><!-- Start Date -->
									<col width=" 80" align="center"><!-- End Date -->
									<col width="140" align="center"><!-- Current Branch -->
									<col width="140" align="center"><!-- Current Assigned Rep -->
									<col width="140" align="center"><!-- New Branch -->
									<col width="140" align="center"><!-- New Assigned Rep -->
									<col width="140" align="center"><!-- Return Message -->
									<tr height=" 35">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('SelectAll')" /></td>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs">Customer Name</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">Current Branch</td>
										<td class="pClothBs">Current Assigned Rep</td>
										<td class="pClothBs">New Branch</td>
										<td class="pClothBs">New Assigned Rep</td>
										<td class="pClothBs">Return Message</td>
									</tr>
								</table>
								<div style="width:1057; height:355; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 95" align="center"><!-- Contract# -->
									<col width="120" align="center"><!-- Customer Name -->
									<col width=" 80" align="center"><!-- Start Date -->
									<col width=" 80" align="center"><!-- End Date -->
									<col width="140" align="center"><!-- Current Branch -->
									<col width="140" align="center"><!-- Current Assigned Rep -->
									<col width="140" align="center"><!-- New Branch -->
									<col width="140" align="center"><!-- New Assigned Rep -->
									<col width="140" align="center"><!-- Return Message -->
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" value="FLT-5874312" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"xxScrItem34Txt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="CHICAGO BULLS INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"dsAcctNm_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="contrVrsnEffFromDt_A1" ezfName="contrVrsnEffFromDt_A1" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"contrVrsnEffFromDt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="contrVrsnEffThruDt_A1" ezfName="contrVrsnEffThruDt_A1" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"contrVrsnEffThruDt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A1" ezfName="xxGenlFldAreaTxt_A1" value="ESS 100 PHILADELPHIA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" id=\"xxGenlFldAreaTxt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxPsnNm_A1" ezfName="xxPsnNm_A1" value="GOOD, STUART, IL 60612" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" id=\"xxPsnNm_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A2" ezfName="xxGenlFldAreaTxt_A2" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('ChangeBranch', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"14\" id=\"xxGenlFldAreaTxt_A2#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="OpenWin_SelectBranch" value="BR" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
												<td><ezf:inputText name="xxPsnNm_A2" ezfName="xxPsnNm_A2" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('ChangeRep', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"14\" id=\"xxPsnNm_A2#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="OpenWin_Rep" value="Rep" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
												<td><ezf:inputText name="dsMsgTxt_A1" ezfName="dsMsgTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
											</tr>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>

<%-- **** Task specific area ends here **** --%>
