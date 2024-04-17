<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202170100 --%>
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
			<input type="hidden" name="pageID" value="ZZVL0000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Set Preferred View Setting">

<center>
	<table width="80%">
		<tr>
			<td>
				<table>
					<col width="150">
					<col width="50">
					<col width="100">
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CMN_SelectCompanyCD" >Global Company Code:</ezf:anchor></td>
						<td><ezf:inputText name="glblCmpyCd_1" ezfName="glblCmpyCd_1" otherAttr=" size=\"10\" maxlength=\"6\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CMN_OpenSelectRole" >Role ID:</ezf:anchor></td>
						<td><ezf:inputText name="roleNm_1" ezfName="roleNm_1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn3" /></td>
					</tr>
				</table>
				<hr>
				<table>
					<col width="50px">
					<col width="700px">
					<col width="300px">
					<tr>
						<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn3" /></td>
						<td></td>
						<td>
						<%-- Pagenation --%>
							<table width="100%">
								<tr align="right">
									<td>
										<!-- <table border="0" cellpadding="1" cellspacing="0">
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
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">10</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
												<td></td>
												<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
											</tr>
										</table>
										-->
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
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
				<table border="1" cellpadding="1" cellspacing="0" width="968px">
				<col width="40px" align="center">
				<col width="72px" align="center">
				<col width="48px" align="center">
				<col width="144px" align="center">
				<col width="240px" align="center">
				<col width="80px" align="center">
				<col width="216px" align="center">
				<col width="80px" align="center">
				<tr>
					<td class="pClothBs">&nbsp;</td>
					<td class="pClothBs">Set Default</td>
					<td class="pClothBs">Default</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scrAppId_1' )">
						Business Application ID<img id="sortIMG.scrAppId_1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bizAppNm_1' )">
						Business Application Name<img id="sortIMG.bizAppNm_1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scrTblNm_1' )">
						TBL Name<img id="sortIMG.scrTblNm_1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scrTblColDefNm_1' )">
						View Setting Name<img id="sortIMG.scrTblColDefNm_1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'usrId_1' )">
						Org Owner<img id="sortIMG.usrId_1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
				</tr>
				</table>
				<div id="bottomTBL" style="word-break:break-all; overflow:auto; height:445; width:985">
					<table border="1" cellpadding="1" cellspacing="0" id="A" width="968px">
						<col width="40px" align="center">
						<col width="72px" align="center">
						<col width="48px" align="center">
						<col width="144px">
						<col width="240px">
						<col width="80px">
						<col width="216px">
						<col width="80px">
						<tbody>
						<ezf:row ezfHyo="A">
							<tr id="id_row{EZF_ROW_NUMBER}">
								<td><ezf:inputCheckBox name="xxChkBox_1" ezfName="xxChkBox_1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputButton name="Set_Default" value="Default" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
								<td><ezf:inputCheckBox name="xxChkBox_3" ezfName="xxChkBox_3" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:label name="scrAppId_1" ezfName="scrAppId_1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:label name="bizAppNm_1" ezfName="bizAppNm_1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:label name="scrTblNm_1" ezfName="scrTblNm_1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:label name="scrTblColDefNm_1" ezfName="scrTblColDefNm_1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:label name="usrId_1" ezfName="usrId_1" ezfHyo="A" ezfArrayNo="0" /></td>
							</tr>
						</ezf:row>
						<ezf:skip>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">Interface Maintenance:Contract/Billing</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">General Business Master Maintenance List:Contract/Billing</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_1" ezfname="xxChkBox_1"></td>
								<td><input type="button" class="pBtn6" value="Default" name="Set_Default" onclick="sendServer(this)"></td>
								<td><input type="checkbox" class="pPro" value="Y" name="xxChkBox_3" ezfname="xxChkBox_3" ezfhyo="A"></td>
								<td><label ezfout name="scrAppId_1" ezfname="scrAppId_1">XXXX0002</label></td>
								<td><label ezfout name="bizAppNm_1" ezfname="bizAppNm_1">PO Change Inquiry 2</label></td>
								<td><label ezfout name="scrTblNm_1" ezfname="scrTblNm_1">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_1" ezfname="scrTblColDefNm_1">ABCDEFGGGGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
								<td><label ezfout name="usrId_1" ezfname="usrId_1">Q99999</label></td>
							</tr>
						</ezf:skip>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
