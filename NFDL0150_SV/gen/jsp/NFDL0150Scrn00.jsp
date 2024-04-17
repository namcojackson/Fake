<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160222203149 --%>
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
			<input type="hidden" name="pageID" value="NFDL0150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Stategy Master - Strategy">
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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Strategy</a></li>
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
						<tr height="42px">
							<td></td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" cellpadding="1" cellspacing="0" style="margin-left:15;">
						<col valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-y:hidden; width:1050;">
									<table border="1"width="1050" height="24" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="100" align="center">		<!-- Work Item Code(radio button) -->
										<col width="230" align="center">		<!-- Work Item Code(code) -->
										<col width="720" align="center">		<!-- Work Item Name -->
										<tr height="27">
											<td class="pClothBs"></td>
											<td class="pClothBs">Code</td>
											<td class="pClothBs">Name</td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="overflow-y:scroll; width:1070; height:460;">
									<table border="1"width="1050" height="24" id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="100" align="center">		<!-- Work Item Code(radio button) -->
										<col width="230" align="center">		<!-- Work Item Code(code) -->
										<col width="720" align="center">		<!-- Work Item Name -->
										<ezf:row ezfHyo="A">
											<tr>
												<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><ezf:inputText name="cltStrgyCd_A" ezfName="cltStrgyCd_A" value="5001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="cltStrgyNm_A" ezfName="cltStrgyNm_A" value="Canon Call customer - Escalation for the Final Notice" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"100\" maxlength=\"100\""/></td>
											</tr>
											<ezf:skip>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="28"  maxlength="28"  name="cltStrgyCd_A" ezfname="cltStrgyCd_A" ezfhyo="A" ezftoupper value="5001"></td>
												<td><input type="text" size="100" maxlength="100" name="cltStrgyNm_A" ezfname="cltStrgyNm_A" ezfhyo="A" value="Canon Call customer - Escalation for the Final Notice"></td>
											</tr>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="15">
						<col width="100" align="left">
						<col width="10" align="">
						<col width="100" align="left">
						<col width="600" align="">
						<col width="90" align="right">
						<col width="10" align="">
						<col width="90" align="right">
						<col width="5" align="">
						<tr height="5"><td colspan="9"></td></tr>
						<tr>
							<td></td>
							<td >
								<ezf:inputButton name="MoveWin_WorkItem" value="Work Item" htmlClass="pBtn8" otherAttr=" id=\"btnWorkItem\""/>
							</td>
							<td>&nbsp;</td>
							<td >
								<ezf:inputButton name="MoveWin_CustomerType" value="Customer Type" htmlClass="pBtn8" otherAttr=" id=\"btnCustomerType\""/>
							</td>
							<td>&nbsp;</td>
							<td >
								<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" otherAttr=" id=\"btnInsertRow\""/>
							</td>
							<td>&nbsp;</td>
							<td>
								<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" otherAttr=" id=\"btnDeleteRow\""/>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
