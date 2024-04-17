<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160704132603 --%>
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
			<input type="hidden" name="pageID" value="NMAL2720Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mass Move Organizations">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mass Move Organizations" class="pTab_ON"><a href="#">MassMoveOrg</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">
					<%-- ##### BODY(TAB) ##### --%>
					<%-- ##### TAB(Main) ##### --%>
					<table style="table-layout:fixed;">
						<col width="110">
						<col width="240">
						<col width="100">
						<col width="240">
						<col width="90">
						<col width="240">
						<col width="90">
							<tr>
								<td class="stab">Business Area</td>
								<td>
									<ezf:select name="bizAreaOrgCd_H" ezfName="bizAreaOrgCd_H" ezfBlank="1" ezfCodeName="bizAreaOrgCd_P" ezfDispName="bizAreaOrgDescTxt_P" otherAttr=" style=\"width:140px\""/>
								</td>
								<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ResrcLookUpForName" >Resource Name(*)</ezf:anchor></td>
								<td><ezf:inputText name="xxPsnNm_H" ezfName="xxPsnNm_H" otherAttr=" size=\"30\" maxlength=\"62\""/></td>
								<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ResrcLookUpForNum" >Resource#(*)</ezf:anchor></td>
								<td><ezf:inputText name="psnNum_H" ezfName="psnNum_H" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
								<td align="center">
									<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
								</td>
							</tr>
							<tr>
								<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrgLookUp" >Organization Name(*)</ezf:anchor></td>
								<td><ezf:inputText name="orgNm_H" ezfName="orgNm_H" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
								<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ResrcLookUpForCd" >Employee ID(*)</ezf:anchor></td>
								<td><ezf:inputText name="psnCd_H" ezfName="psnCd_H" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
					</table>

					<br>

					<table border="0" cellpadding="0" cellspacing="0">
						<col align="left" valign="top">
						<tr>
							<td>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; width:1100;">
									<col align="center" width="30">
									<col align="center" width="160">
									<col align="center" width="160">
									<col align="center" width="100">
									<col align="center" width="80">
									<col align="center" width="130">
									<col align="center" width="130">
									<col align="center" width="130">
									<col align="center" width="90">
									<col align="center" width="90">
									<tr height="36">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AH" ezfName="xxChkBox_AH" value="Y" onClick="sendServer('OnChange_ChkBox_SelectUnSelectAll')" /></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgNm_A1')">Organization Name<img id="sortIMG.orgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgDescTxt_A1')">Organization Description<img id="sortIMG.orgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bizAreaOrgDescTxt_A1')">Business Area<img id="sortIMG.bizAreaOrgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'lineBizTpDescTxt_A1')">Line of<br>Business<img id="sortIMG.lineBizTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_A1')">Resource Name<img id="sortIMG.xxPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnNum_A1')">Resouce#<img id="sortIMG.psnNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgFuncRoleTpDescTxt_A1')">Role<img id="sortIMG.orgFuncRoleTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A1')">Effective<br>Date From<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A1')">Effective<br>Date To<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
								<div style="overflow-x:hidden; overflow-y:scroll; width:1119; height:362px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" >
										<col width="30" align="center">
										<col width="160">
										<col width="160">
										<col width="100">
										<col width="80">
										<col width="130">
										<col width="130">
										<col width="130">
										<col width="90" align="center">
										<col width="90" align="center">
										<ezf:row ezfHyo="A">
										<tr>
											<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="orgNm_A1" ezfName="orgNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="orgDescTxt_A1" ezfName="orgDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="bizAreaOrgDescTxt_A1" ezfName="bizAreaOrgDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="lineBizTpDescTxt_A1" ezfName="lineBizTpDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="xxPsnNm_A1" ezfName="xxPsnNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="psnNum_A1" ezfName="psnNum_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="orgFuncRoleTpDescTxt_A1" ezfName="orgFuncRoleTpDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3 XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table><br>
					<table style="table-layout:fixed;">
						<col width="110">
						<col width="450">
						<col width="120">
						<col width="430">
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ResrcLookUpDtl" >Move Organization To</ezf:anchor></td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="230">
									<col width="35">
									<col width="155">
									<tr>
										<td class="stab"><ezf:inputText name="xxPsnNm_D1" ezfName="xxPsnNm_D1" otherAttr=" size=\"30\" maxlength=\"62\""/></td>
										<td class="stab"><ezf:inputButton name="GetPsnNum" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetPsnNum\""/></td>
										<td class="stab"><ezf:inputText name="psnNum_D1" ezfName="psnNum_D1" value="12345678" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
									</tr>
								</table>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="stab">Move Effective From</td>
							<td>
								<ezf:inputText name="effFromDt_D1" ezfName="effFromDt_D1" value="99/99/9999" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D1', 4);" >
							</td>
							<td class="stab">Mass Update Reason<ezf:inputButton name="OpenWin_RqstHist" value="Request History" htmlClass="pBtn9" /></td>
							<td rowspan="2" valign="top">
								<ezf:textArea name="massUpdRsnCmntTxt_D1" ezfName="massUpdRsnCmntTxt_D1" otherAttr=" rows=\"5\" cols=\"58\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Move Effective To</td>
							<td>
								<ezf:inputText name="effThruDt_D1" ezfName="effThruDt_D1" value="99/99/9999" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D1', 4);" >
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
