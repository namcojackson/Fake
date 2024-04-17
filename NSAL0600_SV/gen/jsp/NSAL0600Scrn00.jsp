<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151225135353 --%>
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
			<input type="hidden" name="pageID" value="NSAL0600Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Cascade Date">
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
										<li title="Contract Search" class="pTab_ON"><a href="#">Cascade Date</a></li>
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
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="80">
												<col width="110">
												<col width="20">
												<col width="80">
												<col width="110">
												<tr>
													<td class="stab">New Start Date</td>
													<td><ezf:inputText name="xxFromDt_H" ezfName="xxFromDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_H', 4);"></td>
													<td>&nbsp;</td>
													<td class="stab">New End Date</td>
													<td><ezf:inputText name="xxThruDt_H" ezfName="xxThruDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_H', 4);"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="80">
												<col width="600">
												<tr>
													<td class="stab">Reason Code</td>
													<td><ezf:select name="svcMemoRsnCd_HS" ezfName="svcMemoRsnCd_HS" ezfBlank="1" ezfCodeName="svcMemoRsnCd_HC" ezfDispName="svcMemoRsnDescTxt_HD" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="80">
												<col width="600">
												<col width="20">
												<col width="100">
												<tr>
													<td class="stab">Notes</td>
													<td><ezf:textArea name="svcCmntTxt_H" ezfName="svcCmntTxt_H" otherAttr=" cols=\"80\" rows=\"4\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="ApplyToAll" value="APPLY TO ALL" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
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
					<table border="0">
						<tr>
							<td height= "75px" style="vertical-align: top;" colspan=2>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width="254" align="center"><!-- Contract# -->
									<col width="100" align="center"><!-- Start Date -->
									<col width="100" align="center"><!-- End Date -->
									<col width="100" align="center"><!-- New Start Date -->
									<col width="100" align="center"><!-- New End Date -->
									<tr height="25">
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">New Start Date</td>
										<td class="pClothBs">New End Date</td>
									</tr>
								</table>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width="254" align="left"><!-- Contract# -->
									<col width="100" align="center"><!-- Start Date -->
									<col width="100" align="center"><!-- End Date -->
									<col width="100" align="center"><!-- New Start Date -->
									<col width="100" align="center"><!-- New End Date -->
									<tr height="25">
										<td><ezf:label name="dsContrNum" ezfName="dsContrNum" /></td>
										<td><ezf:inputText name="contrVrsnEffFromDt" ezfName="contrVrsnEffFromDt" value="01/01/2015" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
										<td><ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" value="12/31/2015" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
										<td><ezf:inputText name="contrVrsnEffFromDt_N" ezfName="contrVrsnEffFromDt_N" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
										<td><ezf:inputText name="contrVrsnEffThruDt_N" ezfName="contrVrsnEffThruDt_N" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" /><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn8" />
							</td>
							<td  align="right">
								<ezf:skip>
										<table border="0" cellpadding="1" cellspacing="0">
											<col >
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col width="0">
											<col width="1">
											<col width="0">
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">40</td>
												<td class="stab">of</td>
												<td class="pOut">120</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
											</tr>
										</table>
								</ezf:skip>

								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
						</tr>
						<tr>
							<td colspan=2>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="210" align="center"><!-- IB ID -->
									<col width="230" align="center"><!-- Serial# -->
									<col width="100" align="center"><!-- Start Date -->
									<col width="100" align="center"><!-- End Date -->
									<col width="100" align="center"><!-- New Start Date -->
									<col width="100" align="center"><!-- New End Date -->
									<tr height="25">
										<td class="pClothBs"></td>
										<td class="pClothBs">IB ID</td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">New Start Date</td>
										<td class="pClothBs">New End Date</td>
									</tr>
								</table>
								<div style="width:881; height:300; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width="210" align="left"><!-- Serial# -->
										<col width="230" align="left"><!-- Serial# -->
										<col width="100" align="center"><!-- Start Date -->
										<col width="100" align="center"><!-- End Date -->
										<col width="100" align="center"><!-- New Start Date -->
										<col width="100" align="center"><!-- New End Date -->
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="contrEffFromDt_A" ezfName="contrEffFromDt_A" value="01/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="contrEffThruDt_A" ezfName="contrEffThruDt_A" value="12/31/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="contrEffFromDt_AN" ezfName="contrEffFromDt_AN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="contrEffThruDt_AN" ezfName="contrEffThruDt_AN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
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


<%-- **** Task specific area ends here **** --%>
