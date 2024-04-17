<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170419140109 --%>
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
			<input type="hidden" name="pageID" value="NSAL0420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Representative Popup">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<!-- ######################################## Header ######################################## -->
				<table border="0" width="99%" align="center">
					<col width="100">
					<col width="120">
					<col width="5">
					<col width="100">
					<col width="120">
					<col width="5">
					<col width="100">
					<col width="120">
					<col width="5">
					<col width="">
					<tr>
						<td class="stab">Region (*)</td>
						<td><ezf:inputText name="svcRgNm_H" ezfName="svcRgNm_H" value="1---------2---------3---------" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">LOB (*)</td>
						<td><ezf:inputText name="lineBizTpDescTxt_H" ezfName="lineBizTpDescTxt_H" value="1---------2---------3---------4---------5---------" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab">Branch Code (*)</td>
						<td><ezf:inputText name="svcContrBrCd_H" ezfName="svcContrBrCd_H" value="123" otherAttr=" size=\"25\" maxlength=\"3\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Branch Name (*)</td>
						<td><ezf:inputText name="svcContrBrDescTxt_H" ezfName="svcContrBrDescTxt_H" value="1---------2---------3---------4---------5---------" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Resource (*)</td>
						<td><ezf:inputText name="xxGenlFldAreaTxt_H" ezfName="xxGenlFldAreaTxt_H" value="1---------2---------3---------4---------5---------" otherAttr=" size=\"25\" maxlength=\"92\""/></td>
						<td></td>
						<td><ezf:inputButton name="Search_Representative" value="Search" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				<hr>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
				<table width="99%">
					<tr align="right">
						<td>
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
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
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
				</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->

<!-- ######################################## DETAIL #################################### -->
				<table border="0" cellpadding="0" cellspacing="0" width="99%" align="left">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="width:963; text-align:center; overflow-x:hidden; overflow-y:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" height="23" align="left">
									<col width="130" align="center">
									<col width="80"  align="center">
									<col width="100"  align="center">
									<col width="180" align="center">
									<col width="200" align="center">
									<col width="180" align="center">
									<col width="70"  align="center">
									<tr>
										<td class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcRgNm_A')">Region</a><img id="sortIMG.svcRgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'lineBizTpDescTxt_A')">LOB</a><img id="sortIMG.lineBizTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrCd_A')">Branch Code</a><img id="sortIMG.svcContrBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrDescTxt_A')">Branch Name</a><img id="sortIMG.svcContrBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_A')">Resource</a><img id="sortIMG.xxGenlFldAreaTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgFuncRoleTpNm_A')">Role</a><img id="sortIMG.orgFuncRoleTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">Hierarchy</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:980; overflow-x:hidden; overflow-y:scroll; height:446;">
								<table id="A" border="1" cellpadding="1" cellspacing="0" align="left">
									<col width="130" align="center">
									<col width="80"  align="center">
									<col width="100"  align="center">
									<col width="180" align="center">
									<col width="200" align="center">
									<col width="180" align="center">
									<col width="70"  align="center">
									<ezf:row ezfHyo="A">
										<tr height="23">
											<td><ezf:inputText name="svcRgNm_A" ezfName="svcRgNm_A" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" id=\"svcRgNm_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="lineBizTpDescTxt_A" ezfName="lineBizTpDescTxt_A" value="1---------2---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" id=\"lineBizTpDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:anchor name="svcContrBrCd_AL" ezfName="svcContrBrCd_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="BranchLink" otherAttr=" id=\"svcContrBrCd_AL#{EZF_ROW_NUMBER}\""><ezf:label name="svcContrBrCd_A" ezfName="svcContrBrCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></ezf:anchor></td>
											<td><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\" id=\"svcContrBrDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="xxGenlFldAreaTxt_A" ezfName="xxGenlFldAreaTxt_A" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"92\" id=\"xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="orgFuncRoleTpNm_A" ezfName="orgFuncRoleTpNm_A" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" id=\"orgFuncRoleTpNm_A#{EZF_ROW_NUMBER}\""/></td>
											<td align="center"><ezf:inputButton name="OpenWin_BranchHierarchy" value="Hierarchy" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" otherAttr=" id=\"OpenWin_BranchHierarchy\""/>
										</tr>
									<ezf:skip>
										<tr height="23">
											<td><input type="text" class="pPro" size="14" maxlength="30" value="1---------2---------3---------" id="svcRgNm_A#{EZF_ROW_NUMBER}" name="svcRgNm_A" ezfname="svcRgNm_A" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="8" maxlength="50" value="1---------2---------" id="lineBizTpDescTxt_A#{EZF_ROW_NUMBER}" name="lineBizTpDescTxt_A" ezfname="lineBizTpDescTxt_A" ezfhyo="A"></td>
											<td><a href="#" onclick="sendServer('BranchLink')" id="svcContrBrCd_AL#{EZF_ROW_NUMBER}" name="svcContrBrCd_AL" ezfname="svcContrBrCd_AL" ezfhyo="A"><label ezfout size="9" name="svcContrBrCd_A" ezfname="svcContrBrCd_A" ezfhyo="A">123</label></a></td>
											<td><input type="text" class="pPro" size="24" maxlength="50" value="1---------2---------3---------4---------5---------" id="svcContrBrDescTxt_A#{EZF_ROW_NUMBER}" name="svcContrBrDescTxt_A" ezfname="svcContrBrDescTxt_A" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="24" maxlength="92" value="1---------2---------3---------4---------5---------" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="20" maxlength="50" value="1---------2---------3---------4---------5---------" id="orgFuncRoleTpNm_A#{EZF_ROW_NUMBER}" name="orgFuncRoleTpNm_A" ezfname="orgFuncRoleTpNm_A" ezfhyo="A"></td>
											<td align="center"><input type="button" class="pBtn4" value="Hierarchy" name="OpenWin_BranchHierarchy" onclick="sendServer('OpenWin_BranchHierarchy','{EZF_ROW_NUMBER}')" id="OpenWin_BranchHierarchy" ezfhyo="A">
										</tr>
									</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
